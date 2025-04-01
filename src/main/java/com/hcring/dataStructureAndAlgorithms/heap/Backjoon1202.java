package com.hcring.dataStructureAndAlgorithms.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Backjoon1202 {

    static class Jewel implements Comparable<Jewel> {
        int weight;
        int value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewel other) {
            return this.weight - other.weight; // 무게 오름차순
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Jewel[] jewels = new Jewel[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(m, v);
        }

        int[] bags = new int[k];
        for (int i = 0; i < k; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewels); // 보석 무게 오름차순 정렬
        Arrays.sort(bags);   // 가방 무게 오름차순 정렬

        long totalValue = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a); // 최대 힙 (가격 내림차순)

        int jewelIndex = 0;
        for (int bagWeight : bags) {
            // 현재 가방에 넣을 수 있는 보석들을 힙에 추가
            while (jewelIndex < n && jewels[jewelIndex].weight <= bagWeight) {
                pq.offer(jewels[jewelIndex].value);
                jewelIndex++;
            }

            // 힙이 비어있지 않다면, 가장 비싼 보석을 꺼내 가방에 넣음
            if (!pq.isEmpty()) {
                totalValue += pq.poll();
            }
        }

        System.out.println(totalValue);
    }
}
