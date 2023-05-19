package com.example.springbootrabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TtlQueueConfig {
    //普通交换机名称
    private final String X_EXCHANGE="X";
    //死信交换机名称
    private final String Y_EXCHAGE="Y";
    //普通队列名称
    private final String QUEUEA = "QA";
    private final String QUEUEB = "QB";
    private final String QUEUEC = "QC";
    //死信队列名称
    private final String QUEUED = "QD";

    @Bean("queueC")
    public Queue queueC(){
        return QueueBuilder.durable(QUEUEC).deadLetterRoutingKey("YD").deadLetterExchange(Y_EXCHAGE).build();
    }
    @Bean("queuCBbindingX")
    public Binding queuCBbindingX(@Qualifier("queueC") Queue queue,@Qualifier("xExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(xExchange()).with("XC");
    }

    @Bean("xExchange")
    public DirectExchange xExchange(){
        return new DirectExchange(X_EXCHANGE);
    }
    @Bean("yExchange")
    public DirectExchange yExchange(){
        return new DirectExchange(Y_EXCHAGE);
    }

    @Bean("queueA")
    public Queue queueA(){
        return QueueBuilder.durable(QUEUEA).deadLetterExchange(Y_EXCHAGE).deadLetterRoutingKey("YD").ttl(10000).build();
    }

    @Bean("queueB")
    public Queue queueB(){
        return QueueBuilder.durable(QUEUEB).deadLetterExchange(Y_EXCHAGE).deadLetterRoutingKey("YD").ttl(40000).build();
    }
    @Bean("queueD")
    public Queue queueD(){
        return QueueBuilder.durable(QUEUED).build();
    }
    @Bean("queueAbindingX")
    public Binding queueAbindingX(@Qualifier("queueA") Queue queueA,@Qualifier("xExchange") DirectExchange exchange){
        return BindingBuilder.bind(queueA).to(exchange).with("XA");
    }
    @Bean("queueBbindingX")
    public Binding queueBbindingX(@Qualifier("queueB") Queue queueB,@Qualifier("xExchange") DirectExchange exchange){
        return BindingBuilder.bind(queueB).to(exchange).with("XB");
    }
    @Bean("queueDbindingY")
    public Binding queueDbindingY(@Qualifier("queueD") Queue queueD,@Qualifier("yExchange") DirectExchange exchange){
        return BindingBuilder.bind(queueD).to(exchange).with("YD");
    }
}
