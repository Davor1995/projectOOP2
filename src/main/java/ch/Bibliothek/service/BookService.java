package ch.Bibliothek.service;

import ch.Bibliothek.model.Book;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private static final String FILE_PATH = "src/main/resources/buecherListe.json";
    private final Gson gson = new Gson();
    private List<Book> books = new ArrayList<>();

    // Buch hinzufügen
    public void addBook(Book book) {

        books.add(book);
        try {
            saveBooksToFile();
        } catch (IOException e) {
            System.out.println("Failed to save books: " + e.getMessage());
        }
    }

    // Alle Bücher ausgeben
    public List<Book> getAllBooks() {
        try {
            loadBooksFromFile();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load books: " + e.getMessage());
        }
        return books;
    }

    // Bücher in Json speichern
    public void saveBooksToFile() throws IOException {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(books, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Methode zum Laden von Büchern
    public void loadBooksFromFile() throws IOException, ClassNotFoundException {
        try (Reader reader = new FileReader(FILE_PATH)) {
            books = new Gson().fromJson(reader, new TypeToken<List<Book>>() {}.getType());
            if (books == null) {
                books = new ArrayList<>();
            }
        } catch (FileNotFoundException e) {
            books = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
