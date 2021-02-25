package dao.interfaces;

import models.Client;

import java.util.List;

public interface ClientDaoInterface extends CrudInterface<Client, Long> {

    List<Client> getClients();
    Client getClientByLogin(String login);
}
