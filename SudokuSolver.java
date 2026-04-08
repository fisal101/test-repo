public class SudokuSolver {
    private static final int SIZE = 9;
    private static final int EMPTY = 0;

    /**
     * Solves a Sudoku puzzle using backtracking algorithm.
     *
     * @param board a 9x9 2D array where 0 represents empty cells
     * @return true if the puzzle is solved, false otherwise
     */
    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                // Find an empty cell
                if (board[row][col] == EMPTY) {
                    // Try digits 1-9
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            // Place the number
                            board[row][col] = num;

                            // Recursively try to solve the rest
                            if (solveSudoku(board)) {
                                return true;
                            }

                            // Backtrack if no solution found
                            board[row][col] = EMPTY;
                        }
                    }
                    // No valid number found, backtrack
                    return false;
                }
            }
        }
        // All cells filled successfully
        return true;
    }

    /**
     * Checks if placing a number at a given position is valid.
     *
     * @param board the Sudoku board
     * @param row the row index
     * @param col the column index
     * @param num the number to check
     * @return true if the placement is valid, false otherwise
     */
    private static boolean isValid(int[][] board, int row, int col, int num) {
        // Check row
        for (int x = 0; x < SIZE; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }

        // Check column
        for (int x = 0; x < SIZE; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }

        // Check 3x3 box
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Prints the Sudoku board in a formatted way.
     *
     * @param board the Sudoku board to print
     */
    public static void printBoard(int[][] board) {
        System.out.println("╔═══════╤═══════╤═══════╗");
        for (int row = 0; row < SIZE; row++) {
            System.out.print("║ ");
            for (int col = 0; col < SIZE; col++) {
                System.out.print(board[row][col] + " ");
                if ((col + 1) % 3 == 0) {
                    System.out.print("│ ");
                }
            }
            System.out.println("║");
            if ((row + 1) % 3 == 0 && row < SIZE - 1) {
                System.out.println("╠═══════╪═══════╪═══════╣");
            }
        }
        System.out.println("╚═══════╧═══════╧═══════╝");
    }

    /**
     * Main method to demonstrate the Sudoku solver.
     */
    public static void main(String[] args) {
        // Example Sudoku puzzle (0 represents empty cells)
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("Original Sudoku:");
        printBoard(board);

        if (solveSudoku(board)) {
            System.out.println("\nSolved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("No solution exists for this Sudoku!");
        }
    }
}
