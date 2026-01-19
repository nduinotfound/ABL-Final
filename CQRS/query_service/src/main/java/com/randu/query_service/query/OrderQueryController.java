package com.randu.query_service.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderQueryController {
    
    @Autowired
    private OrderReadModelRepository orderReadModelRepository;
    
    @GetMapping
    public List<OrderReadModel> getAllOrders() {
        return orderReadModelRepository.findAll();
    }
    
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderReadModel> getOrder(@PathVariable String orderId) {
        return orderReadModelRepository.findById(orderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/customer/{customerId}")
    public List<OrderReadModel> getOrdersByCustomer(@PathVariable String customerId) {
        return orderReadModelRepository.findByCustomerId(customerId);
    }
    
    @GetMapping("/status/{status}")
    public List<OrderReadModel> getOrdersByStatus(@PathVariable String status) {
        return orderReadModelRepository.findByStatus(status);
    }
}