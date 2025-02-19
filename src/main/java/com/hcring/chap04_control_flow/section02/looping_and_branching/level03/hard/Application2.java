package com.hcring.chap04_control_flow.section02.looping_and_branching.level03.hard;

import java.util.Random;
import java.util.Scanner;

public class Application2 {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        int randomNum = rand.nextInt(100);
        System.out.println(randomNum);
        int num = 0;

        for(int i = 1;;i++){
            System.out.printf("정수를 입력하세요 : ");
            num = sc.nextInt();
            if(num < randomNum)
                System.out.println("입력하신 정수보다 큽니다.");
            else if(num > randomNum)
                System.out.println("입력하신 정수보다 작습니다.");
            else {
                System.out.println("정답입니다. " + i + "회만에 정답을 맞추셨습니다.");
                break;
            }
        }
    }
}
