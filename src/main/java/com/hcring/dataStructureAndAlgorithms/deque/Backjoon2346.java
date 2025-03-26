package com.hcring.dataStructureAndAlgorithms.deque;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Backjoon2346 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        StringTokenizer st = new StringTokenizer(input);
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Deque<Integer> deque = new LinkedList<Integer>();
        for(int i = 1; i <= N; i++){
            deque.add(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        while(!deque.isEmpty()){
            for(int i = 1; i < K; i++){
                deque.addLast(deque.pollFirst());
            }

            sb.append(deque.pollFirst());

            if(!deque.isEmpty()){
                sb.append(", ");
            }
        }

        sb.append(">");

        System.out.println(sb);
    }
}
