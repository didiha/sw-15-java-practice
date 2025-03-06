package com.hcring.javaprograming.level01.basic;

public class Calculator {
    public static void checkMethod() {
        System.out.println("메소드 호출 확인");
    }

    public static int sum1to10() {
        int sum = 55;
        return sum;
    }

    public static void checkMaxNumber(int a, int b) {
        System.out.println("두 수 중 큰 수는 " + Math.max(a, b) + "이다.");
    }

    public static int sumTwoNumber(int a, int b) {
        return a + b;
    }

    public static void minusTwoNumber(int a, int b) {
        System.out.println("10과 5의 차는 : " + (a - b));
    }
}