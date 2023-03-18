package io.lucasprojects.granna.dto.User;

public class LoginResponseDTO {
    
    private String token;
    private UserResponseDTO user;

    public LoginResponseDTO() { }
    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public UserResponseDTO getUser() {
        return user;
    }
    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    
}
