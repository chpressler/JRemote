package io.github.chpressler.jremote.devices;

import io.github.chpressler.jremote.interfaces.IDevice;
import com.raspoid.PWMPin;
import com.raspoid.Tools;
import com.raspoid.additionalcomponents.ir.IRSignal;
import com.raspoid.additionalcomponents.ir.IRTransmitter;

/**
 * Created by christian on 11/8/16.
 */
public abstract class AIRDevice implements IDevice {

    void sendSignal(IRSignal signal) {
        IRTransmitter transmitter = new IRTransmitter(PWMPin.PWM1);
        transmitter.transmitSignal(signal);
        Tools.log("Device: " + this.getClass().getName() + " - Signal: " + signal.toString() + " IR signal sent");
    }

}
