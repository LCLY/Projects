import java.util.*;

/**
 * CS 180 Project 2 - Obstruction
 * <p>
 * This class allows you to play and test your ObstructionGame class.
 * When submitting your work for grading, you should only turn in
 * the ObstructionGame.java file.
 *
 * @author
 * @version
 *
 */
public class ObstructionGameEngine {

    public static void main(String[] args) {
        int nrows = 6;
        int ncols = 6;

        // check for incorrect num. of arguments
        if (args.length != 0 && args.length != 2) {
            System.err.printf(
                    "Invalid number of command line arguments.\n"
                            + "Proper invocation:\n\n"
                            + "java ObstructionGame ($numRows $numCols)?\n");
            return;
        }

        // can change the board size through command line args
        if (args.length == 2) {
            Scanner first = new Scanner(args[0].trim());
            Scanner sec = new Scanner(args[1].trim());

            if (!first.hasNextInt() || !sec.hasNextInt()) {
                System.err.printf("Invalid board size parameters\n");
                return;
            }

            nrows = first.nextInt();
            ncols = sec.nextInt();
            first.close();
            sec.close();
        }

        new ObstructionGame(nrows, ncols).play();
    }
}