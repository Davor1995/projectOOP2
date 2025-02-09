package ch.Bibliothek.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Book implements Serializable {
    private String id;
    private String title;
    private String author;
    private boolean isBorrowed;
    private String borrowedByClientId;
    private static final Logger logger = LoggerFactory.getLogger(Book.class);

    public Book(String title, String author) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
        this.borrowedByClientId = null;
        logger.debug("Book created with ID: {}", this.id);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public String getBorrowedByClientId() {
        return borrowedByClientId;
    }

    public void setBorrowedByClientId(String borrowedByClientId) {
        this.borrowedByClientId = borrowedByClientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isBorrowed=" + isBorrowed +
                (isBorrowed ? ", borrowedBy=" + borrowedByClientId : "") +
                '}';
    }

    public void lendBook(String clientId) {
        this.isBorrowed = true;
        this.borrowedByClientId = clientId;
    }

    public void returnBook() {
        this.isBorrowed = false;
        this.borrowedByClientId = null;
    }
}
