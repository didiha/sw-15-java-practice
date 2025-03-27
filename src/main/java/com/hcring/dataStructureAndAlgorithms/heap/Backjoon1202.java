package com.hcring.dataStructureAndAlgorithms.heap;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Backjoon1202 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());

        String inputNK = sc.nextLine();
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] MV = new int[2][N];
        int[] C = new int[K];

        for (int i = 0; i <= N; i++) {
            String inputMV = sc.nextLine();
            MV[0][i] = Integer.parseInt(st.nextToken());
            MV[1][i] = Integer.parseInt(st.nextToken());
        }


    }
}
