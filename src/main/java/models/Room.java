package models;

import enums.RoomClass;
import enums.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    private Long id;
    private int numberOfBeds;
    private RoomClass roomClass;
    private RoomStatus roomStatus;
}
