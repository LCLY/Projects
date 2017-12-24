import java.util.*;

/**
 * CS 180 - Project 2 - Obstruction
 *
 * This class provides the skeleton methods you need to implement and
 * runs the in-console UI to play the game in the main method.
 *
 * @author Ley Yen Choo, lab sec 01
 * @version February, 14 2016
 */
public class ObstructionGame {

    // game variables
    public char[][] gameBoard;
    public char[][] screen;
    int rows;
    int cols;
    // display variables
    public static boolean usingLargeBoard = false;
    public static int numPixels = 6;

    // variable constants
    private static final char DOT = '.';
    private static final char STAR = '*';
    private static final char PLUS = '+';
    private static final char EMPTY = ' ';
    private static final char CROSS = 'X';
    private static final char CIRCLE = 'O';


    /**
     * Initializes all instance variables (such as gameBoard) used to
     * run the game. You may create other variables if you wish.
     *
     * @param rows number of rows on the board
     * @param cols number of columns on the board
     *
     */
    public ObstructionGame(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.gameBoard = new char [this.rows][this.cols];
        for (int i = 0 ; i < this.rows ; i++ ) {
            for (int j = 0 ; j < this.cols ; j++) {
                this.gameBoard[i][j] = EMPTY;
            }
        }

        this.screen = new char [this.rows * numPixels][this.cols * numPixels];
        for (int l = 0; l < this.rows * numPixels ; l++ ) {
            for ( int k = 0 ; k < this.cols * numPixels ; k++ ) {
                this.screen[l][k] = EMPTY;
            }
        }
    }

    /**
     * Updates the board with the player's move.
     *
     * @param row the row of the move
     *
     * @param col the column of the move
     *
     * @param player the player who is making the move.
     *
     * @return false if the location or player is not a legal spot
     *         or player, true otherwise
     */
    public boolean playAtLocation(int row, int col, char player) {
        boolean valid = false;
        if (row > this.rows - 1 || row < 0)
            return false;
        if (col > this.cols - 1 || col < 0)
            return false;
        if (player == CROSS || player == CIRCLE) {
            if (this.gameBoard[row][col] == EMPTY) {
                if (row == 0 && col == 0) {
                    if (this.gameBoard[row][col] != DOT && this.gameBoard[row][col]
                            != CROSS && this.gameBoard[row][col] != CIRCLE) {
                        if (this.gameBoard[row + 1][col + 1] != CROSS || this.gameBoard[row + 1][col + 1] != CIRCLE ||
                            this.gameBoard[row][col + 1] != CROSS || this.gameBoard[row][col + 1] != CIRCLE ||
                            this.gameBoard[row + 1][col] != CROSS || this.gameBoard[row + 1][col] != CIRCLE) {
                            this.gameBoard[row][col] = player;
                            this.gameBoard[row + 1][col + 1] = DOT;
                            this.gameBoard[row][col + 1] = DOT;
                            this.gameBoard[row + 1][col] = DOT;
                            valid = true; //top left corner
                        }
                    }
                }
                else if (row == 0 && col == this.gameBoard[1].length - 1) {
                    if (this.gameBoard[row][col] != DOT && this.gameBoard[row][col]
                            != CROSS && this.gameBoard[row][col] != CIRCLE) {
                        if (this.gameBoard[row][col - 1] != CROSS || this.gameBoard[row][col - 1] != CIRCLE ||
                            this.gameBoard[row + 1][col - 1] != CROSS || this.gameBoard[row + 1][col - 1] != CIRCLE ||
                            this.gameBoard[row + 1][col] != CROSS || this.gameBoard[row + 1][col] != CIRCLE) {
                            this.gameBoard[row][col] = player;
                            this.gameBoard[row][col - 1] = DOT;
                            this.gameBoard[row + 1][col - 1] = DOT;
                            this.gameBoard[row + 1][col] = DOT;
                            valid = true; //top right corner
                        }
                    }
                }
                else if (row == this.gameBoard.length - 1 && col == 0) {
                    if (this.gameBoard[row][col] != DOT && this.gameBoard[row][col]
                            != CROSS && this.gameBoard[row][col] != CIRCLE) {
                        if (this.gameBoard[row - 1][col] != CROSS || this.gameBoard[row - 1][col] != CIRCLE ||
                            this.gameBoard[row - 1][col + 1] != CROSS || this.gameBoard[row - 1][col + 1] != CIRCLE ||
                            this.gameBoard[row][col] != CROSS || this.gameBoard[row][col] != CIRCLE) {
                            this.gameBoard[row][col] = player;
                            this.gameBoard[row - 1][col] = DOT;
                            this.gameBoard[row - 1][col + 1] = DOT;
                            this.gameBoard[row][col + 1] = DOT;
                            valid = true; //bottom left corner
                        }
                    }
                }
                else if (row == this.gameBoard.length - 1 && col == this.gameBoard[1].length - 1) {
                    if (this.gameBoard[row][col] != DOT && this.gameBoard[row][col]
                            != CROSS && this.gameBoard[row][col] != CIRCLE) {
                        if (this.gameBoard[row][col - 1] != CROSS || this.gameBoard[row][col - 1] != CIRCLE ||
                            this.gameBoard[row - 1][col - 1] != CROSS || this.gameBoard[row - 1][col - 1] != CIRCLE ||
                            this.gameBoard[row - 1][col] != CROSS || this.gameBoard[row - 1][col] != CIRCLE) {
                            this.gameBoard[row][col] = player;
                            this.gameBoard[row][col - 1] = DOT;
                            this.gameBoard[row - 1][col - 1] = DOT;
                            this.gameBoard[row - 1][col] = DOT;
                            valid = true; //bottom right corner
                        }
                    }
                }
                else if (row == 0 && col >= 1 && col <= this.gameBoard[1].length - 2) {
                    if (this.gameBoard[row][col] != DOT && this.gameBoard[row][col]
                            != CROSS && this.gameBoard[row][col] != CIRCLE) {
                        if (this.gameBoard[row][col - 1] != CROSS || this.gameBoard[row][col - 1] != CIRCLE ||
                            this.gameBoard[row + 1][col - 1] != CROSS || this.gameBoard[row + 1][col - 1] != CIRCLE ||
                            this.gameBoard[row + 1][col] != CROSS || this.gameBoard[row + 1][col] != CIRCLE ||
                            this.gameBoard[row + 1][col + 1] != CROSS || this.gameBoard[row + 1][col + 1] != CIRCLE ||
                            this.gameBoard[row][col + 1] != CROSS || this.gameBoard[row][col + 1] != CIRCLE) {
                            this.gameBoard[row][col] = player;
                            this.gameBoard[row][col - 1] = DOT;
                            this.gameBoard[row + 1][col - 1] = DOT;
                            this.gameBoard[row + 1][col] = DOT;
                            this.gameBoard[row + 1][col + 1] = DOT;
                            this.gameBoard[row][col + 1] = DOT;
                            valid = true; //top row
                        }
                    }
                }
                else if (row == this.gameBoard.length - 1 && col >= 1 && col <= this.gameBoard[1].length - 2 ) {
                    if (this.gameBoard[row][col] != DOT && this.gameBoard[row][col]
                            != CROSS && this.gameBoard[row][col] != CIRCLE) {
                        if (this.gameBoard[row][col - 1] != CROSS || this.gameBoard[row][col - 1] != CIRCLE ||
                            this.gameBoard[row][col + 1] != CROSS || this.gameBoard[row][col + 1] != CIRCLE ||
                            this.gameBoard[row - 1][col] != CROSS || this.gameBoard[row - 1][col] != CIRCLE ||
                            this.gameBoard[row - 1][col - 1] != CROSS || this.gameBoard[row - 1][col - 1] != CIRCLE ||
                            this.gameBoard[row - 1][col + 1] != CROSS || this.gameBoard[row - 1][col + 1] != CIRCLE) {
                            this.gameBoard[row][col] = player;
                            this.gameBoard[row][col - 1] = DOT;
                            this.gameBoard[row - 1][col + 1] = DOT;
                            this.gameBoard[row - 1][col] = DOT;
                            this.gameBoard[row - 1][col - 1] = DOT;
                            this.gameBoard[row][col + 1] = DOT;
                            valid = true; //bottom row
                        }
                    }
                }
                else if (row >= 1 && row <= this.gameBoard.length - 2 && col == 0) {
                    if (this.gameBoard[row][col] != DOT && this.gameBoard[row][col]
                            != CROSS && this.gameBoard[row][col] != CIRCLE) {
                        if (this.gameBoard[row - 1][col] != CROSS || this.gameBoard[row - 1][col] != CIRCLE ||
                            this.gameBoard[row - 1][col + 1] != CROSS || this.gameBoard[row - 1][col + 1] != CIRCLE ||
                            this.gameBoard[row][col + 1] != CROSS || this.gameBoard[row][col + 1] != CIRCLE ||
                            this.gameBoard[row + 1][col] != CROSS || this.gameBoard[row + 1][col] != CIRCLE ||
                            this.gameBoard[row + 1][col + 1] != CROSS || this.gameBoard[row + 1][col + 1] != CIRCLE) {
                            this.gameBoard[row][col] = player;
                            this.gameBoard[row - 1][col] = DOT;
                            this.gameBoard[row - 1][col + 1] = DOT;
                            this.gameBoard[row][col + 1] = DOT;
                            this.gameBoard[row + 1][col] = DOT;
                            this.gameBoard[row + 1][col + 1] = DOT;
                            valid = true; //leftmost column
                        }
                    }
                }
                else if (row >= 1 && row < this.gameBoard.length - 1 && col == this.gameBoard[1].length) {
                    if (this.gameBoard[row][col] != DOT && this.gameBoard[row][col]
                            != CROSS && this.gameBoard[row][col] != CIRCLE) {
                        if (this.gameBoard[row - 1][col] != CROSS || this.gameBoard[row - 1][col] != CIRCLE ||
                            this.gameBoard[row - 1][col - 1] != CROSS || this.gameBoard[row - 1][col - 1] != CIRCLE ||
                            this.gameBoard[row][col - 1] != CROSS || this.gameBoard[row][col - 1] != CIRCLE ||
                            this.gameBoard[row + 1][col] != CROSS || this.gameBoard[row + 1][col] != CIRCLE ||
                            this.gameBoard[row + 1][col - 1] != CROSS || this.gameBoard[row + 1][col - 1] != CIRCLE) {
                            this.gameBoard[row][col] = player;
                            this.gameBoard[row - 1][col] = DOT;
                            this.gameBoard[row - 1][col - 1] = DOT;
                            this.gameBoard[row + 1][col] = DOT;
                            this.gameBoard[row + 1][col] = DOT;
                            this.gameBoard[row + 1][col - 1] = DOT;
                            valid = true; //rightmost column
                        }
                    }
                }
                else if (row >= 1 && row <= this.gameBoard.length - 2
                        && col <= this.gameBoard[1].length - 2 && col >= 1) {
                    if (this.gameBoard[row][col] != DOT && this.gameBoard[row][col]
                            != CROSS && this.gameBoard[row][col] != CIRCLE) {
                        if (this.gameBoard[row - 1][col - 1] != CROSS || this.gameBoard[row - 1][col - 1] != CIRCLE ||
                            this.gameBoard[row - 1][col] != CROSS || this.gameBoard[row - 1][col] != CIRCLE ||
                            this.gameBoard[row - 1][col + 1] != CROSS || this.gameBoard[row - 1][col + 1] != CIRCLE ||
                            this.gameBoard[row][col + 1] != CROSS || this.gameBoard[row][col + 1] != CIRCLE ||
                            this.gameBoard[row + 1][col + 1] != CROSS || this.gameBoard[row + 1][col + 1] != CIRCLE ||
                            this.gameBoard[row + 1][col] != CROSS || this.gameBoard[row + 1][col] != CIRCLE ||
                            this.gameBoard[row + 1][col - 1] != CROSS || this.gameBoard[row + 1][col - 1] != CIRCLE ||
                            this.gameBoard[row][col - 1] != CROSS || this.gameBoard[row][col - 1] != CIRCLE) {
                            this.gameBoard[row][col] = player;
                            this.gameBoard[row - 1][col - 1] = DOT;
                            this.gameBoard[row - 1][col] = DOT;
                            this.gameBoard[row - 1][col + 1] = DOT;
                            this.gameBoard[row][col + 1] = DOT;
                            this.gameBoard[row + 1][col + 1] = DOT;
                            this.gameBoard[row + 1][col] = DOT;
                            this.gameBoard[row + 1][col - 1] = DOT;
                            this.gameBoard[row][col - 1] = DOT;
                            valid = true; //every other position
                        }
                    }
                }

            }
        }
        return valid;

    }

    /**
     * Determines if there are any valid moves on the board that a
     * player can make. This function is used to check if the game
     * has ended.
     *
     * @return true if the player can make a move
     */
    public boolean anyMovesPossible() {
        int trueFalse = 0;
        for (int i = 0 ; i < this.gameBoard.length ; i++ ) {
            for (int j = 0 ; j < this.gameBoard[1].length; j++ ) {
                if ( this.gameBoard[i][j] == EMPTY ) {
                    trueFalse = 1;
                }
            }
        }
        if ( trueFalse == 1 ) {
            return true;
        }
        return false;

    }

    /**
     * This is a getter method that returns a copy of the game board.
     *
     * @return a copy of the current state of the board
     */
    public char[][] getBoard() {
        char[][] newBoard = new char[this.rows][this.cols];
        for ( int i = 0; i < this.rows ; i++ ) {
            for ( int j = 0; j < this.cols; j++ ) {
                newBoard[i][j] = this.gameBoard[i][j];
            }
        }

        return newBoard;
    }

    /**
     * This method updates the screen variable to represent the player
     * move in the specified cell as large ascii characters.
     * @param row row number of cell
     * @param col column number of cell
     */
    public void updateScreen(int row, int col) {
        int startForRow = row * numPixels;
        int endForRow = (row + 1) * numPixels - 1;
        int startForCol = col * numPixels;
        int endForCol = (col + 1) * numPixels - 1;
        if (row < this.rows && row >= 0 && col < this.cols && col >= 0) {
            if (this.gameBoard[row][col] == CIRCLE) {
                for (int i = startForRow + 1 ; i < endForRow ; i++) {
                    this.screen[i][col * numPixels] = PLUS;
                }
                for (int j = startForRow + 1 ; j < endForRow ; j++) {
                    this.screen[j][(col * numPixels + numPixels) - 1] = PLUS;
                }
                for (int k = startForCol + 1 ; k < endForCol ; k++) {
                    this.screen[row * numPixels][k] = PLUS;
                }
                for (int l = startForCol + 1 ; l < endForCol ; l++) {
                    this.screen[(row * numPixels + numPixels) - 1][l] = PLUS;
                }
            }
            if (this.gameBoard[row][col] == CROSS) {

                for (int i = 0; i <= numPixels - 1 ; i++) {
                    this.screen[i + row * numPixels][i + col * numPixels] = STAR;
                    this.screen[((numPixels - 1) - i) + row * numPixels][i + col * numPixels] = STAR;
                }

            }
        }
        for (int i = 0 ; i < this.gameBoard.length ; i++) {
            for (int j = 0 ; j < this.gameBoard[1].length ; j++) {
                if (this.gameBoard[i][j] == DOT) {
                    for (int a = i * numPixels ; a < (i + 1) * numPixels ; a++) {
                        for (int b = j * numPixels ; b < (j + 1) * numPixels ; b++) {
                            this.screen[a][b] = DOT;
                        }
                    }
                }
            }
        }
    }

    /**
     * This is a getter method that returns of copy of the screen.
     *
     * @return a copy of the current state of the screen
     */
    public char[][] getScreen() {
        char[][] newScreen = new char[this.rows * numPixels][this.cols * numPixels];
        for (int i = 0; i < this.rows * numPixels ; i++) {
            for (int j = 0; j < this.cols * numPixels; j++) {
                newScreen[i][j] = this.screen[i][j];
            }
        }

        return newScreen;
    }

    /**
     * This method prints a pretty version of the board to the
     * console. While we are giving this method to you, feel free to
     * edit it if you are confident enough in your coding abilities.
     */
    public void printBoard() {
        char[][] board = getBoard();

        if (!usingLargeBoard) {
            // Make the header of the board
            System.out.printf("\n ");
            for (int i = 0; i < board[0].length; ++i)
                System.out.printf("   %d", i);
            System.out.println();

            System.out.printf("  ");
            for (int i = 0; i < board[0].length; ++i)
                System.out.printf("----");
            System.out.println("-");

            // Print the board contents
            for (int i = 0; i < board.length; ++i) {
                System.out.printf("%c ", 'A' + i);
                for (int k = 0; k < board[0].length; ++k)
                    System.out.printf("| %c ", board[i][k]);
                System.out.println("|");

                // print the line between each row
                System.out.printf("  ");
                for (int k = 0; k < board[0].length; ++k)
                    System.out.printf("----");
                System.out.println("-");
            }
        } else { // where we are printing the large board

            // Make the header of the board
            System.out.println();
            for (int i = 0; i < board[0].length; ++i) {
                for (int k = 0; k < numPixels; ++k)
                    System.out.printf(" ");
                System.out.printf("%d", i);
            }
            System.out.println();

            System.out.printf("  ");
            for (int i = 0; i < board[0].length; ++i) {
                for (int k = 0; k < numPixels + 1; ++k)
                    System.out.printf("-");
            }
            System.out.println("-");

            // Print the board contents
            for (int i = 0; i < screen.length; ++i) {
                System.out.printf("%c |", i % numPixels == numPixels / 2 ?
                        'A' + i / numPixels : EMPTY);

                // print the row of the screen
                for (int k = 0; k < screen[0].length; ++k) {
                    System.out.printf("%c", screen[i][k]);
                    if ((k + 1) % numPixels == 0)
                        System.out.print("|");
                }
                System.out.println();

                // print the line between each row
                if ((i + 1) % numPixels == 0) {
                    System.out.printf("  ");
                    for (int j = 0; j < board[0].length; ++j) {
                        for (int k = 0; k < numPixels + 1; ++k)
                            System.out.printf("-");
                    }
                    System.out.println("-");
                }
            }
        }
    }

    /**
     * This method initiates gameplay.
     */
    public void play() {
        char currentPlayer = CROSS;

        // Begin playing the game
        Scanner in = new Scanner(System.in);
        do {
            currentPlayer = currentPlayer == CIRCLE ? CROSS : CIRCLE;
            this.printBoard();
            System.out.printf("Current player: '%c'\n", currentPlayer);

            // read and validate the input
            int row = -1;
            int col = -1;
            do {
                System.out.printf("Choose a move location: ");
                String line = in.nextLine();

                if (line == null)
                    continue;

                // Check for special commands
                if (line.equals("switch")) {
                    usingLargeBoard = !usingLargeBoard;
                    System.out.printf("Big Board display is %s.\n",
                            usingLargeBoard ? "on" : "off");
                    this.printBoard();
                    continue;
                }
                else if (line.equals("end")) {
                    System.out.printf("Game is manually ending.\n");
                    return;
                }
                else if (line.contains("=")) {
                    String[] parts = line.split("=");
                    if (parts.length == 2 && parts[0].equals("pixels")) {
                        // Change the number of pixels in the big board

                        if ((new Scanner(parts[1])).hasNextInt()) {
                            if (Integer.parseInt(parts[1]) < 3)
                                continue;
                            numPixels = Integer.parseInt(parts[1]);
                        }
                        else
                            continue;

                        System.out.printf("Big Board cell sizes are "
                                + "now %sx%s pixels.\n", parts[1], parts[1]);

                        screen = new char[numPixels * gameBoard.length]
                                [numPixels * gameBoard[0].length];
                        for (int i = 0; i < screen.length; ++i) {
                            for (int j = 0; j < screen[i].length; ++j) {
                                screen[i][j] = EMPTY;
                            }
                        }

                        for (int i = 0; i < gameBoard.length; ++i)
                            for (int j = 0; j < gameBoard[i].length; ++j)
                                this.updateScreen(i, j);

                        if (usingLargeBoard)
                            this.printBoard();
                    }

                    continue;
                }
                else if (line.length() != 2)
                    continue;

                row = line.charAt(0) - 'A';
                col = line.charAt(1) - '0';

            } while (!this.playAtLocation(row, col, currentPlayer));

        } while (this.anyMovesPossible());

        this.printBoard();
        System.out.printf("\n!!! Winner is Player '%c' !!!\n", currentPlayer);
        in.close();
    }

}