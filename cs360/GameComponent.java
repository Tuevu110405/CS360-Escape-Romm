package cs360;
public abstract class GameComponent {
    protected String name;

    public abstract void inspect();
    public String getName() {
        return this.name;
    }
}