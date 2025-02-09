package ch.Bibliothek.Service;

import ch.Bibliothek.model.Client;
import ch.Bibliothek.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {
    private ClientService clientService;

    @BeforeEach
    void setUp() throws IOException {
        clientService = new ClientService();

    }

    @Test
    void testAddClient() throws IOException {
        Client client = new Client("Alice");
        clientService.addClient(client);
        assertFalse(clientService.getAllClients().isEmpty(), "Client list should not be empty after adding a client.");
        assertEquals("Alice", clientService.getAllClients().get(0).getName(), "The client's name should be Alice.");
    }
}
