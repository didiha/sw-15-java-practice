package com.hcring.level01.basic.chap09;

import java.util.Arrays;
import java.util.Scanner;

public class Application2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("문자열 입력 : ");
        String input = sc.nextLine();

        String[] words = input.trim().split("\\s+");
        String[] uniqueWords = new String[words.length];
        int[] wordCounts = new int[words.length];
        int uniqueIndex = 0;

        words = WordLower(words);
        Equals(words, uniqueWords, wordCounts, uniqueIndex);
    }

    public static String[] WordLower(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].toLowerCase().replaceAll("[^a-zA-Z]", "");
        }
        return strings;
    }

    public static void Equals(String[] strings, String[] uniqueWords, int[] wordCounts, int uniqueIndex) {
        for (String word : strings) {
            if (!word.isEmpty()) {
                boolean found = false;
                for (int i = 0; i < uniqueIndex; i++) {
                    if (uniqueWords[i].equals(word)) {
                        wordCounts[i]++;
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    uniqueWords[uniqueIndex] = word;
                    wordCounts[uniqueIndex] = 1;
                    uniqueIndex++;
                }
            }
        }

        String mostFrequentWord = null;
        int maxCount = 0;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < uniqueIndex; i++) {
            result.append(uniqueWords[i]).append(": ").append(wordCounts[i]).append("\n");

            if (wordCounts[i] > maxCount) {
                maxCount = wordCounts[i];
                mostFrequentWord = uniqueWords[i];
            }
        }

        System.out.println("===== 단어 빈도 =====");
        System.out.println(result);
        System.out.println("가장 빈도 높은 단어 : " + mostFrequentWord + " (" + maxCount + " 번)");
    }
}
