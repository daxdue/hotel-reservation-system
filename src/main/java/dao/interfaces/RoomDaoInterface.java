package dao.interfaces;

import enums.RoomClass;
import enums.RoomStatus;
import models.Room;

import java.util.List;

public interface RoomDaoInterface extends CrudInterface<Room, Long> {

    List<Room> getRooms();
    List<Room> getRoomsByStatus(RoomStatus status);
    List<Room> getRoomsByClass(RoomClass roomClass);
    List<Room> getRoomsByNumberOfBeds(int numberOfBeds);
    void updateStatus(Long id, RoomStatus status);

}
