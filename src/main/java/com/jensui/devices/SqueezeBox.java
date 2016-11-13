package com.jensui.devices;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by christian on 11/9/16.
 */
public class SqueezeBox extends AIPDevice {

    String turnOn = "http://" + getIP() + ":9002/status.suffix?p0=play&player=" + getMAC();
    String turnOff = "http://" + getIP() + ":9002/status.suffix?p0=stop&player=" + getMAC();

    @Override
    public void turnOn() {
        sendHTTPGet(turnOn);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, getId() + " turned on");
    }

    @Override
    public String getId() {
        return "squeezebox";
    }

    @Override
    public void turnOff() {
        sendHTTPGet(turnOff);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, getId() + " turned off");
    }

    @Override
    public void volumeUp() {

    }

    @Override
    public void volumeDown() {

    }

//    @Override
//    public void setVolume(int value) {
//
//    }

    @Override
    public void mute() {

    }

    @Override
    public void channelUp() {

    }

    @Override
    public void channelDown() {

    }

//    @Override
//    public void setChannel(int channel) {
//
//    }

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

//    @Override
//    public void moveCursor(CURSOR c) {
//
//    }

    @Override
    String getIP() {
        return "192.168.1.11";
    }

    @Override
    String getMAC() {
        return "00:04:20:2A:25:CC";
    }
}
