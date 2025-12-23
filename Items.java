public class Items extends GameComponent implements Collectible, Comparable<Items> {
    private int value;
    private String itemType;

    public Items(String name, int value, String itemType){
        this.name = name;
        this.value = value;
        this.itemType = itemType;
    }
    // It can be: Key, Tool, Clue
    public int compareTo(Items other){
        if(this.value < other.value){
            return -1;
        }
        else if(this.value > other.value){
            return 1;
        }
        return 0;
    }
    public void collect(Player p){
        p.getInventory().add(this);
        System.out.println("You have collected the " + itemType + " " + this.getName());
    }
    public void inspect(){
        System.out.println("Item Name: " + this.getName());
        System.out.println("Type: " + this.itemType);
        System.out.println("Value: " + this.value);
    }
    
}
