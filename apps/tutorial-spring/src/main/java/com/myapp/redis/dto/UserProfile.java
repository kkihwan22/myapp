package com.myapp.redis.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserProfile {
    private Long id;
    private String name;
    private int age;

    public UserProfile(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
