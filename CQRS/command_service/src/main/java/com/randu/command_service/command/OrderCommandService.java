package com.randu.command_service.command;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderCommandService {
    
    @Autowired
    private EventStoreRepository eventStoreRepository;
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    public String createOrder(CreateOrderCommand command) {
        String orderId = UUID.randomUUID().toString();
        
        // Create event
        OrderCreatedEvent event = new OrderCreatedEvent(
            orderId, 
            command.getCustomerId(),
            command.getTotalAmount(),
            command.getItems()
        );
        
        // Store event
        saveEvent(event);
        
        // Publish event
        publishEvent(event);
        
        return orderId;
    }
    
    public void updateOrderStatus(String orderId, UpdateOrderStatusCommand command) {
        // Create event
        OrderStatusUpdatedEvent event = new OrderStatusUpdatedEvent(
            orderId,
            command.getStatus(),
            command.getReason()
        );
        
        // Store event
        saveEvent(event);
        
        // Publish event
        publishEvent(event);
    }
    
    private void saveEvent(OrderEvent event) {
        try {
            EventStore eventStore = new EventStore();
            eventStore.setEventId(event.getEventId());
            eventStore.setOrderId(event.getOrderId());
            eventStore.setEventType(event.getEventType());
            eventStore.setTimestamp(event.getTimestamp());
            eventStore.setEventData(objectMapper.writeValueAsString(event));
            
            eventStoreRepository.save(eventStore);
        } catch (Exception e) {
            throw new RuntimeException("Error saving event", e);
        }
    }
    
    private void publishEvent(OrderEvent event) {
        try {
            String eventJson = objectMapper.writeValueAsString(event);
            rabbitTemplate.convertAndSend("order-events-exchange", 
                                        "order.event." + event.getEventType().toLowerCase(),
                                        eventJson);
        } catch (Exception e) {
            throw new RuntimeException("Error publishing event", e);
        }
    }
}