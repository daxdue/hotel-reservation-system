package dao.interfaces;

import enums.RoomClass;
import models.RoomOrder;

import java.util.Date;
import java.util.List;

public interface RoomOrderDaoInterface extends CrudInterface<RoomOrder, Long> {

    List<RoomOrder> getRoomOrders();
    List<RoomOrder> getRoomOrderByRoomClass(RoomClass roomClass);
    List<RoomOrder> getRoomOrderByNumberOfBeds(int numberOfBeds);
    List<RoomOrder> getRoomOrderByCheckin(Date date);
    List<RoomOrder> getRoomOrderByCheckout(Date date);
    List<RoomOrder> getRoomOrdersByCheckinAndCheckout(Date checkin, Date checkout);
}
