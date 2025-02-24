package com.hcring.level01.basic;

public class Rectangle extends Shape implements Resizable{
    private int width, height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    double calculateArea(){
        return width * height;
    }

    @Override
    double calculatePerimeter(){
        return 2 * (width + height);
    }

    @Override
    public void resize(double factor) {
        width *= factor;
        height *= factor;
    }
}
