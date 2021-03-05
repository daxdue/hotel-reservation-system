package enums;

public enum RoomClass {
    ECONOMY(1, "economy"),
    STANDART(2, "standart"),
    LUXE(3, "luxe");

    private int value;
    private String desc;

    RoomClass(int value, String desc) {
    }

    public int getValue() {return value;}
    public String getDesc() {return desc;}
}
