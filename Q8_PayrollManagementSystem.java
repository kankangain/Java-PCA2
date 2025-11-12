import java.util.ArrayList;
import java.util.List;

class Employee {
    private String name;
    private int id;
    private double hoursWorked;
    private double hourlyRate;
    private double salary;
    
    public Employee(String name, int id, double hoursWorked, double hourlyRate) {
        this.name = name;
        this.id = id;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
        this.salary = 0.0;
    }
    
    public String getName() {
        return name;
    }
    
    public int getId() {
        return id;
    }
    
    public double getHoursWorked() {
        return hoursWorked;
    }
    
    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
    
    public double getHourlyRate() {
        return hourlyRate;
    }
    
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public void calculateSalary() {
        double regularHours = 40.0;
        double overtimeMultiplier = 1.5;
        
        if (hoursWorked <= regularHours) {
            salary = hoursWorked * hourlyRate;
        } else {
            double regularPay = regularHours * hourlyRate;
            double overtimeHours = hoursWorked - regularHours;
            double overtimePay = overtimeHours * hourlyRate * overtimeMultiplier;
            salary = regularPay + overtimePay;
        }
    }
    
    public void displayDetails() {
        System.out.println("\n--- Employee Details ---");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Hours Worked: " + hoursWorked);
        System.out.println("Hourly Rate: $" + String.format("%.2f", hourlyRate));
        System.out.println("Calculated Salary: $" + String.format("%.2f", salary));
    }
    
    @Override
    public String toString() {
        return String.format("%-5d | %-20s | %8.2f | $%-10.2f | $%-12.2f", 
                           id, name, hoursWorked, hourlyRate, salary);
    }
}

class PayrollSystem {
    private List<Employee> employees;
    private String companyName;
    
    public PayrollSystem(String companyName) {
        this.companyName = companyName;
        this.employees = new ArrayList<>();
    }
    
    public void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Employee added: " + employee.getName());
    }
    
    public boolean removeEmployee(int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                employees.remove(emp);
                System.out.println("Employee removed: " + emp.getName());
                return true;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
        return false;
    }
    
    public Employee findEmployeeById(int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                return emp;
            }
        }
        return null;
    }
    
    public void calculateAllSalaries() {
        System.out.println("\n=== Calculating Salaries for All Employees ===");
        for (Employee emp : employees) {
            emp.calculateSalary();
            System.out.println("Calculated salary for " + emp.getName() + 
                             ": $" + String.format("%.2f", emp.getSalary()));
        }
    }
    
    public void generatePayrollReport() {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║              " + companyName + " - PAYROLL REPORT                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println(String.format("%-5s | %-20s | %-8s | %-11s | %-12s", 
                                       "ID", "Name", "Hours", "Rate/Hour", "Total Salary"));
        System.out.println("─────────────────────────────────────────────────────────────────────────");
        
        double totalPayroll = 0.0;
        
        for (Employee emp : employees) {
            System.out.println(emp);
            totalPayroll += emp.getSalary();
        }
        
        System.out.println("─────────────────────────────────────────────────────────────────────────");
        System.out.println(String.format("%-38s Total Payroll: $%-12.2f", "", totalPayroll));
        System.out.println(String.format("%-38s Total Employees: %-12d", "", employees.size()));
        System.out.println(String.format("%-38s Average Salary: $%-12.2f", "", 
                                       employees.size() > 0 ? totalPayroll / employees.size() : 0));
        System.out.println("═════════════════════════════════════════════════════════════════════════");
    }
    
    public void generatePayrollSlip(int employeeId) {
        Employee emp = findEmployeeById(employeeId);
        
        if (emp == null) {
            System.out.println("Employee with ID " + employeeId + " not found.");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                      PAYROLL SLIP                              ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║  Company: " + String.format("%-52s", companyName) + "║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║  Employee ID:   " + String.format("%-45d", emp.getId()) + "║");
        System.out.println("║  Name:          " + String.format("%-45s", emp.getName()) + "║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║  Hours Worked:  " + String.format("%-45.2f", emp.getHoursWorked()) + "║");
        System.out.println("║  Hourly Rate:   $" + String.format("%-44.2f", emp.getHourlyRate()) + "║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        
        double regularHours = Math.min(emp.getHoursWorked(), 40.0);
        double overtimeHours = Math.max(emp.getHoursWorked() - 40.0, 0);
        double regularPay = regularHours * emp.getHourlyRate();
        double overtimePay = overtimeHours * emp.getHourlyRate() * 1.5;
        
        System.out.println("║  Regular Hours: " + String.format("%-45.2f", regularHours) + "║");
        System.out.println("║  Regular Pay:   $" + String.format("%-44.2f", regularPay) + "║");
        
        if (overtimeHours > 0) {
            System.out.println("║  Overtime Hours:" + String.format("%-45.2f", overtimeHours) + "║");
            System.out.println("║  Overtime Pay:  $" + String.format("%-44.2f", overtimePay) + "║");
        }
        
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║  TOTAL SALARY:  $" + String.format("%-44.2f", emp.getSalary()) + "║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
    }
    
    public void updateEmployeeHours(int id, double newHours) {
        Employee emp = findEmployeeById(id);
        if (emp != null) {
            emp.setHoursWorked(newHours);
            emp.calculateSalary();
            System.out.println("Updated hours for " + emp.getName() + " to " + newHours);
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }
    
    public void displayAllEmployees() {
        System.out.println("\n=== All Employees ===");
        if (employees.isEmpty()) {
            System.out.println("No employees in the system.");
        } else {
            for (Employee emp : employees) {
                System.out.println("ID: " + emp.getId() + " | Name: " + emp.getName() + 
                                 " | Hours: " + emp.getHoursWorked() + 
                                 " | Rate: $" + emp.getHourlyRate());
            }
        }
    }
}

public class Q8_PayrollManagementSystem {
    public static void main(String[] args) {
        System.out.println("=== Payroll Management System ===\n");
        
        PayrollSystem payroll = new PayrollSystem("Tech Solutions Inc.");
        
        System.out.println("--- Adding Employees ---");
        payroll.addEmployee(new Employee("John Doe", 1001, 45.0, 25.00));
        payroll.addEmployee(new Employee("Jane Smith", 1002, 40.0, 30.00));
        payroll.addEmployee(new Employee("Mike Johnson", 1003, 50.0, 28.00));
        payroll.addEmployee(new Employee("Sarah Williams", 1004, 38.0, 32.00));
        payroll.addEmployee(new Employee("Tom Brown", 1005, 42.0, 27.00));
        
        payroll.displayAllEmployees();
        payroll.calculateAllSalaries();
        payroll.generatePayrollReport();
        
        System.out.println("\n--- Individual Payroll Slip ---");
        payroll.generatePayrollSlip(1003);
        
        System.out.println("\n--- Updating Employee Hours ---");
        payroll.updateEmployeeHours(1002, 44.0);
        
        payroll.generatePayrollReport();
        payroll.generatePayrollSlip(1002);
        
        System.out.println("\n=== Payroll Management System Demo Completed ===");
    }
}
