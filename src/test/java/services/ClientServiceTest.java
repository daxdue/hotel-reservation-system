package services;


import dao.implementations.ClientDaoPostgres;
import dao.interfaces.ClientDaoInterface;
import models.Client;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ClientServiceTest {

    private ClientService clientService = null;
    private Client testClient = null;
    private List<Client> testClients = null;

    @BeforeTest
    public void setUp() {
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

        ClientDaoInterface clientDao = Mockito.mock(ClientDaoPostgres.class);
        Mockito.when(clientDao.get(Mockito.anyLong())).thenReturn(testClient);
        Mockito.when(clientDao.getClientByLogin(Mockito.anyString())).thenReturn(testClient);
        Mockito.when(clientDao.getClients()).thenReturn(testClients);
        clientService = new ClientService(clientDao);
    }

    @Test
    public void clientServiceWillReturnClientById() {
        Assert.assertEquals(testClient, clientService.findClient(Long.valueOf(1)));
    }

    @Test
    public void clientServiceWillReturnClientByLogin() {
        Assert.assertEquals(testClient, clientService.findClientByLogin("daxdue"));
    }

    @Test
    public void clientServiceWillReturnAllClients() {
        Assert.assertEquals(testClients, clientService.findAllClients());
    }

}
