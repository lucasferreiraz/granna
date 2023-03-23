package io.lucasprojects.granna.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import io.lucasprojects.granna.security.JwtAuthenticationFilter;
import io.lucasprojects.granna.security.JwtAuthorizationFilter;
import io.lucasprojects.granna.security.JwtUtils;
import io.lucasprojects.granna.security.UserDetailsSecurityServer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationConfiguration authConfig;

    @Autowired
    private UserDetailsSecurityServer userDetailsSecurityServer;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.headers(headers -> headers.frameOptions().disable());
        http.cors(cors -> cors.disable())
            .csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .anyRequest().authenticated())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilter(new JwtAuthenticationFilter(authenticationManager(authConfig), jwtUtils));
        http.addFilter(new JwtAuthorizationFilter(authenticationManager(authConfig), jwtUtils, userDetailsSecurityServer));

        return http.build();
    }

    @Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

    @Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
