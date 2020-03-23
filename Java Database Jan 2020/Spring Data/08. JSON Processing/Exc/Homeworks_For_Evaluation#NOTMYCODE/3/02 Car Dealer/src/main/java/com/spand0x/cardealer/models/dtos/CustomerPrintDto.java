package com.spand0x.cardealer.models.dtos;

import com.google.gson.annotations.Expose;

import java.time.LocalDate;
import java.util.List;

public class CustomerPrintDto {
    @Expose
    private long id;
    @Expose
    private String name;
    @Expose
    private String birthDay;
    @Expose
    private boolean isYoungDriver;
    @Expose
    private List<SaleDto> sales;

    public CustomerPrintDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public List<SaleDto> getSales() {
        return sales;
    }

    public void setSales(List<SaleDto> sales) {
        this.sales = sales;
    }
}
