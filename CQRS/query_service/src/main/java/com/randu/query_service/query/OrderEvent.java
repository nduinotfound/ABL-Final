package com.randu.query_service.query;

import java.time.LocalDateTime;


public abstract class OrderEvent {
    protected String eventId;
    protected String orderId;
    protected LocalDateTime timestamp;
    protected String eventType;
    
    // Default constructor untuk Jackson
    public OrderEvent() {}
    
    // getters dan setters
    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }
    
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    
    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
}