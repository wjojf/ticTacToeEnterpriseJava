package kdg.tictactoe.presentation.ascii;

import kdg.tictactoe.presentation.ITicTacToe;
import kdg.tictactoe.domain.manager.GridStatefulManager;
import kdg.tictactoe.domain.models.PlayerMove;
import kdg.tictactoe.presentation.ascii.models.PlayerMoveInput;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TTTAnonymus implements ITicTacToe {
    protected final GridStatefulManager gridStatefulManager;
    private final char xSymbol = 'X';
    private final char oSymbol = 'O';
    private final char emptySymbol = ' ';
    private final char[][] displayGrid;
    protected final char[] rowIndexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

    public TTTAnonymus(GridStatefulManager gridStatefulManager) {
        this.gridStatefulManager = gridStatefulManager;
        this.displayGrid = new char[this.gridStatefulManager.getGridSize()][this.gridStatefulManager.getGridSize()];
    }

    public void playGame() {
        System.out.println(getWelcomeMessage());

        this.printGrid();

        while (!this.gridStatefulManager.isGameOver()) {
            PlayerMoveInput playerMoveInput = this.takePlayerMoveInput();
            this.handlePlayerMoveInput(playerMoveInput);
        }

        this.handleGameOver();

    }

    public void printGrid() {
        char[][] displayGrid = this.getDisplayGrid();

        int size = this.gridStatefulManager.getGridSize();

        String columnIndexesLabel = this.getColumnIndexesDisplayRow(size);
        System.out.println(columnIndexesLabel);


        for (int rowNumber = 0; rowNumber < size; rowNumber++) {
            char[] gridRow = displayGrid[rowNumber];

            String gridRowDisplayString = this.getGridRowDisplayString(gridRow, rowNumber);
            String gridRowDelimString = this.getRowDelimString(gridRowDisplayString);

            System.out.println(gridRowDisplayString);
            System.out.println(gridRowDelimString);
        }

    }

    public PlayerMoveInput takePlayerMoveInput() {

        String playerInputMessage = getPlayerInputMessage();

        Scanner keyboard = new Scanner(System.in);

        PlayerMoveInput playerMove;

        while (true) {
            System.out.print(playerInputMessage);
            String playerInput = keyboard.nextLine();
            try {
                playerMove = PlayerMoveInput.FromStringInput(playerInput);

                if (!isValidPlayerMoveInput(playerMove)) {
                    continue;
                }

                return playerMove;
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void handlePlayerMoveInput(PlayerMoveInput playerMoveInput) {
        PlayerMove playerMove;
        try {
            playerMove = this.getPlayerMoveDomain(playerMoveInput);
        }
        catch (IllegalArgumentException e) {
            return;
        }

        this.gridStatefulManager.makeMove(playerMove.row, playerMove.col);
        this.printGrid();
    }

    private char[][] getDisplayGrid() {
        int[][] grid = this.gridStatefulManager.getGrid();

        int xValue = this.gridStatefulManager.getXValue();
        int oValue = this.gridStatefulManager.getOValue();

        for (int i = 0; i < this.gridStatefulManager.getGridSize(); i++) {
            for (int j = 0; j < this.gridStatefulManager.getGridSize(); j++) {
                if (grid[i][j] == xValue) {
                    this.displayGrid[i][j] = this.xSymbol;
                } else
                if (grid[i][j] == oValue) {
                    this.displayGrid[i][j] = this.oSymbol;
                } else {
                    this.displayGrid[i][j] = this.emptySymbol;
                }
            }
        }

        return this.displayGrid;
    }

    private @NotNull String getColumnIndexesDisplayRow(int gridSize) {
        StringBuilder columnIndexesLabel = new StringBuilder();
        columnIndexesLabel.append(" ");
        for (int i = 1; i <= gridSize; i++) {
            columnIndexesLabel.append(" ");
            columnIndexesLabel.append(i);
            columnIndexesLabel.append(" ");
        }

        return columnIndexesLabel.toString();

    }

    private @NotNull String getGridRowDisplayString(char @NotNull [] row, int rowNumber) {

        StringBuilder rowString = new StringBuilder();
        rowString.append(rowIndexes[rowNumber]);

        for (char value : row) {
            rowString.append("|");
            rowString.append(value);
            rowString.append(" ");
        }

        rowString.append("|");

        return rowString.toString();
    }

    private @NotNull String getRowDelimString(@NotNull String row) {
        return "-".repeat(row.length());
    }

    private @NotNull String getPlayerInputMessage() {
        boolean isXTurn = this.gridStatefulManager.isXTurn();

        if (isXTurn) {
            return "X player turn. Enter grid cell (A.1): ";
        }

        return "0 player turn. Enter grid cell (A.1): ";
    }

    private boolean isValidPlayerMoveInput(@NotNull PlayerMoveInput playerMoveInput) {
        PlayerMove playerMove;

        try {
            playerMove = this.getPlayerMoveDomain(playerMoveInput);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return (
                this.gridStatefulManager.isSlotValid(playerMove.row, playerMove.col) &&
                this.gridStatefulManager.isSlotFree(playerMove.row, playerMove.col)
        );
    }

    private PlayerMove getPlayerMoveDomain(PlayerMoveInput playerMoveInput) {
        // check row input is a possible letter
        String rowIndexesString = new String(rowIndexes);

        if (!rowIndexesString.contains(playerMoveInput.row)) {
            throw new IllegalArgumentException("invalid row input");
        }

        // get rowNumber by letter indexes
        int rowNumber = new String(rowIndexes).indexOf(playerMoveInput.row);

        // check col input is a valid integer
        int colNumber;
        try {
            colNumber = Integer.parseInt(playerMoveInput.col);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("invalid col input;");
        }

        return new PlayerMove(rowNumber, colNumber - 1);
    }

    private static String getWelcomeMessage() {
        return "Welcome to TicTacToe!";
    }

    protected void handleGameOver() {
        boolean isDraw = this.gridStatefulManager.isDraw();

        if (isDraw) {
            System.out.println("The Game ended as a draw.");
            return;
        }

        boolean isXWin = this.gridStatefulManager.isXWin();
        if (isXWin) {
            System.out.println("X player won! Congratulations");
            return;
        }

        boolean isOWin = this.gridStatefulManager.isOWin();
        if (isOWin) {
            System.out.println("O player won! Congratulations");
        }
    }

}
