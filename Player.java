import java.util.ArrayList;
import java.util.Stack;

public class Player {

    /* =========================
       Fields
       ========================= */

    // Stack dùng cho backtracking (lệnh "back")
    private Stack<Room> moveHistory;

    // Inventory chứa các Item người chơi đã nhặt
    private ArrayList<Item> inventory;

    // Phòng hiện tại người chơi đang đứng
    private Room currentRoom;

    /* =========================
       Constructor
       ========================= */

    public Player(Room startRoom) {
        this.currentRoom = startRoom;
        this.moveHistory = new Stack<>();
        this.inventory = new ArrayList<>();
    }

    /* =========================
       Movement methods
       ========================= */

    /**
     * Di chuyển sang phòng khác
     * - Lưu phòng cũ vào stack
     * - Cập nhật currentRoom
     */
    public void moveTo(Room nextRoom) throws LockedRoomException {
        if (nextRoom == null) {
            throw new LockedRoomException("Room does not exist.");
        }

        // Kiểm tra phòng có bị khóa không
        if (nextRoom.requiresKey()) {
            String keyName = nextRoom.getRequiredKeyName();
            if (!hasKey(keyName)) {
                throw new LockedRoomException(
                        "Room is locked. You need key: " + keyName
                );
            }
        }

        // Push phòng hiện tại vào stack trước khi di chuyển
        moveHistory.push(currentRoom);
        currentRoom = nextRoom;
    }

    /**
     * Quay lại phòng trước đó (lệnh back)
     */
    public void goBack() {
        if (!moveHistory.isEmpty()) {
            currentRoom = moveHistory.pop();
        } else {
            System.out.println("You cannot go back any further.");
        }
    }

    /* =========================
       Item methods
       ========================= */

    /**
     * Nhặt item trong phòng hiện tại theo tên
     */
    public void pickupItem(String itemName) {
        Item item = currentRoom.removeItemByName(itemName);

        if (item != null) {
            inventory.add(item);
            System.out.println("Picked up: " + item.getName());
        } else {
            System.out.println("Item not found: " + itemName);
        }
    }

    /**
     * Kiểm tra người chơi có key hay không
     */
    public boolean hasKey(String keyName) {
        for (Item item : inventory) {
            if (item.getItemType().equalsIgnoreCase("KEY")
                    && item.getName().equalsIgnoreCase(keyName)) {
                return true;
            }
        }
        return false;
    }

    /* =========================
       Inventory methods
       ========================= */

    /**
     * In inventory của người chơi
     */
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

    /**
     * Sắp xếp inventory theo value (Comparable<Item>)
     * (Dùng insertion sort theo yêu cầu bài)
     */
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

    /* =========================
       Getters
       ========================= */

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
}
