import cs360.*;

public class Main {

    public static void main(String[] args) {

       

        start.connectRoom(hall);

        Player player = new Player(start);

        // ===== Start menu =====
        GameMenu menu = new GameMenu(player);
        menu.run();
    }
}
