package dao.implementations;

import dao.interfaces.RoomOrderDaoInterface;
import enums.OrderStatus;
import enums.RoomClass;
import models.Client;
import models.Room;
import models.RoomOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RoomOrderDaoPostgres implements RoomOrderDaoInterface {

    private Connection connection = null;

    public RoomOrderDaoPostgres(Connection connection) {
        this.connection = connection;
    }

    public RoomOrder save(RoomOrder roomOrder) {
        String query = "INSERT INTO room_orders (number_of_beds, room_class, checkin, checkout, order_status, client_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            //statement.setLong(1, roomOrder.getClient().getId());
            //statement.setLong(2, roomOrder.getRoom().getId());
            statement.setInt(1, roomOrder.getNumberOfBeds());
            statement.setString(2, roomOrder.getRoomClass().name());
            statement.setDate(3, roomOrder.getCheckin());
            statement.setDate(4, roomOrder.getCheckout());
            statement.setString(5, roomOrder.getOrderStatus().name());
            statement.setLong(6, roomOrder.getClient().getId());
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

        return roomOrder;
    }

    public void update(RoomOrder roomOrder, Long id) {
        String query = "UPDATE room_orders SET number_of_beds=?, room_class=?, checkin=?, checkout=?, order_status=? WHERE id=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, roomOrder.getNumberOfBeds());
            statement.setString(2, roomOrder.getRoomClass().name());
            statement.setDate(3, roomOrder.getCheckin());
            statement.setDate(4, roomOrder.getCheckout());
            statement.setString(5, roomOrder.getOrderStatus().name());
            statement.setLong(6, id);
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

    public void updateRoom(RoomOrder roomOrder) {
        String query = "UPDATE room_orders SET room_id=? WHERE id=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, roomOrder.getRoom().getId());
            statement.setLong(2, roomOrder.getId());
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

    public RoomOrder get(Long id) {
        RoomOrder roomOrder = new RoomOrder();
        Client client = new Client();
        String query = "SELECT * FROM room_orders INNER JOIN clients ON room_orders.client_id=clients.id WHERE room_orders.id=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            if(result.next()) {
                client = new Client();
                roomOrder.setId(result.getLong("id"));
                roomOrder.setNumberOfBeds(result.getInt("number_of_beds"));
                roomOrder.setRoomClass(RoomClass.valueOf(result.getString("room_class")));
                roomOrder.setCheckin(result.getDate("checkin"));
                roomOrder.setCheckout(result.getDate("checkout"));
                roomOrder.setOrderStatus(OrderStatus.valueOf(result.getString("order_status")));
                client.setLogin(result.getString("login"));
                client.setFirstName(result.getString("first_name"));
                client.setLastName(result.getString("last_name"));
                roomOrder.setClient(client);
            } else {
                roomOrder = null;
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
        return roomOrder;
    }

    public void remove(Long id) {
        String query = "DELETE FROM room_orders WHERE id=?";
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

    public List<RoomOrder> getRoomOrders() {
        RoomOrder roomOrder = new RoomOrder();
        Client client = new Client();
        List<RoomOrder> roomOrders = new ArrayList<RoomOrder>();
        String query = "SELECT * FROM room_orders INNER JOIN clients ON room_orders.client_id=clients.id";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                roomOrder = new RoomOrder();
                client = new Client();
                roomOrder.setId(result.getLong("id"));
                roomOrder.setNumberOfBeds(result.getInt("number_of_beds"));
                roomOrder.setRoomClass(RoomClass.valueOf(result.getString("room_class")));
                roomOrder.setCheckin(result.getDate("checkin"));
                roomOrder.setCheckout(result.getDate("checkout"));
                roomOrder.setOrderStatus(OrderStatus.valueOf(result.getString("order_status")));
                client.setLogin(result.getString("login"));
                client.setFirstName(result.getString("first_name"));
                client.setLastName(result.getString("last_name"));
                roomOrder.setClient(client);
                roomOrders.add(roomOrder);
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

        return roomOrders;
    }

    public List<RoomOrder> getRoomOrderByRoomClass(RoomClass roomClass) {
        RoomOrder roomOrder = new RoomOrder();
        Client client = new Client();
        List<RoomOrder> roomOrders = new ArrayList<RoomOrder>();
        String query = "SELECT * FROM room_orders INNER JOIN clients ON room_orders.client_id=clients.id WHERE room_orders.room_class=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, roomClass.name());
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                roomOrder = new RoomOrder();
                client = new Client();
                roomOrder.setId(result.getLong("id"));
                roomOrder.setNumberOfBeds(result.getInt("number_of_beds"));
                roomOrder.setRoomClass(RoomClass.valueOf(result.getString("room_class")));
                roomOrder.setCheckin(result.getDate("checkin"));
                roomOrder.setCheckout(result.getDate("checkout"));
                roomOrder.setOrderStatus(OrderStatus.valueOf(result.getString("order_status")));
                client.setLogin(result.getString("login"));
                client.setFirstName(result.getString("first_name"));
                client.setLastName(result.getString("last_name"));
                roomOrder.setClient(client);
                roomOrders.add(roomOrder);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return roomOrders;
    }


    public List<RoomOrder> getRoomOrderByNumberOfBeds(int numberOfBeds) {
        RoomOrder roomOrder = new RoomOrder();
        Client client = new Client();
        List<RoomOrder> roomOrders = new ArrayList<RoomOrder>();
        String query = "SELECT * FROM room_orders INNER JOIN clients ON room_orders.client_id=clients.id WHERE room_orders.number_of_beds=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, numberOfBeds);
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                roomOrder = new RoomOrder();
                client = new Client();
                roomOrder.setId(result.getLong("id"));
                roomOrder.setNumberOfBeds(result.getInt("number_of_beds"));
                roomOrder.setRoomClass(RoomClass.valueOf("room_class"));
                roomOrder.setCheckin(result.getDate("checkin"));
                roomOrder.setCheckout(result.getDate("checkout"));
                roomOrder.setOrderStatus(OrderStatus.valueOf(result.getString("order_status")));
                client.setLogin(result.getString("login"));
                client.setFirstName(result.getString("first_name"));
                client.setLastName(result.getString("last_name"));
                roomOrder.setClient(client);
                roomOrders.add(roomOrder);
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

        return roomOrders;
    }

    public List<RoomOrder> getRoomOrderByCheckin(Date checkin) {
        RoomOrder roomOrder = new RoomOrder();
        Client client = new Client();
        List<RoomOrder> roomOrders = new ArrayList<RoomOrder>();
        String query = "SELECT * FROM room_orders INNER JOIN clients ON room_orders.client_id=clients.id WHERE room_orders.checkin=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setDate(1, checkin);
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                roomOrder = new RoomOrder();
                client = new Client();
                roomOrder.setId(result.getLong("id"));
                roomOrder.setNumberOfBeds(result.getInt("number_of_beds"));
                roomOrder.setRoomClass(RoomClass.valueOf(result.getString("room_class")));
                roomOrder.setCheckin(result.getDate("checkin"));
                roomOrder.setCheckout(result.getDate("checkout"));
                roomOrder.setOrderStatus(OrderStatus.valueOf(result.getString("order_status")));
                client.setLogin(result.getString("login"));
                client.setFirstName(result.getString("first_name"));
                client.setLastName(result.getString("last_name"));
                roomOrder.setClient(client);
                roomOrders.add(roomOrder);
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
        return roomOrders;
    }

    public List<RoomOrder> getRoomOrderByCheckout(Date checkout) {
        RoomOrder roomOrder = new RoomOrder();
        Client client = new Client();
        List<RoomOrder> roomOrders = new ArrayList<RoomOrder>();
        String query = "SELECT * FROM room_orders INNER JOIN clients ON room_orders.client_id=clients.id WHERE checkout=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setDate(1, checkout);
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                roomOrder = new RoomOrder();
                client = new Client();
                roomOrder.setId(result.getLong("id"));
                roomOrder.setNumberOfBeds(result.getInt("number_of_beds"));
                roomOrder.setOrderStatus(OrderStatus.valueOf(result.getString("order_status")));
                roomOrder.setCheckin(result.getDate("checkin"));
                roomOrder.setCheckout(result.getDate("checkout"));
                roomOrder.setOrderStatus(OrderStatus.valueOf(result.getString("order_status")));
                client.setLogin(result.getString("login"));
                client.setFirstName(result.getString("first_name"));
                client.setLastName(result.getString("last_name"));
                roomOrder.setClient(client);
                roomOrders.add(roomOrder);
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
        return roomOrders;
    }

    public List<RoomOrder> getRoomOrdersByCheckinAndCheckout(Date checkin, Date checkout) {
        RoomOrder roomOrder = new RoomOrder();
        Client client = new Client();
        List<RoomOrder> roomOrders = new ArrayList<RoomOrder>();
        String query = "SELECT * FROM room_orders INNER JOIN clients ON room_orders.client_id=clients.id WHERE checkin=? AND checkout=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setDate(1, checkin);
            statement.setDate(2, checkout);
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                roomOrder = new RoomOrder();
                client = new Client();
                roomOrder.setId(result.getLong("id"));
                roomOrder.setNumberOfBeds(result.getInt("number_of_beds"));
                roomOrder.setRoomClass(RoomClass.valueOf(result.getString("room_class")));
                roomOrder.setOrderStatus(OrderStatus.valueOf(result.getString("order_status")));
                roomOrder.setCheckin(result.getDate("checkin"));
                roomOrder.setCheckout(result.getDate("checkout"));
                client.setLogin(result.getString("login"));
                client.setFirstName(result.getString("first_name"));
                client.setLastName(result.getString("last_name"));
                roomOrder.setClient(client);
                roomOrders.add(roomOrder);
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

        return roomOrders;
    }

    public List<RoomOrder> getRoomOrdersByClient(Long clientId) {
        RoomOrder roomOrder = new RoomOrder();
        Client client = new Client();
        List<RoomOrder> roomOrders = new ArrayList<RoomOrder>();
        String query = "SELECT * FROM room_orders INNER JOIN clients ON room_orders.client_id=clients.id WHERE room_orders.client_id=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, clientId);
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                roomOrder = new RoomOrder();
                client = new Client();
                roomOrder.setId(result.getLong("id"));
                roomOrder.setNumberOfBeds(result.getInt("number_of_beds"));
                roomOrder.setRoomClass(RoomClass.valueOf(result.getString("room_class")));
                roomOrder.setOrderStatus(OrderStatus.valueOf(result.getString("order_status")));
                roomOrder.setCheckin(result.getDate("checkin"));
                roomOrder.setCheckout(result.getDate("checkout"));
                client.setLogin(result.getString("login"));
                client.setFirstName(result.getString("first_name"));
                client.setLastName(result.getString("last_name"));
                roomOrder.setClient(client);
                roomOrders.add(roomOrder);
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
        return roomOrders;
    }

}
