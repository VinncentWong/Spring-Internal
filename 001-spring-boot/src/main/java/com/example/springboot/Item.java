package com.example.springboot;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class Item {
    public String namaItem;
    public LocalDate tanggalCheckout;
}
