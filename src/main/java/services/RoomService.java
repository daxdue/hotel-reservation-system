package services;

import dao.interfaces.RoomDaoInterface;
import enums.RoomClass;
import enums.RoomStatus;
import exceptions.NotFoundException;
import models.Room;

import java.util.List;

public class RoomService {

    private RoomDaoInterface roomDao;

    public RoomService(RoomDaoInterface roomDao) {
        this.roomDao = roomDao;
    }

    public Room findRoom(Long id) {
        Room room = roomDao.get(id);
        if(room != null) {
            return room;
        } else {
            throw new NotFoundException();
        }
    }

    public void saveRoom(Room room) {
        roomDao.save(room);
    }

    public void updateRoom(Room room) {
        Room roomDb = roomDao.get(room.getId());
        if(roomDb != null) {
            roomDao.update(room, room.getId());
        } else {
            throw new NotFoundException();
        }
    }

    public void updateRoomStatus(Room room, RoomStatus status) {
        Room roomDb = roomDao.get(room.getId());
        if(roomDb != null) {
            roomDao.updateStatus(room.getId(), status);
        } else {
            throw new NotFoundException();
        }
    }

    public List<Room> findAllRooms() {
        return roomDao.getRooms();
    }

    public List<Room> findRoomsByStatus(RoomStatus status) {
        List<Room> roomList = roomDao.getRoomsByStatus(status);
        if(roomList.size() != 0) {
            return roomList;
        } else {
            throw new NotFoundException();
        }
    }

    public List<Room> findRoomsByClass(RoomClass roomClass) {
        List<Room> roomList = roomDao.getRoomsByClass(roomClass);
        if(roomList.size() != 0) {
            return roomList;
        } else {
            throw new NotFoundException();
        }
    }

    public List<Room> findRoomsByNumberOfBeds(int numberOfBeds) {
        List<Room> roomList = roomDao.getRoomsByNumberOfBeds(numberOfBeds);
        if(roomList.size() != 0) {
            return roomList;
        } else {
            throw new NotFoundException();
        }
    }

    public void deleteRoom(Room room) {
        Room roomDb = roomDao.get(room.getId());
        if(roomDb != null) {
            roomDao.remove(roomDb.getId());
        } else {
            throw new NotFoundException();
        }
    }
}
