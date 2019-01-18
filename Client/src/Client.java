import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Client {
    public static void main(String args[]) {
        try {
            //set the object name of the designated object in the server registry.
            String name = "ServerClock";
            //server ip address
            String serverIP = "192.168.1.65";

            //change port parameter if the port is blocked
            int serverPort = 1088;

            //find working RMI registry
            Registry registry = LocateRegistry.getRegistry(serverIP, serverPort);
            System.out.println("Finding Registry");

            Clock clk = (Clock)registry.lookup(name);
            System.out.println("Successfully found registry");

            LocalDateTime startTime = LocalDateTime.now();
            LocalDateTime time = clk.getTime();
            LocalDateTime endTime = LocalDateTime.now();

            System.out.println(startTime);
            System.out.println(endTime);
            System.out.println(time);

        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }

    private void setTime(){

    }
}
