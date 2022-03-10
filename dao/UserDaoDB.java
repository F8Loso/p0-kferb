package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.revature.beans.User;
import com.revature.beans.User.UserType;
import com.revature.utils.ConnectionUtil;

/**
 * Implementation of UserDAO that reads/writes to a relational database
 */
public class UserDaoDB implements UserDao  {
	    
	private static Connection conn;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	public List<User>userList = new ArrayList<User>();
	
	public UserDaoDB()  {
		ConnectionUtil.getConnection();
		// TODO Auto-generated constructor stub
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/p0", "root", "revature2022SQL");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public User addUser(User user) {
		
	
		// TODO Auto-generated method stub
		String query = "INSERT INTO p0.p0_user (id, first_name, last_name, username, password, user_type) values (?,?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(0, 2);
			pstmt.setInt(1, user.getId());
			pstmt.setString(2, user.getFirstName());
			pstmt.setString(3, user.getLastName());
			pstmt.setString(4, user.getUsername());
			pstmt.setString(5, user.getPassword());
			pstmt.setString(6, user.getUserType().toString());
			pstmt.executeUpdate();}
		
	
		catch(SQLException e) {
		e.printStackTrace();}
		return user;
	}

	public User getUser(Integer userId) {
		// TODO Auto-generated method stub
		String query = "Select * from p0.p0_user where = " + userId;
		User use = new User();
		try {
			stmt = conn.createStatement();
		    rs = stmt.executeQuery(query);
		     use.setId(rs.getInt("id"));
		     use.setUsername(rs.getString("username"));
		     use.setPassword(rs.getString("password"));}
		
		catch(SQLException e) {
              		    
		}return use;
	}

	public User getUser(String username, String pass) {
		// TODO Auto-generated method stub
		String query = "select * from p0.p0_user where username = '" + username + "' and password = '" + pass +"'" ;
		User use = new User();
		try {
			stmt = conn.createStatement();
		    rs = stmt.executeQuery(query);
		
		use.setUsername(rs.getString("username"));
		use.setPassword(rs.getString("password"));}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return use;
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
	
		String query = " select * from p0.p0_user";
	try {	
	    stmt = conn.createStatement();
	    rs = stmt.executeQuery(query);
		while(rs.next()) {
			User use = new User();
		     use.setId(rs.getInt("id"));
			 use.setFirstName(rs.getString("first_name"));
			 use.setLastName(rs.getString("last_name"));
			 use.setUsername(rs.getString("username"));
			 use.setPassword(rs.getString("password"));
			 use.setUserType(rs.getString("user_type"));
			 userList.add(use);}
		}catch(SQLException e) {
			 
			e.printStackTrace();}
			 return userList;
	}

	public User updateUser(User u) {
		// TODO Auto-generated method stub
		String query = "update to p0_user set first_name=?, last_name=?, username=? , password=?, user_type=?, where id =?";  
		try {
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, u.getId());
        pstmt.setString(2, u.getFirstName());
        pstmt.setString(3, u.getLastName());
        pstmt.setString(4, u.getUsername());
        pstmt.setString(5, u.getPassword());
        pstmt.setString(6, u.getUserType().toString());
        pstmt.executeUpdate();}
		catch(SQLException e) {
			return u;
		}
		return null;
	}

	public boolean removeUser(User u) {
		// TODO Auto-generated method stub
		String query = "delete from * p0.p0_user where =" +u.getId();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		}catch(SQLException e) {}
		return false;
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
    	 }
     }
	 
}
