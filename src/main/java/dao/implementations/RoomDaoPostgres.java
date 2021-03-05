package dao.implementations;

import dao.interfaces.RoomDaoInterface;
import enums.RoomClass;
import enums.RoomStatus;
import models.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoPostgres implements RoomDaoInterface {

    private Connection connection = null;

    public RoomDaoPostgres(Connection connection) {
        this.connection = connection;
    }

    public Room save(Room room) {
        String query = "INSERT INTO rooms (number_of_beds, room_class, room_status) VALUES (?, ?, ?)";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, room.getNumberOfBeds());
            statement.setString(2, room.getRoomClass().name());
            statement.setString(3, room.getRoomStatus().name());
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

        return room;
    }

    public void update(Room room, Long id) {
        String query = "UPDATE rooms SET number_of_beds=?, room_class=?, room_status=? WHERE id=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, room.getNumberOfBeds());
            statement.setString(2, room.getRoomClass().name());
            statement.setString(3, room.getRoomStatus().name());
            statement.setLong(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(statement != null) statement.close();
            } catch (SQLException e ) {
                e.printStackTrace();
            }
        }
    }

    public Room get(Long id) {
        Room room = new Room();
        String query = "SELECT * FROM rooms WHERE id=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            if(result.next()) {
                room.setId(result.getLong("id"));
                room.setNumberOfBeds(result.getInt("number_of_beds"));
                room.setRoomClass(RoomClass.valueOf(result.getString("room_class")));
                room.setRoomStatus(RoomStatus.valueOf(result.getString("room_status")));
            } else {
                room = null;
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
        return room;
    }

    public void remove(Long id) {
        String query = "DELETE FROM rooms WHERE id=?";
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

    public List<Room> getRooms() {
        Room room = new Room();
        List<Room> roomList = new ArrayList<Room>();
        String query = "SELECT * FROM rooms";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                room = new Room();
                room.setId(result.getLong("id"));
                room.setNumberOfBeds(result.getInt("number_of_beds"));
                room.setRoomClass(RoomClass.valueOf(result.getString("room_class")));
                room.setRoomStatus(RoomStatus.valueOf(result.getString("room_status")));
                roomList.add(room);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(statement != null) statement.close();
            } catch (SQLException e ) {
                e.printStackTrace();
            }
        }

        return roomList;
    }

    public List<Room> getRoomsByStatus(RoomStatus status) {
        Room room = new Room();
        List<Room> roomList = new ArrayList<Room>();
        String query = "SELECT * FROM rooms WHERE room_status=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, status.name());
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                room = new Room();
                room.setId(result.getLong("id"));
                room.setNumberOfBeds(result.getInt("number_of_beds"));
                room.setRoomClass(RoomClass.valueOf(result.getString("room_class")));
                room.setRoomStatus(RoomStatus.valueOf(result.getString("room_status")));
                roomList.add(room);
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

        return roomList;
    }

    public List<Room> getRoomsByClass(RoomClass roomClass) {
        Room room = new Room();
        List<Room> roomList = new ArrayList<Room>();
        String query = "SELECT * FROM rooms WHERE room_class=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, roomClass.name());
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                room = new Room();
                room.setId(result.getLong("id"));
                room.setNumberOfBeds(result.getInt("number_of_beds"));
                room.setRoomClass(RoomClass.valueOf(result.getString("room_class")));
                room.setRoomStatus(RoomStatus.valueOf(result.getString("room_status")));
                roomList.add(room);
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

        return roomList;
    }

    public List<Room> getRoomsByNumberOfBeds(int numberOfBeds) {
        Room room = new Room();
        List<Room> roomList = new ArrayList<Room>();
        String query = "SELECT * FROM rooms WHERE number_of_beds=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, numberOfBeds);
            statement.executeQuery();
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                room = new Room();
                room.setId(result.getLong("id"));
                room.setNumberOfBeds(result.getInt("number_of_beds"));
                room.setRoomClass(RoomClass.valueOf(result.getString("room_class")));
                room.setRoomStatus(RoomStatus.valueOf(result.getString("room_status")));
                roomList.add(room);
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

        return roomList;
    }

    public void updateStatus(Long id, RoomStatus status) {
        String query = "UPDATE rooms SET room_status=? WHERE id=?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, status.getValue());
            statement.setLong(2, id);
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
}
