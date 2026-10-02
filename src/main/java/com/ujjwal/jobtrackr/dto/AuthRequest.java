package com.ujjwal.jobtrackr.dto;

public class AuthRequest {
    private String email;
    private String password;
    private String name;

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public void setEmail(String v) { this.email = v; }
    public void setPassword(String v) { this.password = v; }
    public void setName(String v) { this.name = v; }
}