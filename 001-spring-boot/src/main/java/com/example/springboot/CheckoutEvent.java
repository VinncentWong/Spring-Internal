package com.example.springboot;

import lombok.*;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDate;

// make a Event Class to support event based communication between beans
@Setter
@Getter
@Builder
@ToString
public class CheckoutEvent extends ApplicationEvent {

    private String namaItem;

    private LocalDate tanggalCheckout;

    public CheckoutEvent(String namaItem, LocalDate tanggalCheckout) {
        super(
                Item.builder()
                        .namaItem(namaItem)
                        .tanggalCheckout(tanggalCheckout)
                        .build()
        );
        this.namaItem = namaItem;
        this.tanggalCheckout = tanggalCheckout;
    }
}
