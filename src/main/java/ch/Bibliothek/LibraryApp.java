package ch.Bibliothek;

import ch.Bibliothek.model.Book;
import ch.Bibliothek.model.Client;
import ch.Bibliothek.service.BookService;
import ch.Bibliothek.service.ClientService;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class LibraryApp extends Application {
    private BookService bookService = new BookService();
    private ClientService clientService = new ClientService();
    private TableView<Book> booksTableView;
    private TableView<Client> clientsTableView;

    @Override
    public void start(Stage primaryStage) throws IOException {
        bookService.loadBooksFromFile();  // Bücher laden
        clientService.loadClientsFromFile();  // Clients laden

        booksTableView = createBooksTableView();
        clientsTableView = createClientsTableView();

        booksTableView = createBooksTableView();
        clientsTableView = createClientsTableView();

        VBox booksContent = createBooksContent();
        VBox clientsContent = createClientsContent();

        TabPane tabPane = new TabPane();
        Tab booksTab = new Tab("Books", booksContent);
        booksTab.setClosable(false);
        Tab clientsTab = new Tab("Clients", clientsContent);
        clientsTab.setClosable(false);

        tabPane.getTabs().addAll(booksTab, clientsTab);

        Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createBooksContent() {
        TextField titleField = new TextField();
        titleField.setPromptText("Enter book title");
        TextField authorField = new TextField();
        authorField.setPromptText("Enter author name");
        Button addButton = new Button("Add Book");
        addButton.setOnAction(e -> {
            try {
                Book book = new Book(titleField.getText(), authorField.getText());
                bookService.addBook(book);
                refreshBookTableView();
                titleField.clear();
                authorField.clear();
            } catch (IOException ex) {
                showAlert("Error Adding Book", ex.getMessage());
            }
        });

        return new VBox(5, titleField, authorField, addButton, booksTableView);
    }

    private TableView<Book> createBooksTableView() {
        TableView<Book> tableView = new TableView<>();
        tableView.setItems(FXCollections.observableArrayList(bookService.getAllBooks()));

        TableColumn<Book, Void> actionCol = new TableColumn<>("Actions");
        actionCol.setCellFactory(col -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            private final Button lendButton = new Button("Lend");
            private final Button returnButton = new Button("Return");
            private final HBox pane = new HBox(5, editButton, deleteButton, lendButton, returnButton);

            {
                editButton.setOnAction(event -> {
                    Book book = getTableView().getItems().get(getIndex());
                    showEditBookDialog(book);
                });
                deleteButton.setOnAction(event -> {
                    Book book = getTableView().getItems().get(getIndex());
                    try {
                        bookService.deleteBook(book);
                        tableView.getItems().remove(book);
                    } catch (IOException e) {
                        showAlert("Error Deleting Book", e.getMessage());
                    }
                });
                lendButton.setOnAction(event -> lendBook(getTableView().getItems().get(getIndex())));
                returnButton.setOnAction(event -> returnBook(getTableView().getItems().get(getIndex())));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : pane);
            }
        });

        TableColumn<Book, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, Boolean> borrowedCol = new TableColumn<>("Borrowed");
        borrowedCol.setCellValueFactory(new PropertyValueFactory<>("borrowed"));
        borrowedCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(item != null && item ? "Yes" : "No");
            }
        });

        TableColumn<Book, String> borrowedByCol = new TableColumn<>("Borrowed by");
        borrowedByCol.setCellValueFactory(cellData -> {
            Optional<Client> client = clientService.findClientById(cellData.getValue().getBorrowedByClientId());
            return new SimpleStringProperty(client.map(Client::getName).orElse(""));
        });

        tableView.getColumns().addAll(actionCol, idCol, titleCol, authorCol, borrowedCol, borrowedByCol);
        return tableView;
    }

    private VBox createClientsContent() {
        TextField clientNameField = new TextField();
        clientNameField.setPromptText("Enter client's name");
        Button addButton = new Button("Add Client");
        addButton.setOnAction(e -> {
            try {
                Client client = new Client(clientNameField.getText());
                clientService.addClient(client);
                refreshClientTableView();
                clientNameField.clear();
            } catch (IOException ex) {
                showAlert("Error Adding Client", ex.getMessage());
            }
        });

        return new VBox(5, clientNameField, addButton, clientsTableView);
    }

    private TableView<Client> createClientsTableView() {
        TableView<Client> tableView = new TableView<>();
        tableView.setItems(FXCollections.observableArrayList(clientService.getAllClients()));

        TableColumn<Client, Void> actionCol = new TableColumn<>("Actions");
        actionCol.setCellFactory(col -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            private final HBox pane = new HBox(5, editButton, deleteButton);

            {
                editButton.setOnAction(event -> {
                    Client client = getTableView().getItems().get(getIndex());
                    showEditClientDialog(client);
                });
                deleteButton.setOnAction(event -> {
                    Client client = getTableView().getItems().get(getIndex());
                    try {
                        clientService.deleteClient(client);
                        tableView.getItems().remove(client);
                    } catch (IOException e) {
                        showAlert("Error Deleting Client", e.getMessage());
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : pane);
            }
        });

        TableColumn<Client, String> nameCol = new TableColumn<>("Client Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Client, String> idCol = new TableColumn<>("Client ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        tableView.getColumns().addAll(actionCol, nameCol, idCol);
        return tableView;
    }

    private void refreshBookTableView() {
        Platform.runLater(() -> {
            ObservableList<Book> books = FXCollections.observableArrayList(bookService.getAllBooks());
            booksTableView.setItems(books);
        });
    }

    private void refreshClientTableView() {
        ObservableList<Client> clients = FXCollections.observableArrayList(clientService.getAllClients());
        clientsTableView.setItems(clients);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showEditBookDialog(Book book) {
        Dialog<Book> dialog = new Dialog<>();
        dialog.setTitle("Edit Book");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField titleField = new TextField(book.getTitle());
        TextField authorField = new TextField(book.getAuthor());

        grid.add(new Label("Title:"), 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(new Label("Author:"), 0, 1);
        grid.add(authorField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                book.setTitle(titleField.getText());
                book.setAuthor(authorField.getText());
                try {
                    bookService.updateBook(book);
                    refreshBookTableView();
                    booksTableView.refresh();
                } catch (IOException e) {
                    showAlert("Error", "Failed to update book: " + e.getMessage());
                }
                return book;
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void showEditClientDialog(Client client) {
        Dialog<Client> dialog = new Dialog<>();
        dialog.setTitle("Edit Client");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField nameField = new TextField(client.getName());

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);

        dialog.getDialogPane().setContent(grid);

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                client.setName(nameField.getText());
                try {
                    clientService.updateClient(client);
                    refreshClientTableView();
                } catch (IOException e) {
                    showAlert("Error", "Failed to update client: " + e.getMessage());
                }
                return client;
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void lendBook(Book book) {
        ChoiceDialog<Client> dialog = new ChoiceDialog<>(null, clientService.getAllClients());
        dialog.setTitle("Lend Book");
        dialog.setHeaderText("Select a client to lend the book to:");
        dialog.setContentText("Choose client:");

        Optional<Client> result = dialog.showAndWait();
        result.ifPresent(client -> {
            book.lendBook(client.getId());
            try {
                bookService.updateBook(book);
                refreshBookTableView();
                booksTableView.refresh();
            } catch (IOException e) {
                showAlert("Error", "Failed to lend book: " + e.getMessage());
            }
        });
    }

    private void returnBook(Book book) {
        book.returnBook();
        try {
            bookService.updateBook(book);
            refreshBookTableView();
            booksTableView.refresh();
        } catch (IOException e) {
            showAlert("Error", "Failed to return book: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
