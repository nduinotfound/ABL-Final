package com.randu.query_service.query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderReadModelRepository extends JpaRepository<OrderReadModel, String> {
    List<OrderReadModel> findByCustomerId(String customerId);
    List<OrderReadModel> findByStatus(String status);
}