package com.example.controllers;

import com.example.model.Permission;
import com.example.model.Role;
import com.example.repositories.PermissionRepository;
import com.example.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequestMapping("admin")
@Controller
@AllArgsConstructor
public class AdminController {
    private PermissionRepository permissionRepository;
    private RoleRepository roleRepository;

    @GetMapping
    public String admin(Model model) {
        model.addAttribute("permissions",
                StreamSupport.stream(permissionRepository.findAll().spliterator(), false)
                        .collect(Collectors.toMap(
                                Permission::getId,
                                Permission::getName
                        )));

        model.addAttribute("roles",
                StreamSupport.stream(roleRepository.findAll().spliterator(), false)
                        .collect(Collectors.toMap(
                                Role::getId,
                                Role::getName
                        )));

        return "admin-page";
    }
}
