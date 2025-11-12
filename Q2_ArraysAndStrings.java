import java.util.Arrays;

public class Q2_ArraysAndStrings {
    
    public static void arraysClassExample() {
        System.out.println("=== Arrays Class Operations ===");
        
        int[] numbers = {45, 12, 78, 34, 89, 23, 67};
        
        System.out.println("Original Array: " + Arrays.toString(numbers));
        
        Arrays.sort(numbers);
        System.out.println("Sorted Array: " + Arrays.toString(numbers));
        
        int key = 34;
        int index = Arrays.binarySearch(numbers, key);
        System.out.println("Index of " + key + ": " + index);
        
        int[] filledArray = new int[5];
        Arrays.fill(filledArray, 100);
        System.out.println("Filled Array: " + Arrays.toString(filledArray));
        
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};
        System.out.println("Arrays equal: " + Arrays.equals(array1, array2));
        System.out.println();
    }
    
    public static void stringHandlingExample() {
        System.out.println("=== String Handling ===");
        
        String str1 = "Hello World";
        String str2 = "Java Programming";
        
        System.out.println("Length: " + str1.length());
        System.out.println("Character at index 6: " + str1.charAt(6));
        System.out.println("Substring: " + str1.substring(0, 5));
        System.out.println("Uppercase: " + str1.toUpperCase());
        System.out.println("Lowercase: " + str1.toLowerCase());
        System.out.println("Contains 'World': " + str1.contains("World"));
        System.out.println("Replace 'World' with 'Java': " + str1.replace("World", "Java"));
        
        String result = str1.concat(" - ").concat(str2);
        System.out.println("Concatenated: " + result);
        
        String[] words = str2.split(" ");
        System.out.println("Split result: " + Arrays.toString(words));
        
        String str3 = "  Hello  ";
        System.out.println("Before trim: '" + str3 + "'");
        System.out.println("After trim: '" + str3.trim() + "'");
        System.out.println();
    }
    
    public static void stringBufferExample() {
        System.out.println("=== StringBuffer Example ===");
        
        StringBuffer sb = new StringBuffer("Hello");
        
        sb.append(" World");
        System.out.println("After append: " + sb);
        
        sb.insert(6, "Java ");
        System.out.println("After insert: " + sb);
        
        sb.replace(0, 5, "Hi");
        System.out.println("After replace: " + sb);
        
        sb.delete(0, 3);
        System.out.println("After delete: " + sb);
        
        sb.reverse();
        System.out.println("After reverse: " + sb);
        
        System.out.println("Capacity: " + sb.capacity());
        System.out.println("Length: " + sb.length());
        System.out.println();
    }
    
    public static void stringBuilderExample() {
        System.out.println("=== StringBuilder Example ===");
        
        StringBuilder builder = new StringBuilder("Programming");
        
        builder.append(" in Java");
        System.out.println("After append: " + builder);
        
        builder.insert(0, "Learn ");
        System.out.println("After insert: " + builder);
        
        builder.delete(0, 6);
        System.out.println("After delete: " + builder);
        
        builder.replace(0, 11, "Coding");
        System.out.println("After replace: " + builder);
        
        long startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("Java");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("StringBuilder Time: " + (endTime - startTime) + "ms");
        
        System.out.println("\nNote: StringBuilder is faster than StringBuffer (not synchronized)");
        System.out.println("StringBuffer is thread-safe (synchronized)");
    }
    
    public static void main(String[] args) {
        arraysClassExample();
        stringHandlingExample();
        stringBufferExample();
        stringBuilderExample();
    }
}
