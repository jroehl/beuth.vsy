import java.rmi.*;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @className RemoteSearch
 * @author jroehl
 * @date
 *
 *  Interface for RMI server
 */
public interface IRemoteSearch extends Remote {

	/**
	 * remote method
	 *
	 * @throws RemoteException
	 */
	CopyOnWriteArrayList<String[]> getNameSearchResult(String query) throws RemoteException;

	/**
	 *
	 * remote method
	 *
	 * @throws RemoteException
	 */
	CopyOnWriteArrayList<String[]> getNumberSearchResult(String query) throws RemoteException;


	CopyOnWriteArrayList<String[]> getSearchResult(String query, Integer type) throws RemoteException;

	void quit() throws RemoteException;
}
