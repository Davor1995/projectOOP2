package ch.Bibliothek.model;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String author;

    public Book() {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return Objects.equals(title, book.title) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }


    public Book(String title, String author) {
        this.title = title;
        this.author = author;
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



}
