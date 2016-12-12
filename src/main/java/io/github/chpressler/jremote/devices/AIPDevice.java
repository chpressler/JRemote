package io.github.chpressler.jremote.devices;

import io.github.chpressler.jremote.interfaces.IDevice;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by christian on 11/8/16.
 */
public abstract class AIPDevice implements IDevice {

    abstract String getIP();
    abstract String getMAC();

    void sendHTTPPost(String ip, String payload) {
       sendHTTPPost(null, ip, payload);
    }

    void sendHTTPPost(Map<String, String> header, String ip, String payload) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "HTTP POST Thread started for IP: " + ip);
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
                    Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Status: " + rc + " and Response: " + rspMsg + "returned from: " + url);
                } catch(Exception e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "HTTP POST could not be sent to URL: " + ip + " Reason: " + e.getMessage());
                } finally {
                    if(connOut != null) {
                        try {
                            connOut.close();
                        } catch (IOException e) {
                            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
                        }
                    }
                    if(connIn != null) {
                        try {
                            connIn.close();
                        } catch (IOException e) {
                            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
                        }
                    }
                    Logger.getLogger(this.getClass().getName()).log(Level.INFO, "HTTP POST Thread ended for IP: " + ip);
                }
            }
        }).start();
    }

    void sendHTTPGet(String url) {
       sendHTTPGet(null, url);
    }

    void sendHTTPGet(Map<String, String> header, String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "HTTP GET Thread started for URL: " + url);
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
                    connIn = connection.getInputStream();
                    int rc = connection.getResponseCode();
                    String rspMsg = getResponse(connIn);
                    Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Status: " + rc + " and Response: " + rspMsg + "returned from: " + url);
                } catch(Exception e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "HTTP GET could not be sent to URL: " + url + " Reason: " + e.getMessage());
                } finally {
                    if(connOut != null) {
                        try {
                            connOut.close();
                        } catch (IOException e) {
                            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
                        }
                    }
                    if(connIn != null) {
                        try {
                            connIn.close();
                        } catch (IOException e) {
                            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
                        }
                    }
                    Logger.getLogger(this.getClass().getName()).log(Level.INFO, "HTTP GET Thread ended for URL: " + url);
                }
            }
        }).start();
    }

    String getResponse(InputStream in) {
        InputStream bin = null;
        try {
            StringBuilder sb = new StringBuilder();
            int i;
            bin = new BufferedInputStream(in);
            while ((i = bin.read()) > -1) {
                sb.append((char) i);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR retrieving response";
        } finally {
            if(bin != null) {
                try {
                    bin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static final int PORT = 9;

    void wake(String ip, String mac) {
        DatagramSocket socket = null;
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
            socket = new DatagramSocket();
            socket.send(packet);
            System.out.println("Wake-on-LAN packet sent.");
        }
        catch (Exception e) {
            System.out.println("Failed to send Wake-on-LAN packet: + e");
            System.exit(1);
        } finally {
            socket.close();
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
