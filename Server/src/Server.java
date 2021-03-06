/*
    CMPT431- Assignment1
    Author: Sen Lin-301250505
            Louis Jia Bao Zhuo-301235952
 */

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args) throws  Exception {
        try {
            //name of the object binding in registry
            String name = "ServerClock";

            //create remote object
            Clock clk = new ClockImpl();

            //cast this object into rmi remote form
            Clock stub = (Clock) UnicastRemoteObject.exportObject(clk, 0);

            int port = 1075;

            //start RMI registry.
            //change port parameter if the port is blocked
            Registry reg = LocateRegistry.createRegistry(port);

            //register this object to rmi registry
            reg.rebind(name, stub);

            System.out.println("ServerClock object is Registered");
        } catch (RemoteException e) {
            System.err.println("Exception:" + e);
            e.printStackTrace();
        }

    }
}
