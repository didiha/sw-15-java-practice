package com.hcring.level01.basic.chap10;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Application1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = 0, y = 0;

        try{
            System.out.print("분자 입력 : ");
            x = sc.nextInt();
            System.out.print("분모 입력 : ");
            y = sc.nextInt();
            if(x > y){
                System.out.println("결과 : " + x / y);
            }
        } catch (InputMismatchException e) {
            System.out.println("오류 : 유효한 정수를 입력하세요.");
        } catch (ArithmeticException e){
            System.out.println("오류 : 0으로 나누는 것은 혀용되지 않습니다.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("실행이 완료되었습니다.");
    }
}
