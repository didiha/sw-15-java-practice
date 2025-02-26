package com.hcring.level01.basic.chap11;

import java.io.*;
import java.util.Scanner;

public class Application2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String originFileName = "";
        String copyFileName = "";
        FileReader fr = null;
        FileWriter fw = null;

        try {
            System.out.print("원본 파일 이름을 입력하세요 : ");
            originFileName = sc.nextLine();
            fr = new FileReader(originFileName);

            System.out.print("복사 파일의 이름을 입력하세요 : ");
            copyFileName = sc.nextLine();
            File copyFile = new File(copyFileName);
            fw = new FileWriter(copyFile);

            int value;
            while((value = fr.read()) != -1){
                fw.write((char)value);
            }

            System.out.println("파일 복사가 성공적으로 완료되었습니다.");
        } catch (FileNotFoundException e) {
            System.out.println("오류 : " + originFileName + " (지정된 파일을 찾을 수 없습니다.)");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                System.out.println("오류 : " + e.getMessage());
            }
        }
    }
}
