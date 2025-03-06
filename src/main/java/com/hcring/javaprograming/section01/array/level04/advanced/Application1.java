package com.hcring.javaprograming.section01.array.level04.advanced;

import java.util.Arrays;
import java.util.Random;

public class Application1 {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] lotto = new int[6];
        boolean[] isNumUsed = new boolean[45];
        int randNum = 0;

        for(int i = 0; i < lotto.length; i++) {
            do {
                randNum = rand.nextInt(45) + 1;
            } while (isNumUsed[randNum]);

            lotto[i] = randNum + 1;
        }

        Arrays.sort(lotto);

        System.out.print("로또 번호: ");

        for (int num : lotto)
            System.out.print(num + " ");

        System.out.println();
    }
}
