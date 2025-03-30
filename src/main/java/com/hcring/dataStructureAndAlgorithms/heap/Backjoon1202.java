package com.hcring.dataStructureAndAlgorithms.heap;

import java.util.*;

public class Backjoon1202 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] jewels = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(sc.nextLine());
            jewels[i][0] = Integer.parseInt(st.nextToken());
            jewels[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] capacities = new int[K];
        for (int i = 0; i < K; i++) {
            capacities[i] = sc.nextInt();
        }

        Arrays.sort(jewels, (a, b) -> Integer.compare(b[0], a[0]));
        Arrays.sort(capacities);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        long maxValue = 0;
        int jewelIndex = 0;
        for (int capacity : capacities) {
            while (jewelIndex < N && jewels[jewelIndex][1] <= capacity) {
                pq.offer(jewels[jewelIndex][0]);
                jewelIndex++;
            }

            if (!pq.isEmpty()) {
                maxValue += pq.poll();
            }
        }

        System.out.println(maxValue);
    }
}
