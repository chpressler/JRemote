package com.jensui;

import com.jensui.interfaces.IDevice;
import org.codehaus.jettison.json.JSONObject;

import javax.websocket.*;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by christian on 11/9/16.
 */
@ClientEndpoint
public class WebSocketClient {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    Session session = null;
    String wsendpoint = "ws://rrs-pressler.rhcloud.com:8000/rrs";
    //String wsendpoint = "ws://127.0.0.1:8080/rrs-1.0/rrs";
    private MessageHandler messageHandler;
    WebSocketContainer container;

    public WebSocketClient() {
        connect();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    if (session == null || !session.isOpen()) {
                        connect();
                    }
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void connect() {
        try {
            container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(wsendpoint));
        } catch(Exception e) {
            logger.log(Level.WARNING, "could not connect to: " + wsendpoint, e);
        }
    }

    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("websocket connected");
        this.session = userSession;
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        System.out.println("websocket disconnected " + userSession.getId() + " - " + reason.toString());
        this.session = null;
        connect();
    }

    @OnMessage
    public void onMessage(String cmd) {
        try {
            JSONObject jsonObj = new JSONObject(cmd);
            String id = jsonObj.getString("deviceId");
            String m = jsonObj.getString("command");;
            for (IDevice d : DeviceHandler.getInstance().getDevices()) {
                if(d.getId().equals(id)) {
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

    public void sendMessage(String message) {
        this.session.getAsyncRemote().sendText(message);
    }

}
