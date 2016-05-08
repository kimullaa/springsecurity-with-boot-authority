package com.example;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pbreh_000 on 2016/05/07.
 */
@Controller
@RequestMapping("auth")
public class AuthController {
    @RequestMapping("api")
    public void xx(@AuthenticationPrincipal User user) {
        System.out.println(user);

    }
}
