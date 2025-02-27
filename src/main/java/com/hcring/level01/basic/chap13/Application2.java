package com.hcring.level01.basic.chap13;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Application2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<String> url = new LinkedList<>();
        String inputUrl = "";

        while (true) {
            System.out.print("방문 URL을 입력하세요 (단, exit를 입력하면 종료) : ");
            inputUrl = sc.nextLine();

            if(inputUrl.equals("exit")) break;

            if(url.size() < 5) {
                url.add(inputUrl);

            } else {
                url.poll();
                url.add(inputUrl);
            }
            System.out.println("최근 방문 url : " + url);
        }

    }
}
