package com.hcring.chap04_control_flow.section02.looping_and_branching.level02.normal;

public class Application2 {
    public static void main(String[] args) {
        for(int i = 0; i < 26; i++){
            char alphabet = (char)('a' + i);
            System.out.print(alphabet);
        }
    }
}
