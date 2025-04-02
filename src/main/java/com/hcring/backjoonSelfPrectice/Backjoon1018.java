package com.hcring.backjoonSelfPrectice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Backjoon1018 {
    // 체스판 다시 칠하기
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        sc.nextLine();

        char[][] chess = new char[M][N];

        for (int i = 0; i < M; i++) {
            String line = sc.nextLine();
            for(int j = 0; j < N; j++){
                chess[i][j] = line.charAt(j);
            }
        }

        int count = checkTheChess(chess);
    }

    public static int checkTheChess(char[][] chess){


        return 0;
    }

}
