import java.util.*;
import java.util.LinkedHashMap;

abstract class Room {
    protected int beds;
    protected int size;
    protected double price;

    public Room(int beds, int size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}

class RoomInventory {
    private LinkedHashMap<String, Integer> availability = new LinkedHashMap<>();

    public RoomInventory() {
        availability.put("Single Room", 5);
        availability.put("Double Room", 3);
        availability.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return availability.getOrDefault(roomType, 0);
    }

    public Set<String> getRoomTypes() {
        return availability.keySet();
    }
}

class RoomSearchService {
    private RoomInventory inventory;
    private HashMap<String, Room> roomCatalog;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;

        roomCatalog = new HashMap<>();
        roomCatalog.put("Single Room", new SingleRoom());
        roomCatalog.put("Double Room", new DoubleRoom());
        roomCatalog.put("Suite Room", new SuiteRoom());
    }

    public void searchRooms() {

        System.out.println("Room Search\n");

        for (String type : inventory.getRoomTypes()) {

            int available = inventory.getAvailability(type);

            if (available > 0) {

                System.out.println(type + ":");

                Room room = roomCatalog.get(type);
                room.displayDetails();

                System.out.println("Available: " + available);
                System.out.println();
            }
        }
    }
}

public class BookMyStayApp{
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        RoomSearchService searchService = new RoomSearchService(inventory);

        searchService.searchRooms();
    }
}