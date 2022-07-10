package spring.boot_security.demo.service;

import spring.boot_security.demo.model.Role;
import spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    //User create();
    User add(User user);
    void delete(User user);
    void deleteById(int id);
    void edit(User user);
    User getById(int id);
    User getByUsername(String username);
    Iterable<Role> getAllRoles();
}
