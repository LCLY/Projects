import static org.junit.Assert.*;
import org.junit.*;

// imports for IO, TestDice
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class Project01StudentTest {

    // given messages (with new lines removed)
    private static final String LOSE_MESSAGE = "\tDice values: %s, Sorry, you scored 0 for the round";
    private static final String THROW_DICE_MESSAGE = "\tDice values: %s, %d + %d = %d points!\n";
    private static final String ALL_DICE_SCORED_MESSAGE = "\tYou have to relaunch the %d dice";
    private static final String NOT_MULTIPLE_OF_100_MESSAGE = "\tYou have to continue, "
            + "the score is not a multiple of 100";
    private static final String CONTINUE_MESSAGE = "\tContinue with %d dice? Current score: %d points (Y/N)\n";
    private static final String ROUND_SCORE_MESSAGE = "\tSuper you scored %d for the round!! Your new score is %d";

    // standard I/O
    private InputStream stdin;
    private PrintStream stdout;

    // Player for testing
    private Player p;
    private String name;

    @Before
    public void setUp() throws Exception {
        name = "John Doe";
        p = new Player(name);
        stdin = System.in;
        stdout = System.out;
    }

    @After
    public void cleanUp() {
        System.setIn(stdin);
        System.setOut(stdout);
    }

    // Test case for constructor

    @Test (timeout = 100)
    // @ScoringWeight(.05)
    public void testConstructor() {

        String message1 = "Player(): Be sure you properly set the player's name";
        String message2 = "Player(): Be sure you properly initialized the player's score";
        String message3 = "Player(): Be sure you properly initialize your dice";

        assertEquals(message1, p.name, name);
        assertEquals(message2, p.score, 0);

        // check initialization of dice by calling a method
        try {
            p.dice.nextInt();
        } catch (Exception e) {
            assertTrue(message3, false);
        }
    }

    // Test cases for throwDice() method

    @Test(timeout = 100)
    // @ScoringWeight(.05)
    public void testThrowDiceCorrectAmount() {
        String message = "throwDice(): Be sure you roll as many dice as are passed to the method";
        String exceptionMessage = "throwDice(): Be sure your method returns a String";

        String dice = "";
        int nDice = 100;

        try {
            dice = p.throwDice(nDice);
        } catch (Exception e) {
            assertTrue(exceptionMessage, false);
        }

        // count number of dice rolls in String returned from throwDice()
        Scanner scan = new Scanner(dice);
        int diceFound = 0;
        while (scan.hasNext()) {
            int temp = scan.nextInt();
            diceFound++;
        }
        assertEquals(message, nDice, diceFound);
    }

    @Test(timeout = 100)
    // @ScoringWeight(.05)
    public void testThrowDiceCheckRange() {
        String message = "throwDice(): Be sure your method's dice values in the proper range of 1-6";
        String exceptionMessage = "throwDice(): Be sure your method returns a String";

        String dice = "";
        int nDice = 100;
        boolean inRange = true;

        try {
            dice = p.throwDice(nDice);
        } catch (Exception e) {
            assertTrue(exceptionMessage, false);
        }

        // check for dice in range 1-6
        Scanner scan = new Scanner(dice);
        while (inRange && scan.hasNext()) {
            int temp = scan.nextInt();
            inRange = temp > 0 && temp < 7;
        }
        assertTrue(message, inRange);
    }

    // Test cases for nbDiceScored() method

    @Test (timeout = 100)
    // @ScoringWeight(.075)
    public void testNbDiceScoredCorrectDice() {
        String message = "nbDicesScored(): Make sure you are counting all 1s and 5s";

        String dice = "1 2 3 4 5 6 5 4 3 2 1 ";
        int nScored = 4;
        assertEquals(message, nScored, p.nbDiceScored(dice));
    }

    @Test(timeout = 100)
    // @ScoringWeight(.075)
    public void testNbDiceScoredIncorrectDice() {
        String message = "nbDicesScored(): Make sure you are only counting 1s and 5s";

        String dice = "2 3 4 6 4 3 2 ";
        int nScored = 0;
        assertEquals(message, nScored, p.nbDiceScored(dice));
    }

    // Test cases for countPoints() method

    @Test(timeout = 100)
    // @ScoringWeight(.0375)
    public void testCountPoints1() {
        String dice = "1 ";

        String message = "countPoints(): Be sure to count 1s as 100pts each";

        int score = 100;

        assertEquals(message, score, p.countPoints(dice));
    }

    @Test(timeout = 100)
    // @ScoringWeight(.0375)
    public void testCountPoints5() {
        String dice = "5 ";
        String message = "countPoints(): Be sure to count 5s as 50pts each";
        int score = 50;
        assertEquals(message, score, p.countPoints(dice));
    }

    @Test(timeout = 100)
    // @ScoringWeight(.0375)
    public void testCountPoints0() {
        String dice = "2 3 4 6 ";
        String message = "countPoints(): Be sure you are only counting points for 1s and 5s";
        int score = 0;
        assertEquals(message, score, p.countPoints(dice));
    }

    @Test(timeout = 100)
    // @ScoringWeight(.0375)
    public void testCountPointsCombination() {
        String dice = "1 1 5 5 6 5 ";
        String message = "countPoints(): Be sure you are adding the scores together properly";
        int score = 350;
        assertEquals(message, score, p.countPoints(dice));
    }

    // Test cases for continueRound() method

    @Test(timeout = 100)
    // @ScoringWeight(.0625)
    public void testContinueRoundPrompt() {
        String message = "continueRound(): Be sure to prompt the user for input";

        String data = "Y";  // "user input"
        try {
            // replace input/output streams
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            System.setOut(new PrintStream(output));

            int score = p.score;
            p.continueRound(1);

            // check that the prompt to the user was output
            assertEquals(message, String.format(CONTINUE_MESSAGE, 1, score), output.toString());
        } catch (Exception e) {
            String exceptionMessage = "continueRound(): Be sure your method handles all I/O properly";
            assertTrue(exceptionMessage, false);
        }
    }

    @Test(timeout = 100)
    // @ScoringWeight(.0625)
    public void testContinueRoundAffirmative() {
        String message = "continueRound(): 'Y' should be affirmative input";

        String data = "Y";  // "user input"
        try {
            // replace input/output streams
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            System.setOut(new PrintStream(output));

            assertTrue(message, p.continueRound(1));
        } catch (Exception e) {
            String exceptionMessage = "continueRound(): Be sure your method handles all I/O properly";
            assertTrue(exceptionMessage, false);
        }
    }

    @Test(timeout = 100)
    // @ScoringWeight(.0625)
    public void testContinueRoundNegative() {
        String message = "continueRound(): 'N' should be negative input";

        String data = "N";  // "user input"
        try {
            // replace input/output streams
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            System.setOut(new PrintStream(output));

            assertFalse(message, p.continueRound(1));
        } catch (Exception e) {
            String exceptionMessage = "continueRound(): Be sure your method handles all I/O properly";
            assertTrue(exceptionMessage, false);
        }
    }

    @Test(timeout = 100)
    // @ScoringWeight(.0625)
    public void testContinueRoundValidation() {
        String message1 = "continueRound(): Be sure to check for valid input";
        String message2 = "continueRound(): Be sure to check for valid input until you get some";

        String data = "abcd \nefghijk \nlmnopqrstuvwx \nY";
        try {
            // replace input/output streams
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            System.setOut(new PrintStream(output));

            boolean b = p.continueRound(1);
            assertTrue(message2, b);
        } catch (Exception e) {
            assertTrue(message1, false);
        }
    }

    // Test cases for playRound() method

    @Test(timeout = 100)
    // @ScoringWeight(.05)
    public void testPlayRoundLaunchDice() {
        String message = "playRound(): Be sure to begin by launching the dice.\n";
        message = message + "THROW_DICE_MESSAGE may be missing or improperly formatted.\n";
        message = message + "Make sure you updated the round score correctly.";
        String message2 = "playRound(): Be sure to identify when the round score is zero.";
        String data = "N";  // "user input"
        try {
            // replace input/output streams
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            System.setOut(new PrintStream(output));

            p.dice = new TestDice("1 2 3 4 5 6 5 4 ");
            p.playRound();

            // check for launched dice message
            Scanner scan = new Scanner(output.toString());
            String launchLine = scan.nextLine() + "\n";
            String expectedLine = String.format(THROW_DICE_MESSAGE, "1 2 3 4 5 ", 0, 150, 150);

            assertEquals(message, expectedLine, launchLine);


            // Reset output stream for second case: zero score
            output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));

            p.dice = new TestDice("2 3 4 6 4 ");
            p.playRound();

            // check for zero round message
            scan = new Scanner(output.toString());
            launchLine = "";
            while (launchLine.equals(""))
                launchLine = scan.nextLine();

            expectedLine = String.format(LOSE_MESSAGE, "2 3 4 6 4 ");

            assertEquals(message2, expectedLine, launchLine);


        } catch (Exception e) {
            assertTrue(message, false);
        }
    }

    @Test(timeout = 100)
    // @ScoringWeight(.05)
    public void testPlayRoundContinue() {
        String message1 = "playRound(): Be sure round continues when round score is not multiple of 100";
        String message2 = "playRound(): Be sure to ask user to continue when round score is multiple of 100.";
        String message3 = "playRound(): Be sure to continue when the user tells you to";
        message1 = message1 + "This test case may have also failed if the above test case failed.";
        message2 = message2 + "This test case may have also failed if the above test case failed.";
        message3 = message3 + "This test case may have also failed if the above test case failed.";

        String data = "N";  // "user input"
        try {
            // replace input/output streams
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            System.setOut(new PrintStream(output));

            p.dice = new TestDice("1 2 3 4 5 2 3 4 ");
            p.playRound();

            // look for "not multiple of 100" continue message
            Scanner scan = new Scanner(output.toString());
            scan.nextLine();

            assertEquals(message1, NOT_MULTIPLE_OF_100_MESSAGE, scan.nextLine());

            //Reset output stream for second case: must ask user to continue
            output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));

            p.dice = new TestDice("1 2 3 5 5 ");
            p.playRound();

            scan = new Scanner(output.toString());
            scan.nextLine();
            String actualLine = scan.nextLine() + "\n";
            assertEquals(message2, String.format(CONTINUE_MESSAGE, 2, 0), actualLine);

            //Reset output stream for third case: user wants to continue
            data = "Y";
            output = new ByteArrayOutputStream();
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            System.setOut(new PrintStream(output));

            p.dice = new TestDice("1 2 3 5 5 2 3 ");
            p.playRound();

            scan = new Scanner(output.toString());
            scan.nextLine();
            scan.nextLine();

            actualLine = scan.nextLine();
            while (actualLine.equals("")) actualLine = scan.nextLine();
            assertEquals(message3, String.format(LOSE_MESSAGE, "2 3 "), actualLine);
        } catch (Exception e) {
            assertTrue("playRound(): an exception was thrown", false);
        }
    }

    @Test(timeout = 100)
    // @ScoringWeight(.05)
    public void testPlayRoundUpdateDice() {
        String data = "N";
        String message1 = "playRound(): Be sure you are updating the number of dice properly.\n";
        String message2 = "playRound(): Be sure to relaunch the dice if all are scored.\n";
        String message3 = "playRound(): Be sure you continue updating the number of dice\n";
        message1 = message1 + "This test case may have also failed if the above test cases failed.";
        message2 = message2 + "This test case may have also failed if the above test cases failed.";
        message3 = message3 + "This test case may have also failed if the above test cases failed.";
        try {
            // replace input/output streams
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            System.setOut(new PrintStream(output));

            p.dice = new TestDice("1 2 3 4 5 2 3 4 ");
            p.playRound();

            // search for appropriate lose message at end of round
            Scanner scan = new Scanner(output.toString());

            String updateLine1 = scan.nextLine();
            updateLine1 = scan.nextLine();
            updateLine1 = scan.nextLine();
            while (updateLine1.equals(""))
                updateLine1 = scan.nextLine();

            String expectedLine1 = String.format(LOSE_MESSAGE, "2 3 4 ");
            assertEquals(message1, expectedLine1, updateLine1);


            //Reset output stream for second case: all dice score, subsequent zero score
            output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));

            p.dice = new TestDice("1 2 3 4 5 1 1 5 2 3 4 6 4 ");
            p.playRound();

            // search for all-dice scored message
            scan = new Scanner(output.toString());
            scan.nextLine();
            scan.nextLine();
            String actualLine = "";
            while (actualLine.equals("")) actualLine = scan.nextLine();
            actualLine = scan.nextLine();
            assertEquals(message2, String.format(ALL_DICE_SCORED_MESSAGE, 5), actualLine);

            // search for lose message at end of round
            actualLine = "";
            while (actualLine.equals("")) actualLine = scan.nextLine();
            assertEquals(message2, String.format(LOSE_MESSAGE, "2 3 4 6 4 "), actualLine);


            //Reset output stream for third case: all dice score, subsequent roll scores
            output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));

            p.dice = new TestDice("1 2 3 4 5 1 1 5 1 2 3 4 5 2 3 4 ");
            p.playRound();

            // search for lose message at end of round
            scan = new Scanner(output.toString());
            scan.nextLine();
            scan.nextLine();
            while (scan.nextLine().equals(""));
            scan.nextLine();
            scan.nextLine();
            while (scan.nextLine().equals(""));
            scan.nextLine();
            actualLine = scan.nextLine();
            while (actualLine.equals("")) actualLine = scan.nextLine();
            assertEquals(message3, String.format(LOSE_MESSAGE, "2 3 4 "), actualLine);
            //stdout.println(output.toString());
        } catch (Exception e) {
            assertTrue("playRound(): an exception was thrown", false);
        }
    }

    @Test(timeout = 100)
    // @ScoringWeight(.05)
    public void testPlayRoundUpdateScore() {
        String message = "playRound(): Be sure to update the score properly.";
        message = message + "This test case may have also failed if the above test cases failed.";

        String data = "N";  // "user input"

        try {
            // replace input/output streams
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            System.setOut(new PrintStream(output));

            p.dice = new TestDice("1 2 3 4 5 1 1 5 2 3 4 6 4 ");
            p.playRound();

            // check for 0 score
            assertEquals(message, 0, p.score);

            //Reset output
            output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));

            p.dice = new TestDice("1 2 3 4 5 5 3 1 ");
            p.playRound();

            // look for round score message at end of round
            Scanner scan = new Scanner(output.toString());
            String actualLine = "";
            while (!(actualLine = scan.nextLine()).equals(""));
            while ((actualLine = scan.nextLine()).equals(""));
            scan.nextLine();
            assertEquals(message, String.format(ROUND_SCORE_MESSAGE, 300, 300), scan.nextLine());
            assertEquals(message, 300, p.score);
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("playRound(): an exception was thrown", false);
        }
    }

    // Test cases for isWinner() method

    @Test(timeout = 100)
    // @ScoringWeight(.025)
    public void testIsWinnerThreshold() {
        String message1 = "isWinner(): Check that you have a threshold of 5000 points";

        p.score = 4999;
        assertFalse(message1, p.isWinner());

        p.score = 5000;
        assertTrue(message1, p.isWinner());
    }

    @Test(timeout = 100)
    // @ScoringWeight(.025)
    public void testIsWinnerExceedsThreshold() {
        String message = "isWinner(): Check that you consider exceeding the threshold as winning the game";
        p.score = 5001;
        assertTrue(message, p.isWinner());
    }

    // TestDice to replace random dice in students submissions

    private class TestDice extends Random {
        private Scanner scan;
        public TestDice(String s) {
            scan = new Scanner(s);
        }

        public int nextInt(int x) {
            if (scan.hasNext())
                return scan.nextInt() - 1;
            else
                return -1;
        }
    }
}