package ch.Bibliothek.Service;

import ch.Bibliothek.model.Book;
import ch.Bibliothek.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest {

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
    }

    @Test
    void testAddBook() throws IOException {
        Book book = new Book("TestTitle", "TestAuthor");
        bookService.addBook(book);
        Book retrievedBook = bookService.getAllBooks().get(0);
        assertEquals(book, retrievedBook, "The added book should be the same as the retrieved book");
    }


    @Test
    void testLoadAndSaveBooks() throws IOException {
        // Vorher sicherstellen, dass keine Bücher geladen sind
        bookService = new BookService(); // Neue Instanz, um sicherzugehen, dass sie leer ist

        Book testBook = new Book("TestTitle", "TestAuthor");
        bookService.addBook(testBook); // Fügt ein Buch hinzu
        bookService.loadBooksFromFile(); // Lädt die Datei neu

        // Prüfen, ob das Buch gespeichert und geladen wurde
        List<Book> books = bookService.getAllBooks();
        assertEquals(1, books.size(), "Books should be loaded correctly");
        assertEquals("TestTitle", books.get(0).getTitle(), "Title should match");
        assertEquals("TestAuthor", books.get(0).getAuthor(), "Author should match");
    }


    @Test
    void testBorrowAndReturnBook() throws IOException {
        Book book = new Book("1984", "George Orwell");
        bookService.addBook(book);
        String clientId = "client123";
        book.lendBook(clientId);
        assertTrue(book.isBorrowed(), "Book should be marked as borrowed.");
        assertEquals(clientId, book.getBorrowedByClientId(), "Borrowed by client ID should match.");

        book.returnBook();
        assertFalse(book.isBorrowed(), "Book should be marked as not borrowed.");
        assertNull(book.getBorrowedByClientId(), "Borrowed by client ID should be null after returning.");
    }
}

