package com.example.demo.Service;

import java.io.File;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.demo.Controller.Home;
import com.example.demo.Controller.DirectoryStructureToJson.Node;
import com.example.demo.Exception.UserServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	
	
	public HashMap<String, String> getHashMap() {
		return hashMap;
	}




	public void setHashMap(HashMap<String, String> hashMap) {
		this.hashMap = hashMap;
	}

	Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	
	 
	
	//CustomUserDetailsService bs = new CustomUserDetailsService();
	
    HashMap<String, String>  hashMap = new HashMap<>();
   
     public void initUserDirectory(String userName){
       hashMap.put(userName, System.getProperty("user.dir"));
  }
     


	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
	
		
	
		
		// TODO Auto-generated method stub
		if (userName.equals("Sadiya")) {
			// return new User(userName:"Sadiya",password:"Sadiya12345",new ArrayList<>());
			logger.info("User found ");
			return new User("Sadiya", "Sadiya123", new ArrayList<>());
		}
		if (userName.equals("Naina")) {
			// return new User(userName:"Sadiya",password:"Sadiya12345",new ArrayList<>());
			logger.info("User found ");
			return new User("Naina", "Naina123", new ArrayList<>());
		}
		if (userName.equals("Safa")) {
			// return new User(userName:"Sadiya",password:"Sadiya12345",new ArrayList<>());
			logger.info("User found ");
			return new User("Safa", "Safa123", new ArrayList<>());
		}

		else {
			logger.debug("login error ");
			logger.error("User not found");

			// throw new RuntimeException("Opps Exception raised....");
			// if(true)throw new UserServiceException("Wrong Credentials");
			throw new UsernameNotFoundException("User not found");
		}
		
	}	
	 

	
	 
 		
		

	   
	






/*

	public String whenUsingSystemProperties_thenReturnCurrentDirectory() {

		String userDirectory = System.getProperty("user.dir");
		System.out.println("cwd:" + userDirectory);
		return ("cwd:" + userDirectory);
	}
*/	


	public Node getNode(File node) {
		if (node.isDirectory()) {
			try {
				return new Node(node.getName(), node.getAbsolutePath(), "directory", getDirList(node));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			return new Node(node.getName(), node.getAbsolutePath(), "file", null);
		}
		return null;
	}

	/*
	 * public static List<Node> getDirList(File node){ List<Node> nodeList=new
	 * ArrayList<>(); for(File n : node.listFiles()){ nodeList.add(getNode(n)); }
	 * return nodeList; }
	 */
	public List<Node> getDirList(File node) {
		// TODO Auto-generated method stub
		List<Node> nodeList = new ArrayList<>();
		for (File n : node.listFiles()) {
			nodeList.add(getNode(n));
		}
		return nodeList;

	}

}


