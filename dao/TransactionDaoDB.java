package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.a.TracingPacketSender;
import com.revature.beans.Transaction;
import com.revature.beans.Transaction.TransactionType;
import com.revature.beans.User;
import com.revature.utils.ConnectionUtil;

public class TransactionDaoDB implements TransactionDao {

	
	private static Connection conn;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	public static List<Transaction> TList = new ArrayList<Transaction>();
	
	public  TransactionDaoDB() {
	 
		ConnectionUtil.getConnection();
		// TODO Auto-generated constructor stub
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/p0", "root", "revature2022SQL");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Transaction> getAllTransactions() {
		// TODO Auto-generated method stub
		    String query = "select from * p0.p0_transactions ";
		    try { 
		    	   Transaction T = new Transaction();
		    	   stmt = conn.createStatement();
		           rs = stmt.executeQuery(query);
		           T.setAmount(rs.getDouble("amount"));
		          
		           T.setType(TransactionType.valueOf("type"));
		           TList.add(T);}
		    
		catch(SQLException e) {
			e.printStackTrace();
		}
		return TList;
	}

}
