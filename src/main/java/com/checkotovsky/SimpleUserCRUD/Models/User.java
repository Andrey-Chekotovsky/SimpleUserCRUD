package com.checkotovsky.SimpleUserCRUD.Models;


import com.checkotovsky.SimpleUserCRUD.Validation.ValidPassword;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class User {
    private int id;
    @NotEmpty(message = "First name shouldn't be empty")
    @Size(min = 2, max = 30, message = "First name should include between 2 and 30 characters")
    private String firstName;

    @NotEmpty(message = "Last name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Last name should include between 2 and 30 characters")
    private  String LastName;
    @NotEmpty(message = "Username shouldn't be empty")
    @Size(min = 2, max = 30, message = "Username should include between 2 and 30 characters")
    private String username;
    @NotEmpty(message = "Password shouldn't be empty")
    @ValidPassword(message = "Password should contain upper  and lower case characters, digit and special symbol")
    private String password;
    private Role role = Role.Customer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public String roleToString()
    {
        return switch (role) {
            case Admin -> "admin";
            case Customer -> "customer";
        };
    }
    public void setRoleWithString(String str)
    {
        switch (str) {
            case "admin": role = Role.Customer; break;
            case "customer": role = Role.Admin; break;
            default: role = Role.Customer; break;
        }
    }
}
