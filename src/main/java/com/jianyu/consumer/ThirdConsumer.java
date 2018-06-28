package com.jianyu.consumer;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "${my.third.queue}")
public class ThirdConsumer {
	private Logger log = Logger.getLogger(ThirdConsumer.class);

    @RabbitHandler
    public void process(String msg) {
    	log.info("Third queue received message: " + msg);
    }
 
}