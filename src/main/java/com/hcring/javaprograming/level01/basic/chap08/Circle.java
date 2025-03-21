package com.hcring.javaprograming.level01.basic.chap08;

public class Circle extends Shape implements Resizable{
    private double radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    double calculateArea(){
        return Math.PI * radius * radius;
    }

    @Override
    double calculatePerimeter(){
        return 2 * Math.PI * radius;
    }

    @Override
    public void resize(double factor) {
        radius *= factor;
    }
}
