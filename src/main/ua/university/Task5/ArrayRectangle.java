package main.ua.university.Task5;

public class ArrayRectangle {
    private Rectangle[] array_rectangle;

    public ArrayRectangle(Rectangle... rectangles) {
        array_rectangle = rectangles;
    }

    public ArrayRectangle(int n) {
        array_rectangle = new Rectangle[n];
    }

    public Rectangle[] getArray_rectangle() {
        return array_rectangle;
    }

    public void setArray_rectangle(Rectangle[] array_rectangle) {
        this.array_rectangle = array_rectangle;
    }

    public boolean isFreeSpace() {
        boolean check = false;
        for (var item : array_rectangle) {
            if (item == null)
                check = true;
        }
        return check;
    }

    public int findFreeIndex() {
        int index = -1;
        for (int i = 0; i < array_rectangle.length; i++) {
            if (array_rectangle[i] == null) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void addRectangle(Rectangle rectangle) {
        if (isFreeSpace())
            array_rectangle[findFreeIndex()] = rectangle;
    }

    public int numberMaxArea() {
        int max = 0;
        for (int i = 0; i < array_rectangle.length; i++) {
            if (array_rectangle[i].getArea() > array_rectangle[max].getArea())
                max = i;
        }
        return max;
    }

    public int numberMinPerimeter() {
        int min = 0;
        for (int i = 0; i < array_rectangle.length; i++) {
            if (array_rectangle[i].getArea() < array_rectangle[min].getArea())
                min = i;
        }
        return min;
    }

    public int numberSquare() {
        int amount = 0;
        for (var item : array_rectangle) {
            if (item != null && item.isSquare())
                amount++;
        }
        return amount;
    }
}
