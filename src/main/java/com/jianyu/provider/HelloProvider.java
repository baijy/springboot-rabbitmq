package com.jianyu.provider;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HelloProvider {

	private Logger log = Logger.getLogger(HelloProvider.class);

	@Value("${my.topic.exchange}")
	private String topicExchange;
	
	@Value("${my.fanout.exchange}")
	private String fanoutExchange;
	
	@Value("${my.first.queue}")
	private String firstQueue;

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		// String context = "hello " + new Date();
		// log.info("Provider send message : " + context);
		
		// Direct模式
		// firstQueue会收到
		rabbitTemplate.convertAndSend(firstQueue,"a direct message");
		
		// Topic模式
		// secondQueue会收到
		rabbitTemplate.convertAndSend(topicExchange, "changsha.food.tomorrow", "a topic msg : changsha.food.tomorrow");
		rabbitTemplate.convertAndSend(topicExchange, "yongzhou.food.today", "a topic msg : yongzhou.food.today");

		// Fanout模式
		// thirdQueue会收到
		rabbitTemplate.convertAndSend(fanoutExchange, "", "a fanout message");
	}

}
