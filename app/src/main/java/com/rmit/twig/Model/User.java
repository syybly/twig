package com.rmit.twig.Model;

public class User {
    private String email;
    private String password;
    private String fullname;
    private String des;
    private String type;

    public User(String email, String password, String fullname, String type) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.type=type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}