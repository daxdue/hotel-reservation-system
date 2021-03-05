package services;


import models.Client;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ClientServiceTest {

    private ClientService clientService;
    private Client testClient = null;
    private List<Client> testClients = null;

    @BeforeTest
    public void setUp() {
        clientService = Mockito.mock(ClientService.class);
        testClient = new Client();
        testClient.setId(Long.valueOf(1));
        testClient.setLogin("daxdue");
        testClient.setFirstName("Alex");
        testClient.setLastName("Petrov");

        Client client1 = new Client(Long.valueOf(1), "daxdue", "Alex", "Petrov");
        Client client2 = new Client(Long.valueOf(2), "vasyalex@gmail.com", "Vasiliy", "Alexeev");
        Client client3 = new Client(Long.valueOf(3), "andrydobrov@yandex.ru", "Andrei", "Dobrob");
        testClients = new ArrayList<>();
        testClients.add(client1);
        testClients.add(client2);
        testClients.add(client3);
    }

    @Test
    public void clientServiceWillReturnClientById() {
        Mockito.when(clientService.findClient(Long.valueOf(1))).thenReturn(testClient);
        Client clientMocked = clientService.findClient(Long.valueOf(1));
        Assert.assertEquals(testClient, clientMocked);
    }

    @Test
    public void clientServiceWillReturnClientByLogin() {
        Mockito.when(clientService.findClientByLogin("daxdue")).thenReturn(testClient);
        Assert.assertEquals(testClient, clientService.findClientByLogin("daxdue"));
    }

    @Test
    public void clientServiceWillReturnAllClients() {
        Mockito.when(clientService.findAllClients()).thenReturn(testClients);
        Assert.assertEquals(testClients, clientService.findAllClients());
    }

}
