package com.enonnemacher.course.service;

import com.enonnemacher.course.entities.Order;
import com.enonnemacher.course.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        Optional<Order> result = orderRepository.findById(id);
        return result.get();
    }
}
