package melo.guilherme.rooms.api.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import melo.guilherme.rooms.api.user.UserRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private AuthenticationService service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(corsConfig())
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/users**").permitAll()
			.antMatchers(HttpMethod.GET, "/users/exists/**").permitAll()
			.antMatchers(HttpMethod.POST, "/users/authentication").permitAll()
			.and()
			.authorizeRequests()
			.antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
			.and()
			.authorizeRequests()
			.anyRequest().authenticated()
			.and().csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilterBefore(new JWTAuthenticationFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
			
	}
	
	private CorsConfigurationSource corsConfig() {
		CorsConfiguration config = new CorsConfiguration();
		
		// "x-access-token"
		config.addExposedHeader("x-access-token");
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config.applyPermitDefaultValues());
		
		return source;
	}
}
