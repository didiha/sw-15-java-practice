package com.hcring.section01.array.level02.normal;

import java.util.Scanner;

public class Application2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.printf("주민등록번호를 입력하세요 : ");
        String idNumber = sc.nextLine();

        char[] ch = idNumber.toCharArray();

        for(int i = 8; i < idNumber.length(); i++){
            ch[i] = '*';
        }

        System.out.println(ch);
    }
}
