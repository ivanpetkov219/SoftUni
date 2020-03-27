package com.softuni.xmlprocessingexercise2.utils.enums;



public enum Discount {
    ZERO(0),FIVE(5),
    TEN(10),
    FIFTEEN(15),
    TWENTY(20),
    THIRTY(30),
    FORTY(40),
    FIFTY(50);

    private int value;

    Discount(int value) {
        this.value = value;
    }

    Discount() {
    }

    public int getValue() {
        return value;
    }
}
