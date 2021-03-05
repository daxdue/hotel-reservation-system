package dao.implementations;

import dao.interfaces.ClientDaoInterface;
import models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoPostgres implements ClientDaoInterface {

    private Connection connection = null;


    public ClientDaoPostgres(Connection connection) {
        this.connection = connection;
    }

    public Client save(Client client) {
        String query = "INSERT INTO clients (login, first_name, last_name) VALUES (?, ?, ?)";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, client.getLogin());
            statement.setString(2, client.getFirstName());
            statement.setString(3, client.getLastName());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if(statement != null) statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    public void update(Client client, Long id) {
        String query = "UPDATE clients set first_name=?, last_name=? where id=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setLong(3, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if(statement != null) statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Client get(Long id) {
        Client client = new Client();
        String query = "SELECT * FROM clients WHERE id=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            if(result.next()) {
                client.setId(result.getLong("id"));
                client.setLogin(result.getString("login"));
                client.setFirstName(result.getString("first_name"));
                client.setLastName(result.getString("last_name"));
            } else {
                client = null;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if(statement != null) statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return client;
    }

    public void remove(Long id) {
        String  query = "DELETE FROM clients WHERE id=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                if(statement != null) statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Client> getClients() {
        Client client = new Client();
        List<Client> clientList = new ArrayList<Client>();
        String query = "SELECT * FROM clients";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                client = new Client();
                client.setId(result.getLong("id"));
                client.setLogin(result.getString("login"));
                client.setFirstName(result.getString("first_name"));
                client.setLastName(result.getString("last_name"));
                clientList.add(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return clientList;
    }

    public Client getClientByLogin(String login) {
        Client client = new Client();
        String query = "SELECT * FROM clients WHERE login=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            if(result.next()) {
                client.setId(result.getLong("id"));
                client.setLogin(result.getString("login"));
                client.setFirstName(result.getString("first_name"));
                client.setLastName(result.getString("last_name"));
            } else {
                client = null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return client;
    }


}
