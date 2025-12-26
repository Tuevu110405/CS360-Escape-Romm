import cs360.*;
import java.util.Scanner;

public class GameMenu {
    private Player player;
    private Scanner scanner;
   
    public GameMenu(Player player) {
        this.player = player;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("=== ESCAPE ROOM MENU ===");
        printHelp();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            try {
                handleCommand(input);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void handleCommand(String input) throws Exception {
        if (input.isEmpty()) return;

        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();

        switch (command) {

            case "look":
                player.getCurrentRoom().inspect();
                player.getCurrentRoom().exploreRecursive(0);
                break;

            case "move":
                requireArg(parts, "move <roomName>");
                Room nextRoom =
                        player.getCurrentRoom().getConnectedRoom(parts[1]);
                player.moveTo(nextRoom);
                break;

            case "back":
                player.goBack();
                break;

            case "pickup":
                requireArg(parts, "pickup <itemName>");
                Item item =
                        player.getCurrentRoom()
                              .removeItemByName(parts[1]);

                if (item == null)
                    throw new Exception("Item not found");

                item.collect(player);
                break;

            case "inventory":
                player.showInventory();
                break;

            case "solve":
                solvePuzzle();
                break;

            case "help":
                printHelp();
                break;

            case "exit":
                System.out.println("Game End!");
                System.exit(0);

            default:
                throw new InvalidCommandException("Unknown command");
        }
    }

    private void solvePuzzle() throws Exception {
        Puzzles puzzle = player.getCurrentRoom().getpuzzle();

        if (puzzle == null)
            throw new Exception("No puzzle in this room");

        System.out.print("Enter answer: ");
        String answer = scanner.nextLine();

        puzzle.attemptSolve(answer);
    }

    private void requireArg(String[] parts, String usage)
            throws InvalidCommandException {

        if (parts.length < 2 || parts[1].isEmpty()) {
            throw new InvalidCommandException("Usage: " + usage);
        }
    }

    private void printHelp() {
        System.out.println("Commands:");
        System.out.println(" look");
        System.out.println(" move <roomName>");
        System.out.println(" back");
        System.out.println(" pickup <itemName>");
        System.out.println(" inventory");
        System.out.println(" solve");
        System.out.println(" help");
        System.out.println(" exit");
    }
}
