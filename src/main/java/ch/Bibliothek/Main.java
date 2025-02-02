package ch.Bibliothek;

import ch.Bibliothek.model.Book;
import ch.Bibliothek.service.BookService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private BookService bookService = new BookService();

    @Override
    public void start(Stage primaryStage) {
        String fileName = "src/main/resources/buecherListe.json";

        //Bücher beim Start laden
        try {
            bookService.loadBooksFromFile();
        } catch (Exception e) {
            System.out.println("Keine gespeicherten Bücher gefunden.");
        }

        // GUI-Komponenten
        TextField titleField = new TextField();
        titleField.setPromptText("Titel");

        TextField authorField = new TextField();
        authorField.setPromptText("Autor");

        Button addButton = new Button("Buch hinzufügen");
        ListView<String> bookListView = new ListView<>();

        // Button-Action
        addButton.setOnAction(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            if (!title.isEmpty() && !author.isEmpty()) {
                Book book = new Book(title, author);
                bookService.addBook(book);
                bookListView.getItems().add(title + " von " + author);
                titleField.clear();
                authorField.clear();
            }
        });

        // Layout
        VBox root = new VBox(10, titleField, authorField, addButton, bookListView);
        Scene scene = new Scene(root, 400, 300);

        // Fenster konfigurieren
        primaryStage.setTitle("Bibliotheksverwaltung");
        primaryStage.setScene(scene);

        //Bücher beim Schliessen speichern
        primaryStage.setOnCloseRequest(e -> {
            try {
                bookService.saveBooksToFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
