package com.jensui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

/**
 * Created by christian on 11/13/16.
 */
public class Logger {

    private final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Logger.class.getName());
    private FileHandler fh = null;

    public Logger() {
        SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
        try {
            fh = new FileHandler("raspiremote" + format.format(Calendar.getInstance().getTime()) + ".log");
        } catch (Exception e) {
            e.printStackTrace();
        }

        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
    }

}
