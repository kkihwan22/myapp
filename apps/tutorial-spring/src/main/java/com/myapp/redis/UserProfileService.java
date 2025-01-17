package com.myapp.redis;

import com.myapp.redis.dto.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class UserProfileService {

    private final ExternalUserService externalUserService;
    private final StringRedisTemplate redisTemplate;

    public UserProfile getUser(Long id) {
        String userName = this.getUserName(id);
        int age = externalUserService.getUserAge(id);
        return new UserProfile(id, userName, age);
    }

    private String getUserName(Long id) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String key = "user-name:" + id;
        String cachedName = ops.get(key);

        if (cachedName != null) return cachedName;

        String name = externalUserService.getUserName(id);
        ops.set(key, name, 10, TimeUnit.SECONDS);
        return name;
    }
}
