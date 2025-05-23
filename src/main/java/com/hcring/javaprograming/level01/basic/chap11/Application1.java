package com.hcring.javaprograming.level01.basic.chap11;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Application1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("파일 이름을 입력하세요 : ");
            String fileName = sc.nextLine();
            FileReader fr = new FileReader(fileName);

            System.out.println("===== 파일 내용 출력 =====");
            int value;
            while((value = fr.read()) != -1){
                System.out.print((char)value);
            }
        } catch (FileNotFoundException e) {
            System.out.println("오류 : 해당 이름을 가진 파일이 없습니다.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
