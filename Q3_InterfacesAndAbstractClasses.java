abstract class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    public abstract void makeSound();
    
    public void sleep() {
        System.out.println(name + " is sleeping...");
    }
    
    public void displayInfo() {
        System.out.println("Animal Name: " + name);
    }
}

interface Flyable {
    void fly();
    
    default void land() {
        System.out.println("Landing...");
    }
}

interface Swimmable {
    void swim();
}

interface Amphibious extends Swimmable {
    void liveOnLand();
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " says: Woof! Woof!");
    }
}

class Bird extends Animal implements Flyable {
    public Bird(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " says: Tweet! Tweet!");
    }
    
    @Override
    public void fly() {
        System.out.println(name + " is flying high in the sky!");
    }
}

class Duck extends Animal implements Flyable, Swimmable {
    public Duck(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " says: Quack! Quack!");
    }
    
    @Override
    public void fly() {
        System.out.println(name + " is flying!");
    }
    
    @Override
    public void swim() {
        System.out.println(name + " is swimming in the pond!");
    }
}

class Frog extends Animal implements Amphibious {
    public Frog(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " says: Ribbit! Ribbit!");
    }
    
    @Override
    public void swim() {
        System.out.println(name + " is swimming in the water!");
    }
    
    @Override
    public void liveOnLand() {
        System.out.println(name + " can live on land!");
    }
}

public class Q3_InterfacesAndAbstractClasses {
    public static void main(String[] args) {
        System.out.println("=== Abstract Class and Interface Demo ===\n");
        
        System.out.println("--- Dog ---");
        Dog dog = new Dog("Buddy");
        dog.displayInfo();
        dog.makeSound();
        dog.sleep();
        System.out.println();
        
        System.out.println("--- Bird ---");
        Bird bird = new Bird("Tweety");
        bird.displayInfo();
        bird.makeSound();
        bird.fly();
        bird.land();
        bird.sleep();
        System.out.println();
        
        System.out.println("--- Duck (Multiple Inheritance) ---");
        Duck duck = new Duck("Donald");
        duck.displayInfo();
        duck.makeSound();
        duck.fly();
        duck.swim();
        duck.land();
        duck.sleep();
        System.out.println();
        
        System.out.println("--- Frog (Amphibious) ---");
        Frog frog = new Frog("Kermit");
        frog.displayInfo();
        frog.makeSound();
        frog.swim();
        frog.liveOnLand();
        frog.sleep();
        System.out.println();
        
        System.out.println("--- Polymorphism Example ---");
        Flyable flyable = new Duck("Daffy");
        flyable.fly();
        flyable.land();
        
        Swimmable swimmable = new Duck("Scrooge");
        swimmable.swim();
    }
}
