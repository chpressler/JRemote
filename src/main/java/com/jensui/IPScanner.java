package com.jensui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by christian on 11/6/16.
 */
public class IPScanner {

    public static void main(String[] args) throws Exception {
        //checkHosts("192.168.1");

        scan1();

        List<String> l = scan2();
    }

    public static void checkHosts(String subnet) throws  Exception {
        int timeout=1000;
        for (int i=1;i<255;i++){
            String host=subnet + "." + i;
            if (InetAddress.getByName(host).isReachable(timeout)){
                System.out.println(host + " is reachable");
            }
        }
    }

    public static void scan1() {
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            while(nis.hasMoreElements())
            {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration ias = ni.getInetAddresses();
                while (ias.hasMoreElements())
                {
                    InetAddress ia = (InetAddress) ias.nextElement();
                    System.out.println(ia.getHostAddress());
                }

            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<String> scan2() throws Exception {
        ArrayList<String> addresses = new ArrayList<String>();
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec("nmap -sn -oG ip.txt 13.150.23.1-255");
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(pr.getInputStream()));
            String line = null;
            while((line=input.readLine()) != null) {
                System.out.println(line);
                if (line.contains("Nmap scan report for")){
                    String[] elements = line.split(" ");
                    int end = elements.length-1;
                    String ip_address = elements[end];
                    String line2 = input.readLine();
                    if (line2.contains("Host is up")){
                        addresses.add(ip_address);
                    }
                }
            }

            int exitVal = pr.waitFor();
            System.out.println("Exited with error code "+exitVal);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return addresses;
    }

}
