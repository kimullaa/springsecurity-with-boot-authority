package com.example.controllers;

import com.example.model.Permission;
import com.example.model.Role;
import com.example.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class AuthController {
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("self")
    public User xx(@AuthenticationPrincipal User user) {
        return user;
    }

    @PutMapping("roles/{id}")
    public Role putRole(@PathVariable Long id, @RequestParam("permission") List<Long> permissions) {
        List<Permission> perms = permissions.stream()
                .map(perm -> new Permission(perm))
                .collect(Collectors.toList());

        Role role = roleRepository.findOne(id);
        role.setPermissions(perms);
        return roleRepository.save(role);
    }
}
