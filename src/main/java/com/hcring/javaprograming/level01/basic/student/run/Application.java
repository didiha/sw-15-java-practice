package com.hcring.javaprograming.level01.basic.student.run;

 import com.hcring.javaprograming.level01.basic.student.model.dto.StudentDTO;

 import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDTO[] students = new StudentDTO[10];

        int count = 0;
        char yn = 'y';

        while(yn == 'y' && count < 10){
            System.out.printf("학년 : ");
            int grade = sc.nextInt();
            System.out.printf("반 : ");
            int classroom = sc.nextInt();
            sc.nextLine();
            System.out.printf("이름 : ");
            String name = sc.nextLine();
            System.out.printf("국어점수 : ");
            int kor = sc.nextInt();
            System.out.printf("영어점수 : ");
            int eng = sc.nextInt();
            System.out.printf("수학점수 : ");
            sc.nextLine();
            int math = sc.nextInt();

            students[count] = new StudentDTO(grade, classroom, name, kor, eng, math);
            count++;

            System.out.printf("계속 추가할 겁니까? (y/n) : ");
            yn = sc.next().charAt(0);
        }
        for(int i = 0; i < count; i++){
            System.out.println(students[i].getInfomation());
        }
    }
}
