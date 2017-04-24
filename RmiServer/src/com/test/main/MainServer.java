package com.test.main;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class MainServer {
	
	public static void main(String args[]){
		
		try {
//			System.setProperty("java.rmi.server.hostname","192.168.1.103");
			LocateRegistry.createRegistry(6600);
			Naming.bind("rmi://127.0.0.1:6600/Hello", new ComputeImpl());
			System.out.println("Service start!!");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
