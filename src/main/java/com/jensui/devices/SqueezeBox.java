package com.jensui.devices;

/**
 * Created by christian on 11/9/16.
 */
public class SqueezeBox extends AIPDevice {

    String turnOn = "http://" + getIP() + ":9002/status.suffix?p0=play&player=00:04:20:2A:25:CC";
    String turnOff = "http://" + getIP() + ":9002/status.suffix?p0=stop&player=00:04:20:2A:25:CC";

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void turnOff() {

    }

    @Override
    public void volumeUp() {

    }

    @Override
    public void volumeDown() {

    }

    @Override
    public void setVolume(int value) {

    }

    @Override
    public void mute() {

    }

    @Override
    public void channelUp() {

    }

    @Override
    public void channelDown() {

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
        return null;
    }

    @Override
    String getMAC() {
        return null;
    }
}
