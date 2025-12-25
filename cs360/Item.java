package cs360;

public class Item extends GameComponent implements Collectible, Comparable<Item> {
    private int value;
    private String itemType;

    public Item(String name, int value, String itemType){
        this.name = name;
        this.value = value;
        this.itemType = itemType;
    }
    // It can be: Key, Tool, Clue
    public int compareTo(Item other){
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
    public String getItemType(){
        return itemType;
    }
    public int getValue(){
        return value;
    }
    
}
