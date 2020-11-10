package homeworktwo;

public class PascalTriangle {

    /*
        1. Formula for storing in Matrix
        arr[i][j] = arr[i-1][j-1] + arr[i-1][j+1]
        2. Formula for storing in Array
        arr[gauss(i) + j] = arr[gauss(i-1) + j - 1] + arr[gaus(i-1) + j]
     */


    int size;
    int[] arr;

    public PascalTriangle(int size) {
        this.size = size;
        this.arr = this.generate();
    }

    public int gaussSum(int number) {
        return (number * (number + 1)) / 2;
    }

    public int[] generate() {
        int n = gaussSum(this.size);
        this.arr = new int[n];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < i + 1; j++) {
                int rowPosition = gaussSum(i);
                int colPosition = j;

                if (j == 0 || j == i) {
                    this.arr[rowPosition + colPosition] = 1;
                } else {
                    int topLeftPosition = gaussSum(i - 1) + j - 1;
                    int topRightPosition = gaussSum(i - 1) + j;
                    this.arr[gaussSum(i) + j] = arr[topLeftPosition] + arr[topRightPosition];
                }
            }
        }
        return this.arr;
    }

    private void makePadding(int padding) {
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
    }

    public void display() {
        for (int i = 0; i < this.size; i++) {
            makePadding((this.size - i) * 3 / 2 + this.size);
            for (int j = 0; j < i + 1; j++) {
                if (j != 0) {
                    makePadding(1);
                }
                System.out.print(this.arr[this.gaussSum(i) + j]);
                if (j != i) {
                    makePadding(1);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        PascalTriangle triangle = new PascalTriangle(5);
        triangle.display();
    }

}
