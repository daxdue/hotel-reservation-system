package models;

import enums.OrderStatus;
import enums.RoomClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomOrder {

    private Long id;
    private int numberOfBeds;
    private RoomClass roomClass;
    private Date checkin;
    private Date checkout;
    private OrderStatus orderStatus;
    private Client client;
    private Room room;
}
