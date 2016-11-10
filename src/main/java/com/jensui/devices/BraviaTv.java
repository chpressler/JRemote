package com.jensui.devices;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by christian on 11/8/16.
 */
public class BraviaTv extends AIPDevice {

    private Map<IRCCMD, String> commands;
    enum IRCCMD {OFF, CHUP, CHDWN, VOLUP, VOLDWN, MUTE, CURUP, CURLFT, CURRGHT, CURDWN, NEXT, BACK, MENU}

    public BraviaTv () {
        commands = new HashMap<>();
        commands.put(IRCCMD.CHUP, "AAAAAQAAAAEAAAAQAw==");
        commands.put(IRCCMD.CHDWN, "AAAAAQAAAAEAAAARAw==");
        commands.put(IRCCMD.VOLUP, "AAAAAQAAAAEAAAASAw==");
        commands.put(IRCCMD.VOLDWN, "AAAAAQAAAAEAAAATAw==");
        commands.put(IRCCMD.MUTE, "AAAAAQAAAAEAAAAUAw==");
        commands.put(IRCCMD.OFF, "AAAAAQAAAAEAAAAvAw==");
    }

    @Override
    public String getId() {
        return "bravia";
    }

    private void sendIRCCode(String ircCode) {
        try {
            String message = "<?xml version=\"1.0\" encoding=\"utf-8\"?><s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" s:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><s:Body><u:X_SendIRCC xmlns:u=\"urn:schemas-sony-com:service:IRCC:1\"><IRCCCode>" + ircCode + "</IRCCCode></u:X_SendIRCC></s:Body></s:Envelope>";
            sendHTTPPost("http://" + getIP() + "/sony/IRCC", message);
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "IRCCode: " + ircCode + " sent to device: " + getId());
        } catch(Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "IRCCode: " + ircCode + " could not be sent to device: " + getId(), e);
        }
    }

    @Override
    void sendHTTPPost(String ip, String payload) throws Exception {
        Map<String, String> header = new HashMap<>();
        header.put("X-Auth-PSK", "0000");
        super.sendHTTPPost(header, ip, payload);
    }

    @Override
    public void turnOff() {
        sendIRCCode(commands.get(IRCCMD.OFF));
    }

    @Override
    public void volumeUp() {
        sendIRCCode(commands.get(IRCCMD.VOLUP));
    }

    @Override
    public void volumeDown() {
        sendIRCCode(commands.get(IRCCMD.VOLDWN));
    }

    @Override
    public void setVolume(int value) {
        Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "command +setVolume(int value) not supported");
        System.out.printf("command not supported");
    }

    @Override
    public void mute() {
       sendIRCCode(commands.get(IRCCMD.MUTE));
    }

    @Override
    public void channelUp() {
       sendIRCCode(commands.get(IRCCMD.CHUP));
    }

    @Override
    public void channelDown() {
        sendIRCCode(commands.get(IRCCMD.CHDWN));
    }

    @Override
    public void setChannel(int channel) {

    }

    @Override
    public void next() {

    }

    @Override
    public void previous() {

    }

    @Override
    public void play() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void menu() {

    }

    @Override
    public void enter() {

    }

    @Override
    public void back() {

    }

    @Override
    public void moveCursor(CURSOR c) {

    }

    @Override
    String getIP() {
        return "192.168.1.8";
    }

    @Override
    String getMAC() {
        return "9C:AD:97:06:6A:C1";
    }

}
