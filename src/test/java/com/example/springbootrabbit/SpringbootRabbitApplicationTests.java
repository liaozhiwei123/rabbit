package com.example.springbootrabbit;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class SpringbootRabbitApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    private final static String exchange_name ="boot_topic_exchange";
    private final static String queue_name = "boot_queue_name";

    @Test
    public void testSendMessage(){
        rabbitTemplate.convertAndSend(exchange_name,"message","测试开始了");
    }

    public static void main(String[] args) {

        String day = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println(day);
        System.out.println("dev");
    }



}


