package com.myapp.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ExternalUserService {
    private final static Logger LOG = LoggerFactory.getLogger(ExternalUserService.class);

    public String getUserName(Long id) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}

        LOG.info("Getting user name from other service...");

        return id == 1 ? "Adam" : "Bab";
    }

    public int getUserAge(Long id) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}

        LOG.info("Getting user age from other service...");

        return id == 1 ? 20 : 32;
    }
}
