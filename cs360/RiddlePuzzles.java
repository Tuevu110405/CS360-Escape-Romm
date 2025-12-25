package cs360;
import java.util.Scanner;

public class RiddlePuzzles extends Puzzles {
    private final String correctAns;
    private String quest;

    public RiddlePuzzles(int difficulty, String correctAns, String quest) {
        super(difficulty, false);
        this.correctAns = correctAns;
        this.quest = quest;
    }

    public void setQuest() {
        System.out.println("Input the question: ");
        Scanner scanner = new Scanner(System.in);
        this.quest  = scanner.nextLine();
        scanner.close();
    }

    @Override
    public boolean attemptSolve(String answer) {
        if (answer.equals(this.correctAns)) {
            this.solved = true;
            return true;
        }
        else {
            this.solved = false;
            return false;
        }
    }

    @Override
    public void inspect() {
        System.out.println("Riddle: " + this.quest);
        super.inspect();
    }

    public void showAns() {
        System.out.println("Correct answer: " + this.correctAns);
    }
}