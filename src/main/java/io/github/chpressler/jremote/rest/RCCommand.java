package io.github.chpressler.jremote.rest;

/**
 * Created by christian on 11/11/16.
 */
public class RCCommand {

    String deviceId;
    String command;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "{ deviceId=" + deviceId + ", command=" + command + "}";
    }

}
