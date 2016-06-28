package controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author jroehl
 * @className RemoteSearch
 * @date Interface for RMI server
 */
public interface IRemoteSearch extends Remote {

    /**
     * remote method
     *
     * @throws RemoteException
     */
    CopyOnWriteArrayList<String[]> getNameSearchResult(String query) throws RemoteException;

    /**
     * remote method
     *
     * @throws RemoteException
     */
    CopyOnWriteArrayList<String[]> getNumberSearchResult(String query) throws RemoteException;

    /**
     * remote method
     *
     * @throws RemoteException
     */
    CopyOnWriteArrayList<String[]> getSearchResult(String query, Integer type) throws RemoteException;

    void quit() throws RemoteException;
}
