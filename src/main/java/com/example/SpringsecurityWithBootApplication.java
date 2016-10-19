package com.example;

import com.example.model.Permission;
import com.example.model.Role;
import com.example.model.User;
import com.example.repositories.PermissionRepository;
import com.example.repositories.RoleRepository;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SpringsecurityWithBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecurityWithBootApplication.class, args);
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    RoleRepository roleRepository;

    @Bean
    public CommandLineRunner execute() {
        return args -> {
            Permission showAll = new Permission("SHOW_ALL");
            Permission createUser = new Permission("CREATE_USER");
            Permission showUserPage = new Permission("SHOW_USER_PAGE");
            Permission showAdminPage = new Permission("SHOW_ADMIN_PAGE");

            Role adminRole = new Role("管理者", Arrays.asList(showAll, createUser, showUserPage, showAdminPage));
            Role popularRole = new Role("一般", Arrays.asList(showUserPage));

            User user = new User("user", "password", Arrays.asList(popularRole));
            User admin = new User("admin", "password", Arrays.asList(adminRole));

            permissionRepository.save(Arrays.asList(showAll, createUser, showUserPage, showAdminPage));
            roleRepository.save(Arrays.asList(adminRole, popularRole));

            userRepository.save(Arrays.asList(user, admin));

        };

    }
}
