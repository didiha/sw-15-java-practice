package com.hcring.javaprograming.section01.array.level03.hard;

import java.util.Random;
import java.util.Scanner;

public class Application1_2 {
    public static void main(String[] args) {
        Random rand = new Random();

        System.out.printf("가로 행의 수를 입력하세요 : ");
        int row = returnNum();
        System.out.printf("세로 열의 수를 입력하세요 : ");
        int col = returnNum();

        char[][] charr = new char[row][col];

        for(int i = 0; i < charr.length; i++){
            for(int j = 0; j < charr[0].length; j++){
                charr[i][j] = (char)(rand.nextInt(26) + 'A');
                System.out.printf(charr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int returnNum (){
        Scanner sc = new Scanner(System.in);

        int num =  sc.nextInt();

        while(num < 1 || num > 10){
            System.out.printf("반드시 1~10까지의 정수를 입력해야 합니다. 다시 입력하세요 : ");
            num =  sc.nextInt();
        }

        return num;
    }
}
