# Sudoku Solver

A Java implementation of a **Sudoku puzzle solver** using the **backtracking algorithm**.

## Overview

This project provides a complete solution to solve any valid Sudoku puzzle. The solver uses a recursive backtracking approach to find the solution efficiently by:
- Trying numbers 1-9 in empty cells
- Validating against Sudoku rules (row, column, and 3x3 box constraints)
- Backtracking when no valid number can be placed
- Continuing recursively until the puzzle is solved

## Features

✅ **Backtracking Algorithm** - Efficient recursive approach to solve puzzles
✅ **Constraint Validation** - Validates row, column, and 3x3 box rules
✅ **Pretty-Print Display** - Formatted output with Unicode box characters
✅ **Easy to Use** - Simple API and example usage included
✅ **Optimized** - Early termination when constraints are violated

## How It Works

### Algorithm Steps

1. **Find Empty Cell**: Scan the board for the first empty cell (marked as 0)
2. **Try Numbers**: Attempt to place numbers 1-9 in that cell
3. **Validate**: Check if the number satisfies all Sudoku constraints:
   - Not already in the same row
   - Not already in the same column
   - Not already in the same 3x3 box
4. **Recurse**: If valid, recursively solve the rest of the puzzle
5. **Backtrack**: If no solution found, remove the number and try the next one
6. **Repeat**: Continue until all cells are filled or no solution exists

### Time Complexity

- **Worst Case**: O(9^(n²)) where n=9, but rarely reaches this
- **Average Case**: Much faster due to constraint propagation and early pruning
- **Practical Performance**: Most puzzles solve in milliseconds

## Usage

### Compilation

```bash
javac SudokuSolver.java
```

### Running

```bash
java SudokuSolver
```

### Code Example

```java
// Create a 9x9 board (0 represents empty cells)
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

// Solve the puzzle
if (SudokuSolver.solveSudoku(board)) {
    System.out.println("Solved!");
    SudokuSolver.printBoard(board);
} else {
    System.out.println("No solution exists!");
}
```

## API Reference

### Main Methods

#### `solveSudoku(int[][] board)`
Solves a Sudoku puzzle using backtracking.

**Parameters:**
- `board`: A 9x9 2D array where 0 represents empty cells, 1-9 are given numbers

**Returns:**
- `true` if puzzle is solved
- `false` if no solution exists

**Example:**
```java
boolean solved = SudokuSolver.solveSudoku(board);
```

#### `isValid(int[][] board, int row, int col, int num)`
Validates if a number can be placed at a specific position.

**Parameters:**
- `board`: The Sudoku board
- `row`: Row index (0-8)
- `col`: Column index (0-8)
- `num`: Number to validate (1-9)

**Returns:**
- `true` if placement is valid
- `false` if it violates Sudoku rules

#### `printBoard(int[][] board)`
Displays the Sudoku board in a formatted table.

**Parameters:**
- `board`: The Sudoku board to display

**Output Example:**
```
╔═══════╤═══════╤═══════╗
║ 5 3 4 │ 6 7 8 │ 9 1 2 │
║ 6 7 2 │ 1 9 5 │ 3 4 8 │
║ 1 9 8 │ 3 4 2 │ 5 6 7 │
╠═══════╪═══════╪═══════╣
║ 8 5 9 │ 7 6 1 │ 4 2 3 │
║ 4 2 6 │ 8 5 3 │ 7 9 1 │
║ 7 1 3 │ 9 2 4 │ 8 5 6 │
╠═══════╪═══════╪═══════╣
║ 9 6 1 │ 5 3 7 │ 2 8 4 │
║ 2 8 7 │ 4 1 9 │ 6 3 5 │
║ 3 4 5 │ 2 8 6 │ 1 7 9 │
╚═══════╧═══════╧═══════╝
```

## Implementation Details

### Validation Logic

The `isValid()` method checks three constraints:

1. **Row Check**: Ensures the number doesn't appear elsewhere in the row
2. **Column Check**: Ensures the number doesn't appear elsewhere in the column
3. **3x3 Box Check**: Ensures the number doesn't appear in the 3x3 sub-grid

### Backtracking Strategy

- When a number is placed, recursion explores the remaining puzzle
- If no solution is found deeper in the recursion, the number is removed
- The algorithm backtracks to try the next number
- This process continues until a solution is found or all possibilities are exhausted

## Example Output

**Input (Unsolved Puzzle):**
```
╔═══════╤═══════╤═══════╗
║ 5 3 0 │ 0 7 0 │ 0 0 0 │
║ 6 0 0 │ 1 9 5 │ 0 0 0 │
║ 0 9 8 │ 0 0 0 │ 0 6 0 │
╠═══════╪═══════╪═══════╣
║ 8 0 0 │ 0 6 0 │ 0 0 3 │
║ 4 0 0 │ 8 0 3 │ 0 0 1 │
║ 7 0 0 │ 0 2 0 │ 0 0 6 │
╠═══════╪═══════╪═══════╣
║ 0 6 0 │ 0 0 0 │ 2 8 0 │
║ 0 0 0 │ 4 1 9 │ 0 0 5 │
║ 0 0 0 │ 0 8 0 │ 0 7 9 │
╚═══════╧═══════╧═══════╝
```

**Output (Solved Puzzle):**
```
╔═══════╤═══════╤═══════╗
║ 5 3 4 │ 6 7 8 │ 9 1 2 │
║ 6 7 2 │ 1 9 5 │ 3 4 8 │
║ 1 9 8 │ 3 4 2 │ 5 6 7 │
╠═══════╪═══════╪═══════╣
║ 8 5 9 │ 7 6 1 │ 4 2 3 │
║ 4 2 6 │ 8 5 3 │ 7 9 1 │
║ 7 1 3 │ 9 2 4 │ 8 5 6 │
╠═══════╪═══════╪═══════╣
║ 9 6 1 │ 5 3 7 │ 2 8 4 │
║ 2 8 7 │ 4 1 9 │ 6 3 5 │
║ 3 4 5 │ 2 8 6 │ 1 7 9 │
╚═══════╧═══════╧═══════╝
```

## Limitations & Considerations

⚠️ **Valid Input Required**: The solver assumes the input puzzle is valid (has a unique solution)
⚠️ **No Input Validation**: For simplicity, the code doesn't validate input format
⚠️ **Single Solution**: Returns the first solution found (doesn't find all possible solutions)

## Potential Enhancements

- Add constraint propagation for better performance
- Implement constraint satisfaction techniques (like naked singles)
- Add timing/performance metrics
- Support for different difficulty levels
- Batch processing multiple puzzles

## License

This project is provided as-is for educational purposes.

## Author

Created with the humanAIze model

---

**Last Updated**: April 8, 2026
