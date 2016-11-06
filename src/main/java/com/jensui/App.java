package com.jensui;

public class App {

    public static void main(String[] args) throws Exception {

        System.out.println(" --- Hello Pi --- ");

        /*System.out.println("<--Pi4J--> GPIO Listen Example ... started.");

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #02 as an input pin with its internal pull down resistor enabled
        final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);

        // set shutdown state for this input pin
        myButton.setShutdownOptions(true);

        // create and register gpio pin listener
        myButton.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
            }

        });

        System.out.println(" ... complete the GPIO #02 circuit and see the listener feedback here in the console.");*/

        // keep program running until user aborts (CTRL-C)
//        while(true) {
//            Thread.sleep(500);
//        }

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller

       /* 1. // get a handle to the GPIO controller
        2. final GpioController gpio = GpioFactory.getInstance();
        3. // creating the pin with parameter PinState.HIGH
        4. // will instantly power up the pin
        5. final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "PinLED", PinState.HIGH);
        6. System.out.println("light is: ON");
        7. // wait 2 seconds
        8. Thread.sleep(2000);
        9. // turn off GPIO 1
        10. pin.low();
        11. System.out.println("light is: OFF");
        12. // wait 1 second
        13. Thread.sleep(1000);
        14. // turn on GPIO 1 for 1 second and then off
        15. System.out.println("light is: ON for 1 second");
        16. pin.pulse(1000, true);
        17. // release the GPIO controller resources
        18. gpio.shutdown();*/

    }

}
