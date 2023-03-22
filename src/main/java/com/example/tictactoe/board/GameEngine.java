package com.example.tictactoe.board;

import java.util.*;

public class GameEngine {
    private Symbol symbolWinner = null;
    private Symbol userSymbol = null;
    private Symbol playerTwoSymbol = null;
    private Board board = new Board();
    private BoardUI boardUI = new BoardUI(board);
    private Scanner sc = new Scanner(System.in);
    private Random random = new Random();
    private boolean end = false;
    private boolean endMove = false;
    private boolean isNextMove = true;
    private int symbolNumber;




    public void chooseSymbol(){
        while (!end) {
            System.out.println("Choose your symbol 0->O ; 1->X");
            symbolNumber = sc.nextInt();
            if (symbolNumber == 0  || symbolNumber == 1 ){
                end = true;
            }
        }

        switch (symbolNumber) {
            case 0: userSymbol = Symbol.O;
                playerTwoSymbol = Symbol.X;
                break;
            case 1: userSymbol = Symbol.X;
                playerTwoSymbol = Symbol.O;
                break;
        }
        System.out.println("Your symbol: " + userSymbol);
        System.out.println("Player 2 symbol: " + playerTwoSymbol);
    }
    public int chooseEnemy(){
        int x = 100000;
        end = false;
        while (!end){
            System.out.println("Choose your enemy \n" +
                    "1- Friend\n" +
                    "2- Computer");
            x = sc.nextInt();
            if (x == 1 || x == 2) {
                end = true;
            }

        }
        return x;
    }

    public void userMove(){
        System.out.println("Player1 move");
        while (!endMove) {
            System.out.println("Choose column");
            int col = sc.nextInt();
            System.out.println("Choose row");
            int row = sc.nextInt();
        if (board.getSymbol(col,row) == null){
                board.userMove(col, row, getUserSymbol());
                endMove = true;
            }
        }
        endMove = false;
        boardUI.printBoard();
    }

    public void playerTwoMove(){
        System.out.println("Player2 move");
        while (!endMove) {
            System.out.println("Choose column");
            int col = sc.nextInt();
            System.out.println("Choose row");
            int row = sc.nextInt();
            if (board.getSymbol(col,row) == null){
                board.userMove(col, row, getPlayerTwoSymbol());
                endMove = true;
            }
        }
        endMove = false;
        boardUI.printBoard();
    }
    public void computerMove(){
        while (!endMove) {
            int col = random.nextInt(3);
            int row = random.nextInt(3);
            if (board.getSymbol(col, row) == null) {
                board.userMove(col, row, getPlayerTwoSymbol());
              endMove = true;
            }
        }
        endMove = false;
        boardUI.printBoard();
    }

    public void game(int enemy){
        switch (enemy){
            case 1:
                while (board.isAvaliableMove() || isNextMove){
                    userMove();
                    isGameWinner(userSymbol);
                    if (board.isAvaliableMove()) {
                        playerTwoMove();
                        isGameWinner(playerTwoSymbol);
                    }
                }
                break;
            case 2:
                while ( board.isAvaliableMove() && isNextMove){
                      userMove();
                     isGameWinner(userSymbol);
                    System.out.println(board.isAvaliableMove() + "    " + isNextMove);
                    if (board.isAvaliableMove() && isNextMove) {
                        computerMove();
                        isGameWinner(playerTwoSymbol);
                    }
                }
                break;
        }
    }

    public void whoWins(){
        if (!isNextMove) {
            if (symbolWinner == userSymbol) {
                System.out.println("You Win!");
            } else {
                System.out.println("Player 2 wins");
            }
        }else {
            System.out.println("Draw!");
        }
    }

    public void isGameWinner(Symbol symbol){
         if (isGameOverHorizontal(symbol) || isGameOverVertical(symbol) || isGameOverSlant(symbol)) {
             isNextMove = false;
             symbolWinner = symbol;
         } else {
             isNextMove = true;
         }
    }
    public boolean isGameOverVertical(Symbol symbol){
        for (int x = 0; x < board.BOARD_SIZE; x++) {
            int check = 0;
            for (int y = 0; y < board.BOARD_SIZE; y++) {
                if (symbol.equals(board.getSymbol(x,y))){
                    check+=1;
                }
            }
            if (check == 3){
                return true;
            }
        }
        return false;
    }

    public boolean isGameOverHorizontal(Symbol symbol){

        for (int y = 0; y < board.BOARD_SIZE; y++) {
            int check = 0;
            for (int x = 0; x < board.BOARD_SIZE; x++) {
                if (symbol.equals(board.getSymbol(x,y))){
                    check+=1;
                }
            }
            if (check == 3){
                return true;
            }
        }return false;
    }

    public boolean isGameOverSlant(Symbol symbol){
        if ((board.getSymbol(0,0) == symbol && board.getSymbol(1,1) == symbol && board.getSymbol(2,2) == symbol) ||
                (board.getSymbol(2,0) == symbol && board.getSymbol(1,1) == symbol && board.getSymbol(0,2) == symbol)){
            return  true;
        }return false;
    }


    public Symbol getUserSymbol() {
        return userSymbol;
    }

    public Symbol getPlayerTwoSymbol() {
        return playerTwoSymbol;
    }
}
