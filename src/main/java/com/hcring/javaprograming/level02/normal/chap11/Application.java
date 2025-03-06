package com.hcring.javaprograming.level02.normal.chap11;

import java.io.*;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("병합할 파일의 개수 입력 : ");
        int time = sc.nextInt();
        String[] fileNames = new String[time];

        for (int i = 0; i < time; i++) {
            System.out.print((i + 1) + "번째 파일 이름 입력 : ");
            fileNames[i] = sc.next();
        }

        System.out.print("병합될 파일명 입력 : ");
        String resultFileName = sc.next();

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(resultFileName))){
            for(int i = 0; i < time; i++){
                try(BufferedReader br = new BufferedReader(new FileReader(fileNames[i]))){
                    String line;
                    while((line = br.readLine()) != null){
                        bw.write(line);
                        bw.newLine();
                    }
                }  catch (FileNotFoundException e){
                    System.out.println("오류 : " + fileNames[i] + "(지정된 파일을 찾을 수 없습니다)");
                    return;
                }
            }
            System.out.println("파일 병합이 완료되었습니다.");
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }
}
