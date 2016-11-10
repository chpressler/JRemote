package com.jensui.devices;

import com.jensui.interfaces.IDevice;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Map;

/**
 * Created by christian on 11/8/16.
 */
public abstract class AIPDevice implements IDevice {

    abstract String getIP();
    abstract String getMAC();

    @Override
    public void turnOn() {
        wake(getIP(), getMAC());
        System.out.println(getId() + " turned on");
    }

    void sendHTTPPost(String ip, String payload) throws Exception {
       sendHTTPPost(null, ip, payload);
    }

    void sendHTTPPost(Map<String, String> header, String ip, String payload) throws Exception {
        OutputStream connOut = null;
        InputStream connIn = null;
        try {
            URL url = new URL(ip);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if(header != null) {
                header.forEach((k, v) -> connection.setRequestProperty(k, v));
            }
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml");
            connection.connect();
            connOut = connection.getOutputStream();
            connOut.write(payload.getBytes());

            connIn = connection.getInputStream();
            int rc = connection.getResponseCode();
            String rspMsg = getResponse(connIn);
            System.out.println(rspMsg);

        } finally {
            if(connOut != null) {
                connOut.close();
            }
            if(connIn != null) {
                connIn.close();
            }
        }
    }

    void sendHTTPGet(Map<String, String> header, String url) throws Exception {
        OutputStream connOut = null;
        InputStream connIn = null;
        try {
            URL _url = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
            if(header != null) {
                header.forEach((k, v) -> connection.setRequestProperty(k, v));
            }
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "text/xml");
            connection.connect();
//            connOut = connection.getOutputStream();
//            connOut.write(payload.getBytes());

            connIn = connection.getInputStream();
            int rc = connection.getResponseCode();
            String rspMsg = getResponse(connIn);
            System.out.println("Status: " + connection.getResponseCode() + " returned. " + rspMsg);

        } finally {
            if(connOut != null) {
                connOut.close();
            }
            if(connIn != null) {
                connIn.close();
            }
        }
    }

    String getResponse(InputStream in) {
        try {
            StringBuilder sb = new StringBuilder();
            int i;
            InputStream bin = new BufferedInputStream(in);
            while ((i = bin.read()) > -1) {
                sb.append((char) i);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR retrieving response";
        }
    }

    static final int PORT = 9;

    void wake(String ip, String mac) {
        try {
            byte[] macBytes = getMacBytes(mac);
            byte[] bytes = new byte[6 + 16 * macBytes.length];
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) 0xff;
            }
            for (int i = 6; i < bytes.length; i += macBytes.length) {
                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
            }
            InetAddress address = InetAddress.getByName(ip);
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, PORT);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();
            System.out.println("Wake-on-LAN packet sent.");
        }
        catch (Exception e) {
            System.out.println("Failed to send Wake-on-LAN packet: + e");
            System.exit(1);
        }
    }

    byte[] getMacBytes(String macStr) throws IllegalArgumentException {
        byte[] bytes = new byte[6];
        String[] hex = macStr.split("(\\:|\\-)");
        if (hex.length != 6) {
            throw new IllegalArgumentException("Invalid MAC address.");
        }
        try {
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid hex digit in MAC address.");
        }
        return bytes;
    }

}
