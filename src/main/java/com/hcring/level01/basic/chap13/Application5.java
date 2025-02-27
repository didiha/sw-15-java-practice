package com.hcring.level01.basic.chap13;

import java.util.Scanner;
import java.util.TreeSet;

public class Application5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeSet<String> dictionary = new TreeSet<>();
        String input = "";

        label:
        while (true) {
            System.out.print("단어 입력 ('exit' 입력 시 종료): ");
            input = sc.nextLine();
            switch (input) {
                case "exit":
                    System.out.println("정렬된 단어 : " + dictionary);
                    break label;
                default:
                    dictionary.add(input);
                    break;
            }
        }
    }
}
