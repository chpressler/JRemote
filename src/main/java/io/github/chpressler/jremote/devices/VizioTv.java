package io.github.chpressler.jremote.devices;

import io.github.chpressler.jremote.irprotocols.IRProtocolVizioTVRemote;

/**
 * Created by christian on 11/8/16.
 */
public class VizioTv extends AIRDevice {


    @Override
    public String getId() {
        return "viziotv";
    }

    @Override
    public void turnOn() {
        sendSignal(IRProtocolVizioTVRemote.button_on_off);
    }

    @Override
    public void turnOff() {
        sendSignal(IRProtocolVizioTVRemote.button_on_off);
    }

    @Override
    public void volumeUp() {
        sendSignal(IRProtocolVizioTVRemote.button_volumeUp);
    }

    @Override
    public void volumeDown() {
        sendSignal(IRProtocolVizioTVRemote.button_volumeDown);
    }

    @Override
    public void mute() {

    }

    @Override
    public void channelUp() {
        sendSignal(IRProtocolVizioTVRemote.button_channelUp);
    }

    @Override
    public void channelDown() {
        sendSignal(IRProtocolVizioTVRemote.button_channelDown);
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
}
