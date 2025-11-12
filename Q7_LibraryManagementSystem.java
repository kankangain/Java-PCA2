// Question 7: Library Management System

import java.util.ArrayList;
import java.util.List;

class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isIssued;
    private String issuedTo;
    
    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = false;
        this.issuedTo = null;
    }
    
    public int getBookId() {
        return bookId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public boolean isIssued() {
        return isIssued;
    }
    
    public String getIssuedTo() {
        return issuedTo;
    }
    
    public void setIssued(boolean issued) {
        this.isIssued = issued;
    }
    
    public void setIssuedTo(String memberName) {
        this.issuedTo = memberName;
    }
    
    @Override
    public String toString() {
        String status = isIssued ? "Issued to: " + issuedTo : "Available";
        return String.format("ID: %d | Title: %s | Author: %s | Status: %s", 
                           bookId, title, author, status);
    }
}

class Member {
    private int memberId;
    private String name;
    private String email;
    private List<Book> issuedBooks;
    
    public Member(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.issuedBooks = new ArrayList<>();
    }
    
    public int getMemberId() {
        return memberId;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public List<Book> getIssuedBooks() {
        return issuedBooks;
    }
    
    public void addIssuedBook(Book book) {
        issuedBooks.add(book);
    }
    
    public void removeIssuedBook(Book book) {
        issuedBooks.remove(book);
    }
    
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Email: %s | Books Issued: %d", 
                           memberId, name, email, issuedBooks.size());
    }
}

class Library {
    private String libraryName;
    private List<Book> books;
    private List<Member> members;
    private List<Book> issuedBooks;
    
    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.issuedBooks = new ArrayList<>();
    }
    
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully: " + book.getTitle());
    }
    
    public void addMember(Member member) {
        members.add(member);
        System.out.println("Member registered successfully: " + member.getName());
    }
    
    public void issueBook(int bookId, int memberId) {
        Book book = findBookById(bookId);
        Member member = findMemberById(memberId);
        
        if (book == null) {
            System.out.println("Error: Book not found with ID " + bookId);
            return;
        }
        
        if (member == null) {
            System.out.println("Error: Member not found with ID " + memberId);
            return;
        }
        
        if (book.isIssued()) {
            System.out.println("Error: Book '" + book.getTitle() + "' is already issued to " + book.getIssuedTo());
            return;
        }
        
        book.setIssued(true);
        book.setIssuedTo(member.getName());
        member.addIssuedBook(book);
        issuedBooks.add(book);
        
        System.out.println("Success: Book '" + book.getTitle() + "' issued to " + member.getName());
    }
    
    public void returnBook(int bookId) {
        Book book = findBookById(bookId);
        
        if (book == null) {
            System.out.println("Error: Book not found with ID " + bookId);
            return;
        }
        
        if (!book.isIssued()) {
            System.out.println("Error: Book '" + book.getTitle() + "' is not issued");
            return;
        }
        
        String memberName = book.getIssuedTo();
        Member member = findMemberByName(memberName);
        
        if (member != null) {
            member.removeIssuedBook(book);
        }
        
        book.setIssued(false);
        book.setIssuedTo(null);
        issuedBooks.remove(book);
        
        System.out.println("Success: Book '" + book.getTitle() + "' returned by " + memberName);
    }
    
    public void displayAvailableBooks() {
        System.out.println("\n=== Available Books in " + libraryName + " ===");
        boolean hasAvailable = false;
        
        for (Book book : books) {
            if (!book.isIssued()) {
                System.out.println(book);
                hasAvailable = true;
            }
        }
        
        if (!hasAvailable) {
            System.out.println("No books available at the moment.");
        }
    }
    
    public void displayIssuedBooks() {
        System.out.println("\n=== Issued Books ===");
        
        if (issuedBooks.isEmpty()) {
            System.out.println("No books are currently issued.");
        } else {
            for (Book book : issuedBooks) {
                System.out.println(book);
            }
        }
    }
    
    public void displayAllBooks() {
        System.out.println("\n=== All Books in " + libraryName + " ===");
        
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
    
    public void displayAllMembers() {
        System.out.println("\n=== Library Members ===");
        
        if (members.isEmpty()) {
            System.out.println("No members registered.");
        } else {
            for (Member member : members) {
                System.out.println(member);
            }
        }
    }
    
    public void displayMemberBooks(int memberId) {
        Member member = findMemberById(memberId);
        
        if (member == null) {
            System.out.println("Error: Member not found with ID " + memberId);
            return;
        }
        
        System.out.println("\n=== Books issued to " + member.getName() + " ===");
        List<Book> memberBooks = member.getIssuedBooks();
        
        if (memberBooks.isEmpty()) {
            System.out.println("No books currently issued to this member.");
        } else {
            for (Book book : memberBooks) {
                System.out.println(book);
            }
        }
    }
    
    private Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }
    
    private Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }
    
    private Member findMemberByName(String name) {
        for (Member member : members) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }
}

public class Q7_LibraryManagementSystem {
    public static void main(String[] args) {
        System.out.println("=== Library Management System ===\n");
        
        Library library = new Library("City Public Library");
        
        System.out.println("--- Adding Books ---");
        library.addBook(new Book(101, "Java Programming", "James Gosling"));
        library.addBook(new Book(102, "Python Basics", "Guido van Rossum"));
        library.addBook(new Book(103, "Data Structures", "Thomas Cormen"));
        library.addBook(new Book(104, "Clean Code", "Robert Martin"));
        library.addBook(new Book(105, "Design Patterns", "Gang of Four"));
        
        System.out.println("\n--- Registering Members ---");
        library.addMember(new Member(1, "Alice Johnson", "alice@email.com"));
        library.addMember(new Member(2, "Bob Smith", "bob@email.com"));
        library.addMember(new Member(3, "Charlie Brown", "charlie@email.com"));
        
        library.displayAllBooks();
        library.displayAllMembers();
        
        System.out.println("\n--- Issuing Books ---");
        library.issueBook(101, 1);
        library.issueBook(103, 2);
        library.issueBook(105, 1);
        
        System.out.println();
        library.issueBook(101, 3);
        
        library.displayAvailableBooks();
        library.displayIssuedBooks();
        library.displayMemberBooks(1);
        
        System.out.println("\n--- Returning Books ---");
        library.returnBook(101);
        library.returnBook(103);
        
        library.displayAvailableBooks();
        library.displayMemberBooks(1);
        
        System.out.println("\n=== Library Management System Demo Completed ===");
    }
}
