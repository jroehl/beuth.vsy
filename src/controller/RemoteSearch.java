
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * @className RemoteSearch
 * @author jroehl
 * @date
 *
 *  Interface for RMI server
 */
public interface RemoteSearch extends Remote {

/**
 * remote method
 *
 * @throws RemoteException
 */
ArrayList<String> getNameSearchResult(String s) throws RemoteException;

/**
 *
 * remote method
 *
 * @throws RemoteException
 */
ArrayList<String> getNumberSearchResult(String s) throws RemoteException;

void quit() throws RemoteException;
}
