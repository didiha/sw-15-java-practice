package com.hcring.chap16;

import java.util.*;
import java.util.stream.Collectors;

public class Application1 {

    public static void main(String[] args) {

        List<com.hcring.chap16.employee.Employee> employees = Arrays.asList(
                new com.hcring.chap16.employee.Employee("홍길동", 25, "IT", 6000),
                new com.hcring.chap16.employee.Employee("김철수", 28, "HR", 3000),
                new com.hcring.chap16.employee.Employee("이영희", 30, "IT", 7000),
                new com.hcring.chap16.employee.Employee("박민수", 22, "영업", 4000),
                new com.hcring.chap16.employee.Employee("최지현", 35, "HR", 3500),
                new com.hcring.chap16.employee.Employee("한석봉", 40, "IT", 5000)
        );

        // 1. 모든 직원의 이름을 쉼표로 구분된 문자열로 반환
        String allNames = // 코드 작성

                // 2. IT 부서에서 급여가 5,000 이상인 직원의 이름을 급여 순으로 내림차순 정렬하여 반환
                List<String> highSalaryIT = // 코드 작성

        // 3. 각 부서별 평균 급여를 계산하여 맵으로 반환
        Map<String, Double> averageSalaryByDept = // 코드 작성

                System.out.println(allNames); // 출력 예시: "홍길동, 김철수, 이영희, 박민수, 최지현, 한석봉"
        System.out.println(highSalaryIT); // 출력 예시: [이영희, 홍길동, 한석봉]
        System.out.println(averageSalaryByDept); // 출력 예시: {HR=3250.0, IT=6000.0, 영업=4000.0}
    }
}
