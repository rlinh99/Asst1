/*
    CMPT431- Assignment1
    Author: Sen Lin-301250505
            Louis Jia Bao Zhuo-301235952

    *Please run Client file with sudo permission*
*/

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class Client {
    public static void main(String args[]) {
        try {
            //set the object name of the designated object in the server registry.
            String name = "ServerClock";
            //server ip address
            String serverIP = "localhost";

            //change port parameter if the port is blocked
            int serverPort = 1075;

            //find working RMI registry
            Registry registry = LocateRegistry.getRegistry(serverIP, serverPort);
            System.out.println("Finding Registry");

            //lookup for registry
            Clock clk = (Clock) registry.lookup(name);
            System.out.println("Successfully found registry");

            //get timezone info from server
            String zoneId = clk.getZoneId();

            //start timer for RTT
            LocalDateTime startTime = LocalDateTime.now();
            //retrive time from server
            LocalDateTime timeObj = clk.getTime();
            //end timer for RTT
            LocalDateTime endTime = LocalDateTime.now();

            //calculate dsc
            Duration offset = Duration.between(startTime, endTime).dividedBy(2);

            LocalDateTime sysTime = timeObj.plus(offset);

            //formatting linux command line format
            String timeStr = formatHelper(sysTime);

            //set timezone and time
            setTimeZone(zoneId);
            setTime(timeStr);

            //print out the result for debugging
            System.out.println("Client system time is synchronized");
            System.out.println("Time retrieved from server is: " + timeObj.toString() + " in " + zoneId);
            System.out.println("RTT is: " + offset.multipliedBy(2).toString());
            System.out.println("Offset is: " + offset.toString());
            System.out.println("System time is set to: " + sysTime.toString() + "  " + zoneId);
        } catch (Exception e) {
            System.err.println(" Unhandled Exception:");
            e.printStackTrace();
        }
    }

    private static void setTimeZone(String zoneId) throws IOException {
        //mock linux command line operation - timedatectl -for timezone setting
        Runtime rt = Runtime.getRuntime();
        rt.exec(new String[]{"timedatectl", "set-timezone", zoneId});
    }

    private static void setTime(String timeStr) throws IOException {
        //mock linux command line operation - date - for time setting
        Runtime rt = Runtime.getRuntime();
        rt.exec(new String[]{"date", "-s", timeStr});
    }

    private static String formatHelper(LocalDateTime time) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("DD MMM YYYY HH:mm:ss.SSSSSS");

        return time.format(fmt);
    }
}
