/*
    CMPT431- Assignment1
    Author: Sen Lin-301250505
            Louis Jia Bao Zhuo-301235952
 */

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

//Interface for Client and Server to Register/Client to call
public interface Clock extends Remote {
    //the method that gets the current time of the server
    LocalDateTime getTime() throws RemoteException;

    //the method that gets the current timezone of the server
    String getZoneId() throws RemoteException;
}
