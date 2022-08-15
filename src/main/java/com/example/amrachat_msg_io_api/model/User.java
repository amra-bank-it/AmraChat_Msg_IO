package com.example.amrachat_msg_io_api.model;

import javax.persistence.*;

@Entity
@Table(name = "Chat_Users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long Id;
    private String firstname;
    private String lastname;
    private String surname;
    private String permissions;
    private String department;

    public User(){

    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + Id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", surname='" + surname + '\'' +
                ", permissions='" + permissions + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
