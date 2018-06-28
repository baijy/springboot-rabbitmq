package com.jianyu.consumer;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "${my.first.queue}")
public class FirstConsumer {
	private Logger log = Logger.getLogger(FirstConsumer.class);

    @RabbitHandler
    public void process(String msg) {
    	log.info("First queue received message: " + msg);
    }
 
}

