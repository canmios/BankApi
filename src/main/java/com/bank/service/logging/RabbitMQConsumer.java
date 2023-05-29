package com.bank.service.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);
    /*
    @RabbitListener(queues = {"${bank.rabbitmq.queue}"})
    public void consume(String message){
        LOGGER.info(String.format("Received message -> %s", message));
    }

     */
}
