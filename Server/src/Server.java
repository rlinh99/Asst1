import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args) throws  Exception {
        //set the name of the object going to be binded to the registry.
        try {
            String name = "ServerClock";

            Clock clk = new ClockImpl();
            Clock stub = (Clock) UnicastRemoteObject.exportObject(clk, 0);

            Registry reg = LocateRegistry.getRegistry(1088);
            reg.rebind(name, stub);

            System.out.println("ServerClock object is Registered");
        } catch (RemoteException e) {
            System.err.println("Exception:" + e);
            e.printStackTrace();
        }

    }
}
