package com.example.CarRentalAplication.contract;



public class ClientsecurityDTO {
    private String username;
    private String password;
    private String role;
    private Byte active;

    public ClientsecurityDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ClientsecurityDTO() {
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Byte getActive() {
        return this.active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }
}
