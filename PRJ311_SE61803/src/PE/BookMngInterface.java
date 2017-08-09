/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PE;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author Razer Blade
 */
public interface BookMngInterface extends Remote {
    Vector getInitialData() throws RemoteException;
    boolean saveList(Vector data) throws RemoteException;
    int isExisted(String code, Vector data) throws RemoteException;
}
