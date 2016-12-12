package io.github.chpressler.jremote.interfaces;

/**
 * Created by christian on 11/8/16.
 */
public interface IDevice {

    //enum CURSOR {UP, DOWN, RIGHT, LEFT}

    String getId();
    void turnOn();
    void turnOff();
    void volumeUp();
    void volumeDown();
    //void setVolume(int value);
    void mute();
    void channelUp();
    void channelDown();
    //void setChannel(int channel);
    void next();
    void previous();
    void play();
    void stop();
    void pause();
    void menu();
    void enter();
    void back();
    //void moveCursor(CURSOR c);

}
