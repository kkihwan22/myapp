package com.myapp;

import com.myapp.redis.RankingService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class RankingPerformanceTests {
    private static final Logger LOG = LoggerFactory.getLogger(RankingPerformanceTests.class);

    @Autowired
    private RankingService rankingService;


    @Test
    void inMemorySortPerformance() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            int score = (int)(Math.random() * 1000000);
            list.add(score);
        }

        Instant before = Instant.now();
        Collections.sort(list);  // n log n
        Duration elapsed = Duration.between(before, Instant.now());
        LOG.info("running : {} ms.", elapsed.getNano()/1000000 );
    }

    @Test
    void insertScore() {
        for (int i = 0; i < 1000000; i++) {
            int score = (int)(Math.random() * 1000000);
            String userId = "user_"+i;
            rankingService.serUserScore(userId, score);
        }
    }

    @Test
    void getRankingTest() {
        Instant before = Instant.now();
        Duration elapsed = Duration.between(before, Instant.now());
        Long ranking = rankingService.getUserRanking("user_100");
        LOG.info("ranking : {}, running time : {} ms.", ranking, elapsed.getNano()/1000000 );
    }

    @Test
    void topRankTest() {
        Instant before = Instant.now();
        Duration elapsed = Duration.between(before, Instant.now());
        List<String> ranking = rankingService.getTopRank(10);
        LOG.info("ranking list : {}, running time : {} ms.", ranking, elapsed.getNano()/1000000 );
    }
}
