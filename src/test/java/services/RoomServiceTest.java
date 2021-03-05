package services;

import dao.implementations.RoomDaoPostgres;
import dao.interfaces.RoomDaoInterface;
import enums.RoomClass;
import enums.RoomStatus;
import models.Room;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class RoomServiceTest {

    private RoomService roomService = null;
    private Room testRoom = null;
    private List<Room> testRooms = null;

    @BeforeTest
    public void setUp() {
        testRoom = new Room();
        testRoom.setId(Long.valueOf(1));
        testRoom.setNumberOfBeds(2);
        testRoom.setRoomClass(RoomClass.STANDART);
        testRoom.setRoomStatus(RoomStatus.OCCUPIED);

        Room room1 = new Room(Long.valueOf(1), 1, RoomClass.STANDART, RoomStatus.OCCUPIED);
        Room room2 = new Room(Long.valueOf(2), 2, RoomClass.ECONOMY, RoomStatus.OCCUPIED);
        Room room3 = new Room(Long.valueOf(3), 3, RoomClass.LUXE, RoomStatus.OCCUPIED);
        testRooms = new ArrayList<>();
        testRooms.add(room1);
        testRooms.add(room2);
        testRooms.add(room3);

        RoomDaoInterface roomDao = Mockito.mock(RoomDaoPostgres.class);
        roomService = new RoomService(roomDao);
        Mockito.when(roomDao.get(Mockito.anyLong())).thenReturn(testRoom);
        Mockito.when(roomDao.getRooms()).thenReturn(testRooms);
        Mockito.when(roomDao.getRoomsByStatus(Mockito.any(RoomStatus.class))).thenReturn(testRooms);
        Mockito.when(roomDao.getRoomsByNumberOfBeds(Mockito.anyInt())).thenReturn(testRooms);
        Mockito.when(roomDao.getRoomsByClass(Mockito.any(RoomClass.class))).thenReturn(testRooms);

    }

    @Test
    public void roomServiceCanFindRoomById() {
        Assert.assertEquals(testRoom, roomService.findRoom(Long.valueOf(1)));
    }

    @Test
    public void roomServiceCanFindAllRooms() {
        Assert.assertEquals(testRooms, roomService.findAllRooms());
    }

    @Test
    public void roomServiceCanFindRoomsByStatus() {
        Assert.assertEquals(testRooms, roomService.findRoomsByStatus(RoomStatus.OCCUPIED));
    }

    @Test
    public void roomServiceCanFindRoomsByNumberOfBeds() {
        Assert.assertEquals(testRooms, roomService.findRoomsByNumberOfBeds(2));
    }

    @Test
    public void roomServiceCanFindRoomsByRoomClass() {
        Assert.assertEquals(testRooms, roomService.findRoomsByClass(RoomClass.LUXE));
    }
}
