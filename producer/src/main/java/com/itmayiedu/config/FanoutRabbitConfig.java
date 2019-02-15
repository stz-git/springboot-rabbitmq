package com.itmayiedu.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class FanoutRabbitConfig {

    private static final String A_QUEUE = "a_queue";
    private static final String B_QUEUE = "b_queue";
    private static final String EXCHANGE_NAME = "fanout_exchange";

    //dead letter exchange
    private static final String DEAD_LETTER_EXCHANGE = "dead_letter_exchange";
    private static final String DEAD_LETTER_QUEUE_1 = "dead_letter_queue_1";
    private static final String DEAD_LETTER_QUEUE_2 = "dead_letter_queue_2";

    private static final String BINDING_DEAD_EXCHANGE = "x-dead-letter-exchange";


    @Bean
    public Queue a_queue() {
        Map<String, Object> args = new HashMap<>();
        args.put(BINDING_DEAD_EXCHANGE, DEAD_LETTER_EXCHANGE);
        return new Queue(A_QUEUE, true, false, false, args);
    }

    @Bean
    public Queue b_queue() {
        return new Queue(B_QUEUE);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding bindingExchangeAQueue() {
        return BindingBuilder.bind(a_queue()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeBQueue() {
        return BindingBuilder.bind(b_queue()).to(fanoutExchange());
    }

    @Bean
    public Queue deadQueue1() {
        return new Queue(DEAD_LETTER_QUEUE_1, true);
    }

    @Bean
    public Queue deadQueue2() {
        return new Queue(DEAD_LETTER_QUEUE_2, true);
    }

    @Bean
    public FanoutExchange deadFanoutExchange() {
        return new FanoutExchange(DEAD_LETTER_EXCHANGE);
    }

    @Bean
    public Binding bindingQueue2DeadExchange1() {
        return BindingBuilder.bind(deadQueue1()).to(deadFanoutExchange());
    }

    @Bean
    public Binding bindingQueue2DeadExchange2() {
        return BindingBuilder.bind(deadQueue2()).to(deadFanoutExchange());
    }


}
