package utils;

import com.sun.tools.corba.se.idl.constExpr.Not;
import enums.HintType;
import enums.OrderStatus;
import enums.RoomClass;
import enums.RoomStatus;
import exceptions.AlreadyExistsException;
import exceptions.NotFoundException;
import exceptions.UnknownCommand;
import models.Client;
import models.Room;
import models.RoomOrder;
import services.ClientService;
import services.RoomOrderService;
import services.RoomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuManager {

    private static final String LINES =          "---------------------------------------------------------------------";
    private static final String STARS =          "*********************************************************************";
    private static final String CENTER_PATTERN = "                    %s";
    private static final String HELP = "help";
    private static final String QUITE = "qq";
    private static final String H = "-h";
    private static final String A = "-a";
    private static final String I = "-i";
    private static final String U = "-u";
    private static final String D = "-d";
    private static final String L = "-l";
    private static final String N = "-n";
    private static final String S = "-s";
    private static final String C = "-c";
    private static final String B = "-b";
    private static final String AA = "A";
    private static final String LL = "L";
    private static final String SS = "S";
    private static final String BB = "B";
    private static final String CC = "C";
    private static final String OO = "O";
    private static final String RR = "R";

    private static final String CLIENT = "client ";
    private static final String ROOM  = "room ";
    private static final String ROOM_ORDER = "room order ";
    private static final String HELP_CLIENT = "client " + H;
    private static final String HELP_ROOM = "room " + H;
    private static final String HELP_ROOM_ORDER = "room order " + H;

    private static final String NEW_CLIENT = CLIENT + N;
    private static final String ALL_CLIENTS = CLIENT + A;
    private static final String FIND_CLIENT_ID = CLIENT + I;
    private static final String FIND_CLIENT_LOGIN = CLIENT + L;
    private static final String UPDATE_CLIENT = CLIENT + U;
    private static final String UPDATE_CLIENT_ALL = CLIENT + U + AA;
    private static final String UPDATE_CLIENT_LOGIN = CLIENT + U + LL;
    private static final String UPDATE_CLIENT_FIRST = CLIENT + U + "F";
    private static final String UPDATE_CLIENT_LAST = CLIENT + U + "S";
    private static final String DELETE_CLIENT = CLIENT + D;

    private static final String NEW_ROOM = ROOM + N;
    private static final String ALL_ROOMS = ROOM + A;
    private static final String ROOM_BY_ID = ROOM + I;
    private static final String ROOM_BY_STATUS = ROOM + S;
    private static final String ROOM_BY_CLASS = ROOM + C;
    private static final String ROOM_BY_BEDS = ROOM + B;
    private static final String ROOM_UPDATE = ROOM + U;
    private static final String ROOM_UPDATE_STATUS = ROOM + U + SS;
    private static final String ROOM_DELETE = ROOM + D;

    private static final String SHUT_DOWN = "+++ Shutting dowm... +++";
    private static final String EMPTY_ALERT = " list is empty.";
    private static final String CLIENT_FOUND = "+++ Client successful found! +++";
    private static final String CLIENT_NOT_FOUND = "+++ Client not found! +++";
    private static final String CLIENT_UPDATED = "+++ Client successful updated! +++";
    private static final String CLIENT_DELETED = "+++ Client successful deleted! +++";
    private static final String ROOM_CREATED = "+++ Room successful created! +++";
    private static final String ROOM_NOT_FOUND = "+++ Room not found +++";
    private static final String ROOM_FOUND = "+++ Room found +++";
    private static final String ROOM_UPDATED = "+++ Room updated +++";
    private static final String ROOM_DELETED = "+++ Room deleted +++";

    private static final String ROOM_ORDER_NEW = ROOM_ORDER + N;
    private static final String ROOM_ORDERS_ALL = ROOM_ORDER + A;
    private static final String ROOM_ORDER_ID = ROOM_ORDER + I;
    private static final String ROOM_ORDER_UPDATE_STATUS = ROOM_ORDER + U + SS;
    private static final String ROOM_ORDER_UPDATE_BEDS = ROOM_ORDER + N + BB;
    private static final String ROOM_ORDER_UPDATE_CHECKIN = ROOM_ORDER + U + CC;
    private static final String ROOM_ORDER_UPDATE_CHECKOUT = ROOM_ORDER + U + OO;
    private static final String ROOM_ORDER_UPDATE_ROOM = ROOM_ORDER + U + RR;

    private static final String ROOM_ORDERS_BY_CLASS = ROOM_ORDER + "-rc";
    private static final String ROOM_ORDERS_BY_BEDS = ROOM_ORDER + "-nb";
    private static final String ROOM_ORDERS_BY_CHECKIN = ROOM_ORDER + "-ci";
    private static final String ROOM_ORDERS_BY_CHECKOUT = ROOM_ORDER + "-co";
    private static final String ROOM_ORDERS_BY_CHECKIN_AND_CHECKOUT = ROOM_ORDER + "-io";
    private static final String ROOM_ORDERS_BY_CLIENT = ROOM_ORDER + C;
    private static final String ROOM_ORDER_DELETE = ROOM_ORDER + D;

    private static final String ROOM_ORDER_CREATED = "+++ Room order created +++";
    private static final String ROOM_ORDER_FOUND = "+++ Room order found +++";
    private static final String ROOM_ORDER_NOT_FOUND = "+++ Room order not found +++";
    private static final String ROOM_ORDER_DELETED = "+++ Room order deleted +++";
    private static final String ROOM_ORDER_UPDATED = "+++ Room order updated +++";

    private static final String UNKNOWN_COMMAND = "+++ Unknown command +++";
    private static final String TITLE = "Hotel reservation system v1.0";
    private static final String HELP_HINT = "To get help insert 'help'";
    private static final String GLOBAL_HELP = "The system has three sections: 'Client', 'Room', 'Room order'";
    private static final String GLOBAL_HELP_SECTION_PATTERN = " * To get help for section ";
    private static final String GLOBAL_CLIENT_HELP = GLOBAL_HELP_SECTION_PATTERN + "'Client' insert 'client -h'";
    private static final String GLOBAL_ROOM_HELP =  GLOBAL_HELP_SECTION_PATTERN + "'Room' insert 'room -h'";
    private static final String GLOBAL_ROOM_ORDER_HELP = GLOBAL_HELP_SECTION_PATTERN +
            "'Room order' insert 'room order -h";
    private static final String CLIENT_HINT = "Client helper\r\n\r\n" + "* To create new client insert 'client -n'\r\n" +
            "* To get all clients insert 'client -a'\r\n" +
            "* To find client by ID insert 'client -i'\r\n" +
            "* To find client by login insert 'client -l'\r\n" +
            "* To update client insert 'client -u'\r\n" +
            "* To delete client insert 'client -d'";
    private static final String CLIENT_UPDATE_HINT = "* To change all fields insert 'client -uA'\r\n" +
            "* To change login insert 'client -uL'\r\n" +
            "* To change first name insert 'client -uF'\r\n" +
            "* To change last name insert 'client -uS'";
    private static final String ROOM_HINT = "Room helper\r\n\r\n" + "* To create new room insert 'room -n'\r\n" +
            "* To get all rooms insert 'room -a'\r\n" +
            "* To find room by ID insert 'room -i'\r\n" +
            "* To update room insert 'room -u'\r\n" +
            "* To update room status insert 'room -uS'\r\n" +
            "* To get rooms by status insert 'room -s'\r\n" +
            "* To get rooms by class insert 'room -c'\r\n" +
            "* To get rooms by number of beds insert 'room -b'\r\n" +
            "* To delete room insert 'room -d'";
    private static final String ROOM_CLASS_HINT = "Room classes:\r\n" +
            "1 - economy\r\n" + "2 - standart\r\n" + "3 - luxe\r\n" +
            "*** Insert only digit ***";

    private static final String ROOM_STATUS_HINT = "Room statuses:\r\n" +
            "1 - Vacant\r\n" + "2 - Occuped\r\n" + "*** Insert only digit ***";

    private static final String ROOM_ORDER_STATUS_HINT = "Room order statuses:\r\n" +
            "1 - Unprocessed\r\n" + "2 - Processing\r\n" + "3 - Completed" + "*** Insert only digit ***";

    private static final String ROOM_ORDER_HINT = "Room order helper\r\n\r\n" +
            "* To create new room order insert 'room order -n'\r\n" +
            "* To get all room orders insert 'room order -a'\r\n" +
            "* To find room order by ID insert 'room order -i'\r\n" +
            "* To update room order status insert 'room order -uS'\r\n" +
            "* To update room order number of beds insert 'room order -uN'\r\n" +
            "* To update room order checkin date  insert 'room order -uC'\r\n" +
            "* To update room order checkout date insert 'room order -uO'\r\n" +
            "* To update room order room insert 'room order -uR'\r\n" +
            "* To find room orders by room class insert 'order -rc'\r\n" +
            "* To find room orders by number of beds insert 'order -nb'\r\n" +
            "* To find room orders by checkin time insert 'order -ci'\r\n" +
            "* To find room orders by checkout time insert 'order -co'\r\n" +
            "* To find orders by checkin and checkout time insert 'order -io'\r\n" +
            "* To find orders by client insert 'order -c'\r\n" +
            "* To delete room order insert 'order -d'";
    private static final String DATE_FORMAT_HINT = "* Insert date with format: \r\n" +
            "* dd.mm.yyyy";


    public static void runProcess() {
        ClientService clientService = new ClientService();
        RoomService roomService = new RoomService();
        RoomOrderService roomOrderService = new RoomOrderService();

        boolean exit = false;
        Scanner input = new Scanner(System.in);
        showWelcome();
        showHelpHint();

        while (!exit) {
            String command = input.nextLine();
            switch (command) {
                case HELP:
                    showHelp();
                    break;

                case HELP_CLIENT:
                    showHelpHint(HintType.CLIENT_HINT);
                    break;

                case HELP_ROOM:
                    showHelpHint(HintType.ROOM_HINT);
                    break;

                case HELP_ROOM_ORDER:
                    showHelpHint(HintType.ROOM_ORDER_HINT);
                    break;

                case NEW_CLIENT:
                    Client createClient = new Client();
                    System.out.println("-- Insert login --");
                    createClient.setLogin(input.nextLine());
                    System.out.println("-- Insert first name --");
                    createClient.setFirstName(input.nextLine());
                    System.out.println("-- Insert last name --");
                    createClient.setLastName(input.nextLine());
                    try {
                        clientService.saveClient(createClient);
                        System.out.println("* Client successful created! *");
                    } catch (AlreadyExistsException e) {
                        System.out.println("* Client already exists! *");
                    }
                    System.out.println(LINES);
                    break;

                case ALL_CLIENTS:
                    List<Client> clients = clientService.findAllClients();
                    if(clients.size() != 0) {
                        for(Client client : clients) {
                            System.out.println("ID: " + client.getId() + " Login: " + client.getLogin() +
                                    " First name: " + client.getFirstName() + " Last name: " + client.getLastName());
                        }
                    } else {
                        System.out.println("Client" + EMPTY_ALERT);
                    }
                    System.out.println(LINES);
                    break;

                case FIND_CLIENT_ID:
                    try {
                        System.out.println("-- Insert client id --");
                        Client client = clientService.findClient(input.nextLong());
                        input.nextLine();
                        System.out.println("ID: " + client.getId() + " Login: " + client.getLogin() +
                                " First name: " + client.getFirstName() + " Last name: " + client.getLastName());
                    } catch (NotFoundException e) {
                        System.out.println(CLIENT_NOT_FOUND);
                    }
                    System.out.println(LINES);
                    break;

                case FIND_CLIENT_LOGIN:
                    try {
                        System.out.println("-- Insert client login --");
                        Client client = clientService.findClientByLogin(input.nextLine());
                        System.out.println("ID: " + client.getId() + " Login: " + client.getLogin() +
                                " First name: " + client.getFirstName() + " Last name: " + client.getLastName());
                    } catch (NotFoundException e) {
                        System.out.println(CLIENT_NOT_FOUND);
                    }
                    System.out.println(LINES);
                    break;

                case UPDATE_CLIENT:
                    System.out.println(CLIENT_UPDATE_HINT);
                    break;

                case UPDATE_CLIENT_ALL:
                    try {
                        System.out.println("-- Insert client login --");
                        Client clientToUpdate = clientService.findClientByLogin(input.nextLine());
                        System.out.println(CLIENT_FOUND);
                        System.out.println("-- Insert new login --");
                        clientToUpdate.setLogin(input.nextLine());
                        System.out.println("-- Insert new first name --");
                        clientToUpdate.setFirstName(input.nextLine());
                        System.out.println("-- Insert new last name --");
                        clientToUpdate.setLastName(input.nextLine());
                        clientService.updateClient(clientToUpdate);
                    } catch (NotFoundException e) {
                        System.out.println(CLIENT_NOT_FOUND);
                    } catch (UnknownCommand e) {
                        System.out.println(UNKNOWN_COMMAND);
                    }
                    System.out.println(LINES);
                    break;

                case UPDATE_CLIENT_LOGIN:
                    try {
                        System.out.println("-- Insert client login --");
                        Client clientToUpdate = clientService.findClientByLogin(input.nextLine());
                        System.out.println(CLIENT_FOUND);
                        System.out.println("-- Insert new login --");
                        clientToUpdate.setLogin(input.nextLine());
                        clientService.updateClient(clientToUpdate);
                    } catch (NotFoundException e) {
                        System.out.println(CLIENT_NOT_FOUND);
                    } catch (UnknownCommand e) {
                        System.out.println(UNKNOWN_COMMAND);
                    }
                    System.out.println(LINES);
                    break;

                case UPDATE_CLIENT_FIRST:
                    try {
                        System.out.println("-- Insert client login --");
                        Client clientToUpdate = clientService.findClientByLogin(input.nextLine());
                        System.out.println(CLIENT_FOUND);
                        System.out.println("-- Insert new first name --");
                        clientToUpdate.setFirstName(input.nextLine());
                        clientService.updateClient(clientToUpdate);
                    } catch (NotFoundException e) {
                        System.out.println(CLIENT_NOT_FOUND);
                    } catch (UnknownCommand e) {
                        System.out.println(UNKNOWN_COMMAND);
                    }
                    System.out.println(LINES);
                    break;

                case UPDATE_CLIENT_LAST:
                    try {
                        System.out.println("-- Insert client login --");
                        Client clientToUpdate = clientService.findClientByLogin(input.nextLine());
                        System.out.println(CLIENT_FOUND);
                        System.out.println("-- Insert new last name --");
                        clientToUpdate.setLastName(input.nextLine());
                        clientService.updateClient(clientToUpdate);
                    } catch (NotFoundException e) {
                        System.out.println(CLIENT_NOT_FOUND);
                    } catch (UnknownCommand e) {
                        System.out.println(UNKNOWN_COMMAND);
                    }
                    System.out.println(CLIENT_UPDATED);
                    System.out.println(LINES);
                    break;

                case DELETE_CLIENT:
                    try {
                        System.out.println("-- Insert client login --");
                        Client clientToDelete = clientService.findClientByLogin(input.nextLine());
                        System.out.println(CLIENT_FOUND);
                        clientService.deleteClient(clientToDelete);
                        System.out.println(CLIENT_DELETED);
                    } catch (NotFoundException e) {
                        System.out.println(CLIENT_NOT_FOUND);
                    } catch (UnknownCommand e) {
                        System.out.println(UNKNOWN_COMMAND);
                    }
                    System.out.println(LINES);
                    break;

                case NEW_ROOM:
                    Room room = new Room();
                    System.out.println("--- Insert number of beds ---");
                    room.setNumberOfBeds(input.nextInt());
                    System.out.println("--- Insert room class ---");
                    System.out.println(ROOM_CLASS_HINT);
                    Integer roomClass = input.nextInt();
                    switch (roomClass) {
                        case 1:
                            room.setRoomClass(RoomClass.ECONOMY);
                            break;
                        case 2:
                            room.setRoomClass(RoomClass.STANDART);
                            break;
                        case 3:
                            room.setRoomClass(RoomClass.LUXE);
                            break;
                    }
                    room.setRoomStatus(RoomStatus.VACANT);
                    roomService.saveRoom(room);
                    System.out.println(ROOM_CREATED);
                    break;

                case ALL_ROOMS:
                    List<Room> rooms = roomService.findAllRooms();
                    if(rooms.size() != 0) {
                        for(Room roomShow : rooms) {
                            System.out.println("ID: " + roomShow.getId() + " Number of beds: " + roomShow.getNumberOfBeds() +
                                    " Room class: " + roomShow.getRoomClass() +
                                    " Room status: " + roomShow.getRoomStatus());
                        }
                    } else {
                        System.out.println("Room " + EMPTY_ALERT);
                    }
                    System.out.println(LINES);
                    break;

                case ROOM_BY_ID:
                    try {
                        System.out.println("-- Insert room ID --");
                        Room roomWithId = roomService.findRoom(input.nextLong());
                        System.out.println("ID: " + roomWithId.getId() + " Number of beds: " + roomWithId.getNumberOfBeds() +
                                " Room class: " + roomWithId.getRoomClass() +
                                " Room status: " + roomWithId.getRoomStatus());
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_NOT_FOUND);
                    }
                    System.out.println(LINES);
                    break;

                case ROOM_BY_STATUS:
                    try {
                        List<Room> roomsByStatus = new ArrayList<>();
                        System.out.println("-- Insert room status --");
                        System.out.println(ROOM_STATUS_HINT);
                        Integer roomStatus = input.nextInt();
                        switch (roomStatus) {
                            case 1:
                                roomsByStatus = roomService.findRoomsByStatus(RoomStatus.VACANT);
                                break;
                            case 2:
                                roomsByStatus = roomService.findRoomsByStatus(RoomStatus.OCCUPIED);
                                break;
                        }
                        if(roomsByStatus.size() != 0) {
                            for(Room roomShow : roomsByStatus) {
                                System.out.println("ID: " + roomShow.getId() + " Number of beds: " + roomShow.getNumberOfBeds() +
                                        " Room class: " + roomShow.getRoomClass() +
                                        " Room status: " + roomShow.getRoomStatus());
                            }
                        }
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_NOT_FOUND);
                    }
                    break;

                case ROOM_BY_CLASS:
                    try {
                        List<Room> roomByClass = new ArrayList<>();
                        System.out.println("-- Insert room class --");
                        System.out.println(ROOM_CLASS_HINT);
                        roomClass = input.nextInt();
                        switch (roomClass) {
                            case 1:
                                roomByClass = roomService.findRoomsByClass(RoomClass.ECONOMY);
                                break;
                            case 2:
                                roomByClass = roomService.findRoomsByClass(RoomClass.STANDART);
                                break;
                            case 3:
                                roomByClass = roomService.findRoomsByClass(RoomClass.LUXE);
                                break;
                        }
                        if(roomByClass.size() != 0) {
                            for(Room roomShow : roomByClass) {
                                System.out.println("ID: " + roomShow.getId() + " Number of beds: " + roomShow.getNumberOfBeds() +
                                        " Room class: " + roomShow.getRoomClass() +
                                        " Room status: " + roomShow.getRoomStatus());
                            }
                        }
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_NOT_FOUND);
                    }
                    break;

                case ROOM_BY_BEDS:
                    try {
                        List<Room> roomsByBeds = new ArrayList<>();
                        System.out.println("-- Insert number of beds --");
                        Integer bedsNum = input.nextInt();
                        roomsByBeds = roomService.findRoomsByNumberOfBeds(bedsNum);
                        if(roomsByBeds.size() != 0) {
                            for(Room roomShow : roomsByBeds) {
                                System.out.println("ID: " + roomShow.getId() + " Number of beds: " + roomShow.getNumberOfBeds() +
                                        " Room class: " + roomShow.getRoomClass() +
                                        " Room status: " + roomShow.getRoomStatus());
                            }
                        }
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_NOT_FOUND);
                    }
                    break;

                case ROOM_UPDATE:
                    try {
                        System.out.println("-- Insert room id --");
                        Room roomToUpdate = roomService.findRoom(input.nextLong());
                        System.out.println(ROOM_FOUND);
                        System.out.println("-- Insert number of beds --");
                        roomToUpdate.setNumberOfBeds(input.nextInt());
                        System.out.println("-- Insert room class --");
                        System.out.println(ROOM_CLASS_HINT);
                        roomClass = input.nextInt();
                        switch (roomClass) {
                            case 1:
                                roomToUpdate.setRoomClass(RoomClass.ECONOMY);
                                break;
                            case 2:
                                roomToUpdate.setRoomClass(RoomClass.STANDART);
                                break;
                            case 3:
                                roomToUpdate.setRoomClass(RoomClass.LUXE);
                                break;
                        }
                        System.out.println("-- Insert room status --");
                        System.out.println(ROOM_STATUS_HINT);
                        Integer roomStatus = input.nextInt();
                        switch (roomStatus) {
                            case 1:
                                roomToUpdate.setRoomStatus(RoomStatus.VACANT);
                                break;
                            case 2:
                                roomToUpdate.setRoomStatus(RoomStatus.OCCUPIED);
                                break;
                        }
                        roomService.updateRoom(roomToUpdate);
                        System.out.println(ROOM_UPDATED);
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_NOT_FOUND);
                    } catch (UnknownCommand e) {
                        System.out.println(UNKNOWN_COMMAND);
                    }
                    System.out.println(LINES);
                    break;

                case ROOM_UPDATE_STATUS:
                    try {
                        System.out.println("-- Insert room id --");
                        Room roomToUpdate = roomService.findRoom(input.nextLong());
                        System.out.println(ROOM_FOUND);
                        System.out.println("-- Insert room status --");
                        System.out.println(ROOM_STATUS_HINT);
                        Integer roomStatus = input.nextInt();
                        switch (roomStatus) {
                            case 1:
                                roomToUpdate.setRoomStatus(RoomStatus.VACANT);
                                break;
                            case 2:
                                roomToUpdate.setRoomStatus(RoomStatus.OCCUPIED);
                                break;
                        }
                        roomService.updateRoom(roomToUpdate);
                        System.out.println(ROOM_UPDATED);
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_NOT_FOUND);
                    } catch (UnknownCommand e) {
                        System.out.println(UNKNOWN_COMMAND);
                    }
                    System.out.println(LINES);
                    break;

                case ROOM_DELETE:
                    try {
                        System.out.println("-- Insert room id --");
                        Room roomToDelete = roomService.findRoom(input.nextLong());
                        System.out.println(ROOM_FOUND);
                        roomService.deleteRoom(roomToDelete);
                        System.out.println(ROOM_DELETED);
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_NOT_FOUND);
                    } catch (UnknownCommand e) {
                        System.out.println(UNKNOWN_COMMAND);
                    }
                    System.out.println(LINES);
                    break;

                case ROOM_ORDER_NEW:
                    try {
                        RoomOrder roomOrder = new RoomOrder();
                        System.out.println("-- Insert client login --");
                        String clientLogin = input.nextLine();
                        Client client = clientService.findClientByLogin(clientLogin);
                        roomOrder.setClient(client);
                        System.out.println("-- Insert required number of beds --");
                        roomOrder.setNumberOfBeds(input.nextInt());
                        System.out.println("-- Insert required room class --");
                        System.out.println(ROOM_CLASS_HINT);
                        roomClass = input.nextInt();
                        input.nextLine();
                        switch (roomClass) {
                            case 1:
                                roomOrder.setRoomClass(RoomClass.ECONOMY);
                                break;
                            case 2:
                                roomOrder.setRoomClass(RoomClass.STANDART);
                                break;
                            case 3:
                                roomOrder.setRoomClass(RoomClass.LUXE);
                                break;
                        }
                        System.out.println("--- Insert checkin date ---");
                        String checkinStr = input.nextLine();
                        System.out.println("--- Insert checkout date ---");
                        String checkoutStr = input.nextLine();
                        DateValidator dateValidator = new DateValidator();
                        if(dateValidator.isDatesValid(checkinStr, checkoutStr)) {
                            roomOrder.setCheckin(dateValidator.convert(checkinStr));
                            roomOrder.setCheckout(dateValidator.convert(checkoutStr));
                            roomOrderService.saveRoomOrder(roomOrder);
                            System.out.println(ROOM_ORDER_CREATED);
                        } else {
                            System.out.println("--- Invalid date format ---");
                        }
                        System.out.println(LINES);
                    } catch (NotFoundException e) {
                        System.out.println(CLIENT_NOT_FOUND);
                    }

                    break;

                case ROOM_ORDERS_ALL:
                    try {
                        List<RoomOrder> roomOrders = roomOrderService.findAllOrders();
                        if(roomOrders.size() != 0) {
                            for(RoomOrder roomOrderShow : roomOrders) {
                                System.out.println("ID: " + roomOrderShow.getId() + "\r\n" +
                                        "Number of beds: " + roomOrderShow.getNumberOfBeds() + "\r\n" +
                                        "Room class: " + roomOrderShow.getRoomClass() + "\r\n" +
                                        "Checkin date: " + roomOrderShow.getCheckin().toString() + "\r\n" +
                                        "Checkout date: " + roomOrderShow.getCheckout().toString() + "\r\n" +
                                        "Order status: " + roomOrderShow.getOrderStatus() + "\r\n" +
                                        "Client: " + roomOrderShow.getClient().getFirstName() + " " +
                                        roomOrderShow.getClient().getLastName());
                                System.out.println(STARS);
                            }
                        } else {
                            System.out.println("Room " + EMPTY_ALERT);
                        }
                        System.out.println(LINES);
                    } catch (NotFoundException e) {
                        System.out.println("Orders not found!");
                    }
                    System.out.println(LINES);
                    break;

                case ROOM_ORDER_ID:
                    try {
                        System.out.println("--- Insert room order ID ---");
                        RoomOrder roomOrderWithId = roomOrderService.findRoomOrder(input.nextLong());
                        System.out.println("ID: " + roomOrderWithId.getId() + "\r\n" +
                                "Number of beds: " + roomOrderWithId.getNumberOfBeds() + "\r\n" +
                                "Room class: " + roomOrderWithId.getRoomClass() + "\r\n" +
                                "Checkin date: " + roomOrderWithId.getCheckin().toString() + "\r\n" +
                                "Checkout date: " + roomOrderWithId.getCheckout().toString() + "\r\n" +
                                "Order status: " + roomOrderWithId.getOrderStatus().name() + "\r\n" +
                                "Client: " + roomOrderWithId.getClient().getFirstName() +
                                " " + roomOrderWithId.getClient().getLastName());
                        System.out.println(LINES);
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_ORDER_NOT_FOUND);
                    }
                    break;

                case ROOM_ORDERS_BY_CLASS:
                    try {
                        List<RoomOrder> roomOrdersByClass = new ArrayList<>();
                        System.out.println("-- Insert room class --");
                        System.out.println(ROOM_CLASS_HINT);
                        roomClass = input.nextInt();
                        switch (roomClass) {
                            case 1:
                                roomOrdersByClass = roomOrderService.findRoomOrdersByRoomClass(RoomClass.ECONOMY);
                                break;
                            case 2:
                                roomOrdersByClass = roomOrderService.findRoomOrdersByRoomClass(RoomClass.STANDART);
                                break;
                            case 3:
                                roomOrdersByClass = roomOrderService.findRoomOrdersByRoomClass(RoomClass.LUXE);
                                break;
                        }
                        if(roomOrdersByClass.size() != 0) {
                            for(RoomOrder roomOrderShow : roomOrdersByClass) {
                                System.out.println("ID: " + roomOrderShow.getId() + "\r\n" +
                                        "Number of beds: " + roomOrderShow.getNumberOfBeds() + "\r\n" +
                                        "Room class: " + roomOrderShow.getRoomClass() + "\r\n" +
                                        "Checkin date: " + roomOrderShow.getCheckin().toString() + "\r\n" +
                                        "Checkout date: " + roomOrderShow.getCheckout().toString() + "\r\n" +
                                        "Order status: " + roomOrderShow.getOrderStatus() + "\r\n" +
                                        "Client: " + roomOrderShow.getClient().getFirstName() + " " +
                                        roomOrderShow.getClient().getLastName());
                                System.out.println(STARS);
                            }
                        } else {
                            System.out.println("Room " + EMPTY_ALERT);
                        }
                        System.out.println(LINES);
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_ORDER_NOT_FOUND);
                    }
                    break;

                case ROOM_ORDERS_BY_BEDS:
                    try {
                        List<RoomOrder> roomOrderByBeds = new ArrayList<>();
                        System.out.println("--- Insert number of beds ---");
                        Integer numberOfBeds = input.nextInt();
                        roomOrderByBeds = roomOrderService.findRoomOrdersByNumberOfBeds(numberOfBeds);
                        if(roomOrderByBeds.size() != 0) {
                            for(RoomOrder roomOrderShow : roomOrderByBeds) {
                                System.out.println("ID: " + roomOrderShow.getId() + "\r\n" +
                                        "Number of beds: " + roomOrderShow.getNumberOfBeds() + "\r\n" +
                                        "Room class: " + roomOrderShow.getRoomClass() + "\r\n" +
                                        "Checkin date: " + roomOrderShow.getCheckin().toString() + "\r\n" +
                                        "Checkout date: " + roomOrderShow.getCheckout().toString() + "\r\n" +
                                        "Order status: " + roomOrderShow.getOrderStatus() + "\r\n" +
                                        "Client: " + roomOrderShow.getClient().getFirstName() + " " +
                                        roomOrderShow.getClient().getLastName());
                                System.out.println(STARS);
                            }
                        }
                        System.out.println(LINES);
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_ORDER_NOT_FOUND);
                    }
                    break;

                case ROOM_ORDERS_BY_CHECKIN:
                    try {
                        List<RoomOrder> roomOrdersByCheckin = new ArrayList<>();
                        System.out.println("--- Insert checkin date ---");
                        System.out.println(DATE_FORMAT_HINT);
                        String checkinStr = input.nextLine();
                        DateValidator dateValidator = new DateValidator();
                        if(dateValidator.isDateValid(checkinStr)) {
                            roomOrdersByCheckin = roomOrderService.findRoomOrdersByCheckin(dateValidator.convert(checkinStr));
                            if(roomOrdersByCheckin.size() != 0) {
                                for(RoomOrder roomOrderShow : roomOrdersByCheckin) {
                                    System.out.println("ID: " + roomOrderShow.getId() + "\r\n" +
                                            "Number of beds: " + roomOrderShow.getNumberOfBeds() + "\r\n" +
                                            "Room class: " + roomOrderShow.getRoomClass() + "\r\n" +
                                            "Checkin date: " + roomOrderShow.getCheckin().toString() + "\r\n" +
                                            "Checkout date: " + roomOrderShow.getCheckout().toString() + "\r\n" +
                                            "Order status: " + roomOrderShow.getOrderStatus() + "\r\n" +
                                            "Client: " + roomOrderShow.getClient().getFirstName() + " " +
                                            roomOrderShow.getClient().getLastName());
                                    System.out.println(STARS);
                                }
                            }
                        } else {
                            System.out.println("--- Invalid date format ---");
                        }
                        System.out.println(LINES);
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_ORDER_NOT_FOUND);
                    }
                    break;

                case ROOM_ORDERS_BY_CHECKOUT:
                    try {
                        List<RoomOrder> roomOrdersByCheckout = new ArrayList<>();
                        System.out.println("--- Insert checkout date ---");
                        System.out.println(DATE_FORMAT_HINT);
                        String checkoutStr = input.nextLine();
                        DateValidator dateValidator = new DateValidator();
                        if(dateValidator.isDateValid(checkoutStr)) {
                            roomOrdersByCheckout = roomOrderService.findRoomOrdersByCheckout(dateValidator.convert(checkoutStr));
                            if(roomOrdersByCheckout.size() != 0) {
                                for(RoomOrder roomOrderShow : roomOrdersByCheckout) {
                                    System.out.println("ID: " + roomOrderShow.getId() + "\r\n" +
                                            "Number of beds: " + roomOrderShow.getNumberOfBeds() + "\r\n" +
                                            "Room class: " + roomOrderShow.getRoomClass() + "\r\n" +
                                            "Checkin date: " + roomOrderShow.getCheckin().toString() + "\r\n" +
                                            "Checkout date: " + roomOrderShow.getCheckout().toString() + "\r\n" +
                                            "Order status: " + roomOrderShow.getOrderStatus() + "\r\n" +
                                            "Client: " + roomOrderShow.getClient().getFirstName() + " " +
                                            roomOrderShow.getClient().getLastName());
                                    System.out.println(STARS);
                                }
                            }
                        } else {
                            System.out.println("--- Invalid date format ---");
                        }
                        System.out.println(LINES);
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_ORDER_NOT_FOUND);
                    }
                    break;

                case ROOM_ORDERS_BY_CHECKIN_AND_CHECKOUT:
                    try {
                        List<RoomOrder> roomOrdersByCheckout = new ArrayList<>();
                        System.out.println("--- Insert checkout date ---");
                        System.out.println(DATE_FORMAT_HINT);
                        String checkinStr = input.nextLine();
                        System.out.println("--- Insert checkout date ---");
                        System.out.println(DATE_FORMAT_HINT);
                        String checkoutStr = input.nextLine();
                        DateValidator dateValidator = new DateValidator();
                        if(dateValidator.isDatesValid(checkinStr, checkoutStr)) {
                            roomOrdersByCheckout = roomOrderService.findRoomOrdersByCheckinAndCheckout(dateValidator.convert(checkinStr), dateValidator.convert(checkoutStr));
                            if(roomOrdersByCheckout.size() != 0) {
                                for(RoomOrder roomOrderShow : roomOrdersByCheckout) {
                                    System.out.println("ID: " + roomOrderShow.getId() + "\r\n" +
                                            "Number of beds: " + roomOrderShow.getNumberOfBeds() + "\r\n" +
                                            "Room class: " + roomOrderShow.getRoomClass() + "\r\n" +
                                            "Checkin date: " + roomOrderShow.getCheckin().toString() + "\r\n" +
                                            "Checkout date: " + roomOrderShow.getCheckout().toString() + "\r\n" +
                                            "Order status: " + roomOrderShow.getOrderStatus() + "\r\n" +
                                            "Client: " + roomOrderShow.getClient().getFirstName() + " " +
                                            roomOrderShow.getClient().getLastName());
                                    System.out.println(STARS);
                                }
                            }
                        } else {
                            System.out.println("--- Invalid date format ---");
                        }
                        System.out.println(LINES);
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_ORDER_NOT_FOUND);
                    }
                    break;

                case ROOM_ORDERS_BY_CLIENT:
                    try {
                        List<RoomOrder> roomOrdersByClient = new ArrayList<>();
                        System.out.println("-- Insert client login --");
                        String clientLogin = input.nextLine();
                        Client client = clientService.findClientByLogin(clientLogin);
                        if (client != null) {
                            roomOrdersByClient = roomOrderService.findRoomOrderByClient(client);
                            if (roomOrdersByClient.size() != 0) {
                                for (RoomOrder roomOrderShow : roomOrdersByClient) {
                                    System.out.println("ID: " + roomOrderShow.getId() + "\r\n" +
                                            "Number of beds: " + roomOrderShow.getNumberOfBeds() + "\r\n" +
                                            "Room class: " + roomOrderShow.getRoomClass() + "\r\n" +
                                            "Checkin date: " + roomOrderShow.getCheckin().toString() + "\r\n" +
                                            "Checkout date: " + roomOrderShow.getCheckout().toString() + "\r\n" +
                                            "Order status: " + roomOrderShow.getOrderStatus() + "\r\n" +
                                            "Client: " + roomOrderShow.getClient().getFirstName() + " " +
                                            roomOrderShow.getClient().getLastName());
                                    System.out.println(STARS);
                                }
                            }
                        }
                        System.out.println(LINES);
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_ORDER_NOT_FOUND);
                    }
                    break;

                case ROOM_ORDER_UPDATE_STATUS:
                    try {
                        System.out.println("-- Insert room order id --");
                        RoomOrder roomOrder = roomOrderService.findRoomOrder(input.nextLong());
                        System.out.println(ROOM_ORDER_FOUND);
                        System.out.println("-- Insert room order status --");
                        System.out.println(ROOM_ORDER_STATUS_HINT);
                        Integer roomStatus = input.nextInt();
                        switch (roomStatus) {
                            case 1:
                                roomOrder.setOrderStatus(OrderStatus.UNPROCESSED);
                                break;
                            case 2:
                                roomOrder.setOrderStatus(OrderStatus.PROCESSING);
                                break;
                            case 3:
                                roomOrder.setOrderStatus(OrderStatus.COMPLETED);
                                break;
                        }
                        roomOrderService.updateRoomOrder(roomOrder);
                        System.out.println(ROOM_ORDER_UPDATED);
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_NOT_FOUND);
                    } catch (UnknownCommand e) {
                        System.out.println(UNKNOWN_COMMAND);
                    }
                    System.out.println(LINES);
                    break;

                case ROOM_ORDER_UPDATE_BEDS:
                    try {
                        System.out.println("-- Insert room order id --");
                        RoomOrder roomOrder = roomOrderService.findRoomOrder(input.nextLong());
                        System.out.println(ROOM_ORDER_FOUND);
                        System.out.println("-- Insert room order number of beds --");
                        Integer numberOfBeds = input.nextInt();
                        roomOrder.setNumberOfBeds(numberOfBeds);
                        roomOrderService.updateRoomOrder(roomOrder);
                        System.out.println(ROOM_ORDER_UPDATED);
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_NOT_FOUND);
                    } catch (UnknownCommand e) {
                        System.out.println(UNKNOWN_COMMAND);
                    }
                    System.out.println(LINES);
                    break;

                case ROOM_ORDER_UPDATE_CHECKIN:
                    try {
                        System.out.println("-- Insert room order id --");
                        RoomOrder roomOrder = roomOrderService.findRoomOrder(input.nextLong());
                        System.out.println(ROOM_ORDER_FOUND);
                        System.out.println("-- Insert checkin date --");
                        String checkin = input.nextLine();
                        DateValidator dateValidator = new DateValidator();
                        if(dateValidator.isDateValid(checkin)) {
                            roomOrder.setCheckin(dateValidator.convert(checkin));
                        }
                        roomOrderService.updateRoomOrder(roomOrder);
                        System.out.println(ROOM_ORDER_UPDATED);
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_NOT_FOUND);
                    } catch (UnknownCommand e) {
                        System.out.println(UNKNOWN_COMMAND);
                    }
                    System.out.println(LINES);
                    break;

                case ROOM_ORDER_UPDATE_CHECKOUT:
                    try {
                        System.out.println("-- Insert room order id --");
                        RoomOrder roomOrder = roomOrderService.findRoomOrder(input.nextLong());
                        System.out.println(ROOM_ORDER_FOUND);
                        System.out.println("-- Insert checkout date --");
                        String checkout = input.nextLine();
                        DateValidator dateValidator = new DateValidator();
                        if(dateValidator.isDateValid(checkout)) {
                            roomOrder.setCheckin(dateValidator.convert(checkout));
                        }
                        roomOrderService.updateRoomOrder(roomOrder);
                        System.out.println(ROOM_ORDER_UPDATED);
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_ORDER_NOT_FOUND);
                    } catch (UnknownCommand e) {
                        System.out.println(UNKNOWN_COMMAND);
                    }
                    System.out.println(LINES);
                    break;

                case ROOM_ORDER_UPDATE_ROOM:
                    try {
                        System.out.println("--- Insert room order id");
                        RoomOrder roomOrder = roomOrderService.findRoomOrder(input.nextLong());
                        System.out.println(ROOM_ORDER_FOUND);
                        System.out.println("--- Insert room id ---");
                        Room roomDb = roomService.findRoom(input.nextLong());
                        roomOrder.setRoom(roomDb);
                        System.out.println(ROOM_FOUND);
                        roomOrderService.setRoom(roomOrder);

                    } catch (NotFoundException e) {
                        System.out.println(ROOM_ORDER_NOT_FOUND);
                    }
                    System.out.println(LINES);
                    break;

                case ROOM_ORDER_DELETE:
                    try {
                        System.out.println("-- Insert order id --");
                        RoomOrder roomOrderToDelete = roomOrderService.findRoomOrder(input.nextLong());
                        System.out.println(ROOM_ORDER_FOUND);
                        roomOrderService.deleteRoomOrder(roomOrderToDelete);
                        System.out.println(ROOM_ORDER_DELETED);
                        System.out.println(LINES);
                    } catch (NotFoundException e) {
                        System.out.println(ROOM_ORDER_NOT_FOUND);
                    } catch (UnknownCommand e) {
                        System.out.println(UNKNOWN_COMMAND);
                    }
                    break;

                case QUITE:
                    exit = true;
                    break;

                /*
                default:
                    System.out.println(UNKNOWN_COMMAND);
                    break;
                */
            }
        }
    }


    private static void showHelp() {
        System.out.println(LINES);
        System.out.println(GLOBAL_HELP);
        System.out.println(GLOBAL_CLIENT_HELP);
        System.out.println(GLOBAL_ROOM_HELP);
        System.out.println(GLOBAL_ROOM_ORDER_HELP);

    }

    private static void showWelcome() {
        System.out.println(STARS);
        System.out.printf(CENTER_PATTERN, TITLE);
        System.out.println();
        System.out.println(STARS);
    }

    private static void showHelpHint() {
        System.out.println(LINES);
        System.out.println(HELP_HINT);
    }

    private static void showHelpHint(HintType hintType) {
        System.out.println(LINES);
        switch (hintType) {
            case CLIENT_HINT:
                System.out.println(CLIENT_HINT);
                break;
            case ROOM_HINT:
                System.out.println(ROOM_HINT);
                break;
            case ROOM_ORDER_HINT:
                System.out.println(ROOM_ORDER_HINT);
                break;
        }
    }

}
