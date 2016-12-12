package io.github.chpressler.jremote.devices;

import io.github.chpressler.jremote.irprotocols.IRProtocolVizioSoundbarRemote;
import io.github.chpressler.jremote.irprotocols.IRProtocolVizioTVRemote;

/**
 * Created by christian on 11/8/16.
 */
public class VizioSoundbar extends AIRDevice {

    @Override
    public String getId() {
        return "viziosoundbar";
    }

    @Override
    public void turnOn() {
        sendSignal(IRProtocolVizioSoundbarRemote.button_on_off);
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
        sendSignal(IRProtocolVizioSoundbarRemote.button_volumeDown);
    }

    @Override
    public void mute() {
        sendSignal(IRProtocolVizioSoundbarRemote.button_mute);
    }

    @Override
    public void channelUp() {

    }

    @Override
    public void channelDown() {

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
