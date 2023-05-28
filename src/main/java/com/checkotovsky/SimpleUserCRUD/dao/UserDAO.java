package com.checkotovsky.SimpleUserCRUD.dao;

import com.checkotovsky.SimpleUserCRUD.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserDAO {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<User> selectAll()
    {
        return jdbcTemplate.query("SELECT * FROM users;", new UserMapper());
    }
    public User select(int id)
    {
        return jdbcTemplate.query("SELECT * FROM users WHERE user_id = ?;", new Object[]{id},
                new UserMapper()).stream().findAny().orElse(null);
    }
    public void update(User user, int id)
    {
        jdbcTemplate.update("UPDATE users SET first_name = ?, last_name = ?, username = ?, " +
                        "pass = ?, user_role = ? WHERE user_id = ?;", user.getFirstName(), user.getLastName(),
                user.getUsername(), user.getPassword(), user.roleToString(), id);
    }
    public void insert(User user)
    {
        jdbcTemplate.update("INSERT INTO users(first_name, last_name, username, pass, user_role) " +
                        "VALUES (?, ?, ?, ?, ?);",
                user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.roleToString());
    }
    public void delete(int id)
    {
        jdbcTemplate.update("DELETE FROM users WHERE user_id = ?", id);
    }
}
