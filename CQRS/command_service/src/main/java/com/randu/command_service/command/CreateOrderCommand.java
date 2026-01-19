package com.randu.command_service.command;

import java.math.BigDecimal;
import java.util.List;

public class CreateOrderCommand {
    private String customerId;
    private BigDecimal totalAmount;
    private List<OrderItem> items;
    public CreateOrderCommand() {
    }
    public CreateOrderCommand(String customerId, BigDecimal totalAmount, List<OrderItem> items) {
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.items = items;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public List<OrderItem> getItems() {
        return items;
    }
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    
    // getters and setters
    
}