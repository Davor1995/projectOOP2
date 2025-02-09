package ch.Bibliothek.service;

import ch.Bibliothek.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryService {
    private List<Book> books = new ArrayList<>();

    public List<Book> searchBooks(String query) {
        return books.stream()
                .filter(book -> book.getTitle().contains(query) || book.getAuthor().contains(query))
                .collect(Collectors.toList());
    }
}
