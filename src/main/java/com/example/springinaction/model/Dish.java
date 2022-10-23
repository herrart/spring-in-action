package com.example.springinaction.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@Getter
@Setter
@ToString
@Table
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIME)
    private final Date createdAt;

    @NotNull
    @Size(min = 3, message = "Название должно быть не менее 3 символов")
    private String name;
    @NotNull
    @Size(min = 1, message = "Выберите хотя бы один ингредиент")
    @ManyToMany
    private List<Ingredient> ingredients = new java.util.ArrayList<>();

}
