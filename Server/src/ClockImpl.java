import java.rmi.RemoteException;
import java.time.LocalTime;

public class ClockImpl implements Clock {
    @Override
    public LocalTime getTime(int a, int b) throws RemoteException {
        return LocalTime.now();
    }
}
