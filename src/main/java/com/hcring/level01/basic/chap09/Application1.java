package com.hcring.level01.basic.chap09;

import java.util.Scanner;

public class Application1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("문자열 입력 : ");
        String str = sc.nextLine();

        String[] result = str.split(" ");
        for(int i = 0; i < result.length; i++){
            result[i] = Character.toUpperCase(result[i].charAt(0)) + result[i].substring(1) + " ";
        }

        System.out.print("변환된 문자열 : ");
        for(String s : result){
            System.out.print(s);
        }
        System.out.println("총 단어 갯수: " + result.length);
    }
}
