package com.revature.driver;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.io.FileNotFoundException;
import com.revature.beans.Account;
import com.revature.beans.Account.AccountType;
import com.revature.beans.User;
import com.revature.beans.User.UserType;
import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoDB;
import com.revature.dao.AccountDaoFile;
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
																									
    System.out.println("********* \t Welcome to Revature Bank !! \t *********");	
	
    AccountDaoDB adao = new AccountDaoDB();
    User userA = new User();
    UserDaoDB udao = new UserDaoDB();	
    UserService userv = new UserService(udao, adao);
	AccountService aserv = new AccountService(adao);
    Integer id = 0;
    String fName = null;
	String lName = null;
	String username = null;
	String password = null;
	Scanner log = new Scanner(System.in);
	Scanner logA = new Scanner(System.in);
	int options;
	boolean check = false;
	
	
	System.out.println("Choose from available options [1-7]");
	
	
	while(!check){
	System.out.println("Register with Revature Bank: [1]");
	System.out.println("Enter Revature login information: [2]");
	System.out.println("Exit Revature Bank: [3]");
	System.out.println("Choose from available options [4]");
	System.out.println("Choose from available options [5]");
	System.out.println("Choose from available options [6]");
    options = log.nextInt();
	
	switch(options) {
	
	case 1: 
		System.out.println("----------------------------------------------------");
		 
		 udao.userList.size();
		 System.out.println("Enter a valid Id:");
		 id = log.nextInt();
		 System.out.print("Enter your firstname: ");
		 fName = logA.next();
		 System.out.print("Enter your lastname: ");
		 lName = logA.next();
		 System.out.print("Enter your username: ");
		 username = logA.next();
		 System.out.print("Enter your password: ");
		 password = logA.next();
		 User user = new User(id, fName, lName, username, password, UserType.CUSTOMER);
		 userv.register(user);
		 if(userv.equals(userv))
		 System.out.println("Welcome to Revature!!! ");
		 else
			 System.out.println("Please register again!!! ");
		 System.out.println("----------------------------------------------------");
	
	break;
	    
	  case 2: 
		
		 System.out.println("----------------------------------------------------");
		 try {
	     System.out.print("Enter your username: ");
		 username = logA.next();
		 System.out.print("Enter your password: ");
		 password = logA.next();
		 User logU= userv.login(username, password); 
		 System.out.println("logged in user :" + logU);
		 System.out.println("You have successfully logged in!");
		 SessionCache.setCurrentUser(logU);}
		 catch(ClassCastException e){
		
		{System.out.println("Please enter a valid login: ");
		  
	  
		int option = 0;
		int accType = 0;
		double sBalance = 0;	
		
			
					
					
			
			}}case 3:
		       
		        System.out.println("\t\t\t 1.Apply Account ");
				System.out.println("\t\t\t 2.Deposit");
				System.out.println("\t\t\t 3.Withdraw ");
				System.out.println("\t\t\t 4.Fund Transfer ");
				System.out.println("\t\t\t 5.Approve/Reject Account ");
				System.out.println("\t\t\t 6.Logout ");
				System.out.print(" Logout? (1.Yes/2.No) :");
		        options = log.nextInt(); 
		       
		                 int logout = 0;
		                 logout = log.nextInt();
		                 if (logout == 1) {
			             SessionCache.setCurrentUser(null);}
		
		 break;
	}
		System.out.println("Enter a number between 1 to 6");
		
				
			 System.out.println("----------------------------------------------------");
	}}}
