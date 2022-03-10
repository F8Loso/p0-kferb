package com.revature.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.Transaction;
import com.revature.beans.User;

public class TransactionDaoFile implements TransactionDao {
	  
	public static List<Transaction> TList = new ArrayList<Transaction>();
	public static String fileLocation = "C:\\Users\\sferb\\OneDrive\\Desktop\\FSD softwares\\eclipse\\p0-kferb-master\\bin\\src\\main\\resources\\Users.txt " ;
	private static File userFile = new File(fileLocation);
  
	
	
	public Transaction  addTransaction(Account fromAct, Account toAct, double amount) {
		// TODO Auto-generated method stub
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile, true))) {
	           oos.writeObject(amount);
	           System.out.println("Transaction Posted !!! ");}
							
			catch(Exception e) {
		e.printStackTrace();}
	return addTransaction(fromAct, toAct, amount);
	}
	public List<Transaction> getAllTransactions() {
	         
		 try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
			   do {TList.add((Transaction) ois.readObject()); 
					
			   }while(ois.readObject() != null);
					
				}catch(Exception e) {
			   
					e.printStackTrace();
				}
					return TList;
	
}}
