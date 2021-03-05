package dao.interfaces;

import enums.RoomClass;
import models.RoomOrder;

import java.sql.Date;
import java.util.List;

public interface RoomOrderDaoInterface extends CrudInterface<RoomOrder, Long> {
    void updateRoom(RoomOrder roomOrder);
    List<RoomOrder> getRoomOrders();
    List<RoomOrder> getRoomOrderByRoomClass(RoomClass roomClass);
    List<RoomOrder> getRoomOrderByNumberOfBeds(int numberOfBeds);
    List<RoomOrder> getRoomOrderByCheckin(Date checkin);
    List<RoomOrder> getRoomOrderByCheckout(Date checkout);
    List<RoomOrder> getRoomOrdersByCheckinAndCheckout(Date checkin, Date checkout);
    List<RoomOrder> getRoomOrdersByClient(Long clientId);
}
