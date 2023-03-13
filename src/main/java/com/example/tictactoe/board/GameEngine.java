package com.example.tictactoe.board;

import java.util.Random;
import java.util.Scanner;

public class GameEngine {
    private Symbol userSymbol = null;
    private Symbol playerTwoSymbol = null;
    private boolean isEnd = false;
    private Board board = new Board();
    private BoardUI boardUI = new BoardUI(board);
    private Scanner sc = new Scanner(System.in);
    private Random random = new Random();
    private boolean end = false;
    private boolean endMove = false;
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
            System.out.println(board.getSymbol(row,col));
        if (board.getSymbol(row,col) == null){
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
            if (board.getSymbol(row,col) == null){
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
            if (board.getSymbol(row, col) == null) {
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
                while (board.isAvaliableMove()){
                    userMove();
                    if (board.isAvaliableMove()) {
                        playerTwoMove();
                    }
                }
                break;
            case 2:
                while (board.isAvaliableMove()){
                      userMove();
                    if (board.isAvaliableMove()) {
                        computerMove();
                    }
                }
                break;
        }
    }



    public Symbol getUserSymbol() {
        return userSymbol;
    }

    public Symbol getPlayerTwoSymbol() {
        return playerTwoSymbol;
    }
}
