package ch.Bibliothek.service;

import ch.Bibliothek.model.Client;
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
import java.util.stream.Collectors;

public class ClientService {
    private List<Client> clients = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String CLIENTS_FILE = "src/main/resources/clients.json"; // Pfad zur JSON-Datei

    public void addClient(Client client) throws IOException {
        clients.add(client);
        saveClientsToFile();
    }

    public void deleteClient(Client client) throws IOException {
        clients.remove(client);
        saveClientsToFile();
    }

    public void updateClient(Client client) throws IOException {
        Optional<Client> clientToUpdate = clients.stream()
                .filter(c -> c.getId().equals(client.getId()))
                .findFirst();

        clientToUpdate.ifPresent(c -> {
            c.setName(client.getName());
            logger.debug("Client updated: {}", c);
        });

        if (!clientToUpdate.isPresent()) {
            logger.error("No client found with ID: {}", client.getId());
            throw new IOException("No client found with ID: " + client.getId());
        }

        saveClientsToFile();
    }

    public List<Client> getAllClients() {
        return new ArrayList<>(clients);
    }

    private void saveClientsToFile() throws IOException {
        try (Writer writer = new FileWriter(CLIENTS_FILE)) {
            gson.toJson(clients, writer);
        } catch (IOException e) {
            logger.error("Failed to save clients", e);
            throw e;
        }
    }

    public void loadClientsFromFile() {
        File file = new File("src/main/resources/clients.json");
        if (!file.exists()) {
            logger.error("Clients file does not exist: {}", file.getAbsolutePath());
            clients = new ArrayList<>();
            return;
        }

        try (Reader reader = new FileReader(file)) {
            Type listOfClientsType = new TypeToken<ArrayList<Client>>() {}.getType();
            clients = gson.fromJson(reader, listOfClientsType);
            if (clients == null) {
                clients = new ArrayList<>();
            }
            logger.info("Loaded clients: {}", clients.size());
        } catch (FileNotFoundException e) {
            logger.error("Failed to find the clients file: {}", file.getAbsolutePath(), e);
            clients = new ArrayList<>();
        } catch (IOException e) {
            logger.error("Failed to read the clients file: {}", file.getAbsolutePath(), e);
            clients = new ArrayList<>();
        }
    }

    public Optional<Client> findClientById(String id) {
        return clients.stream().filter(client -> client.getId().equals(id)).findFirst();
    }
}
