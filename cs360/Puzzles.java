package cs360;
public abstract class Puzzles extends GameComponent implements Comparable <Puzzles> {
    protected int difficulty;
    protected boolean solved;

    public abstract boolean attemptSolve(String answer);

    public Puzzles(int difficulty, boolean solved) {
        this.difficulty = difficulty;
        this.solved = solved;
    }

    @Override
    public int compareTo(Puzzles other) {
        if (this.difficulty > other.difficulty) return 1;
        if (this.difficulty < other.difficulty) return -1;
        return 0;
    }

    @Override
    public void inspect() {
        System.out.println("--- Inspecting Puzzle ---");
        System.out.println("Name: " + this.name);
        System.out.println("Difficulty: " + this.difficulty);
        System.out.println("Status: " + (this.solved ? "Solved" : "Not Solved"));
    }

    boolean isSolved() {
        return this.solved;
    }
}