package com.myapp.redis;

import com.myapp.redis.dto.UserProfile;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RedisCacheController {
    private final static Logger LOG = LoggerFactory.getLogger(RedisCacheController.class);

    private final UserProfileService userProfileService;
    private final RankingService rankingService;

    @GetMapping("/users/{id}/profile")
    public UserProfile getUser(@PathVariable(name = "id") Long id) {
        return userProfileService.getUser(id);
    }

    @PutMapping("/users/{userId}/score")
    public Boolean putScore(@PathVariable String userId, @RequestBody UserProfile profile) {
        return rankingService.serUserScore(profile.getName(), profile.getAge());
    }

    @GetMapping("/users/{userId}/rank")
    public Long getRank(@PathVariable String userId) {
        return rankingService.getUserRanking(userId);
    }

    @GetMapping("/top-rank")
    public List<String> getTopRanks() {
        return rankingService.getTopRank(10);
    }
}
