package com.hcring.javaprograming.level01.basic.chap13;

import java.util.Properties;
import java.util.Scanner;

public class Application6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Properties phoneBook = new Properties();
        String input = "";

        label:
        while (true){
            System.out.print("이름과 전화번호를 띄어쓰기 기준으로 입력하세요 (검색하려면 'search', 종료하려면 'exit'): ");
            input = sc.nextLine();

            switch (input){
                case "search":
                    System.out.print("검색할 이름 : ");
                    String searchName = sc.nextLine();
                    if(!phoneBook.containsKey(searchName)){
                        System.out.println(searchName + "씨의 번호는 등록되어 있지 않습니다.");
                        break;
                    }
                    System.out.println(searchName + "씨의 전화번호 : " + phoneBook.getProperty(searchName));
                    break;
                case "exit":
                    break label;
                default:
                    String[] parts = input.split(" ");
                    if (parts.length != 2) {
                        System.out.println("입력이 잘못 되었습니다. 다음 양식으로 입력해주세요 : <이름> <전화번호>");
                    } else {
                        String name = parts[0];
                        String phone = parts[1];
                        phoneBook.setProperty(name, phone);
                        System.out.println("추가 완료 : " + name + " " + phone);
                    }
                    break;
            }
        }
    }
}
