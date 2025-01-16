package com.myapp.redis;

import com.myapp.redis.dto.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RedisCacheController {

    private final UserProfileService userProfileService;

    @GetMapping("/users/{id}/profile")
    public UserProfile getUser(@PathVariable(name = "id") Long id) {
        return userProfileService.getUser(id);
    }
}
