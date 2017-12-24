import static org.junit.Assert.*;
import org.junit.*;

import java.util.Arrays;

public class Project02StudentTest {
    private static final int ROWS = 6;
    private static final int COLS = 6;

    private static final char DOT = '.';
    private static final char STAR = '*';
    private static final char PLUS = '+';
    private static final char EMPTY = ' ';
    private static final char CROSS = 'X';
    private static final char CIRCLE = 'O';

    private static final String MSG_VAL_LOC_PT = "ObstructionGame.playAtLocation() : Check if \"playAtLocation()\" " +
            "handles valid locations and player types correctly.";
    private static final String MSG_ILL_LOC = "ObstructionGame.playAtLocation() : Check if \"playAtLocation()\" " +
            "handles illegal locations correctly.";

    private static final String MSG_MOV_1 = "Play game : Incorrect \"gameBoard\" after 1st move \"O @ C2\".";
    private static final String MSG_MOV_2 = "Play game : Incorrect \"gameBoard\" after 2nd move \"X @ B4\". " +
            "[1st move = \"O @ C2\"]";
    private static final String MSG_MOV_3 = "Play game : Incorrect \"gameBoard\" after 3rd move \"O @ E4\". " +
            "[1st move = \"O @ C2\", 2nd move = \"X @ B4\"]";
    private static final String MSG_MOV_4 = "Play game : Incorrect \"gameBoard\" after 4th move \"X @ E1\". " +
            "[1st move = \"O @ C2\", 2nd move = \"X @ B4\", 3rd move = \"O @ E4\"]";
    private static final String MSG_MOV_5 = "Play game : Incorrect \"gameBoard\" after 5th move \"O @ B0\". " +
            "[1st move = \"O @ C2\", 2nd move = \"X @ B4\", 3rd move = \"O @ E4\", 4th move = \"X @ E1\"]";
    private static final String MSG_MOV_6 = "Play game : Incorrect \"gameBoard\" after 6th move \"X @ A2\". " +
            "[1st move = \"O @ C2\", 2nd move = \"X @ B4\", 3rd move = \"O @ E4\", 4th move = \"X @ E1\", 5th move = " +
            "\"O @ B0\"]";
    private static final String MSG_SCREEN_GAME_ENDED = "Play game : Incorrect \"screen\" after game ended. " +
            "[1st move = \"O @ C2\", 2nd move = \"X @ B4\", 3rd move = \"O @ E4\", 4th move = \"X @ E1\", 5th move = " +
            "\"O @ B0\", 6th move = \"X @ A2\"]";

    private static final char[][] BOARD_MOVE_1 = {{EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, DOT, DOT, DOT, EMPTY, EMPTY},
            {EMPTY, DOT, CIRCLE, DOT, EMPTY, EMPTY},
            {EMPTY, DOT, DOT, DOT, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY}};

    private static final char[][] BOARD_MOVE_2 = {{EMPTY, EMPTY, EMPTY, DOT, DOT, DOT},
            {EMPTY, DOT, DOT, DOT, CROSS, DOT},
            {EMPTY, DOT, CIRCLE, DOT, DOT, DOT},
            {EMPTY, DOT, DOT, DOT, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY}};

    private static final char[][] BOARD_MOVE_3 = {{EMPTY, EMPTY, EMPTY, DOT, DOT, DOT},
            {EMPTY, DOT, DOT, DOT, CROSS, DOT},
            {EMPTY, DOT, CIRCLE, DOT, DOT, DOT},
            {EMPTY, DOT, DOT, DOT, DOT, DOT},
            {EMPTY, EMPTY, EMPTY, DOT, CIRCLE, DOT},
            {EMPTY, EMPTY, EMPTY, DOT, DOT, DOT}};

    private static final char[][] BOARD_MOVE_4 = {{EMPTY, EMPTY, EMPTY, DOT, DOT, DOT},
            {EMPTY, DOT, DOT, DOT, CROSS, DOT},
            {EMPTY, DOT, CIRCLE, DOT, DOT, DOT},
            {DOT, DOT, DOT, DOT, DOT, DOT},
            {DOT, CROSS, DOT, DOT, CIRCLE, DOT},
            {DOT, DOT, DOT, DOT, DOT, DOT}};

    private static final char[][] BOARD_MOVE_5 = {{DOT, DOT, EMPTY, DOT, DOT, DOT},
            {CIRCLE, DOT, DOT, DOT, CROSS, DOT},
            {DOT, DOT, CIRCLE, DOT, DOT, DOT},
            {DOT, DOT, DOT, DOT, DOT, DOT},
            {DOT, CROSS, DOT, DOT, CIRCLE, DOT},
            {DOT, DOT, DOT, DOT, DOT, DOT}};

    private static final char[][] BOARD_MOVE_6 = {{DOT, DOT, CROSS, DOT, DOT, DOT},
            {CIRCLE, DOT, DOT, DOT, CROSS, DOT},
            {DOT, DOT, CIRCLE, DOT, DOT, DOT},
            {DOT, DOT, DOT, DOT, DOT, DOT},
            {DOT, CROSS, DOT, DOT, CIRCLE, DOT},
            {DOT, DOT, DOT, DOT, DOT, DOT}};

    private static final char[][] BIG_CIRCLE = {{EMPTY, PLUS, PLUS, PLUS, PLUS, EMPTY},
            {PLUS, EMPTY, EMPTY, EMPTY, EMPTY, PLUS},
            {PLUS, EMPTY, EMPTY, EMPTY, EMPTY, PLUS},
            {PLUS, EMPTY, EMPTY, EMPTY, EMPTY, PLUS},
            {PLUS, EMPTY, EMPTY, EMPTY, EMPTY, PLUS},
            {EMPTY, PLUS, PLUS, PLUS, PLUS, EMPTY}};

    private static final char[][] BIG_CROSS = {{STAR, EMPTY, EMPTY, EMPTY, EMPTY, STAR},
            {EMPTY, STAR, EMPTY, EMPTY, STAR, EMPTY},
            {EMPTY, EMPTY, STAR, STAR, EMPTY, EMPTY},
            {EMPTY, EMPTY, STAR, STAR, EMPTY, EMPTY},
            {EMPTY, STAR, EMPTY, EMPTY, STAR, EMPTY},
            {STAR, EMPTY, EMPTY, EMPTY, EMPTY, STAR}};

    private static final char[][] BIG_DOT =  {{DOT, DOT, DOT, DOT, DOT, DOT},
            {DOT, DOT, DOT, DOT, DOT, DOT},
            {DOT, DOT, DOT, DOT, DOT, DOT},
            {DOT, DOT, DOT, DOT, DOT, DOT},
            {DOT, DOT, DOT, DOT, DOT, DOT},
            {DOT, DOT, DOT, DOT, DOT, DOT}};

    private ObstructionGame game;

    @Before
    public void setUp() throws Exception {
        game = new ObstructionGame(ROWS, COLS);
    }

    /**
     * Test ObstructionGame()
     */
    @Test(timeout = 100)
    //@ScoringWeight(.02)
    public void testConstructorDimensions() {
        try {
            // gameBoard
            assertEquals("ObstructionGame() : Check if \"gameBoard\" is initialized properly.",
                    ROWS, game.gameBoard.length);
            assertEquals("ObstructionGame() : Check if \"gameBoard\" is initialized properly.",
                    COLS, game.gameBoard[0].length);
            // screen
            assertEquals("ObstructionGame() : Check if \"screen\" is initialized properly.",
                    ROWS * ObstructionGame.numPixels, game.screen.length);
            assertEquals("ObstructionGame() : Check if \"screen\" is initialized properly.",
                    COLS * ObstructionGame.numPixels, game.screen[0].length);
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.02)
    public void testConstructorInitGameBoard() {
        try {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    assertEquals("ObstructionGame() : Check if \"gameBoard\" is initialized properly.",
                            EMPTY, game.gameBoard[i][j]);
                }
            }
        } catch (Exception e) {
            assertTrue("ObstructionGame() : Check if the game is initialized properly.", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.02)
    public void testConstructorInitScreen() {
        try {
            for (int i = 0; i < ROWS * ObstructionGame.numPixels; i++) {
                for (int j = 0; j < COLS * ObstructionGame.numPixels; j++) {
                    assertEquals("ObstructionGame() : Check if \"screen\" is initialized properly.",
                            EMPTY, game.screen[i][j]);
                }
            }
        } catch (Exception e) {
            assertTrue("ObstructionGame() : Check if the game is initialized properly.", false);
        }
    }

    /**
     * Test ObstructionGame.playAtLocation()
     */
    @Test(timeout = 100)
    //@ScoringWeight(.02)
    public void testPlayAtLocationValidPlayers() {
        try {
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(0, 0, CROSS));
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(0, 2, CIRCLE));
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.04)
    public void testPlayAtLocationInvalidPlayer() {
        try {
            assertFalse("ObstructionGame.playAtLocation() : Check if \"playAtLocation()\" " +
                    "handles invalid player types correctly.", game.playAtLocation(0, 0, 'A'));
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.04)
    public void testPlayAtLocationOutsideGameBoard() {
        try {
            assertFalse("ObstructionGame.playAtLocation() : Check if \"playAtLocation()\" " +
                    "handles boundary conditions correctly.", game.playAtLocation(ROWS, COLS, CROSS));
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.05)
    public void testPlayAtLocationIllegalDot() {
        try {
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(0, 0, CROSS));
            assertFalse(MSG_ILL_LOC, game.playAtLocation(0, 1, CIRCLE));
            assertFalse(MSG_ILL_LOC, game.playAtLocation(1, 0, CIRCLE));
            assertFalse(MSG_ILL_LOC, game.playAtLocation(1, 1, CIRCLE));

            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(2, 2, CROSS));
            assertFalse(MSG_ILL_LOC, game.playAtLocation(1, 1, CIRCLE));
            assertFalse(MSG_ILL_LOC, game.playAtLocation(1, 2, CIRCLE));
            assertFalse(MSG_ILL_LOC, game.playAtLocation(1, 3, CIRCLE));
            assertFalse(MSG_ILL_LOC, game.playAtLocation(2, 1, CIRCLE));
            assertFalse(MSG_ILL_LOC, game.playAtLocation(2, 3, CIRCLE));
            assertFalse(MSG_ILL_LOC, game.playAtLocation(3, 1, CIRCLE));
            assertFalse(MSG_ILL_LOC, game.playAtLocation(3, 2, CIRCLE));
            assertFalse(MSG_ILL_LOC, game.playAtLocation(3, 3, CIRCLE));
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.04)
    public void testPlayAtLocationIllegalCrossOnCross() {
        try {
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(0, 0, CROSS));
            assertFalse(MSG_ILL_LOC, game.playAtLocation(0, 0, CROSS));
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.03)
    public void testPlayAtLocationIllegalCircleOnCircle() {
        try {
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(0, 0, CIRCLE));
            assertFalse(MSG_ILL_LOC, game.playAtLocation(0, 0, CIRCLE));
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.04)
    public void testPlayAtLocationIllegalCircleOnCross() {
        try {
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(0, 0, CROSS));
            assertFalse(MSG_ILL_LOC, game.playAtLocation(0, 0, CIRCLE));
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.03)
    public void testPlayAtLocationIllegalCrossOnCircle() {
        try {
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(0, 0, CIRCLE));
            assertFalse(MSG_ILL_LOC, game.playAtLocation(0, 0, CROSS));
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }
    /**
     * Test ObstructionGame.anyMovesPossible()
     */
    @Test(timeout = 100)
    //@ScoringWeight(.02)
    public void testAnyMovesPossibleFreshBoard() {
        try {
            assertTrue("ObstructionGame.anyMovesPossible() : Check if \"anyMovesPossible()\" " +
                    "handles all possible scenarios.", game.anyMovesPossible());
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.04)
    public void testAnyMovesPossibleIntermediate() {
        try {
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(2, 2, CIRCLE));
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(1, 4, CROSS));
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(4, 4, CIRCLE));

            assertTrue("ObstructionGame.anyMovesPossible() : Check if \"anyMovesPossible()\" " +
                    "handles all possible scenarios.", game.anyMovesPossible());
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.07)
    public void testAnyMovesPossibleFinal() {
        try {
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(2, 2, CIRCLE));
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(1, 4, CROSS));
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(4, 4, CIRCLE));
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(4, 1, CROSS));
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(1, 0, CIRCLE));
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(0, 2, CROSS));

            assertFalse("ObstructionGame.anyMovesPossible() : Check if \"anyMovesPossible()\" " +
                    "handles the case when the game is over.", game.anyMovesPossible());
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    /**
     * Test ObstructionGame.getBoard()
     */
    @Test(timeout = 100)
    //@ScoringWeight(.06)
    public void testGetBoardCopy() {
        try {
            char[][] gameBoardCopy = game.getBoard();

            gameBoardCopy[0][0] = 'Z';
            assertTrue("ObstructionGame.getBoard() : Check if you return a copy of \"gameBoard\".",
                    'Z' != game.gameBoard[0][0]);
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.02)
    public void testGetBoardFreshBoard() {
        try {
            char[][] gameBoardCopy = game.getBoard();

            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    assertEquals("ObstructionGame.getBoard() : Check if you return an exact copy of \"gameBoard\".",
                            game.gameBoard[i][j], gameBoardCopy[i][j]);
                }
            }
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.02)
    public void testGetBoardIntermediate() {
        try {
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(2, 2, CIRCLE));
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(1, 4, CROSS));
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(4, 4, CIRCLE));

            char[][] gameBoardCopy = game.getBoard();

            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    assertEquals("ObstructionGame.getBoard() : Check if you return an exact copy of gameBoard.",
                            game.gameBoard[i][j], gameBoardCopy[i][j]);
                }
            }
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.02)
    public void testGetBoardFinal() {
        try {
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(2, 2, CIRCLE));
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(1, 4, CROSS));
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(4, 4, CIRCLE));
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(4, 1, CROSS));
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(1, 0, CIRCLE));
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(0, 2, CROSS));

            char[][] gameBoardCopy = game.getBoard();

            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    assertEquals("ObstructionGame.getBoard() : Check if you return an exact copy of gameBoard.",
                            game.gameBoard[i][j], gameBoardCopy[i][j]);
                }
            }
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    /**
     * Test ObstructionGame.updateScreen()
     */
    @Test(timeout = 100)
    //@ScoringWeight(.04)
    public void testUpdateScreenOutsideScreen() {
        try {
            game.updateScreen(ROWS, COLS);

            for (int i = 0; i < ROWS * ObstructionGame.numPixels; i++) {
                for (int j = 0; j < COLS * ObstructionGame.numPixels; j++) {
                    assertEquals("ObstructionGame.updateScreen() : Check if you handle boundary conditions.",
                            EMPTY, game.screen[i][j]);
                }
            }
        } catch (Exception e) {
            assertTrue("ObstructionGame.updateScreen() : Check if you handle boundary conditions.", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.05)
    public void testUpdateScreenForO() {
        try {
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(0, 0, CIRCLE));
            game.updateScreen(0, 0);

            assertTrue("ObstructionGame.updateScreen() : Check updateScreen for O.",
                    Arrays.deepEquals(BIG_CIRCLE, getScreenView(0, 0)));
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.05)
    public void testUpdateScreenForX() {
        try {
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(0, 0, CROSS));
            game.updateScreen(0, 0);

            assertTrue("ObstructionGame.updateScreen() : Check updateScreen for X.",
                    Arrays.deepEquals(BIG_CROSS, getScreenView(0, 0)));
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.02)
    public void testUpdateScreenIntermediate() {
        try {
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(2, 2, CIRCLE));
            game.updateScreen(2, 2);
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(1, 4, CROSS));
            game.updateScreen(1, 4);
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(4, 4, CIRCLE));
            game.updateScreen(4, 4);

            assertTrue("ObstructionGame.updateScreen() : Check updateScreen for O.",
                    Arrays.deepEquals(BIG_CIRCLE, getScreenView(2, 2)));
            assertTrue("ObstructionGame.updateScreen() : Check updateScreen for X.",
                    Arrays.deepEquals(BIG_CROSS, getScreenView(1, 4)));
            assertTrue("ObstructionGame.updateScreen() : Check updateScreen for O.",
                    Arrays.deepEquals(BIG_CIRCLE, getScreenView(4, 4)));
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.04)
    public void testUpdateScreenFinal() {
        try {
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(2, 2, CIRCLE));
            game.updateScreen(2, 2);
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(1, 4, CROSS));
            game.updateScreen(1, 4);
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(4, 4, CIRCLE));
            game.updateScreen(4, 4);
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(4, 1, CROSS));
            game.updateScreen(4, 1);
            assertTrue(MSG_VAL_LOC_PT,game.playAtLocation(1, 0, CIRCLE));
            game.updateScreen(1, 0);
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(0, 2, CROSS));
            game.updateScreen(0, 2);

            assertTrue("ObstructionGame.updateScreen() : Check updateScreen for O.",
                    Arrays.deepEquals(BIG_CIRCLE, getScreenView(2, 2)));
            assertTrue("ObstructionGame.updateScreen() : Check updateScreen for X.",
                    Arrays.deepEquals(BIG_CROSS, getScreenView(1, 4)));
            assertTrue("ObstructionGame.updateScreen() : Check updateScreen for O.",
                    Arrays.deepEquals(BIG_CIRCLE, getScreenView(4, 4)));
            assertTrue("ObstructionGame.updateScreen() : Check updateScreen for X.",
                    Arrays.deepEquals(BIG_CROSS, getScreenView(4, 1)));
            assertTrue("ObstructionGame.updateScreen() : Check updateScreen for O.",
                    Arrays.deepEquals(BIG_CIRCLE, getScreenView(1, 0)));
            assertTrue("ObstructionGame.updateScreen() : Check updateScreen for X.",
                    Arrays.deepEquals(BIG_CROSS, getScreenView(0, 2)));
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    /**
     * Test ObstructionGame.getScreen()
     */
    @Test(timeout = 100)
    //@ScoringWeight(.05)
    public void testGetScreenCopy() {
        try {
            char[][] screenCopy = game.getScreen();

            screenCopy[0][0] = 'Z';
            assertTrue("ObstructionGame.getScreen() : Check if you return a copy of screen.",
                    'Z' != game.screen[0][0]);
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.02)
    public void testGetScreenFreshBoard() {
        try {
            char[][] screenCopy = game.getScreen();

            for (int i = 0; i < ROWS * ObstructionGame.numPixels; i++) {
                for (int j = 0; j < COLS * ObstructionGame.numPixels; j++) {
                    assertEquals("ObstructionGame.getScreen() : Check if you return an exact copy of screen.",
                            game.screen[i][j], screenCopy[i][j]);
                }
            }
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.02)
    public void testGetScreenIntermediate() {
        try {
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(2, 2, CIRCLE));
            game.updateScreen(2, 2);
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(1, 4, CROSS));
            game.updateScreen(1, 4);
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(4, 4, CIRCLE));
            game.updateScreen(4, 4);

            char[][] screenCopy = game.getScreen();

            for (int i = 0; i < ROWS * ObstructionGame.numPixels; i++) {
                for (int j = 0; j < COLS * ObstructionGame.numPixels; j++) {
                    assertEquals("ObstructionGame.getScreen() : Check if you return an exact copy of screen.",
                            game.screen[i][j], screenCopy[i][j]);
                }
            }
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    @Test(timeout = 100)
    //@ScoringWeight(.02)
    public void testGetScreenFinal() {
        try {
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(2, 2, CIRCLE));
            game.updateScreen(2, 2);
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(1, 4, CROSS));
            game.updateScreen(1, 4);
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(4, 4, CIRCLE));
            game.updateScreen(4, 4);
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(4, 1, CROSS));
            game.updateScreen(4, 1);
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(1, 0, CIRCLE));
            game.updateScreen(1, 0);
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(0, 2, CROSS));
            game.updateScreen(0, 2);

            char[][] screenCopy = game.getScreen();

            for (int i = 0; i < ROWS * ObstructionGame.numPixels; i++) {
                for (int j = 0; j < COLS * ObstructionGame.numPixels; j++) {
                    assertEquals("ObstructionGame.getScreen() : Check if you return an exact copy of screen.",
                            game.screen[i][j], screenCopy[i][j]);
                }
            }
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    /**
     * Play game from begin to end.
     */
    @Test(timeout = 100)
    //@ScoringWeight(.09)
    public void testPlayGame() {
        try {
            // Move 1
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(2, 2, CIRCLE));
            game.updateScreen(2, 2);
            assertTrue(MSG_MOV_1, Arrays.deepEquals(BOARD_MOVE_1, game.getBoard()));

            // Move 2
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(1, 4, CROSS));
            game.updateScreen(1, 4);
            assertTrue(MSG_MOV_2, Arrays.deepEquals(BOARD_MOVE_2, game.getBoard()));

            // Move 3
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(4, 4, CIRCLE));
            game.updateScreen(4, 4);
            assertTrue(MSG_MOV_3, Arrays.deepEquals(BOARD_MOVE_3, game.getBoard()));

            // Move 4
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(4, 1, CROSS));
            game.updateScreen(4, 1);
            assertTrue(MSG_MOV_4, Arrays.deepEquals(BOARD_MOVE_4, game.getBoard()));

            // Move 5
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(1, 0, CIRCLE));
            game.updateScreen(1, 0);
            assertTrue(MSG_MOV_5, Arrays.deepEquals(BOARD_MOVE_5, game.getBoard()));

            // Move 6
            assertTrue(MSG_VAL_LOC_PT, game.playAtLocation(0, 2, CROSS));
            game.updateScreen(0, 2);
            assertTrue(MSG_MOV_6, Arrays.deepEquals(BOARD_MOVE_6, game.getBoard()));

            assertFalse(game.anyMovesPossible());

            // Final view
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(0, 0)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(0, 1)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_CROSS, getScreenView(0, 2)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(0, 3)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(0, 4)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(0, 5)));

            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_CIRCLE, getScreenView(1, 0)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(1, 1)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(1, 2)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(1, 3)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_CROSS, getScreenView(1, 4)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(1, 5)));

            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(2, 0)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(2, 1)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_CIRCLE, getScreenView(2, 2)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(2, 3)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(2, 4)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(2, 5)));

            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(3, 0)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(3, 1)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(3, 2)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(3, 3)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(3, 4)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(3, 5)));

            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(4, 0)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_CROSS, getScreenView(4, 1)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(4, 2)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(4, 3)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_CIRCLE, getScreenView(4, 4)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(4, 5)));

            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(5, 0)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(5, 1)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(5, 2)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(5, 3)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(5, 4)));
            assertTrue(MSG_SCREEN_GAME_ENDED, Arrays.deepEquals(BIG_DOT, getScreenView(5, 5)));
        } catch (Exception e) {
            assertTrue("An exception was thrown!", false);
        }
    }

    private char[][] getScreenView(int row, int col) {
        char[][] view = new char[ObstructionGame.numPixels][ObstructionGame.numPixels];

        int startR = row * ObstructionGame.numPixels;
        int startC = col * ObstructionGame.numPixels;

        int r = 0;
        int c = 0;

        for (int i = startR; i < startR + ObstructionGame.numPixels; i++) {
            for (int j = startC; j < startC + ObstructionGame.numPixels; j++) {
                view[r][c++] = game.screen[i][j];
            }

            r++;
            c = 0;
        }

        return view;
    }
}