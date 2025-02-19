package com.hcring.chap04_control_flow.section02.looping_and_branching.level04.advanced;

import java.util.Scanner;

public class Application1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.printf("문자열을 입력하세요: ");
        String str = sc.nextLine();
        System.out.printf("숫자를 입력하세요: ");
        int num = sc.nextInt();

        int shift = num % 26;

        StringBuilder encryptedString = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch != ' ') {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                char encryptedChar = (char) ((ch - base + shift) % 26 + base);
                encryptedString.append(encryptedChar);
            } else {
                encryptedString.append(ch);
            }
        }

        System.out.println("암호화된 문자열: " + encryptedString.toString());

        sc.close();
    }
}
