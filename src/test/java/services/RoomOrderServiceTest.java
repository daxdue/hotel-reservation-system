package services;

import enums.OrderStatus;
import enums.RoomClass;
import enums.RoomStatus;
import models.Client;
import models.Room;
import models.RoomOrder;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.DateValidator;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RoomOrderServiceTest {

    RoomOrderService roomOrderService = null;
    RoomOrder testRoomOrder = null;
    List<RoomOrder> testRoomOrders = null;
    Date checkin = null;
    Date checkout = null;

    @BeforeTest
    public void setUp() {
        checkin = DateValidator.currentDate();
        checkout = DateValidator.currentDate();
        roomOrderService = Mockito.mock(RoomOrderService.class);
        testRoomOrder = new RoomOrder();
        testRoomOrder.setId(Long.valueOf(1));
        testRoomOrder.setRoom(new Room(Long.valueOf(1), 2, RoomClass.LUXE, RoomStatus.OCCUPIED));
        testRoomOrder.setNumberOfBeds(2);
        testRoomOrder.setRoomClass(RoomClass.LUXE);
        testRoomOrder.setOrderStatus(OrderStatus.UNPROCESSED);
        testRoomOrder.setCheckin(checkin);
        testRoomOrder.setCheckout(checkout);
        testRoomOrder.setClient(new Client(Long.valueOf(1), "fedorov", "Fedor", "Fedorov"));
        testRoomOrders = new ArrayList<>();
        testRoomOrders.add(testRoomOrder);
    }

    @Test
    public void roomOrderServiceCanFindRoomOrderById() {
        Mockito.when(roomOrderService.findRoomOrder(Long.valueOf(1))).thenReturn(testRoomOrder);
        Assert.assertEquals(testRoomOrder, roomOrderService.findRoomOrder(Long.valueOf(1)));
    }

    @Test
    public void roomOrderServiceCanFindAllOrders() {
        Mockito.when(roomOrderService.findAllOrders()).thenReturn(testRoomOrders);
        Assert.assertEquals(testRoomOrders, roomOrderService.findAllOrders());
    }

    @Test
    public void roomOrderCanFindRoomOrdersByRoomClass() {
        Mockito.when(roomOrderService.findRoomOrdersByRoomClass(RoomClass.LUXE)).thenReturn(testRoomOrders);
        Assert.assertEquals(testRoomOrders, roomOrderService.findRoomOrdersByRoomClass(RoomClass.LUXE));
    }

    @Test
    public void roomOrderServiceCanFindRoomOrdersByNumberOfBeds() {
        Mockito.when(roomOrderService.findRoomOrdersByNumberOfBeds(2)).thenReturn(testRoomOrders);
        Assert.assertEquals(testRoomOrders, roomOrderService.findRoomOrdersByNumberOfBeds(2));
    }

    @Test
    public void roomOrderServiceCanFindRoomOrdersByCheckin() {
        Mockito.when(roomOrderService.findRoomOrdersByCheckin(checkin)).thenReturn(testRoomOrders);
        Assert.assertEquals(testRoomOrders, roomOrderService.findRoomOrdersByCheckin(checkin));
    }

    @Test
    public void roomOrderServiceCanFindRoomOrdersByCheckout() {
        Mockito.when(roomOrderService.findRoomOrdersByCheckout(checkout)).thenReturn(testRoomOrders);
        Assert.assertEquals(testRoomOrders, roomOrderService.findRoomOrdersByCheckout(checkout));
    }

    @Test
    public void roomOrderServiceCanFindRoomOrdersByCheckinAndCheckout(){

        Mockito.when(roomOrderService.findRoomOrdersByCheckinAndCheckout(checkin,
                checkout)).thenReturn(testRoomOrders);
        Assert.assertEquals(testRoomOrders, roomOrderService.findRoomOrdersByCheckinAndCheckout(checkin,
                checkout));
    }
}
