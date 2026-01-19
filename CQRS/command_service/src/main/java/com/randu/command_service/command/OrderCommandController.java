package com.randu.command_service.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderCommandController {
    
    @Autowired
    private OrderCommandService orderCommandService;
    
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderCommand command) {
        String orderId = orderCommandService.createOrder(command);
        return ResponseEntity.ok(orderId);
    }
    
    @PutMapping("/{orderId}/status")
    public ResponseEntity<Void> updateOrderStatus(
            @PathVariable String orderId,
            @RequestBody UpdateOrderStatusCommand command) {
        orderCommandService.updateOrderStatus(orderId, command);
        return ResponseEntity.ok().build();
    }
}