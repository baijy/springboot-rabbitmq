package com.jianyu.consumer;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "${my.second.queue}")
public class SecondConsumer {
	private Logger log = Logger.getLogger(SecondConsumer.class);

    @RabbitHandler
    public void process(String msg) {
    	log.info("Second queue received message: " + msg);
    }
 
}

