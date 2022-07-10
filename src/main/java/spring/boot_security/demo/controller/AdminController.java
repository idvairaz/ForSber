package spring.boot_security.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spring.boot_security.demo.model.Role;
import spring.boot_security.demo.model.User;
import spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/api_admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/getroles")
    private List<Role> allRoles() {
        return (List<Role>) userService.getAllRoles();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    private List<User> allUsers() {
        return userService.getAllUsers();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    private User getUser(@PathVariable("id") int id) {
        return userService.getById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping
    public void PutEditModal(@RequestBody User user) {
        userService.edit(user);;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    private void DeleteModal(@PathVariable("id") int id) {
        userService.deleteById(id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping
    public User PostAdd(@RequestBody User user) {
        return userService.add(user);
    }
}
