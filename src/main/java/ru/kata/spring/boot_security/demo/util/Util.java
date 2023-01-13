package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Util {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public Util(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void initDB() {
        Role adminRole = new Role(1, "ROLE_ADMIN");
        Role userRole = new Role(2, "ROLE_USER");
        Set<Role> adminSet = new HashSet<>();
        Set<Role> userSet = new HashSet<>();
        roleService.addRole(adminRole);
        roleService.addRole(userRole);
        adminSet.add(adminRole);
        adminSet.add(userRole);
        userSet.add(userRole);

        User admin = new User("admin", "admin",
                "+7-910-496-93-45", "admin", adminSet);
        admin.setId(1);

        User user = new User("user", "user",
                "+7-234-567-45-32", "user", userSet);

        user.setId(2);

        userService.saveUser(admin);
        userService.saveUser(user);

    }
}
