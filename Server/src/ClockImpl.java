/*
    CMPT431- Assignment1
    Author: Sen Lin-301250505
            Louis Jia Bao Zhuo-301235952
 */

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ClockImpl implements Clock {
    @Override
    public LocalDateTime getTime() throws RemoteException {
        LocalDateTime time = LocalDateTime.now();
        System.out.println("The Server Time is: "+ time.toString());
        return time;
    }

    public String getZoneId() throws RemoteException{
        ZoneId zoneId = ZoneId.systemDefault();
        return zoneId.toString();
    }
}
