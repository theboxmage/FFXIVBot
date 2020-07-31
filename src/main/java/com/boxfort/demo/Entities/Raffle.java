package com.boxfort.demo.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Raffle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    public String title;
    @Column(unique = true)
    private Long messageId;
    private LocalDateTime startDateTime = LocalDateTime.now();
    private LocalDateTime endDateTime;

    private String author;
    private Long winner;
    private Long authorId;
}
