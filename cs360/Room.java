package cs360;

import java.util.ArrayList;

public class Room extends GameComponent {
    private ArrayList<GameComponent> contents;
    private ArrayList<Room> connectedRooms;
    private boolean isExit;
    private String name;
    private String requiredKeyName = null;

    public Room(String name, boolean isExit) {
        this.name = name;
        this.contents = new ArrayList<>();
        this.connectedRooms = new ArrayList<>();
        this.isExit = isExit;
    }

    public void addContent(GameComponent component) {
        contents.add(component);
    }

    // add Room for connectedRooms
    public void addConnectedRooms(Room a) {
        connectedRooms.add(a);
    }

    public boolean isExit() {
        return isExit;
    }

    public ArrayList<Room> getConnected() {
        return connectedRooms;
    }

    public String getName() {
        return name;
    }

public void inspect() {

    System.out.println("\n=== ROOM: " + name + " ===");

    /* ===== Connected rooms ===== */
    if (connectedRooms.isEmpty()) {
        System.out.println("No connected rooms.");
    } else {
        System.out.println("Next room:");
        for (Room r : connectedRooms) {
            System.out.println("- " + r.getName());
        }
    }

    /* ===== Contents ===== */
    boolean hasContent = false;
    System.out.println("\nContents:");

    for (GameComponent gc : contents) {

        if (gc instanceof Item) {
            hasContent = true;
            Item item = (Item) gc;
            System.out.println("- Item: " + item.getName());
        }
        else if (gc instanceof Puzzles) {
            hasContent = true;
            Puzzles p = (Puzzles) gc;

            if (!p.isSolved()) {   
                System.out.println("- Puzzle found:");
            }
        }
    }

    if (!hasContent) {
        System.out.println("None");
    }
}


public void exploreRecursive(int depth){
        String indent = "";
        for(int i = 0; i < depth; i++){
            indent += " ";
        }
        System.out.println(indent + "The Room: "+ getName());
        for(GameComponent gc : contents){
            if(gc instanceof Room){
                ((Room)gc).exploreRecursive(depth);
            }
            else{
                gc.inspect();
            }
        }
    }
  
    public int maxDepthRecursive() {
        int maxDepth = 0;
        for (GameComponent gc : contents) {
            if (gc instanceof Room) {
                int d = ((Room) gc).maxDepthRecursive();
                if (d > maxDepth)
                    maxDepth = d;
            }
        }
        return 1 + maxDepth;
    }

    // getter, setter, checkstatus for key
    public boolean requiresKey() {
        return requiredKeyName != null;
    }

    public String getRequiredKeyName() {
        return requiredKeyName;
    }

    public void setRequiredKeyName(String keyname) {
        this.requiredKeyName = keyname;
    }

    // removing items picked by player
    public Item removeItemByName(String name) {
        for (int i = 0; i < contents.size(); i++) {
            GameComponent gc = contents.get(i);
            if (gc instanceof Item && gc.getName().equalsIgnoreCase(name)) {
                contents.remove(i);
                return (Item) gc;
            }
            if (gc instanceof Room) {
                Item nestedItem = ((Room) gc).removeItemByName(name);
                if (nestedItem != null) {
                    return nestedItem;
                }
            }
        }
        return null;
    }

    public Room getConnectedRoom(String name) {
        for (Room r : connectedRooms) {
            if (r.getName().equalsIgnoreCase(name)) {
                return r;
            }
        }
        return null;
    }

    public Puzzles getpuzzle() {
        for (GameComponent gc : contents) {
            if (gc instanceof Puzzles) {
                return (Puzzles) gc;
            }
        }
        return null;
    }
    
    public Puzzles findPuzzleRecursive() {

    for (GameComponent gc : contents) {

        if (gc instanceof Puzzles) {
            return (Puzzles) gc;
        }

        if (gc instanceof Room) {
            Puzzles found =
                    ((Room) gc).findPuzzleRecursive();
            if (found != null) {
                return found;
            }
        }
    }

    return null;
}

}

