package com.example.springbootrabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DelayConfig {
    //延迟队列
    public final static String DELAY_QUEUE ="delayed.queue";
    //延迟交换机
    public final static String DELAY_EXCHANGE = "delayed.exchange";
    //延迟routing key
    public final static String DELAY_ROUTINGKEY = "delayed.routingkey";

    @Bean("delayExchange")
    public CustomExchange customExchange(){
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-delay-type","direct");
        //String name, String type, boolean durable, boolean autoDelete, Map<String, Object> arguments
        return new CustomExchange(DELAY_EXCHANGE,"x-delayed-message",true,false,arguments);
    }
    @Bean("delayQueue")
    public Queue delayQueue(){
        return QueueBuilder.durable(DELAY_QUEUE).build();
    }
    @Bean
    public Binding delayExchangeBindingQueue(@Qualifier("delayQueue") Queue queue,@Qualifier("delayExchange") CustomExchange customExchange){
        return BindingBuilder.bind(queue).to(customExchange).with(DELAY_ROUTINGKEY).noargs();
    }

}
