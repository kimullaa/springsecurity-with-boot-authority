package com.example.services;

import com.example.model.User;
import com.example.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User target = userRepository.findOneByName(username);
        List<SimpleGrantedAuthority> authorities = target.getRoles().stream()
                .flatMap(i -> i.getPermissions().stream())
                .map(i -> new SimpleGrantedAuthority(i.getName()))
                .collect(Collectors.toList());
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(target.getName(), target.getPassword(), authorities);
        return user;
    }
}
