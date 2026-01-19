package com.randu.query_service.query;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration 
@EnableRabbit 
public class RabbitMQEventConfig {
    
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
    
    @Bean
    public TopicExchange orderEventsExchange() {
        return new TopicExchange("order-events-exchange", true, false);
    }
    
    @Bean
    public Queue orderEventsQueue() {
        return new Queue("order-events-queue", true);
    }
    
    @Bean
    public Binding  orderEventsBinding() {
        return BindingBuilder.bind(orderEventsQueue())
                .to(orderEventsExchange())
                .with("order.event.*");
    }
}