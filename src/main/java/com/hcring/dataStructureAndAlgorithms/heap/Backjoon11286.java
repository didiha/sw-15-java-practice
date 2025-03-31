package com.hcring.dataStructureAndAlgorithms.heap;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Backjoon11286 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // 절댓값 힙 구현
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int absA = Math.abs(a);
            int absB = Math.abs(b);

            if (absA == absB) {
                // 절댓값이 같으면 음수 우선
                return Integer.compare(a, b);
            } else {
                // 절댓값이 다르면 절댓값이 작은 값 우선
                return Integer.compare(absA, absB);
            }
        });

        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            if (x == 0) {
                if (pq.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(pq.poll());
                }
            } else {
                pq.offer(x);
            }
        }
    }
}
