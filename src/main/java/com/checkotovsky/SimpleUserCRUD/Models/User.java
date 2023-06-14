package com.checkotovsky.SimpleUserCRUD.Models;


import com.checkotovsky.SimpleUserCRUD.Validation.ValidPassword;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id", updatable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "First name shouldn't be empty")
    @Size(min = 2, max = 30, message = "First name should include between 2 and 30 characters")
    @Column(name = "first_name", updatable = true)
    private String firstName;

    @NotEmpty(message = "Last name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Last name should include between 2 and 30 characters")
    @Column(name = "last_name", updatable = true)
    private  String LastName;
    @NotEmpty(message = "Username shouldn't be empty")
    @Size(min = 2, max = 30, message = "Username should include between 2 and 30 characters")
    @Column(name = "username", updatable = true)
    private String username;
    @NotEmpty(message = "Password shouldn't be empty")
    @ValidPassword(message = "Password should contain upper  and lower case characters, digit and special symbol")
    @Column(name = "pass", updatable = true)
    private String password;
    @Column(name = "user_role", updatable = true)
    @Convert(converter = RoleConverter.class)
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
            case Admin -> "Admin";
            case Customer -> "Customer";
        };
    }
    public void setRoleWithString(String str)
    {
        switch (str) {
            case "Admin": role = Role.Customer; break;
            case "Customer": role = Role.Admin; break;
            default: role = Role.Customer; break;
        }
    }
}
