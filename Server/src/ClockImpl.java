import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ClockImpl implements Clock {
    @Override
    public LocalDateTime getTime() throws RemoteException {
        return LocalDateTime.now();
    }
}
