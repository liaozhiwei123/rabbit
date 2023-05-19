package com.example.springbootrabbit.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class DeadConsumer {

    @RabbitListener(queues={"QD"})
    public void dele(Message message, Channel channel){
        String msg = new String(message.getBody());
        log.info("消息时间{},消费信息{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),msg);

    }
}
