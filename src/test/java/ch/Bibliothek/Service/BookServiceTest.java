package ch.Bibliothek.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.Bibliothek.model.Book;
import ch.Bibliothek.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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
    void testLoadAndSaveBooks() throws IOException, ClassNotFoundException {
        bookService.addBook(new Book("TestTitle", "TestAuthor"));
        bookService.saveBooksToFile(); // Angenommen, diese Methode wirft keine Exception und speichert in einer temporären Testdatei

        bookService.loadBooksFromFile(); // Angenommen, diese Methode lädt aus derselben temporären Testdatei
        assertEquals(1, bookService.getAllBooks().size(), "Books should be loaded correctly");
    }
}

