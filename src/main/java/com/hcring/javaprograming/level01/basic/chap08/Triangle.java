package com.hcring.javaprograming.level01.basic.chap08;

public class Triangle extends Shape implements Resizable{
    private int base, height, side1, side2, side3;

    public Triangle(int base, int height, int side1, int side2, int side3) {
        this.base = base;
        this.height = height;
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    double calculateArea() {
        return base * height / 2;
    }

    @Override
    double calculatePerimeter() {
        return  side1 + side2 + side3;
    }

    @Override
    public void resize(double factor){
        base *= factor;
        height *= factor;
        side1 *= factor;
        side2 *= factor;
        side3 *= factor;
    }
}
