package com.revature.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.User;

/**
 * Implementation of AccountDAO which reads/writes to files
 */
public class AccountDaoFile implements AccountDao {
	// use this file location to persist the data to
	
	public static String fileLocation = "C:\\Users\\sferb\\OneDrive\\Desktop\\FSD softwares\\eclipse\\p0-kferb-master\\bin\\src\\main\\resources\\Users.txt "  ;
	private static File userFile = new File(fileLocation);
	private static List<Account> accountsList = new ArrayList<Account>();
	private static int id = 0;

	public Account addAccount(Account a) {
		// TODO Auto-generated method stub
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile, true))) {
	           oos.writeObject(a);
	           System.out.println("AccountId Registered !!");}
		 catch (IOException e) {
		 }		 
		return a;}
	

	public Account getAccount(Integer actId) {
		// TODO Auto-generated method stub
		Account lookUpAcc = null;
		for (Account account : accountsList) {
			if(account.getId().equals(actId));
		}
		return lookUpAcc;
	}

	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		Account lookUpAccount = null;
		for (Account account : accountsList) {
        		
		}
		return accountsList;
		
	}

	public List<Account> getAccountsByUser(User u) {
		// TODO Auto-generated method stub
		for (Account account : accountsList) {
		if(accountsList.contains(account));	
		}
		
		return getAccountsByUser(u);
	}

	public Account updateAccount(Account a) {
		// TODO Auto-generated method stub
		Account lookUpAcc = null;
		for (Account account : accountsList) {
		if(account.getOwnerId().equals(id));	
		}
		
		return a;
	}

	public boolean removeAccount(Account a) {
		// TODO Auto-generated method stub
		boolean exit = false;
		if(a.getId().equals(a));
		exit = accountsList.remove(a); 
		
		return exit;
	}

}
