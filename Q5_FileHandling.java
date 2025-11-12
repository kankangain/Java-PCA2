// Question 5: File Handling, Reading and Writing Files, and Serialization

import java.io.*;
import java.util.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int rollNumber;
    private double marks;
    private transient String password;
    
    public Student(String name, int rollNumber, double marks, String password) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "Student{name='" + name + "', rollNumber=" + rollNumber + 
               ", marks=" + marks + ", password='" + password + "'}";
    }
}

public class Q5_FileHandling {
    
    public static void writeToFile() {
        System.out.println("=== Writing Text to File ===");
        
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write("Hello, Java File Handling!\n");
            writer.write("This is line 2.\n");
            writer.write("This is line 3.\n");
            System.out.println("Data written to output.txt successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        
        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter("buffered_output.txt"))) {
            bWriter.write("Using BufferedWriter");
            bWriter.newLine();
            bWriter.write("This is more efficient for large files");
            System.out.println("Data written to buffered_output.txt successfully!");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
    }
    
    public static void readFromFile() {
        System.out.println("=== Reading from File ===");
        
        try (FileReader reader = new FileReader("output.txt")) {
            int character;
            System.out.print("Content (using FileReader): ");
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        System.out.println("\nContent (using BufferedReader):");
        try (BufferedReader bReader = new BufferedReader(new FileReader("output.txt"))) {
            String line;
            int lineNumber = 1;
            while ((line = bReader.readLine()) != null) {
                System.out.println("Line " + lineNumber++ + ": " + line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\nContent (using Scanner):");
        try (Scanner scanner = new Scanner(new File("output.txt"))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        System.out.println();
    }
    
    public static void fileOperations() {
        System.out.println("=== File Operations ===");
        
        File file = new File("test_file.txt");
        
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
            
            System.out.println("File name: " + file.getName());
            System.out.println("Absolute path: " + file.getAbsolutePath());
            System.out.println("Writable: " + file.canWrite());
            System.out.println("Readable: " + file.canRead());
            System.out.println("File size (bytes): " + file.length());
            
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
    }
    
    public static void serializationExample() {
        System.out.println("=== Serialization Example ===");
        
        Student student = new Student("Alice Johnson", 101, 85.5, "secret123");
        
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("student.ser"))) {
            oos.writeObject(student);
            System.out.println("Object serialized successfully!");
            System.out.println("Serialized object: " + student);
        } catch (IOException e) {
            System.out.println("Serialization error: " + e.getMessage());
        }
        System.out.println();
    }
    
    public static void deserializationExample() {
        System.out.println("=== Deserialization Example ===");
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("student.ser"))) {
            Student student = (Student) ois.readObject();
            System.out.println("Object deserialized successfully!");
            System.out.println("Deserialized object: " + student);
            System.out.println("Note: password field is null (transient keyword)");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Deserialization error: " + e.getMessage());
        }
        System.out.println();
    }
    
    public static void directoryOperations() {
        System.out.println("=== Directory Operations ===");
        
        File directory = new File("test_directory");
        
        if (directory.mkdir()) {
            System.out.println("Directory created: " + directory.getName());
        } else {
            System.out.println("Directory already exists or cannot be created.");
        }
        
        File currentDir = new File(".");
        System.out.println("\nFiles in current directory:");
        String[] files = currentDir.list();
        if (files != null) {
            for (int i = 0; i < Math.min(5, files.length); i++) {
                System.out.println("  " + files[i]);
            }
            if (files.length > 5) {
                System.out.println("  ... and " + (files.length - 5) + " more files");
            }
        }
        
        if (directory.delete()) {
            System.out.println("\nDirectory deleted successfully.");
        }
        System.out.println();
    }
    
    public static void copyFile() {
        System.out.println("=== Copy File Example ===");
        
        try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output_copy.txt"))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("File copied successfully from output.txt to output_copy.txt");
        } catch (IOException e) {
            System.out.println("Error copying file: " + e.getMessage());
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Java File Handling Demo ===\n");
        
        writeToFile();
        readFromFile();
        fileOperations();
        serializationExample();
        deserializationExample();
        directoryOperations();
        copyFile();
        
        System.out.println("File handling demonstration completed!");
    }
}
