package enums;

public enum RoomStatus {
    VACANT(1, "VACANT"),
    OCCUPIED(2, "Occuped");

    private int value;
    private String desc;

    RoomStatus(int value, String desc) {
    }

    public int getValue() {return value;}
    public String getDesc() {return desc;}
}
