package com.jensui;

import com.jensui.interfaces.IDevice;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by christian on 11/8/16.
 */
public class DeviceHandler {

    private static DeviceHandler instance = null;
    private List<IDevice> devices;

    public List<IDevice> getDevices() {
        return devices;
    }

    public static DeviceHandler getInstance() {
        if(instance == null) {
            synchronized(DeviceHandler.class) {
                instance = new DeviceHandler();
            }
        }
        return instance;
    }

    private DeviceHandler() {
        devices = new ArrayList<IDevice>();
        try {
            registerDevices();
        } catch (Exception e) {
            System.out.println("register devices failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void registerDevices() throws Exception {
        for(File f : getPackageContent("com.jensui.devices")) {
            Object device;
            Class clazz = Class.forName("com.jensui.devices."+f.getName().replace(".class", ""));
            if(!Modifier.isAbstract(clazz.getModifiers())) {
                device = clazz.newInstance();
                if (device instanceof IDevice) {
                    devices.add((IDevice) device);
                }
            }
        }
    }

    private File[] getPackageContent(String packageName) throws Exception {
        ArrayList<File> list = new ArrayList<>();
        String relPath = "com.jensui.devices".replace('.', '/');
        URL url = ClassLoader.getSystemClassLoader().getResource(relPath);
        if (url == null) {
            throw new Exception("No URL found for path: " + relPath);
        }
        File dir = new File(url.getFile());
        for (File f : dir.listFiles()) {
            list.add(f);
        }
        return list.toArray(new File[]{});
    }

}
