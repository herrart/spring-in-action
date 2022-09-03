package com.example.springinaction.repository;

import com.example.springinaction.model.Ingredient;
import java.util.Optional;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(Long id);
    Ingredient save(Ingredient ingredient);
}
