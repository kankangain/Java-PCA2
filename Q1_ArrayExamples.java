public class Q1_ArrayExamples {
    
    public static void singleDimensionalArrayExample() {
        System.out.println("=== Single-Dimensional Array ===");
        
        int[] numbers = {10, 20, 30, 40, 50};
        
        System.out.print("Array elements: ");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
        
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        System.out.println("Sum of elements: " + sum);
        
        int max = numbers[0];
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        System.out.println("Maximum element: " + max);
        System.out.println();
    }
    
    public static void multiDimensionalArrayExample() {
        System.out.println("=== Multi-Dimensional Array ===");
        
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("Matrix elements:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        
        int[][] matrix2 = {
            {9, 8, 7},
            {6, 5, 4},
            {3, 2, 1}
        };
        
        System.out.println("\nMatrix Addition Result:");
        int[][] result = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = matrix[i][j] + matrix2[i][j];
                System.out.print(result[i][j] + "\t");
            }
            System.out.println();
        }
        
        int diagonalSum = 0;
        for (int i = 0; i < matrix.length; i++) {
            diagonalSum += matrix[i][i];
        }
        System.out.println("\nSum of diagonal elements: " + diagonalSum);
    }
    
    public static void main(String[] args) {
        singleDimensionalArrayExample();
        multiDimensionalArrayExample();
    }
}
