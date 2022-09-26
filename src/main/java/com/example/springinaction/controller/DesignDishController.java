package com.example.springinaction.controller;

import com.example.springinaction.model.Dish;
import com.example.springinaction.model.Ingredient;
import com.example.springinaction.model.Ingredient.Type;
import com.example.springinaction.model.Order;
import com.example.springinaction.repository.IngredientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignDishController {

    private final IngredientRepository ingredientRepository;

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "order")
    public Order addOrderToModel() {
        return new Order();
    }

    @ModelAttribute(name = "dish")
    public Dish addDishToModel() {
        return new Dish();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String addDishToOrder(@Valid Dish dish,
                                 Errors errors,
                                 @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }
        order.addDish(dish);
        log.info("Processing dish: {}", dish);
        //System.out.println(dish);
        return "redirect:orders/current";
    }

    private Iterable<Ingredient> filterByType(
            Iterable<Ingredient> ingredients, Type type) {
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


}
