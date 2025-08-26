package com.example.bookapi.config;

import com.example.bookapi.model.Book;
import com.example.bookapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private final BookRepository bookRepository;
    
    @Autowired
    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Initialize with sample data
        if (bookRepository.count() == 0) {
            bookRepository.save(new Book(
                "Clean Code",
                "Robert C. Martin",
                "978-0132350884",
                LocalDate.of(2008, 8, 1),
                "A Handbook of Agile Software Craftsmanship",
                45.99
            ));
            
            bookRepository.save(new Book(
                "Effective Java",
                "Joshua Bloch",
                "978-0134685991",
                LocalDate.of(2017, 12, 27),
                "Best practices for the Java platform",
                52.99
            ));
            
            bookRepository.save(new Book(
                "Spring in Action",
                "Craig Walls",
                "978-1617294945",
                LocalDate.of(2018, 10, 2),
                "Covers Spring 5.0",
                59.99
            ));
            
            bookRepository.save(new Book(
                "Java: The Complete Reference",
                "Herbert Schildt",
                "978-1260440232",
                LocalDate.of(2020, 3, 27),
                "Comprehensive guide to Java programming",
                65.00
            ));
            
            bookRepository.save(new Book(
                "Design Patterns",
                "Gang of Four",
                "978-0201633610",
                LocalDate.of(1994, 10, 21),
                "Elements of Reusable Object-Oriented Software",
                54.95
            ));
        }
    }
}
