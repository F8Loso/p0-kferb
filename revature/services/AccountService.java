package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFileFormat.Type;

import com.revature.beans.Account;
import com.revature.beans.Transaction;
import com.revature.beans.Transaction.TransactionType;
import com.revature.beans.Account.AccountType;
import com.revature.beans.User;
import com.revature.beans.User.UserType;
import com.revature.dao.AccountDao;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.exceptions.OverdraftException;
import com.revature.exceptions.UnauthorizedException;

/**
 * This class should contain the business logic for performing operations on Accounts
 */
public class AccountService {
	
	public AccountDao actDao;
	public static final double STARTING_BALANCE = 25d;
	private static List<Account> accList = new ArrayList<Account>();	
	public AccountService(AccountDao dao) {
		this.actDao = dao;
	}
	

	/**
	 * Withdraws funds from the specified account
	 * @throws OverdraftException if amount is greater than the account balance
	 * @throws UnsupportedOperationException if amount is negative
	 */
	public void withdraw(Account a, Double amount) {
	        Account withdraw = new Account();
	        
		
              if(!a.isApproved()) {
	 
             throw new OverdraftException();
              }else if (amount>a.getBalance()) {
	         throw new OverdraftException(); 
              }else if (amount<0) {
	         throw new UnsupportedOperationException();
             
              } else {
            	  
            	 a.setBalance(a.getBalance());
              }}
               
	         
	
	          
	
	
	/**        
	 * Deposit funds to an account
	 * @throws UnsupportedOperationException if amount is negative
	 */
	public void deposit(Account a, Double amount) { 
	   
		         if(!a.isApproved()) {
				
				throw new UnsupportedOperationException();
		      }else if (amount <= 0 ) {
				throw new UnsupportedOperationException();}
				else { a.setBalance(a.getBalance() + amount);	}          		
			}
	
	
	/**
	 * Transfers funds between accounts
	 * @throws UnsupportedOperationException if amount is negative or 
	 * the transaction would result in a negative balance for either account
	 * or if either account is not approved
	 * @param fromAct the account to withdraw from
	 * @param toAct the account to deposit to
	 * @param amount the monetary value to transfer
	 */
	public void transfer(Account fromAct, Account toAct, double amount) {
	  Account transfer = new Account();
     
	      
		if (amount > fromAct.getBalance()) {
			
			throw new UnsupportedOperationException();
		} else if (amount < 0.0) {
			
			throw new UnsupportedOperationException();
		} else if (!fromAct.isApproved()) {
			
			throw new UnsupportedOperationException();
		} else if (!toAct.isApproved()) {
					throw new UnsupportedOperationException();
		} else
			transfer.equals(toAct);
			fromAct.setBalance(fromAct.getBalance() - amount);
			toAct.setBalance(toAct.getBalance() + amount);}
	        
	          
	/**
	 * Creates a new account for a given User
	 * @return the Account object that was created
	 */
	public Account createNewAccount(User u) {
	    
		
      Account account = new Account();
      
        
	
		
		account.setId(0);
        account.setOwnerId(u.getId());
       
        account.setBalance(STARTING_BALANCE);
        if(actDao.getAccountsByUser(u) == null) {
        	
        	account.setType(AccountType.SAVINGS);
        }else { account.setType(AccountType.CHECKING);
        }
                     
        	
    	accList.add(account);
    	u.setAccounts(accList);
    	
    	
		return account;
	    
	  
		
	}
	
	/**
	 * Approve or reject an account.
	 * @param a
	 * @param approval
	 * @throws UnauthorizedException if logged in user is not an Employee
	 * @return true if account is approved, or false if unapproved
	 */
	public boolean approveOrRejectAccount(Account a, boolean approval) {
		boolean trick = false;
		
		
		if(a.equals(a) && !a.isApproved() ) 
	    	  
	    	throw new UnauthorizedException();
		 
		return trick ;
	}
	
	


	
	public List<Account> getAccounts(User u) {
		List<Account> accountList = new ArrayList<Account>();
		accountList = u.getAccounts();
		return accountList;
	}
}


