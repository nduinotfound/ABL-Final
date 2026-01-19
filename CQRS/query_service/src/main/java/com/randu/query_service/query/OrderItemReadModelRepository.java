package com.randu.query_service.query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface OrderItemReadModelRepository extends JpaRepository<OrderItemReadModel, Long> {
    List<OrderItemReadModel> findByOrderId(String orderId);
    void deleteByOrderId(String orderId);
}