package com.example.springinaction.controller;

import com.example.springinaction.model.Order;
import com.example.springinaction.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")

public class OrderController {
    private final OrderRepository orderRepository;

    @GetMapping
    public String redirectToOrderForm() {
        return "orderForm";
    }

    @GetMapping("current")
    public String showOrderForm(Order order) {
        if (order.getDishes() == null) return "redirect:/design";
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order,
                               Errors errors,
                               SessionStatus sessionStatus) {
        if(errors.hasErrors()) {
            return "orderForm";
        }
        log.info("Order submitted: {}", order);
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

}
