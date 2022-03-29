package com.example.demo.jwthelper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.Controller.Home;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//methods - for generating token
//validate
//isExp
//util class for jwt
@Component
public class JwtUtil {
	
	Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    private static final long serialVersionUID = -2550185165626007488L;

   public static final long JWT_TOKEN_VALIDITY = 10 * 60;
    
   
  //  public static final long EXPIRATION_TIME = 900_000; // 15 mins


    private String secret="java";

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
    	logger.info("retrieve username from jwt token");
    	
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    
    public Date getExpirationDateFromToken(String token) {
    	logger.info("retrieve expiration date from jwt token");
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
    	logger.debug("for retrieveing any information from token we will need the secret key");
    	logger.info("Secret key ");
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) 
    {   logger.warn("check the if the token has expired");
        logger.info("check if the token has expired");
        final Date expiration = getExpirationDateFromToken(token);
        
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) {
    	logger.info("Generate token for user");
        Map<String, Object> claims = new HashMap<>();
        
        
        
        return doGenerateToken(claims, userDetails.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {
    	
    	logger.debug("while creating the token claims of the token, like Issuer, Expiration, Subject, and the ID");

    	logger.info("while creating the token claims of the token, like Issuer, Expiration, Subject, and the ID");

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
    	
    	logger.info("Validate token ");
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

	public static void invalidateRelatedTokens(HttpServletRequest httpServletRequest) {
		// TODO Auto-generated method stub
		
	}

	/*public boolean canTokenBeRefreshed(String token) {
	     return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	    }

	    private boolean ignoreTokenExpiration(String token) {
		// TODO Auto-generated method stub
		return false;
	}

		public String refreshToken(String token) {
	        final Date createdDate = new Date();
	        final Date expirationDate = calculateExpirationDate(createdDate);

	        final Claims claims = getAllClaimsFromToken(token);
	        claims.setIssuedAt(createdDate);
	        claims.setExpiration(expirationDate);

	        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	    }

		private Date calculateExpirationDate(Date createdDate) {
			// TODO Auto-generated method stub
			return null;
		}

 */
}