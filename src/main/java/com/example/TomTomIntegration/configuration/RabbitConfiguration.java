package com.example.TomTomIntegration.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Value("${rabbitmq.username}")
    private String username;

    @Value("${rabbitmq.password}")
    private String password;

    @Value("${rabbitmq.vhost}")
    private String virtualHost;

    @Value("${rabbitmq.hostname}")
    private String hostname;

    @Value("${poi.logs.queue}")
    private String poiLogsQueueName;

    @Value("${poi.logs.exchange}")
    private String poiLogsExchangeName;

    @Value("${poi.logs.routing-key}")
    private String poiLogsRoutingKey;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(hostname);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        cachingConnectionFactory.setVirtualHost(virtualHost);
        return cachingConnectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue poiLogsQueue() {
        return new Queue(poiLogsQueueName);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(poiLogsExchangeName, true, false);
    }

    @Bean
    public Binding poiLogsBinding() {
        return BindingBuilder.bind(poiLogsQueue()).to(exchange()).with(poiLogsRoutingKey);
    }
}
