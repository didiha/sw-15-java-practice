package com.hcring.section01.array.level03.hard;

import java.util.Scanner;

public class Application1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.printf("홀수인 양의 정수를 입력하세요 : ");
        int num = sc.nextInt();

        while(num < 0 || num % 2 == 0){
            System.out.println("양수 혹은 홀수만 입력해야 합니다.");
            num = sc.nextInt();
        }

        int[] iarr = new int[num];
        int half = num / 2;

        for(int i = 0; i < half; i++){
            iarr[i] = i + 1;
            System.out.print(iarr[i] + " ");
        }

        for(int i = half; i < num; i++){
            iarr[i] = num - i;
            System.out.print(iarr[i] + " ");
        }
    }
}
