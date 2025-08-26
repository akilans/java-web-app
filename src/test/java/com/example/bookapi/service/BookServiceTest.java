package com.example.bookapi.service;

import com.example.bookapi.model.Book;
import com.example.bookapi.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book testBook;

    @BeforeEach
    void setUp() {
        testBook = new Book(
            "Test Book",
            "Test Author",
            "978-0123456789",
            LocalDate.of(2023, 1, 1),
            "Test Description",
            29.99
        );
        testBook.setId(1L);
    }

    @Test
    void getAllBooks_ShouldReturnAllBooks() {
        List<Book> books = Arrays.asList(testBook);
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(1, result.size());
        assertEquals(testBook.getTitle(), result.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookById_ExistingId_ShouldReturnBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));

        Optional<Book> result = bookService.getBookById(1L);

        assertTrue(result.isPresent());
        assertEquals(testBook.getTitle(), result.get().getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void getBookById_NonExistingId_ShouldReturnEmpty() {
        when(bookRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Book> result = bookService.getBookById(999L);

        assertFalse(result.isPresent());
        verify(bookRepository, times(1)).findById(999L);
    }

    @Test
    void saveBook_ShouldReturnSavedBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);

        Book result = bookService.saveBook(testBook);

        assertEquals(testBook.getTitle(), result.getTitle());
        verify(bookRepository, times(1)).save(testBook);
    }

    @Test
    void updateBook_ExistingBook_ShouldReturnUpdatedBook() {
        Book updatedDetails = new Book(
            "Updated Title",
            "Updated Author",
            "978-0987654321",
            LocalDate.of(2023, 12, 31),
            "Updated Description",
            39.99
        );
        
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);

        Book result = bookService.updateBook(1L, updatedDetails);

        assertNotNull(result);
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(testBook);
    }

    @Test
    void deleteBook_ExistingBook_ShouldReturnTrue() {
        when(bookRepository.existsById(1L)).thenReturn(true);

        boolean result = bookService.deleteBook(1L);

        assertTrue(result);
        verify(bookRepository, times(1)).existsById(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteBook_NonExistingBook_ShouldReturnFalse() {
        when(bookRepository.existsById(999L)).thenReturn(false);

        boolean result = bookService.deleteBook(999L);

        assertFalse(result);
        verify(bookRepository, times(1)).existsById(999L);
        verify(bookRepository, never()).deleteById(999L);
    }

    @Test
    void existsByIsbn_ExistingIsbn_ShouldReturnTrue() {
        when(bookRepository.findByIsbn("978-0123456789")).thenReturn(Optional.of(testBook));

        boolean result = bookService.existsByIsbn("978-0123456789");

        assertTrue(result);
        verify(bookRepository, times(1)).findByIsbn("978-0123456789");
    }
}
