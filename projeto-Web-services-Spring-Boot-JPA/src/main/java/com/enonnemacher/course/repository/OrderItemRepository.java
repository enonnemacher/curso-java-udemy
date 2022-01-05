package com.enonnemacher.course.repository;

import com.enonnemacher.course.entities.Order;
import com.enonnemacher.course.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
