package com.jensui;

import com.jensui.interfaces.IDevice;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by christian on 11/6/16.
 */
public class RaspiRemote {

    public static void main(String[] args) {

        //Server server = null;
        WebSocketClient client = null;

        try {
//            server = new Server();
//            server.start();

            client = new WebSocketClient();
            //client.addMessageHandler(message -> triggerDevice(message));

            System.out.println(RaspiRemote.class.getSimpleName() + " Version: " + getVersion() + " started. \nTo quit type in exit or quit or call rest service.");
            Scanner s = new Scanner(System.in);

            String nextLine;
            while (!(nextLine = s.next()).equals("exit") && !nextLine.equals("quit")) {
                if(nextLine.equals("devices")) {
                    System.out.println("registered devices:");
                    for(IDevice d : DeviceHandler.getInstance().getDevices()) {
                        System.out.println("Device id: " + d.getId() + " - class: " + d.getClass().getName());
                    }
                } else {
                    System.out.println("invalid command");
                }
            }
        } catch(Exception e) {
            System.out.println(RaspiRemote.class.getSimpleName() + " Version: " + getVersion() + " could not be started. Reason: " + e.getMessage());
            //e.printStackTrace();
        } finally {
//            try {
//                server.shutdown();
//            } catch(Exception e1) {
//                e1.printStackTrace();
//            }
            if(client != null && client.session != null) {
                try {
                    client.session.close();
                } catch (IOException e) {
                    Logger.getLogger(RaspiRemote.class.getName()).log(Level.SEVERE, "WebSocket client could not be closed. Reason: " + e.getMessage());
                }
            }
        }

    }

    public static String getVersion() {
        return "1.0"; //TODO - read from pom.xml
    }

//    private static void triggerDevice(String message) {
//        System.out.println("trigger received: " + message);
////        //parse Message
////        //String id = ...
////        for(IDevice d : DeviceHandler.getInstance().getDevices()) {
////            if(d.getId().equals(id)) {
////                d.
////            }
////        }
//    }

}
