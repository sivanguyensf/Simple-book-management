/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PE;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Vector;
import java.io.*;
import java.util.StringTokenizer;

/**
 *
 * @author Razer Blade
 */
public class BookServer extends UnicastRemoteObject implements BookMngInterface {

    String filename; 
    Vector tmpData = null;

    public BookServer(String filename) throws RemoteException {
        super();
        this.filename = filename;
        tmpData = getInitialData();
    }

    @Override
    public Vector getInitialData() throws RemoteException {
        Vector data = new Vector(0);
        try {
            FileReader f = new FileReader(filename);
            BufferedReader br = new BufferedReader(f);
            String line;
            StringTokenizer stk;
            while ((line = br.readLine()) != null) {
                stk = new StringTokenizer(line, ",");
                Vector v = new Vector();
                v.add(stk.nextToken().trim());//code
                v.add(stk.nextToken().trim());//author
                v.add(Integer.parseInt(stk.nextToken().trim()));//price
                v.add(Integer.parseInt(stk.nextToken().trim()));//year of release
                v.add(stk.nextToken().trim());//publisher
                data.add(v);
            }
            br.close();
            f.close();
        } catch (Exception e) {
        }
        return data;
    }

    @Override
    public boolean saveList(Vector data) throws RemoteException {
        try {
            FileWriter f = new FileWriter(filename);
            PrintWriter pw = new PrintWriter(f);
            for (int i = 0; i < data.size(); i++) {
                Vector v = ((Vector)(data.get(i)));
                String S = "";
                S += v.get(0) + "," + v.get(1) + "," + v.get(2) + "," + v.get(3) + "," + v.get(4);
                pw.println(S);
            }
            pw.close();
            f.close();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    
    @Override
    public synchronized int isExisted(String code, Vector data) throws RemoteException {
        for (int i = 0; i < data.size(); i++) {
            Vector row = (Vector) data.get(i);
            if (code.equals(row.get(0))){
                return 1;
            }
        }
        for (int i = 0; i< tmpData.size();i++)
        {
            Vector row = (Vector) tmpData.get(i);
            if (code.equals(row.get(0)))
            {
                return 0;
            }
        }
        return -1;
    }
}
