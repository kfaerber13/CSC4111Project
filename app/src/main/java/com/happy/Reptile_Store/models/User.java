package com.happy.Reptile_Store.models;

public class User {
    String  id ,firstname, lastname , ph, mail , type  , user_image , address ;

    public User() {
    }

    public User(String id, String firstname, String lastname, String ph, String mail, String type, String user_image, String address) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.ph = ph;
        this.mail = mail;
        this.type = type;
        this.user_image = user_image;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
