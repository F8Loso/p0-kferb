package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.Account.AccountType;
import com.revature.beans.User;
import com.revature.utils.ConnectionUtil;

/**
 * Implementation of AccountDAO which reads/writes to a database
 */
public class AccountDaoDB implements AccountDao {
	
	private static Connection conn;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	List<Account>accList = new ArrayList<Account>();

   public AccountDaoDB() {
	ConnectionUtil.getConnection();
	// TODO Auto-generated constructor stub
	try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/p0", "root", "revature2022SQL");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();}}
	

	
	public Account addAccount(Account a) {
		// TODO Auto-generated method stub
		String query = "Insert into * p0_accounts (account_id, owner_id,type, balance, approved) values (?,?,?,?,?)";
 		int status = 0;
		try {
			  pstmt = conn.prepareStatement(query);
			  pstmt.setInt(1,0);
			  pstmt.setInt(2, a.getOwnerId());
	          pstmt.setString(3, a.getType().toString());
	          pstmt.setDouble(4, a.getBalance());
	          pstmt.setString(5,"0");
		      status = pstmt.executeUpdate();

		if (status > 0) {
			System.out.println("\n Application for Account Submitted! Application is pending approval. \n\n"); 
		
		}}catch(SQLException e) {}
		return a;
	}

	public Account getAccount(Integer actId) {
		// TODO Auto-generated method stub
		String query = "Select from * p0_accounts where =  " + actId;
		 Account acc = new Account();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
			
			  acc.setId(rs.getInt("accountId"));
	          acc.setOwnerId(rs.getInt("ownerId"));
	          acc.setType(AccountType.valueOf(rs.getString("type")));
	          acc.setBalance(rs.getDouble("balance"));
	          acc.setApproved(rs.getBoolean("account_status"));}}
	   catch(SQLException e) {}
		
		return acc;
	}

	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		String query = "select from * p0_accounts ";
		Account acc = new Account();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		      while(rs.next()) {
	          acc.setId(rs.getInt("accountId"));
	          acc.setOwnerId(rs.getInt("ownerId"));
	          acc.setType(AccountType.valueOf(rs.getString("type")));
	          acc.setBalance(rs.getDouble("balance"));
	          acc.setApproved(rs.getBoolean("account_status"));
	          accList.add(acc);}}
	   catch(SQLException e) {       
	          
	   }
		return accList;
	}

	public List<Account> getAccountsByUser(User u) {
		// TODO Auto-generated method stub
		String query = "select from * p0_accounts where ownerId = " + u.getId();
		Account acc = new Account();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			  while(rs.next()) {
	          acc.setId(rs.getInt("accountId"));
	          acc.setType(AccountType.valueOf(rs.getString("type")));
	          acc.setBalance(rs.getDouble("balance"));
	          acc.setApproved(rs.getBoolean("approved"));
	          accList.add(acc);}}
	   catch(SQLException e) {       
	          
	   }return accList;
	}

	public Account updateAccount(Account a) {
		// TODO Auto-generated method stub
		String query = "select from * 	p0_accounts set balance=?, approved=? where account_id=? ";
		
		
		try {
			pstmt = conn.prepareStatement(query);
		    pstmt.setDouble(1, a.getBalance());
			pstmt.setBoolean(2, a.isApproved());
			pstmt.executeUpdate();}
			
		catch(SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	public boolean removeAccount(Account a) {
		// TODO Auto-generated method stub
		String query = "delete from * p0_accounts where accountId =";
		try {
			stmt = conn.createStatement();
			stmt.execute(query);}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
			
			return true;
	}

	public static void closeResources() {
   	 try {
   		 if(rs!=null)
   			 rs.close();
   		 if(stmt!=null)
   			 stmt.close();
   		 if(pstmt!=null)
   			 pstmt.close();
   		 if(conn!=null)
   			 conn.close();
   	 }catch(SQLException e) {
   		 e.printStackTrace();
   	  	
	
}}}
