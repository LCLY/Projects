/**
 * Project 1
 * This program is the main method for player.
 * @author Ley Yen Choo, lab sec 01
 * @version February, 11 2016
 */
public class Game {

    public static void main(String[] args) {
        Player sophia = new Player("Sophia");
        Player riya = new Player("Riya");
        Player nicole = new Player("Nicole");

        while (true) {

            System.out.printf("Sophia plays:\n");
            sophia.playRound();

            if (sophia.isWinner()) {
                System.out.printf("\nSophia wins!!\n");
                return;
            }

            System.out.print("Riya plays:\n");
            riya.playRound();

            if (riya.isWinner()) {
                System.out.printf("\nRiya wins!!\n");
                return;
            }

            System.out.printf("Nicole plays:\n");
            nicole.playRound();

            if (nicole.isWinner()) {
                System.out.printf("\nNicole wins!!\n");
                return;
            }
        }
    }
}