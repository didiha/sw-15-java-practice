package com.hcring.javaprograming.level01.basic.chap13;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Application3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<String> waitLine = new LinkedList<>();
        String input = "";

        label:
        while(true){
            System.out.print("대기 고객 이름 입력 (다음 고객으로 넘어가려면 'next', 종료하려면 'exit'): ");
            input = sc.nextLine();
            switch(input){
                case "next" :
                    if(waitLine.isEmpty()){
                        System.out.println("대기 고객이 없습니다.");
                        break;
                    }

                    System.out.println(waitLine.poll() + " 고객님 차례입니다.");
                    break;

                case "exit" :
                    break label;

                default:
                    waitLine.offer(input);
                    System.out.println(input + " 고객님 대기 등록 되었습니다.");
            }
        }
    }
}
