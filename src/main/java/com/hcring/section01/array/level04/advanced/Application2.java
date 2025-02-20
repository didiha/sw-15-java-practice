package com.hcring.section01.array.level04.advanced;

import java.util.Random;
import java.util.Scanner;

public class Application2 {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);
        int[] randArr = new int[4];

        for (int i = 0; i < randArr.length; i++) {
            randArr[i] = rand.nextInt(10);
        }

        String input = "";

        for (int i = 0; i < 10; i++) {
            System.out.println(10 - i + "회 남으셨습니다.");
            System.out.printf("4자리 숫자를 입력하세요 : ");
            input = sc.nextLine();

            while (input.length() != 4 || !input.matches("\\d+")) {
                System.out.printf("4자리 정수를 입력해야 합니다. : ");
                input = sc.nextLine();
            }

            int countS = 0;
            int countB = 0;

            for (int j = 0; j < 4; j++) {
                int inputNum = Character.getNumericValue(input.charAt(j));

                if (randArr[j] == inputNum) {
                    countB++;
                } else if (contains(randArr, inputNum)) {
                    countS++;
                }
            }

            if (countB == 4) {
                System.out.println("정답입니다.");
                break;
            } else {
                System.out.println("아쉽네요 " + countS + "S " + countB + "B 입니다.");
            }

            if (i == 9) {
                System.out.println("10번의 기회를 모두 소진하셨습니다.");
            }
        }
    }

    private static boolean contains(int[] arr, int num) {
        for (int n : arr) {
            if (n == num) {
                return true;
            }
        }
        return false;
    }
}
