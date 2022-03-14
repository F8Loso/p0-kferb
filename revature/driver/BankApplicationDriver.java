package com.revature.driver;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assume.assumeNoException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.io.FileNotFoundException;
import com.revature.beans.Account;
import com.revature.beans.Account.AccountType;
import com.revature.beans.User;
import com.revature.beans.User.UserType;
import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoDB;
import com.revature.dao.AccountDaoFile;
import com.revature.dao.TransactionDaoDB;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.dao.UserDaoFile;
import com.revature.exceptions.InvalidCredentialsException;
import com.revature.exceptions.UsernameAlreadyExistsException;
import com.revature.services.AccountService;
import com.revature.services.UserService;
import com.revature.utils.SessionCache;


/**
 * This is the entry point to the application
 */
public class BankApplicationDriver {
         
	
	 public static void main(String[] args) {
		// your code here...
			BankApplicationDriver bad = new BankApplicationDriver();																						
    System.out.println("********* \t Welcome to Revature Bank !! \t *********");	
          	
                  AccountDaoDB adao = new AccountDaoDB();
                  User userA = new User();
                  UserType cType = User.UserType.CUSTOMER;
                  UserType eType = User.UserType.EMPLOYEE;
                  UserDaoDB udao = new UserDaoDB();	
                  UserService userv = new UserService(udao,adao);
                  AccountService aserv = new AccountService(adao);
                  TransactionDaoDB tserv = new TransactionDaoDB();
                  Account acc = new Account();
                  Account acc1 = new Account();
	              int id = 0;
                  String fName =  null;
	              String lName = null;
	              String username = null;
	              String password = null;
                  Scanner log = new Scanner(System.in);
 	              Scanner logA = new Scanner(System.in);
                  boolean check = false;
 try {
         // file systems connections for users
		File file = new File("Users.txt");

		FileOutputStream f = new FileOutputStream(file, true);
		ObjectOutputStream o = new ObjectOutputStream(f);

		FileInputStream fi = new FileInputStream(file);
		ObjectInputStream oi = new ObjectInputStream(fi);

		o.close();
		oi.close();

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();}
	
	
	
	int options;
	while(!check) {
	System.out.println("Choose from available options [1-6]");
	System.out.println("Register with Revature Bank: [1]");
	System.out.println("Enter Revature login information: [2]");
	System.out.println("Display all users: [3]");
	System.out.println("Choose from available options [4]");
	System.out.println("Choose from available options [5]");
	System.out.println("Choose from available options [6]");
    options = log.nextInt();
	
	switch(options) {
	
	case 1: 
		System.out.println("----------------------------------------------------");
		 System.out.println("Choose CUSTOMER [1] or EMPLOYEE [2]"); 
		 options = log.nextInt();
		 if(options==1) {
		 try{
		 id = udao.userList.size();
		 System.out.print("Enter your firstname: ");
		 fName = logA.next();
		 System.out.print("Enter your lastname: ");
		 lName = logA.next();
		 System.out.print("Enter your username: ");
		 username = logA.next();
		 System.out.print("Enter your password: ");
		 password = logA.next();
		 userA = new User(id,fName,lName,username,password,cType);
		 udao.addUser(userA);
	     }catch(UsernameAlreadyExistsException e) {
		 e.printStackTrace();}
		 
	     
		 }if(options==2) {
		 try{
		 id = udao.userList.size();
		 System.out.print("Enter your firstname: ");
		 fName = logA.next();
		 System.out.print("Enter your lastname: ");
		 lName = logA.next();
		 System.out.print("Enter your username: ");
		 username = logA.next();
		 System.out.print("Enter your password: ");
		 password = logA.next();
		 userA = new User(id,fName,lName,username,password,eType);
		 udao.addUser(userA);
	     }catch(UsernameAlreadyExistsException e) {
		 e.printStackTrace();}
	     
	      }break;
		 
	      
	case 2: 
		
		 System.out.println("----------------------------------------------------");
		 
	     System.out.print("Enter your username: ");
		 username = logA.next();
		 System.out.print("Enter your password: ");
		 password = logA.next();
		 User logU= userv.login(username, password); 
     	 System.out.println("logged in user :" + logU);
     	if (logU != null) {
		 System.out.println("You have successfully logged in!");
		 SessionCache.setCurrentUser(logU);}
		 
		
		int option = 0;
		int accType = 0;
		double sBalance = 0;	
		
			 while(options<=6) {
		        System.out.println("------------------------------------------------------------");
		        System.out.println("Choose from available options! ");
		        System.out.println("\t\t\t 1.Apply Account ");
				System.out.println("\t\t\t 2.List of user accou");
				System.out.println("\t\t\t 3.Make a Deposit ");
				System.out.println("\t\t\t 4.Fund Transfer ");
				System.out.println("\t\t\t 5.Approve/Reject Account ");
				System.out.println("\t\t\t 6.Logout ");
				System.out.println("Enter an avaialable option: ");
			    options = log.nextInt(); 
			    switch(options) { 
			    case 1: 
			    	System.out.print("select the Account Type [1.Checking/2.Saving]: ");
					option = log.nextInt();
					System.out.print("Enter Starting balance:");
					sBalance = log.nextDouble();
					acc.setBalance(sBalance);
					System.out.println("Logged user ID :" + SessionCache.getCurrentUser().get().getId());
					acc.setOwnerId(logU.getId());
					acc.isApproved();
					if (option == 1) {acc.setType(AccountType.CHECKING);}
					else if (option ==2) {acc.setType(AccountType.SAVINGS);}
					
					List<Account> accountList = new ArrayList<Account>();
					accountList.add(acc);
					logU.setAccounts(accountList);
					aserv.createNewAccount(logU);
					break;
						
			    case 2:

					System.out.println("Available Accounts for this user");
					aserv.getAccounts(logU).forEach(System.out::println);
				break;
			    	case 3:
			    		System.out.print("Enter Account ID to Deposit :");
						int accountId = 0 ;
						accountId = log.nextInt();
						System.out.print("Enter the amount to deposit :");
						double amount = 0;
						amount = log.nextDouble();
						acc = adao.getAccount(accountId);
						aserv.deposit(acc, amount);
						break;
					
				case 4:

					break;
				case 5:
					break;
				case 6:
					System.out.print("Do you want to Logout? (1.Yes/2.No) :");
					int logout = 0;
					logout = log.nextInt();
				if (logout == 1) {
						options = 7;
					}
				else {
					 SessionCache.setCurrentUser(null);

				}
					break;
					
				default:
					System.out.println("Enter a number between 1 to 6");
					break;
				}

			}
	
		break;
				
		case 3:
			udao.getAllUsers().forEach(System.out::println);
			break;
		case 4:
			System.out.print("Enter Id of the customer to remove: ");
			id = log.nextInt();
			User u = udao.getUser(id);
			udao.removeUser(u);
			break;
		case 5:  
			System.out.print("Enter Id of the customer to Update: ");
			id = log.nextInt();
			System.out.print("Enter First Name to Update :");
			fName = logA.next();
			System.out.print("Enter Last Name to Update:");
			lName = logA.next();
			System.out.println("Enter username to update: ");
			username = logA.next();			
			System.out.print("Enter Password to Update: ");
			password = log.next();
			User updatedUser = new User();
			updatedUser.setId(id);
			updatedUser.setFirstName(fName);
			updatedUser.setLastName(lName);
			updatedUser.setUsername(username);
			updatedUser.setPassword(password);
			udao.updateUser(updatedUser);
			break;

	}}}}
	          	                  
			  
	     
	      