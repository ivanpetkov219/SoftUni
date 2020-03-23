package com.spand0x.cardealer.services;

import com.spand0x.cardealer.models.dtos.SaleCustomerCarDiscountDto;

import java.util.List;

public interface SaleService {
    void seedSales();

    List<SaleCustomerCarDiscountDto> getSalesWithDiscount();
}
