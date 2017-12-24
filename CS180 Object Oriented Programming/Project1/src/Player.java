/**
 * Project 1
 * This program is a game using 5 dice.
 * @author Ley Yen Choo, lab sec 01
 * @version February, 11 2016
 */
import java.util.Scanner;
import java.util.Random;

public class Player {
    Random dice;
    String name;
    int score; //total score
    private static final String LOSE_MESSAGE = "\tDice values: %s, Sorry, you scored 0 for the round\n\n";
    private static final String THROW_DICE_MESSAGE = "\tDice values: %s, %d + %d = %d points!\n";
    private static final String ALL_DICE_SCORED_MESSAGE = "\tYou have to relaunch the %d dice\n\n";
    private static final String NOT_MULTIPLE_OF_100_MESSAGE = "\tYou have to continue, "
            + "the score is not a multiple of 100\n\n";
    private static final String CONTINUE_MESSAGE = "\tContinue with %d dice? Current score: %d points (Y/N)\n";
    private static final String ROUND_SCORE_MESSAGE = "\tSuper you scored %d for the round!! Your new score is %d\n\n";

    public Player(String name) {
        this.name = name;
        this.dice = new Random();
        this.score = 0;        //initialize other instance variable
    }

    public String throwDice(int nbDice) {
        String diceVal = "";
        int valueDice;
        for (int i = 0; i < nbDice; i++) {
            valueDice = dice.nextInt(6) + 1; //random 6 numbers for each dice
            diceVal += valueDice + " "; //make the numbers into a string
        }
        //simulate dice throw
        return diceVal;
    }

    public int nbDiceScored(String diceValues) {
        int val = 0;
        String str = diceValues;
        if (diceValues == null) {
            return 0;
        }
        String[] parts = str.split(" ");
        for (int i = 0 ; i < parts.length; i++) {
            if (Integer.parseInt(parts[i]) == 1 || Integer.parseInt(parts[i]) == 5) {
                val++;
            }
        }
        return val;
    }

    public int countPoints(String diceValues) {
        int val = 0;
        String str = diceValues;
        if (diceValues == null) {
            return 0;
        }
        String[] parts = str.split(" ");
        for (int i = 0; i < parts.length; i++) {
            if (Integer.parseInt(parts[i]) == 1) {
                val = val + 100;
            } else if (Integer.parseInt(parts[i]) == 5) {
                val = val + 50;
            }
        }
        // count points
        return val;
    }

    public boolean continueRound(int nbDice) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.printf(CONTINUE_MESSAGE, nbDice, score);
            String decision = input.nextLine();
            decision.toUpperCase();
            if (decision.equals("Y")) {
                return true;
            } else if (decision.equals("N")) {
                return false;
            } else {
                continue;
            }
        }
    }

    public boolean isWinner() {
        if (score >= 5000) {
            return true;
        } else {
            return false;
        }
    }

    public void playRound() {
        int numberOfDice = 5;
        int roundScore = 0;
        int launchScore;


        while (true) {
            String x = throwDice(numberOfDice); //launch dice
            launchScore = countPoints(x); //to calculate the launchscore
            if (launchScore == 0) { //no score
                System.out.printf(LOSE_MESSAGE, x, launchScore);
                break;
            }
            System.out.printf(THROW_DICE_MESSAGE, x, roundScore, launchScore, roundScore + launchScore);
            roundScore = roundScore + launchScore; //calculate round score

            numberOfDice = numberOfDice - nbDiceScored(x); //the current number of dice
            if (numberOfDice == 0) {
                System.out.printf(ALL_DICE_SCORED_MESSAGE, 5);
                numberOfDice = 5;
                continue;
            }

            if (roundScore % 100 == 0) { //check if its a multiply of 100
                if (continueRound(numberOfDice)) {
                    continue;
                } else {
                    score = score + roundScore;
                    System.out.printf(ROUND_SCORE_MESSAGE, roundScore, score);

                }
            } else {
                System.out.printf(NOT_MULTIPLE_OF_100_MESSAGE);
                continue;
            }
        }
    }


}
