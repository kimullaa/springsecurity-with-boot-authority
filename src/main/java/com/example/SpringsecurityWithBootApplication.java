package com.example;

import com.example.model.Permission;
import com.example.model.Role;
import com.example.model.User;
import com.example.repositories.PermissionRepository;
import com.example.repositories.RoleRepository;
import com.example.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringsecurityWithBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecurityWithBootApplication.class, args);
    }

    private final UserRepository userRepository;
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Bean
    public CommandLineRunner execute() {
        return args -> {
            Permission changeRole = new Permission("CHANGE_ROLE");
            Permission showAllUserPage = new Permission("SHOW_ALL_USER");

            Role adminRole = new Role("管理者", Arrays.asList(showAllUserPage, changeRole));
            Role userRole = new Role("一般", Arrays.asList(showAllUserPage));

            User user = new User("user", encoder.encode("password"), Arrays.asList(userRole));
            User admin = new User("admin", encoder.encode("password"), Arrays.asList(adminRole));

            permissionRepository.saveAll(Arrays.asList(changeRole, showAllUserPage));
            roleRepository.saveAll(Arrays.asList(adminRole, userRole));
            userRepository.saveAll(Arrays.asList(user, admin));

        };

    }
}
