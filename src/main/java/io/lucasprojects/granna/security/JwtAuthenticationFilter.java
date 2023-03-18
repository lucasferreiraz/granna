package io.lucasprojects.granna.security;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.lucasprojects.granna.domain.exception.ErrorResponse;
import io.lucasprojects.granna.domain.model.User;
import io.lucasprojects.granna.dto.User.LoginRequestDTO;
import io.lucasprojects.granna.dto.User.LoginResponseDTO;
import io.lucasprojects.granna.dto.User.UserResponseDTO;
import io.lucasprojects.granna.utils.DateConverter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        super();
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;

        setFilterProcessesUrl("/api/auth");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            LoginRequestDTO login = new ObjectMapper()
                    .readValue(request.getInputStream(), LoginRequestDTO.class);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());

            Authentication auth = authenticationManager.authenticate(authToken);
            return auth;

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password");
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        String token = jwtUtils.generateToken(authResult);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setProfile(user.getProfile());
        userResponseDTO.setInativationDate(user.getInativationDate());
        userResponseDTO.setSignUpDate(user.getSignUpDate());

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken("Bearer " + token);
        loginResponseDTO.setUser(userResponseDTO);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(loginResponseDTO));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        
        String dateTime = DateConverter.parse(new Date());
        ErrorResponse error = new ErrorResponse(dateTime, 401, "Unauthorized", failed.getMessage());

        response.setStatus(401);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(error));
    }
}
