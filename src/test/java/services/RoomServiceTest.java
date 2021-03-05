package services;

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
        roomService = Mockito.mock(RoomService.class);
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
    }

    @Test
    public void roomServiceCanFindRoomById() {
        Mockito.when(roomService.findRoom(Long.valueOf(1))).thenReturn(testRoom);
        Assert.assertEquals(testRoom, roomService.findRoom(Long.valueOf(1)));
    }

    @Test
    public void roomServiceCanFindAllRooms() {
        Mockito.when(roomService.findAllRooms()).thenReturn(testRooms);
        Assert.assertEquals(testRooms, roomService.findAllRooms());
    }

    @Test
    public void roomServiceCanFindRoomsByStatus() {
        Mockito.when(roomService.findRoomsByStatus(RoomStatus.OCCUPIED)).thenReturn(testRooms);
        Assert.assertEquals(testRooms, roomService.findRoomsByStatus(RoomStatus.OCCUPIED));
    }

    @Test
    public void roomServiceCanFindRoomsByNumberOfBeds() {
        Mockito.when(roomService.findRoomsByNumberOfBeds(2)).thenReturn(testRooms);
        Assert.assertEquals(testRooms, roomService.findRoomsByNumberOfBeds(2));
    }
}
