package ru.alwertus.zerospring.delme;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@RequiredArgsConstructor

@Entity
public class Book {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String accessRole;

    @Getter @Setter
    private Long ownerId;
}
