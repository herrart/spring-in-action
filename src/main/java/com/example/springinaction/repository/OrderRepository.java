package com.example.springinaction.repository;

import com.example.springinaction.model.Order;

public interface OrderRepository {
    Order save(Order order);
}
