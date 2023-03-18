package io.lucasprojects.granna.dto.User;

import java.util.Date;

public class UserResponseDTO {

    private Long id;
    private String email;
    private String name;
    private String profile;
    private Date inativationDate;
    private Date signUpDate;

    public UserResponseDTO() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Date getInativationDate() {
        return inativationDate;
    }

    public void setInativationDate(Date inativationDate) {
        this.inativationDate = inativationDate;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

}
