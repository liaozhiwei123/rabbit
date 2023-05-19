package com.example.springbootrabbit.consumer;

import com.example.springbootrabbit.config.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class DelayQueueConsumer {

    @RabbitListener(queues ={DelayConfig.DELAY_QUEUE})
    public void receiMessage(Message message){
        log.info("时间{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        String msg = new String(message.getBody(),StandardCharsets.UTF_8);
        System.out.println(msg);
    }


}
