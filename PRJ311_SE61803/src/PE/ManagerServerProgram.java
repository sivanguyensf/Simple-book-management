/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PE;

import java.rmi.Naming;
import java.lang.Runtime;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Razer Blade
 */
public class ManagerServerProgram {
    public static void main(String[] args) {
        String serviceName = "rmi://localhost:1098/BookService";
        String filename = "book.txt";
        BookServer server = null;
        try {
            server = new BookServer(filename);
            LocateRegistry.createRegistry(1098);
            Naming.rebind(serviceName, server);
            System.out.println("Service " + serviceName + " is running.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
