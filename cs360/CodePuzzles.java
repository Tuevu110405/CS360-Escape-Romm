package cs360;
public class CodePuzzles extends Puzzles {
    private int correctCode;

    public CodePuzzles(int difficulty, int targetCode) {
        super(difficulty, false);
        this.correctCode = targetCode;
    }

    public boolean guessCode(int guess) {
        if (guess == this.correctCode) {
            this.solved = true;
            System.out.println("Correct answer. Congratulation.");
            return true;
        } else if (guess < this.correctCode) {
            System.out.println("Your code is less than the answer code.");
        } else {
            System.out.println("Your code is greater than the answer code.");
        }
        return false;
    }

    @Override
    public boolean attemptSolve(String answer) {
        try {
            int guess = Integer.parseInt(answer);
            return guessCode(guess);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
            return false;
        }
    }

    @Override
    public void inspect() {
        System.out.println("Try to enter a number.");
        super.inspect();
    }
}
