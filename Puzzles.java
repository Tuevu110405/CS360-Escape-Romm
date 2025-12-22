public abstract class Puzzles extends GameComponent implements Comparable <Puzzles> {
    protected int difficulty;
    protected boolean solved;

    public abstract boolean attempSolve(String answer);

    public Puzzles Comparable(Puzzles other) {
        if (this.difficulty > other.difficulty) {
            return this;
        }
        else if (this.difficulty < other.difficulty) {
            return other;
        }
        else {
            System.out.println("Same difficulty.");
            return null;
        }
    }

    @Override
    public void inspect() {
        System.out.println("--- Inspecting Puzzle ---");
        System.out.println("Name: " + this.name);
        System.out.println("Difficulty: " + this.difficulty);
        System.out.println("Status: " + (this.solved ? "Solved" : "Not Solved"));
    }
}