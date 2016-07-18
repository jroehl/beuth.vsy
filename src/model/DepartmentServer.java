package model;

import controller.IRemoteSearch;
import controller.Search;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.CopyOnWriteArrayList;

/**
*
+ DepartmentServer serves the Phonebook content etc.
+ 
+
*/
public class DepartmentServer extends UnicastRemoteObject implements IRemoteSearch {

    private PhoneBook phoneBook;

    public DepartmentServer() throws RemoteException {
        super();
        this.phoneBook = new PhoneBook();
        // Create example phonebook
    }

    @Override
    public CopyOnWriteArrayList<String[]> getNameSearchResult(String name) throws RemoteException {
        System.out.println("RMI received");
        System.out.println("looking for " + name);
        return getSearchResult(name, 0);
    }

    @Override
    public CopyOnWriteArrayList<String[]> getNumberSearchResult(String number) throws RemoteException {
        System.out.println("RMI received");
        System.out.println("looking for " + number);
        return getSearchResult(number, 1);
    }

    @Override
    public CopyOnWriteArrayList<String[]> getSearchResult(String query, Integer type) throws RemoteException {
        String threadName = "search-number";
        if (type == 0) {
            threadName = "search-name";
        }
        CopyOnWriteArrayList<String[]> result = new CopyOnWriteArrayList<>();
        Thread t = new Thread(new Search(query, type, result, this.phoneBook), threadName);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("search done");
        return result;
    }

    @Override
    public void quit() throws RemoteException {
        System.out.println("start quitting department server");
        System.exit(0);
    }

    /**
     * main creates remote obj and exports and registers it with RMI
     *
     * @param args
     */
    public static void main(String args[]) {

        try {
            DepartmentServer server = new DepartmentServer();

            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

            Naming.rebind("server", server);

            System.out.println("Server ready and waiting for RMIs on port " + Registry.REGISTRY_PORT);
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }


}
