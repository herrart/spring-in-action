package com.example.springinaction.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@Getter
@Setter
@ToString
@Table(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIME)
    private final Date placedAt;

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
    @OneToMany(cascade = CascadeType.ALL)
    private List<Dish> dishes = new ArrayList<>();

    public void addDish(Dish dish) {
        dishes.add(dish);
    }
}
