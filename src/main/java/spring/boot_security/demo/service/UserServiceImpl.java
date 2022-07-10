package spring.boot_security.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.boot_security.demo.model.Role;
import spring.boot_security.demo.model.User;
import spring.boot_security.demo.dao.RoleDao;
import spring.boot_security.demo.dao.UserDao;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public User add(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRoles().isEmpty()){
            Role roleUser = roleDao.getByName("ROLE_USER");
            user.addRole(roleUser);
        } else {
            user.setRoles(roleDao.getRolesByName(user.getRoles()));
        }
        userDao.add(user);
        return user;
    }

    @Override
    @Transactional
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    @Transactional
    public void deleteById(int id) {

        delete(getById(id));
    }

    @Override
    @Transactional
    public void edit(User user) {
        user.setRoles(roleDao.getRolesByName(user.getRoles()));
        userDao.edit(user);
    }

    @Override
    public User getById(int id) {
        User user = userDao.getById(id);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userDao.getAllUsers().forEach(user -> users.add(user));
        return users;
    }

    public List<Role> getAllRoles() {
        return (List<Role>) roleDao.getAll();
    }

    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }

}
