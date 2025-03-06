package com.hcring.javaprograming.level02.normal.book.run;

import com.hcring.javaprograming.level02.normal.book.model.dto.BookDTO;

public class Application {
    public static void main(String[] args) {
        BookDTO bookDto = new BookDTO();

        BookDTO bookDto2 = new BookDTO("자바의 정석", "도우출판", "남궁성");

        BookDTO bookDto3 = new BookDTO("홍길동전", "활빈당", "허균", 5000000, 0.5);
    }
}
