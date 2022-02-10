package com.dam2;

import org.bson.types.ObjectId;

public class User {

    private ObjectId id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;

    public User() {

    }

    public ObjectId getId() {
        return id;
    }

    public User setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username)
    {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password)
    {
        this.password = password;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public User setFirstname(String firstname)
    {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public User setLastname(String lastname)
    {
        this.lastname = lastname;
        return this;
    }
    @Override
    public String toString() {
        return "User{" +
                "_id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }


}

