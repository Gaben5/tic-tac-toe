package com.example.tictactoe.board;
public class Board {
    public static final int BOARD_SIZE = 3;
    private final Symbol[][] gameBoard = new Symbol[BOARD_SIZE][BOARD_SIZE];
    private Symbol player1Symbol = null;
    private Symbol player2Symbol = null;


    public Board() {
    }
    public Symbol getSymbol(int x, int y) {
        if (y < 0 || y > 2 || x < 0 || x > 2){
            return null;
        } else  {
            return gameBoard[x][y];
        }
    }
    public void userMove(int a, int b, Symbol symbol){
                gameBoard[a][b] = symbol;
    }

    public void clearBoard(){
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                gameBoard[x][y] = null;
            }
        }
    }
    public boolean isAvaliableMove(){
        int isZero = 0;
        for (int y = 0; y < Board.BOARD_SIZE; y++) {
            for (int x = 0; x < Board.BOARD_SIZE; x++) {
                if (getSymbol(x,y) != null){
                    isZero += 0;
                }else {
                    isZero += 1;
                }

            }
        }
        return isZero != 0;
    }

    public Symbol getPlayer1Symbol() {
        return player1Symbol;
    }

    public Symbol getPlayer2Symbol() {
        return player2Symbol;
    }

    public void setPlayer1Symbol(Symbol player1Symbol) {
        this.player1Symbol = player1Symbol;
    }

    public void setPlayer2Symbol(Symbol player2Symbol) {
        this.player2Symbol = player2Symbol;
    }
}
