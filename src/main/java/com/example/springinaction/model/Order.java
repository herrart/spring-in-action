package com.example.springinaction.model;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Date placedAt;

    @NotBlank(message = "Поле не должно быть пустым")
    private String deliveryName;
    @NotBlank(message = "Поле не должно быть пустым")
    private String deliveryStreet;
    @NotBlank(message = "Поле не должно быть пустым")
    private String deliveryCity;
    @NotBlank(message = "Поле не должно быть пустым")
    private String deliveryState;
    @NotBlank(message = "Поле не должно быть пустым")
    private String deliveryZip;
    @CreditCardNumber(message = "Некорректный номер банковской карты")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
            message = "Срок действия карты в формате ММ/ГГ")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Некорректный CVV")
    private String ccCVV;

    private List<Dish> dishes = new ArrayList<>();

    public void addDish(Dish dish) {
        dishes.add(dish);
    }
}
