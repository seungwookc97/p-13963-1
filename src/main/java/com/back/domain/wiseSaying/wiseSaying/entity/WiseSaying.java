package com.back.domain.wiseSaying.wiseSaying.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class WiseSaying {
    @Id
    @GeneratedValue(strategy = IDENTITY) // AUTO_INCREMENT
    private int id;
    private String author;
    private String content;

    public WiseSaying( String content, String author) {
        this.content = content;
        this.author = author;
    }

    public void modify(String content, String author) {
        this.content = content;
        this.author = author;
    }


    public boolean isNew() {
        return this.id == 0;
    }
}
