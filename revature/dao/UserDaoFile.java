package com.revature.dao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


import com.revature.beans.User;
import com.revature.exceptions.InvalidCredentialsException;

/**
 * Implementation of UserDAO that reads and writes to a file
 */
public class UserDaoFile implements UserDao {
	
	
	public static String fileLocation = "C:\\Users\\sferb\\OneDrive\\Desktop\\FSD softwares\\eclipse\\p0-kferb-master\\bin\\src\\main\\resources\\Users.txt " ;
	private static File userFile = new File(fileLocation);
	public static List<User> userList = new ArrayList<User>();	
	private static int id = 0;
	
	public User addUser(User user) {
	try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile, true))) {
           oos.writeObject(user);
           System.out.println("Registered User! ");}
	 catch (IOException e) {
	 
         e.printStackTrace();
	  	// TODO Auto-generated catch block
	}
		return user;
	}

	public User getUser(Integer userId) {
		// TODO Auto-generated method stub
		User lookUpUser = null;
		for (User user : userList) {
	  if(user.getId().equals(userId));
			lookUpUser = user;
		}
        return lookUpUser;
	}

	public User getUser(String username, String pass) {
		// TODO Auto-generated method stub
		User lookUpUser = null;
		for(User user : userList) {
	   	if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(pass))
	    	user = lookUpUser;}
	  
		return lookUpUser;
	}
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		 try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
	   do {userList.add((User) ois.readObject()); 
			
	   }while(ois.readObject() != null);
			
		}catch(Exception e) {
	   
			e.printStackTrace();
		}
			return userList;
	}

	public User updateUser(User u) {
		// TODO Auto-generated method stub
		User lookUpUser = null;
		for (User user : userList) {
	   if(user.getId().equals(u.getId()));
		}	 
	   return updateUser(lookUpUser) ;
	
	}

	public boolean removeUser(User u) {
		// TODO Auto-generated method stub
		boolean rem = false;
		for(User user: userList) {
		if(user.getId().equals(u.getId())) {
		rem = userList.remove(u); }}
		
		return rem;
	}
	    
}
