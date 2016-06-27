public class DepartmentServer extends UnicastRemoteObject implements RemoteSearch {

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

private PhoneServer server = new PhoneServer();

public DepartmentServer() throws RemoteException {
        super();

        // Create example phonebook
}
}
