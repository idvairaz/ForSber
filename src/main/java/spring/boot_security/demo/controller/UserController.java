package spring.boot_security.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot_security.demo.model.User;
import spring.boot_security.demo.service.UserDetailsImpl;

@RestController
@RequestMapping(value = "/user/api_user")
public class UserController {

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping
    private User viewUser(@AuthenticationPrincipal UserDetailsImpl user) {
        return user.getUser();
    }
}
