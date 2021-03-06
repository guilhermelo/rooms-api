package melo.guilherme.rooms.api.config.security;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import melo.guilherme.rooms.api.user.User;
import melo.guilherme.rooms.api.user.UserRepository;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
	
	private final TokenService tokenService;
	private final UserRepository userRepository;
	
	public JWTAuthenticationFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		String token = getToken(request);
		
		boolean isTokenValid = tokenService.isTokenValid(token);
		
		if(isTokenValid) {
			authenticateClient(token);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private void authenticateClient(String token) {
		String userId = tokenService.getUserId(token);
		User user = userRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getToken(HttpServletRequest request) {

		String token = request.getHeader("Authorization");
		
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7);
	}

}
