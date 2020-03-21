package melo.guilherme.rooms.api.config.security;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import melo.guilherme.rooms.api.user.User;

@Service
public class TokenService {
	
	private String secret = "mysecret";
	
	public String generateToken(Authentication authentication) {
		User loggedUser = (User) authentication.getPrincipal();
		LocalDate expirationDate = LocalDate.now().plusDays(1);
		
		return Jwts.builder()
				   .setIssuer("Rooms API")
				   .setIssuedAt(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
				   .setSubject(loggedUser.getId())
				   .setExpiration(Date.from(expirationDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))
				   .signWith(SignatureAlgorithm.HS256, secret)
				   .compact();
		
	}

	public boolean isTokenValid(String token) {
		try {
			getClaims(token);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getUserId(String token) {
		
		Claims claims = getClaims(token).getBody();
		
		return claims.getSubject();
	}
	
	public Jws<Claims> getClaims(String token) {
		return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
	}

}
