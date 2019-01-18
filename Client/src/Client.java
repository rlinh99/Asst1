import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;

public class Client {
    public static void main(String args[]) {
        try {
            String name = "ServerClock";
            String serverIP = "192.168.1.65";
            int serverPort = 1088;
            Registry registry = LocateRegistry.getRegistry(serverIP, serverPort);
            System.out.println("Finding Registry");

            Clock clk = (Clock)registry.lookup(name);
            System.out.println("Successful");
            LocalTime startTime = LocalTime.now();
            LocalTime time = clk.getTime();
            LocalTime endTime = LocalTime.now();

            System.out.println(startTime);
            System.out.println(endTime);
            System.out.println(time);

        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }
}
