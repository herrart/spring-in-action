package com.example.springinaction.repository;

import com.example.springinaction.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
