package com.example.tictactoe.board;

public class BoardUI {
    private final Board board;
    public BoardUI(Board board) {
        this.board = board;
    }
    public void printBoard(){
        for (int y = 0; y < Board.BOARD_SIZE; y++) {
             for (int x = 0; x < Board.BOARD_SIZE; x++) {
                Symbol symbol = board.getSymbol(x,y);
                System.out.print("|" + (symbol == null ? " " : symbol));
            }
            System.out.print("|\n");
        }
    }
    public void legend(){
        int a = 1;
        for (int x = 0; x < Board.BOARD_SIZE; x++) {
            for (int y = 0; y < Board.BOARD_SIZE; y++) {
                System.out.print("|" + y + ","+ x);
                a+=1;
            }
            System.out.print("|\n");
        }
    }
    public String getDescription() {
        return "This is Tic-Tac-Toe game";
    }
    public String getInstruction() {
        return "Objective: Have players place their 3 " +
                    "assigned marks (X or O) in a continuous line, either vertically, horizontally or diagonally.";
    }
}
