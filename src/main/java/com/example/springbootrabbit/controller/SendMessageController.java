package com.example.springbootrabbit.controller;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/message")
public class SendMessageController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMessage/{msg}")
    public String sendMessage(@PathVariable("msg") String msg) {
        //""
        log.info("开始时间{},发送信息{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), msg);
        rabbitTemplate.convertAndSend("X", "XA", "消息来自10s队列" + msg);
        rabbitTemplate.convertAndSend("X", "XB", "消息来自40s队列" + msg);
        return msg;
    }
    @GetMapping("/sendMessage/{msg}/{ttlTime}")
    public String sendTlMessage(@PathVariable("msg") String msg,@PathVariable("ttlTime") String ttlTime) {
        //""
        log.info("开始时间{},发送信息{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), msg);
        rabbitTemplate.convertAndSend("X", "XC", "发送者控制超时时间" + msg, message ->{
            message.getMessageProperties().setExpiration(ttlTime);
            return message;
        });
        return msg;
    }
    @GetMapping("/sendDelayMessage/{msg}/{delayTime}")
    @Before(value = "execution(com.atguigu.spring5.aopanno.User.add(..))")
    public String sendDelayMessage(@PathVariable("msg") String msg,@PathVariable("delayTime") Integer delayTime) {
        //""
        log.info("开始时间{},发送信息{}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), msg);
        rabbitTemplate.convertAndSend("X", "XC", "发送者控制超时时间" + msg, message ->{
            //单位毫秒
            message.getMessageProperties().setDelay(delayTime);
            return message;
        });
        return msg;
    }

    public static void main(String[] args) {
        int i  =  1;
        String str = "hello";
        Integer num = 200;
        int[] arr = {1,2,3,4,5};
        MyData myData = new MyData();

        change(i,str,num,arr,myData);

        System.out.println("i ="+i);
        System.out.println("str ="+str);
        System.out.println("num = "+num);
        System.out.println("my.a =" + myData.a);
    }
    public static void change(int j,String s,Integer n,int[] a,MyData m){
     j +=1;
     s += "word";
     n +=1;
     a[0] +=1;
     m.a += 1;
    }

}
class MyData{
    int a = 10;
}