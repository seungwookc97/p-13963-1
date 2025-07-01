package com.back;

import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {

    private final int version;

    public PersonRepository() {
        this.version = 1;
    }

    public long count() {
        System.out.println("PersonRepository(v%d).count() 작동".formatted(version));
        return 3;
    }
}