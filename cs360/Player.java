package cs360;
import java.util.ArrayList;
import java.util.Stack;

public class Player {

    private Stack<Room> moveHistory;
    private ArrayList<Item> inventory;
    private Room currentRoom;
    //constructor
    public Player(Room startRoom) {
        this.currentRoom = startRoom;
        this.moveHistory = new Stack<>();
        this.inventory = new ArrayList<>();
    }

    public void moveTo(Room nextRoom) throws LockedRoomException {
        if (nextRoom == null) {
            throw new LockedRoomException("Room does not exist.");
        }

        if (nextRoom.requiresKey()) {
            String keyName = nextRoom.getRequiredKeyName();
            if (!hasKey(keyName)) {
                throw new LockedRoomException(
                        "Room is locked. You need key: " + keyName
                );
            }
        }

        moveHistory.push(currentRoom);
        currentRoom = nextRoom;
    }
    //move back to the room before
    public void goBack() {
        if (!moveHistory.isEmpty()) {
            currentRoom = moveHistory.pop();
        } else {
            System.out.println("You cannot go back any further.");
        }
    }

    public void pickupItem(String itemName) {
        Item item = currentRoom.removeItemByName(itemName);

        if (item != null) {
            inventory.add(item);
            System.out.println("Picked up: " + item.getName());
        } else {
            System.out.println("Item not found: " + itemName);
        }
    }
    //check if player has keys
    public boolean hasKey(String keyName) {
        for (Item item : inventory) {
            if (item.getItemType().equalsIgnoreCase("KEY")
                    && item.getName().equalsIgnoreCase(keyName)) {
                return true;
            }
        }
        return false;
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("Inventory:");
        for (Item item : inventory) {
            System.out.println("- " + item.getName()
                    + " (value=" + item.getValue()
                    + ", type=" + item.getItemType() + ")");
        }
    }

    public void sortInventory() {
        for (int i = 1; i < inventory.size(); i++) {
            Item key = inventory.get(i);
            int j = i - 1;

            while (j >= 0 && inventory.get(j).compareTo(key) > 0) {
                inventory.set(j + 1, inventory.get(j));
                j--;
            }
            inventory.set(j + 1, key);
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
}
