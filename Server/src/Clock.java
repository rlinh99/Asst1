import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.LocalTime;

//Interface for Client and Server to Register/Client to call
public interface Clock extends Remote{
    //the method that gets the current time of the system
    LocalDateTime getTime() throws RemoteException;
}
