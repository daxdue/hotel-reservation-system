package services;

import dao.implementations.ClientDaoPostgres;
import dao.implementations.ConnectionFactory;
import dao.interfaces.ClientDaoInterface;
import enums.SourceType;
import exceptions.AlreadyExistsException;
import exceptions.NotFoundException;
import models.Client;

import java.util.List;

public class ClientService {

    private ClientDaoInterface clientDao;

    public ClientService() {
        clientDao = new ClientDaoPostgres(ConnectionFactory.getConnection(SourceType.POSTGRES));
    }

    public Client findClient(Long id) {
        Client client = clientDao.get(id);
        if(client != null) {
            return client;
        }
        throw new NotFoundException();
    }

    public Client findClientByLogin(String login) {
        Client client = clientDao.getClientByLogin(login);
        if(client != null) {
            return client;
        }
        throw new NotFoundException();
    }

    public void saveClient(Client client) {
        Client clientDb = clientDao.getClientByLogin(client.getLogin());
        if(clientDb == null) {
            clientDao.save(client);
        } else {
            throw new AlreadyExistsException();
        }
    }

    public void updateClient(Client client) {
        Client clientDb = clientDao.getClientByLogin(client.getLogin());
        if(clientDb != null) {
            clientDao.update(client, clientDb.getId());
        } else {
            throw new NotFoundException();
        }
    }

    public void deleteClient(Client client) {
        Client clientDb = clientDao.getClientByLogin(client.getLogin());
        if(clientDb != null) {
            clientDao.remove(clientDb.getId());
        } else {
            throw new NotFoundException();
        }
    }



    public List<Client> findAllClients() {
        return clientDao.getClients();
    }

}
