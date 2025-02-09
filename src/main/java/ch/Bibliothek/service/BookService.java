package ch.Bibliothek.service;

import ch.Bibliothek.model.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookService {
    private List<Book> books = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String BOOKS_FILE = "src/main/resources/buecherListe.json"; // Pfad zur JSON-Datei

    public void addBook(Book book) throws IOException {
        books.add(book);
        saveBooksToFile();
    }

    public void deleteBook(Book book) throws IOException {
        books.remove(book);
        saveBooksToFile();
    }

    public void updateBook(Book book) throws IOException {
        if (book.getId() == null) {
            logger.error("Attempt to update book without ID");
            throw new IllegalArgumentException("Book must have an ID to update");
        }
        Optional<Book> bookToUpdate = books.stream()
                .filter(b -> b.getId().equals(book.getId()))
                .findFirst();

        if (bookToUpdate.isPresent()) {
            Book foundBook = bookToUpdate.get();
            foundBook.setTitle(book.getTitle());
            foundBook.setAuthor(book.getAuthor());
            foundBook.setBorrowed(book.isBorrowed());
            foundBook.setBorrowedByClientId(book.getBorrowedByClientId());
            saveBooksToFile();
        } else {
            logger.error("No book found with ID: {}", book.getId());
            throw new IOException("No book found with ID: " + book.getId());
        }
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    private void saveBooksToFile() throws IOException {
        try (Writer writer = new FileWriter(BOOKS_FILE)) {
            gson.toJson(books, writer);
        } catch (IOException e) {
            logger.error("Failed to save books", e);
            throw e;
        }
    }

    public void loadBooksFromFile() {
        File file = new File("src/main/resources/buecherListe.json");
        if (!file.exists()) {
            logger.error("Books file does not exist: {}", file.getAbsolutePath());
            return;
        }

        try (Reader reader = new FileReader(file)) {
            Type listOfBooksType = new TypeToken<ArrayList<Book>>() {}.getType();
            books = gson.fromJson(reader, listOfBooksType);
            if (books == null) {
                books = new ArrayList<>();
            }
            logger.info("Loaded books: {}", books.size());
        } catch (FileNotFoundException e) {
            logger.error("Failed to find the books file: {}", file.getAbsolutePath(), e);
        } catch (IOException e) {
            logger.error("Failed to read the books file: {}", file.getAbsolutePath(), e);
        }
    }

}
