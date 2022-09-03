package com.example.springinaction.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Dish {
    private Long id;
    private Date createdAt = new Date();

    @NotNull
    @Size(min = 3, message = "Название должно быть не менее 3 символов")
    private String name;

    @NotNull
    @Size(min = 1, message = "Выбирите хотя бы один ингредиент")
    private List<Ingredient> ingredients;
}
