package com.jensui;

import com.jensui.interfaces.IDevice;
import org.codehaus.jettison.json.JSONObject;

import javax.websocket.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by christian on 11/9/16.
 */
@ClientEndpoint
public class WebSocketClient_ {

    private static volatile WebSocketClient_ instance;

    public static WebSocketClient_ getInstance() {
        synchronized (WebSocketClient_.class) {
            if(instance == null) {
                instance = new WebSocketClient_();
            }
            return instance;
        }
    }

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private volatile Session session = null;
    String wsendpoint = "ws://rrs-pressler.rhcloud.com:8000/rrs";
    //String wsendpoint = "ws://127.0.0.1:8080/rrs-1.0/rrs";
    private MessageHandler messageHandler;
    WebSocketContainer container;
    Thread watchdog;

    public Session getSession() {
        synchronized (this) {
            return session;
        }
    }

    private WebSocketClient_() {
        connect();
        watchdog = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    while (true) {
                        //Logger.getLogger(this.getClass().getName()).log(Level.INFO, "websocket watchdog checking session...");
                        if (session == null || !session.isOpen()) {
                            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "websocket client watchdog trying to reconnect");
                            connect();
                        }
                        try {
                            Thread.sleep(20000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        watchdog.start();
    }

    void close() throws Exception {
        watchdog.stop();
    }

    void connect() {
        synchronized (this) {
            if (session != null) {
                try {
                    session.close();
                } catch (IOException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "existing websocket session: " + session.getId() + " could not be closed");
                }
            }
        }
        try {
            container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(wsendpoint));
        } catch (Exception e) {
            logger.log(Level.WARNING, "could not connect to: " + wsendpoint, e);
        }
    }

    @OnOpen
    public void onOpen(Session userSession) {
        synchronized (this) {
            this.session = userSession;
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "websocket connected session: " + session.getId());
        }

    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        synchronized (this) {
            this.session = null;
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "websocket disconnected for session: " + userSession.getId() + ". Reason: " + reason.toString());
            //Logger.getLogger(this.getClass().getName()).log(Level.INFO, "websocket disconnected for session: " + userSession.getId() + ". Reason: " + reason.toString() + ". - trying to reconnect...");
            //connect();
        }
    }

    @OnMessage
    public void onMessage(String cmd) {
        try {
            JSONObject jsonObj = new JSONObject(cmd);
            String id = jsonObj.getString("deviceId");
            String m = jsonObj.getString("command");
            for (IDevice d : DeviceHandler.getInstance().getDevices()) {
                if (d.getId().equals(id)) {
                    Class cls = Class.forName("com.jensui.interfaces.IDevice");
                    //Class cls = d.getClass();
                    Class noparams[] = {};
                    Method method = cls.getDeclaredMethod(m, noparams);
                    method.invoke(d, null);
                    Logger.getLogger(this.getClass().getName()).log(Level.INFO, "command: " + m + " received and called on for device: " + d.getId());
                    return;
                }
            }
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "invalid command: " + m);
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage());
            //e.printStackTrace();
        }
    }

    //public void sendMessage(String message) {
    // this.session.getAsyncRemote().sendText(message);
    //}

}
