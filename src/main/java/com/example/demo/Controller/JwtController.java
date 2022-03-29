package com.example.demo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.UserServiceException;
import com.example.demo.Service.CustomUserDetailsService;
import com.example.demo.jwthelper.JwtUtil;
import com.example.demo.model.JwtRequest;
import com.example.demo.model.JwtResponse;



@RestController
public class JwtController{
	
	Logger logger = LoggerFactory.getLogger(JwtController.class);

	@Autowired
	  private AuthenticationManager authenticationManager;
	  
	  @Autowired
	    private JwtUtil jwtUtil;
	  
	   @Autowired
	    private CustomUserDetailsService customUserDetailsService;

	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	  public ResponseEntity<JwtResponse> generateToken( @RequestBody JwtRequest jwtRequest)   throws Exception {
		  
	
		 logger.warn("Use proper credential to login ");
		  System.out.println(jwtRequest);
		  try {

	            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));


	        } catch (UsernameNotFoundException e) {
	            e.printStackTrace();
	            
	            logger.error("Error !!!Wrong Credentials" );
	            throw new Exception("Usename not found");
	          
	        }
		  catch (BadCredentialsException e)
		  
	        {
			  logger.error("Error !!!Wrong Credentials" );
	            e.printStackTrace();
	            throw new Exception("Bad Credentials");
	        }

		   //fine area..
		  
		  logger.info("Token Accessed");
		  
		  
		  
	        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
          
	       customUserDetailsService.initUserDirectory(userDetails.getUsername());
	        
	        String token = this.jwtUtil.generateToken(userDetails);
	        System.out.println("JWT " + token);

	        //{"token":"value"}

	       
	        return ResponseEntity.ok(new JwtResponse(token));

		  
		 
	  }
	 
	
	/*@RequestMapping(value = "/logout")
	  public ResponseEntity<JwtResponse> refreshtoken( @RequestBody JwtRequest jwtRequest)   throws Exception {
		  
	
		 logger.warn("Use proper credential to login ");
		  System.out.println(jwtRequest);
		  try {

	            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));


	        } catch (UsernameNotFoundException e) {
	            e.printStackTrace();
	            
	            logger.error("Error !!!Wrong Credentials" );
	            throw new Exception("Bad Credentials");
	          
	        }
		  catch (BadCredentialsException e)
		  
	        {
			  logger.error("Error !!!Wrong Credentials" );
	            e.printStackTrace();
	            throw new Exception("Bad Credentials");
	        }

		   //fine area..
		  
		  logger.info("Token Accessed");
	        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

	        String token = this.jwtUtil.generateToken(userDetails);
	        System.out.println("JWT " + token);

	        //{"token":"value"}

	        return ResponseEntity.ok(new JwtResponse(token));

		  
		 
	  }
	*/
	 
	     
	    
}