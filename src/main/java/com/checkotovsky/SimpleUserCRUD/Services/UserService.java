package com.checkotovsky.SimpleUserCRUD.Services;

import com.checkotovsky.SimpleUserCRUD.Models.User;
import com.checkotovsky.SimpleUserCRUD.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UsersRepository usersRepository;
    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> findAll()
    {
        return usersRepository.findAll();
    }
    public User findById(int id)
    {
        Optional<User> user = usersRepository.findById(id);
        return user.orElse(null);
    }
    @Transactional
    public void save(User user)
    {
        usersRepository.save(user);
    }
    @Transactional
    public void update(int id, User updatedUser)
    {
        updatedUser.setId(id);
        usersRepository.save(updatedUser);
    }
    @Transactional
    public void delete(int id)
    {
        usersRepository.deleteById(id);
    }
}
