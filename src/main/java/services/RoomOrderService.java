package services;

import dao.implementations.ConnectionFactory;
import dao.implementations.RoomOrderDaoPostgres;
import dao.interfaces.RoomOrderDaoInterface;
import enums.OrderStatus;
import enums.RoomClass;
import enums.SourceType;
import exceptions.NotFoundException;
import models.Client;
import models.RoomOrder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RoomOrderService {

    private RoomOrderDaoInterface roomOrderDao;

    public RoomOrderService() {
        roomOrderDao = new RoomOrderDaoPostgres(ConnectionFactory.getConnection(SourceType.POSTGRES));
    }

    public RoomOrder findRoomOrder(Long id) {
        RoomOrder roomOrder = roomOrderDao.get(id);
        if(roomOrder != null) {
            return roomOrder;
        }
        throw new NotFoundException();
    }

    public void saveRoomOrder(RoomOrder roomOrder) {
        roomOrder.setOrderStatus(OrderStatus.UNPROCESSED);
        roomOrderDao.save(roomOrder);
    }

    public void updateRoomOrder(RoomOrder roomOrder) {
        RoomOrder roomOrderDb = roomOrderDao.get(roomOrder.getId());
        if(roomOrderDb != null) {
            roomOrderDao.update(roomOrder, roomOrderDb.getId());
        } else {
            throw new NotFoundException();
        }
    }

    public void setRoom(RoomOrder roomOrder) {
        RoomOrder roomOrderDb = roomOrderDao.get(roomOrder.getId());
        if(roomOrderDb != null) {
            roomOrderDao.updateRoom(roomOrder);
        } else {
            throw new NotFoundException();
        }
    }

    public void deleteRoomOrder(RoomOrder roomOrder) {
        RoomOrder roomOrderDb = roomOrderDao.get(roomOrder.getId());
        if(roomOrderDb != null) {
            roomOrderDao.remove(roomOrderDb.getId());
        } else {
            throw new NotFoundException();
        }
    }

    public List<RoomOrder> findAllOrders() {
        List<RoomOrder> roomOrders = new ArrayList<RoomOrder>();
        roomOrders = roomOrderDao.getRoomOrders();
        if(roomOrders.size() == 0) {
            throw new NotFoundException();
        }
        return roomOrderDao.getRoomOrders();
    }

    public List<RoomOrder> findRoomOrdersByRoomClass(RoomClass roomClass) {
        List<RoomOrder> roomOrders = new ArrayList<RoomOrder>();
        roomOrders = roomOrderDao.getRoomOrderByRoomClass(roomClass);
        if(roomOrders.size() == 0) {
            throw new NotFoundException();
        }
        return roomOrders;
    }

    public List<RoomOrder> findRoomOrdersByNumberOfBeds(int numberOfBeds) {
        List<RoomOrder> roomOrders = new ArrayList<RoomOrder>();
        roomOrders = roomOrderDao.getRoomOrderByNumberOfBeds(numberOfBeds);
        if(roomOrders.size() == 0) {
            throw new NotFoundException();
        }
        return roomOrders;
    }

    public List<RoomOrder> findRoomOrdersByCheckin(Date checkin) {
        List<RoomOrder> roomOrders = new ArrayList<RoomOrder>();
        roomOrders = roomOrderDao.getRoomOrderByCheckin(checkin);
        if(roomOrders.size() == 0) {
            throw new NotFoundException();
        }
        return roomOrders;
    }

    public List<RoomOrder> findRoomOrdersByCheckout(Date checkout) {
        List<RoomOrder> roomOrders = new ArrayList<RoomOrder>();
        roomOrders = roomOrderDao.getRoomOrderByCheckout(checkout);
        if(roomOrders.size() == 0) {
            throw new  NotFoundException();
        }
        return roomOrders;
    }

    public List<RoomOrder> findRoomOrdersByCheckinAndCheckout(Date checkin, Date checkout) {
        List<RoomOrder> roomOrders = new ArrayList<RoomOrder>();
        roomOrders = roomOrderDao.getRoomOrdersByCheckinAndCheckout(checkin, checkout);
        if(roomOrders.size() == 0) {
            throw new NotFoundException();
        }
        return roomOrders;
    }

    public List<RoomOrder> findRoomOrderByClient(Client client) {
        List<RoomOrder> roomOrders = new ArrayList<RoomOrder>();
        try {
            if(client != null) {
                roomOrders = roomOrderDao.getRoomOrdersByClient(client.getId());
                if(roomOrders.size() == 0) {
                    throw new NotFoundException();
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return roomOrders;
    }
}
