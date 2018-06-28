package com.jianyu.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置类
 * 
 * @author jianyu.bai
 *
 */
@Configuration
public class RabbitConfig {

	@Value("${my.first.queue}")
	private String firstQueue;

	@Value("${my.second.queue}")
	private String secondQueue;
	
	@Value("${my.third.queue}")
	private String thirdQueue;

	@Value("${my.topic.exchange}")
	private String topicExchange;

	@Value("${my.fanout.exchange}")
	private String fanoutExchange;

	@Bean(name = "firstQueue")
	public Queue getFirstQueue() {
		return new Queue(firstQueue);
	}

	@Bean(name = "secondQueue")
	public Queue getSecondQueue() {
		return new Queue(secondQueue);
	}
	
	@Bean(name = "thirdQueue")
	public Queue getThirdQueue() {
		return new Queue(thirdQueue);
	}

	@Bean(name = "topicExchange")
	public TopicExchange getTopicExchange() {
		return new TopicExchange(topicExchange);
	}
	
    @Bean(name = "fanoutExchange")
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchange);
    }
	
	@Bean
	Binding bindingSecond(@Qualifier("secondQueue") Queue queue, @Qualifier("topicExchange")TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("#.food.#");
	}
	
    @Bean
    Binding bindingThird(@Qualifier("thirdQueue") Queue queue, @Qualifier("fanoutExchange")FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

}
