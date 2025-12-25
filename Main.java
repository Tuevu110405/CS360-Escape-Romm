import java.util.Arrays;

import cs360.*;

public class Main {
    Room a;
    Room b;
    Room c;
    Puzzles riddleA;
    GameComponent keyA;
    GameComponent chestA;
    Puzzles riddleB;
    GameComponent keyB;
    GameComponent chestB;
    Puzzles riddleC;
    GameComponent keyC;
    GameComponent chestC;

    public Main() {
        a = new Room("A", false);
        b = new Room("B", false);
        c = new Room("C", true);
        riddleA = new RiddlePuzzles(1, "needle", "What has one eye but can't see?");
        keyA = new Item("keyA", 1, "key");
        chestA = new Room("chestA", false);
        ((Room) chestA).addContent(riddleA);
        a.addContent(chestA);
        a.addContent(keyA);
        riddleB = new RiddlePuzzles(2, "cold", "What can you catch but not throw?");
        keyB = new Item("keyB", 2, "key");
        chestB = new Room("chestB", false);
        ((Room) chestB).addContent(riddleB);
        b.addContent(chestB);
        b.addContent(keyB);
        riddleC = new CodePuzzles(3, 10);
        keyC = new Item("keyC", 3, "key");
        chestC = new Room("chestC", false);
        ((Room) chestC).addContent(riddleC);
        c.addContent(chestC);
        c.addContent(keyC);
        a.addConnectedRooms(b);
        b.addConnectedRooms(c);
    }

    public void run() {

    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

}
