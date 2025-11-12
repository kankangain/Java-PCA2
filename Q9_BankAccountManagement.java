// Question 9: Bank Account Management System

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

class InsufficientBalanceException extends Exception {
    private double balance;
    private double requestedAmount;
    
    public InsufficientBalanceException(double balance, double requestedAmount) {
        super("Insufficient balance! Available: $" + String.format("%.2f", balance) + 
              ", Requested: $" + String.format("%.2f", requestedAmount));
        this.balance = balance;
        this.requestedAmount = requestedAmount;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public double getShortfall() {
        return requestedAmount - balance;
    }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

class Transaction {
    private static int transactionCounter = 1000;
    
    private int transactionId;
    private String type;
    private double amount;
    private double balanceAfter;
    private Date timestamp;
    
    public Transaction(String type, double amount, double balanceAfter) {
        this.transactionId = ++transactionCounter;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = new Date();
    }
    
    @Override
    public String toString() {
        return String.format("ID: %d | Type: %-10s | Amount: $%-10.2f | Balance: $%-10.2f | Time: %s", 
                           transactionId, type, amount, balanceAfter, timestamp);
    }
}

class Account {
    private static int accountCounter = 1000;
    
    private int accountNumber;
    private String accountType;
    private double balance;
    private Customer customer;
    private List<Transaction> transactions;
    
    public Account(Customer customer, String accountType, double initialBalance) {
        this.accountNumber = ++accountCounter;
        this.customer = customer;
        this.accountType = accountType;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        
        if (initialBalance > 0) {
            transactions.add(new Transaction("OPENING", initialBalance, balance));
        }
    }
    
    public int getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountType() {
        return accountType;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive! Provided: $" + amount);
        }
        
        balance += amount;
        transactions.add(new Transaction("DEPOSIT", amount, balance));
        System.out.println("Deposit successful! Amount: $" + String.format("%.2f", amount));
        System.out.println("New Balance: $" + String.format("%.2f", balance));
    }
    
    public void withdraw(double amount) throws InsufficientBalanceException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive! Provided: $" + amount);
        }
        
        if (amount > balance) {
            throw new InsufficientBalanceException(balance, amount);
        }
        
        balance -= amount;
        transactions.add(new Transaction("WITHDRAWAL", amount, balance));
        System.out.println("Withdrawal successful! Amount: $" + String.format("%.2f", amount));
        System.out.println("New Balance: $" + String.format("%.2f", balance));
    }
    
    public void displayBalance() {
        System.out.println("\n=== Account Balance ===");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + customer.getName());
        System.out.println("Account Type: " + accountType);
        System.out.println("Current Balance: $" + String.format("%.2f", balance));
    }
    
    public void displayTransactionHistory() {
        System.out.println("\n=== Transaction History for Account #" + accountNumber + " ===");
        System.out.println("Account Holder: " + customer.getName());
        System.out.println("─────────────────────────────────────────────────────────────────────────────────");
        
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
        System.out.println("─────────────────────────────────────────────────────────────────────────────────");
        System.out.println("Total Transactions: " + transactions.size());
    }
    
    @Override
    public String toString() {
        return String.format("Account #%d | Type: %-10s | Balance: $%-10.2f | Holder: %s", 
                           accountNumber, accountType, balance, customer.getName());
    }
}

class Customer {
    private static int customerCounter = 100;
    
    private int customerId;
    private String name;
    private String email;
    private String phone;
    private List<Account> accounts;
    
    public Customer(String name, String email, String phone) {
        this.customerId = ++customerCounter;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.accounts = new ArrayList<>();
    }
    
    public int getCustomerId() {
        return customerId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void addAccount(Account account) {
        accounts.add(account);
    }
    
    public List<Account> getAccounts() {
        return accounts;
    }
    
    public void displayCustomerInfo() {
        System.out.println("\n=== Customer Information ===");
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Number of Accounts: " + accounts.size());
    }
    
    public void displayAllAccounts() {
        System.out.println("\n=== Accounts for " + name + " ===");
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (Account account : accounts) {
                System.out.println(account);
            }
        }
    }
    
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-20s | Email: %-25s | Phone: %s", 
                           customerId, name, email, phone);
    }
}

class Bank {
    private String bankName;
    private List<Customer> customers;
    private List<Account> accounts;
    
    public Bank(String bankName) {
        this.bankName = bankName;
        this.customers = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }
    
    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer registered: " + customer.getName());
    }
    
    public Account createAccount(Customer customer, String accountType, double initialBalance) 
            throws InvalidAmountException {
        if (initialBalance < 0) {
            throw new InvalidAmountException("Initial balance cannot be negative!");
        }
        
        Account account = new Account(customer, accountType, initialBalance);
        customer.addAccount(account);
        accounts.add(account);
        System.out.println("Account created successfully! Account Number: " + account.getAccountNumber());
        return account;
    }
    
    public void displayAllCustomers() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║              " + bankName + " - Customer List                           ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
        
        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }
    
    public void displayAllAccounts() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║              " + bankName + " - All Accounts                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
        
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (Account account : accounts) {
                System.out.println(account);
            }
        }
    }
}

public class Q9_BankAccountManagement {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║              Bank Account Management System                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝\n");
        
        Bank bank = new Bank("Global Bank");
        
        System.out.println("--- Creating Customers ---");
        Customer customer1 = new Customer("Alice Johnson", "alice@email.com", "555-1234");
        Customer customer2 = new Customer("Bob Smith", "bob@email.com", "555-5678");
        Customer customer3 = new Customer("Charlie Brown", "charlie@email.com", "555-9012");
        
        bank.addCustomer(customer1);
        bank.addCustomer(customer2);
        bank.addCustomer(customer3);
        
        try {
            System.out.println("\n--- Creating Accounts ---");
            Account account1 = bank.createAccount(customer1, "SAVINGS", 5000.00);
            Account account2 = bank.createAccount(customer2, "CHECKING", 3000.00);
            Account account3 = bank.createAccount(customer1, "CHECKING", 2000.00);
            
            bank.displayAllCustomers();
            bank.displayAllAccounts();
            
            System.out.println("\n\n--- Transactions for Account #" + account1.getAccountNumber() + " ---");
            account1.displayBalance();
            
            System.out.println("\n>> Depositing $1500...");
            account1.deposit(1500.00);
            
            System.out.println("\n>> Withdrawing $2000...");
            account1.withdraw(2000.00);
            
            System.out.println("\n>> Depositing $500...");
            account1.deposit(500.00);
            
            account1.displayBalance();
            account1.displayTransactionHistory();
            
            System.out.println("\n\n--- Transactions for Account #" + account2.getAccountNumber() + " ---");
            account2.displayBalance();
            
            System.out.println("\n>> Depositing $800...");
            account2.deposit(800.00);
            
            System.out.println("\n>> Withdrawing $1500...");
            account2.withdraw(1500.00);
            
            System.out.println("\n>> Attempting to withdraw $5000...");
            try {
                account2.withdraw(5000.00);
            } catch (InsufficientBalanceException e) {
                System.out.println("❌ Transaction Failed!");
                System.out.println("Error: " + e.getMessage());
                System.out.println("Shortfall: $" + String.format("%.2f", e.getShortfall()));
            }
            
            account2.displayBalance();
            account2.displayTransactionHistory();
            
            System.out.println("\n--- Testing Invalid Deposit ---");
            try {
                account1.deposit(-100.00);
            } catch (InvalidAmountException e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
            
            System.out.println("\n--- Testing Invalid Withdrawal ---");
            try {
                account1.withdraw(-50.00);
            } catch (InvalidAmountException e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
            
            customer1.displayCustomerInfo();
            customer1.displayAllAccounts();
            
        } catch (InvalidAmountException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║              Bank Account Management Demo Completed                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
    }
}
