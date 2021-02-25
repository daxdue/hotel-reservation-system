package models;

import enums.RoomClass;

import java.util.Date;

public class RoomOrder {

    private int id;
    private int numberOfBeds;
    private RoomClass roomClass;
    private Date checkin;
    private Date checkout;
}
