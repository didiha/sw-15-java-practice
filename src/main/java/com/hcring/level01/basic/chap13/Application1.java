package com.hcring.level01.basic.chap13;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Application1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<Double> score = new LinkedList<>();
        double sum = 0;
        char yn = 'y';
        int num = 0;

        while(yn == 'y'){
            System.out.print("학생 성적 : ");
            score.add(sc.nextDouble());
            System.out.print("추가 입력하려면 y : ");
            yn = sc.next().charAt(0);
        }
        num = score.size();
        System.out.println("학생 인원 : " + num);

        for(int i = 0 ; i < score.size() ; i++){
            sum += score.get(i);
        }

        System.out.println("평균 점수 : " + sum / num);
    }
}
