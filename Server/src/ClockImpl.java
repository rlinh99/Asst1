import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ClockImpl implements Clock {
    @Override
    public LocalDateTime getTime() throws RemoteException {
        return LocalDateTime.now();
    }

    public String getZoneId() throws RemoteException{
        ZoneId zoneId = ZoneId.systemDefault();
        return zoneId.toString();
    }
}
