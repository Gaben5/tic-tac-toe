package com.example.tictactoe.board;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Board {
    public static final int BOARD_SIZE = 3;
    private boolean isGameEnd = true;
    private Symbol[][] gameBoard = new Symbol[BOARD_SIZE][BOARD_SIZE];

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
        for (int y = 0; y < Board.BOARD_SIZE; y++) {
            for (int x = 0; x < Board.BOARD_SIZE; x++) {
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
        if (isZero == 0){
            return false;
        }else {
            return true;
        }
    }

    public void isGameOverHorizontal(Symbol symbol){
        isGameEnd = true;
        for (int y = 0; y < BOARD_SIZE; y++) {
            int check = 0;
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (symbol.equals(getSymbol(x,y))){
                    check+=1;
                }
            }
            if (check == 3){
                isGameEnd = false;
            }
        }
    }
    public void isGameOverVertical(Symbol symbol){
        for (int x = 0; x < BOARD_SIZE; x++) {
            int check = 0;
            for (int y = 0; y < BOARD_SIZE; y++) {
                if (symbol.equals(getSymbol(x,y))){
                    check+=1;
                }
            }
            if (check == 3){
                isGameEnd = false;
            }
        }
    }
    public void isGameOverSlant(Symbol symbol){
        if ((getSymbol(0,0) == symbol && getSymbol(1,1) == symbol && getSymbol(2,2) == symbol) ||
                (getSymbol(2,0) == symbol && getSymbol(1,1) == symbol && getSymbol(0,2) == symbol)){
            isGameEnd = false;
        }
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }
}
