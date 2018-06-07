package com.example.controllers;

import com.example.model.Permission;
import com.example.model.Role;
import com.example.repositories.PermissionRepository;
import com.example.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequestMapping("roles")
@Controller
@AllArgsConstructor
public class RoleController {
    private PermissionRepository permissionRepository;
    private RoleRepository roleRepository;

    @GetMapping
    public String roles(Model model) {
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

        return "roles";
    }

    @PutMapping("{id}")
    @ResponseBody
    public Role putRole(@PathVariable Long id, @RequestParam("permissions") List<Long> permissions) {
        List<Permission> perms = permissions.stream()
                .map(perm -> new Permission(perm))
                .collect(Collectors.toList());

        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("oops"));
        role.setPermissions(perms);
        return roleRepository.save(role);
    }
}
