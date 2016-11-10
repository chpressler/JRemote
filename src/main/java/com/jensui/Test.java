package com.jensui;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by christian on 11/9/16.
 */
public class Test {

    public static void main(String[] args) throws IOException {

        InetAddress localhost = InetAddress.getLocalHost();
        // this code assumes IPv4 is used
        byte[] ip = localhost.getAddress();

        for (int i = 1; i <= 254; i++)
        {
            ip[3] = (byte)i;
            InetAddress address = InetAddress.getByAddress(ip);
            if (address.isReachable(1000))
            {
                System.out.println(address + " machine is turned on and can be pinged");
                System.out.println(address.getHostAddress() + " - " + address.getCanonicalHostName() + " - " + address.getHostName());
                InetAddress address1 = InetAddress.getByName(address.getHostName());
                System.out.println("hostName: " + address1.getHostName());

//                NetworkInterface network = NetworkInterface.getByInetAddress(address);
//                byte[] mac = network.getHardwareAddress();

                System.out.println("MAC: " + getMacAdressByUseArp(address.getHostAddress()));
            }
            else if (!address.getHostAddress().equals(address.getHostName()))
            {
                System.out.println(address + " machine is known in a DNS lookup");
            }
            else
            {
                System.out.println(address + " the host address and host name are equal, meaning the host name could not be resolved");
            }
        }

    }

    private static String getMacAdressByUseArp(String ip) throws IOException {
        String cmd = "arp -n " + ip;
        Scanner s = new Scanner(Runtime.getRuntime().exec(cmd).getInputStream());
        String str = null;
        Pattern pattern = Pattern.compile("(([0-9A-Fa-f]{2}[-:]){5}[0-9A-Fa-f]{2})|(([0-9A-Fa-f]{4}\\.){2}[0-9A-Fa-f]{4})");
        try {
            while (s.hasNext()) {
                str = s.next();
                Matcher matcher = pattern.matcher(str);
                if (matcher.matches()){
                    break;
                }
                else{
                    str = null;
                }
            }
        }
        finally {
            s.close();
        }
        return (str != null) ? str.toUpperCase(): null;
    }

}
