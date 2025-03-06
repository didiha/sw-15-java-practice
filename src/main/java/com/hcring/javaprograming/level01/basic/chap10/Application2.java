package com.hcring.javaprograming.level01.basic.chap10;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Application2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("생년월일 입력 (yyyy-MM-dd 양식으로 작성) : ");
        String inputDate = sc.nextLine();
        try{
            LocalDate birthDate = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate now = LocalDate.now();
            LocalDate toAdultDate = birthDate.minusYears(20);

            if(birthDate.isBefore(toAdultDate)){
                throw new InvalidAgeException("만 20세 미만은 입장 불가입니다.");
            }
            System.out.println("입장하셔도 됩니다.");
        } catch(InvalidAgeException e){
            System.out.println(e.getMessage());
        } catch(DateTimeParseException e){
            System.out.println("날짜 양식을 잘못 입력하셨습니다.");
        }
    }
}
