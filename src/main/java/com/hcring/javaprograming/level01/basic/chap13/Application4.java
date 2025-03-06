package com.hcring.javaprograming.level01.basic.chap13;

import java.util.HashSet;
import java.util.Scanner;

public class Application4 {
    public static void main(String[] args) {
        HashSet<String> id = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        String input = "";

        label:
        while(true){
            System.out.print("학생 ID 입력('exit' 입력 시 종료): ");
            input = sc.nextLine();
            switch(input){
                case "exit":
                    break label;
                default:
                    if(id.contains(input)){
                        System.out.println("이미 등록된 ID입니다.");
                        break;
                    }
                    id.add(input);
                    System.out.println("ID가 추가되었습니다.");
            }
        }
        System.out.println("모든 학생의 ID : " + id);
    }
}
