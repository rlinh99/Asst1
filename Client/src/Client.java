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
            String serverIP = "192.168.1.65";

            //change port parameter if the port is blocked
            int serverPort = 1075;

            //find working RMI registry
            Registry registry = LocateRegistry.getRegistry(serverIP, serverPort);
            System.out.println("Finding Registry");

            Clock clk = (Clock) registry.lookup(name);
            System.out.println("Successfully found registry");

            LocalDateTime startTime = LocalDateTime.now();
            LocalDateTime time = clk.getTime();
            LocalDateTime endTime = LocalDateTime.now();

            var duration = Duration.between(startTime, endTime);

            System.out.println(duration);
            System.out.println(time);

            LocalDateTime sysTime = time.plus(duration);

            String timeStr = formatHelper(time);

            setTime(timeStr);

        } catch (Exception e) {
            System.err.println(" Unhandled Exception:");
            e.printStackTrace();
        }
    }

    private static void setTime(String timeStr) throws IOException {

        Runtime rt = Runtime.getRuntime();
        rt.exec(new String[]{"date", "-s", timeStr});
    }

    private static String formatHelper(LocalDateTime time) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("DD MMM YYYY HH:mm:ss.SSSS");
        String timeStr = time.format(fmt);

        System.out.println(timeStr);
        return timeStr;
    }
}
