package com.myapp.redis;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisSessionController {

    @GetMapping("/login")
    public String login(HttpSession session, @RequestParam String name) {
        session.setAttribute("name", name);
        return "success!";
    }

    @GetMapping("/myName")
    public String getMyName(HttpSession session ) {
        return (String) session.getAttribute("name");
    }
}
