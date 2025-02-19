package com.hcring.chap03_method_and_api.level02.normal;

public class Application {
    public static void main(String[] args) {
        System.out.println(RandomMarker.randomNumber(7, 14));
        System.out.println(RandomMarker.randomUpperAlphabet(7));
        System.out.println(RandomMarker.rockPaperScissors());
        RandomMarker.tossCoin();
    }
}
