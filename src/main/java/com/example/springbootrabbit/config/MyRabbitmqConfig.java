package com.example.springbootrabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq的配置类
 */
/*@Configuration
public class MyRabbitmqConfig {
    private final static String exchange_name ="boot_topic_exchange";
    private final static String queue_name = "boot_queue_name";
    //获取交换机实例
    @Bean("bootExchange")
    public Exchange getExchange(){
        return ExchangeBuilder.topicExchange(exchange_name).durable(true).build();
    }
    @Bean("bootQueue")
    public Queue getMessageQueue(){
        return new Queue(queue_name);
    }
    //绑定
    @Bean
    public Binding bindingMessageQueue(@Qualifier("bootExchange") Exchange exchange,@Qualifier("bootQueue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("#.message.#").noargs();
    }
}*/
