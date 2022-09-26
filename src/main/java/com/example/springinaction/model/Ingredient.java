package com.example.springinaction.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@Getter
@Table

public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private final String name;
    @Enumerated(EnumType.STRING)
    @org.hibernate.annotations.Type(type = "com.example.springinaction.repository.EnumTypePostgreSql")
    private Type type;

    public enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}
