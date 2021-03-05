package services;

import dao.implementations.RoomOrderDaoPostgres;
import dao.interfaces.RoomOrderDaoInterface;
import enums.OrderStatus;
import enums.RoomClass;
import enums.RoomStatus;
import models.Client;
import models.Room;
import models.RoomOrder;
import org.mockito.Mock;
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

        RoomOrderDaoInterface roomOrderDao = Mockito.mock(RoomOrderDaoPostgres.class);
        roomOrderService = new RoomOrderService(roomOrderDao);
        Mockito.when(roomOrderDao.get(Mockito.anyLong())).thenReturn(testRoomOrder);
        Mockito.when(roomOrderDao.getRoomOrders()).thenReturn(testRoomOrders);
        Mockito.when(roomOrderDao.getRoomOrderByRoomClass(Mockito.any(RoomClass.class))).thenReturn(testRoomOrders);
        Mockito.when(roomOrderDao.getRoomOrderByNumberOfBeds(Mockito.anyInt())).thenReturn(testRoomOrders);
        Mockito.when(roomOrderDao.getRoomOrderByCheckin(Mockito.any(Date.class))).thenReturn(testRoomOrders);
        Mockito.when(roomOrderDao.getRoomOrderByCheckout(Mockito.any(Date.class))).thenReturn(testRoomOrders);
        Mockito.when(roomOrderDao.getRoomOrdersByCheckinAndCheckout(
                Mockito.any(Date.class), Mockito.any(Date.class)
        )).thenReturn(testRoomOrders);


    }

    @Test
    public void roomOrderServiceCanFindRoomOrderById() {
        Assert.assertEquals(testRoomOrder, roomOrderService.findRoomOrder(Long.valueOf(1)));
    }

    @Test
    public void roomOrderServiceCanFindAllOrders() {
        Assert.assertEquals(testRoomOrders, roomOrderService.findAllOrders());
    }

    @Test
    public void roomOrderCanFindRoomOrdersByRoomClass() {
        Assert.assertEquals(testRoomOrders, roomOrderService.findRoomOrdersByRoomClass(RoomClass.LUXE));
    }

    @Test
    public void roomOrderServiceCanFindRoomOrdersByNumberOfBeds() {
        Assert.assertEquals(testRoomOrders, roomOrderService.findRoomOrdersByNumberOfBeds(2));
    }

    @Test
    public void roomOrderServiceCanFindRoomOrdersByCheckin() {
        Assert.assertEquals(testRoomOrders, roomOrderService.findRoomOrdersByCheckin(checkin));
    }

    @Test
    public void roomOrderServiceCanFindRoomOrdersByCheckout() {
        Assert.assertEquals(testRoomOrders, roomOrderService.findRoomOrdersByCheckout(checkout));
    }

    @Test
    public void roomOrderServiceCanFindRoomOrdersByCheckinAndCheckout(){
        Assert.assertEquals(testRoomOrders, roomOrderService.findRoomOrdersByCheckinAndCheckout(checkin,
                checkout));
    }
}
