package com.example.demo.Controller;



import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.UserServiceException;
import com.example.demo.Service.CustomUserDetailsService;
import com.example.demo.model.ErrorMessage;
import com.example.demo.model.JwtResponse;

import ch.qos.logback.core.pattern.parser.Node;



@RestController
public class Home   {
	
	
	
	//@Autowired
	//JwtResponse response;
	Logger logger = LoggerFactory.getLogger(Home.class);
	 @RequestMapping("/welcome")
	 
	    public  String  welcome() throws Exception {
		 
		 
		 logger.debug("login success done");
		 logger.info("Authorization was successful ");
	   
         return "login done successful  with token";
         
		 //throw new Exception("gysuuswkebwdjhe");
		   
		    	
	    }
    
	    
	    
       
	    
	 @RequestMapping("/test")

	    public String test() throws Exception {
		 logger.debug("login error ");

		 throw new RuntimeException("Opps Exception raised....");
	        
	    }

	 @RequestMapping("/getusers")
	 
	    public String getUser() {
		 if(true)throw new UserServiceException("User service is thrwon");
	       
		 return "{\"name\":\"Sadiya\"}";
	    }
	 @Autowired 
	private  CustomUserDetailsService   customUserDetailsService ;
	 
	// @Autowired 
	//private Main  main ;
	 
	 
	 @RequestMapping("/restapi/cwd")
	 
	 public String getpath() throws Exception {
		 logger.info("Current working directory ");
		 
		 return customUserDetailsService.whenUsingSystemProperties_thenReturnCurrentDirectory();
		 
	 }
	 
	 @RequestMapping("/restapi/ls")
	public List<com.example.demo.Controller.DirectoryStructureToJson.Node> getDirList(){
	logger.info("list the directory ");
	
		File file = new File(System.getProperty("user.dir"));
		 
	return	customUserDetailsService.getDirList(file);
		 
	}
	
	 @RequestMapping("/restapi/cd/{directory}")
	 
		public com.example.demo.Controller.DirectoryStructureToJson.Node getedist(@PathVariable String directory){
		 logger.info("Current directory ");
			 File file = new File(System.getProperty("user.dir"));
			 System.out.println(directory);
			 com.example.demo.Controller.DirectoryStructureToJson.Node   response = null;
		        for(com.example.demo.Controller.DirectoryStructureToJson.Node node: customUserDetailsService.getDirList(file)) {
		            if(node.getType().equalsIgnoreCase("directory")) {
		                if(node.getName().equalsIgnoreCase(directory)){
		                    response = node;
		                    break;
		                }
		            }
		        }
		        if(response!=null) {
		        	System.setProperty("user.dir", System.getProperty("user.dir")+"/"+directory);
		        }
		        return response;
	//	return	customUserDetailsService.getDirList(file);
			 
		}
	 
	
	  public  String ApplicationExceptionHandler (ErrorMessage exception) {
		 return exception.getMessage();
	 }
	 
		//@RequestMapping("/ls")
		
  //*/
 
// @RequestMapping("/restapi/Logout")
	 
	// public String logout() {
		 
	// }
	    

		/*    public static List<Node> getDirList(File node){
		        List<Node> nodeList=new ArrayList<>();
		        for(File n : node.listFiles()){
		            nodeList.add(getNode(n));
		        }
		        return nodeList;
		    }
	 
			public List<Node> getDirList(File node) {
				// TODO Auto-generated method stub
				 List<Node> nodeList=new ArrayList<>();
			        for(File n : node.listFiles()){
			            nodeList.add(getNode(n));
			        }
			       return nodeList;
				
			}
	    }
	private Object getNode(File file) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	 
	 
}
