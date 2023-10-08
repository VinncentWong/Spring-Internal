package com.example.springboot;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

// dont forget to register the listener into a Bean
@Component
public class CheckoutEventListener {

    @EventListener(CheckoutEvent.class)
    public void getCheckoutData(CheckoutEvent event){
        System.out.println("get event " + event);
    }
}
