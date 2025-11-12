class InsufficientFundsException extends Exception {
    private double amount;
    
    public InsufficientFundsException(double amount) {
        super("Insufficient funds! Required: $" + amount);
        this.amount = amount;
    }
    
    public double getAmount() {
        return amount;
    }
}

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

class CustomDivisionException extends ArithmeticException {
    public CustomDivisionException(String message) {
        super(message);
    }
}

class BankAccount {
    private double balance;
    private String accountNumber;
    
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(amount - balance);
        }
        balance -= amount;
        System.out.println("Withdrawal successful! New balance: $" + balance);
    }
    
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful! New balance: $" + balance);
    }
    
    public double getBalance() {
        return balance;
    }
}

class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) throws InvalidAgeException {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("Invalid age: " + age + ". Age must be between 0 and 150.");
        }
        this.name = name;
        this.age = age;
    }
    
    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

public class Q4_ExceptionHandling {
    
    public static void tryCatchFinallyExample() {
        System.out.println("=== Try-Catch-Finally Example ===");
        try {
            int[] numbers = {1, 2, 3};
            System.out.println("Accessing element at index 5: " + numbers[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception caught: " + e.getMessage());
        } finally {
            System.out.println("Finally block always executes!\n");
        }
    }
    
    public static void multipleCatchExample() {
        System.out.println("=== Multiple Catch Blocks ===");
        try {
            String str = null;
            System.out.println(str.length());
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General Exception caught: " + e.getMessage());
        }
        System.out.println();
    }
    
    public static void throwExample() {
        System.out.println("=== Throw Keyword Example ===");
        try {
            int age = -5;
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        System.out.println();
    }
    
    public static void customExceptionExample() {
        System.out.println("=== Custom Exception Example - Bank Account ===");
        BankAccount account = new BankAccount("ACC001", 1000.0);
        
        try {
            System.out.println("Initial balance: $" + account.getBalance());
            account.deposit(500.0);
            account.withdraw(800.0);
            account.withdraw(1000.0);
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Short by: $" + e.getAmount());
        }
        System.out.println();
    }
    
    public static void ageValidationExample() {
        System.out.println("=== Custom Exception - Age Validation ===");
        
        try {
            Person person1 = new Person("Alice", 25);
            person1.displayInfo();
            
            Person person2 = new Person("Bob", -5);
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        try {
            Person person3 = new Person("Charlie", 200);
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println();
    }
    
    public static void nestedTryCatchExample() {
        System.out.println("=== Nested Try-Catch Example ===");
        try {
            try {
                int result = 10 / 0;
            } catch (ArithmeticException e) {
                System.out.println("Inner catch: Division by zero");
                throw new CustomDivisionException("Custom: Cannot divide by zero!");
            }
        } catch (CustomDivisionException e) {
            System.out.println("Outer catch: " + e.getMessage());
        }
        System.out.println();
    }
    
    public static void methodWithThrows() throws InterruptedException {
        System.out.println("=== Throws Keyword Example ===");
        System.out.println("Sleeping for 1 second...");
        Thread.sleep(1000);
        System.out.println("Wake up!\n");
    }
    
    public static void main(String[] args) {
        System.out.println("=== Java Exception Handling Demo ===\n");
        
        tryCatchFinallyExample();
        multipleCatchExample();
        throwExample();
        
        customExceptionExample();
        ageValidationExample();
        nestedTryCatchExample();
        
        try {
            methodWithThrows();
        } catch (InterruptedException e) {
            System.out.println("Interrupted: " + e.getMessage());
        }
        
        System.out.println("Program completed successfully!");
    }
}
