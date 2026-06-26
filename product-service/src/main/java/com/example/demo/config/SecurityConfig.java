package com.example.demo.config;

import com.example.demo.filter.JwtFilter;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.document.UserDocument;
import com.example.demo.repository.UserRepository;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private  JwtFilter jwtFilter;
	@Autowired
	UserRepository repo;



	@Bean
	   SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception 
	   {
		http
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(auth -> auth
		        .requestMatchers("/api/products/login", "/api/products/test")
		        .permitAll()
		        .anyRequest().authenticated())
		        .addFilterBefore(jwtFilter,
		                 UsernamePasswordAuthenticationFilter.class);
				
		
		return http.build();
	   }
	
	

    @Bean
    CommandLineRunner init(
            UserRepository repo,
            PasswordEncoder encoder) {

        return args -> {

            if(repo.findByUsername("john").isEmpty()) {

                UserDocument user = new UserDocument();

                user.setId(UUID.randomUUID().toString());
                user.setUsername("john");
                user.setPassword(
                        encoder.encode("password"));
                user.setRole("USER");

                repo.save(user);
            }
        };
    }
    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration config)
            throws Exception {

        return config.getAuthenticationManager();
    }
    @Bean
     PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}