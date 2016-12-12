package io.github.chpressler.jremote;

import io.github.chpressler.jremote.devices.BraviaTv;
import io.github.chpressler.jremote.devices.SqueezeBox;
import io.github.chpressler.jremote.devices.VizioSoundbar;
import io.github.chpressler.jremote.devices.VizioTv;
import io.github.chpressler.jremote.interfaces.IDevice;

import java.io.File;
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
        devices.add(new BraviaTv());
        devices.add(new SqueezeBox());
        devices.add(new VizioTv());
        devices.add(new VizioSoundbar());
//        URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
//        String jarPath = URLDecoder.decode(url.getFile(), "UTF-8");
//        //String relPath = "com.jensui.devices".replace('.', '/');
//        //URL url = ClassLoader.getSystemClassLoader().getResource("");
//        JarFile jarFile = new JarFile(url.getFile());
//        Enumeration<JarEntry> e = jarFile.entries();
//        //URL[] urls = { new URL("jar:file:" + url+"!/com/jensui/devices") };
//        ClassLoader cl = URLClassLoader.getSystemClassLoader();//newInstance(urls);
//        while (e.hasMoreElements()) {
//            JarEntry je = e.nextElement();
//            System.out.println(" --- " + je.toString() + " - " + je.getClass().getName() + " - " + je.getName());
//            if(je.isDirectory() || !je.getName().endsWith(".class")){
//                continue;
//            }
//            String className = je.getName().replace(".class", "");
//            className = className.replace('/', '.');
//            Class c = cl.loadClass(className);
//            System.out.println(" ---------------------------- " + c.getName());
//            Object device;
//            if(!Modifier.isAbstract(c.getModifiers())) {
//                device = c.newInstance();
//                if (device instanceof IDevice) {
//                    devices.add((IDevice) device);
//                }
//            }
//        }
//        for(File f : getPackageContent("com.jensui.devices")) {
//            Object device;
//            Class clazz = Class.forName("com.jensui.devices."+f.getName().replace(".class", ""));
//            if(!Modifier.isAbstract(clazz.getModifiers())) {
//                device = clazz.newInstance();
//                if (device instanceof IDevice) {
//                    devices.add((IDevice) device);
//                }
//            }
//        }
    }

    private File[] getPackageContent(String packageName) throws Exception {
        ArrayList<File> list = new ArrayList<>();
        String relPath = "com.jensui.devices".replace('.', '/');
        URL url = ClassLoader.getSystemClassLoader().getResource(relPath);
        if (url == null) {
            throw new Exception("No URL found for path: " + relPath);
        }
        File dir = new File(url.getFile());
        System.out.println(" -------- " + url.toString() + " - Dir: " + dir);
        for (File f : dir.listFiles()) {
            list.add(f);
        }
        return list.toArray(new File[]{});
    }

}
