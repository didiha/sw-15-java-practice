package com.hcring.javaprograming.level02.normal;

import java.util.Random;

public class RandomMarker {
    static Random rand = new Random();

    public static int randomNumber(int min, int max){
        int result = rand.nextInt((max - min) + min) + (max - min);
        return result;
    }

    public static String randomUpperAlphabet(int length){
        String str1 = "";
        for(int i = 0; i < length; i++){
            char ch = (char)(rand.nextInt(26) + 'A');
            str1 += ch;
        }
        return str1;
    }

    public static String rockPaperScissors(){
        int num = rand.nextInt(3);
        String str2 = "";
        return (num == 0 ? (str2 = "rock") : (num == 1 ? (str2 = "paper") : (str2 = "scissors")));
    }

    public static void tossCoin(){
        int num2 = rand.nextInt(2);
        String str3 = "";
        str3 = num2 == 0 ? "앞면" : "뒷면";
        System.out.println(str3);
    }
}
