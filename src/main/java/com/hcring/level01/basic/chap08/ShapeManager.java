package com.hcring.level01.basic.chap08;

public class ShapeManager {
    private Shape[] shapes = new Shape[10];
    private int index;

    public void addShape(Shape shape) {
        /* 배열에 전달 된 Shape를 추가. 단, 배열의 크기가 부족할 경우 2배로 늘려서 추가. */
        if(index < shapes.length){
            Shape[] newShapes = new Shape[shapes.length * 2];
            System.arraycopy(shapes, 0, newShapes, 0, shapes.length);
            shapes = newShapes;
        }
        shapes[index++] = shape;
    }

    public void removeShape(Shape shape) {
        /* 배열에서 전달 된 Shape를 찾아 제거. 단, 제거 된 인덱스가 비어 있지 않도록 뒤에 있는 객체를 앞으로 당김. */
        for(int i = 0; i < index; i++){
            if(shapes[i] == shape){
                for(int j = i; j < index - 1; j++){
                    shapes[j] = shapes[j + 1];
                }
                shapes[--index] = null;
                break;
            }
        }
    }

    public void printAllShapes() {
        /* 배열에 저장 된 모든 도형의 이름, 넓이, 둘레를 출력 */
        for(int i = 0; i < shapes.length; i++){
            if(shapes[i] != null){
                System.out.println("Shape: " + shapes[i].getClass().getSimpleName());
                System.out.println("Area: " + shapes[i].calculateArea());
                System.out.println("Perimeter: " + shapes[i].calculatePerimeter());
            }
        }
    }

    public double getTotalArea() {
        /* 배열에 저장 된 모든 도형의 넓이를 더해서 반환 */
        double totalArea = 0;
        for(int i = 0; i < shapes.length; i++){
            if(shapes[i] != null){
                totalArea += shapes[i].calculateArea();
            }
        }
        return totalArea;
    }

    public double getTotalPerimeter() {
        /* 배열에 저장 된 모든 도형의 둘레를 더해서 반환 */
        double totalPerimeter = 0;
        for(int i = 0; i < shapes.length; i++){
            if(shapes[i] != null){
                totalPerimeter += shapes[i].calculatePerimeter();
            }
        }
        return totalPerimeter;
    }
}
