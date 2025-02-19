package com.hcring.chap03_method_and_api.level01.basic;

public class Application {
    public static void main(String[] args) {
        Calculator.checkMethod();
        System.out.println("1부터 10까지의 합 : " + Calculator.sum1to10());
        Calculator.checkMaxNumber(10, 20);
        System.out.println("10과 20의 합은 : " + Calculator.sumTwoNumber(10, 20));
        Calculator.minusTwoNumber(10, 5);
    }
}
