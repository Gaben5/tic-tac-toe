package com.example.tictactoe;

import com.example.tictactoe.board.Board;
import com.example.tictactoe.board.BoardUI;
import com.example.tictactoe.board.GameEngine;

import java.util.Scanner;

public class TicTacToeRunner {
    private Scanner sc = new Scanner(System.in);
    private int symbol;
    private void startGame(){
        boolean end = false;
        Board board = new Board();
        BoardUI boardUI = new BoardUI(board);
        GameEngine gameEngine = new GameEngine();
        System.out.println(boardUI.getDescription());
        System.out.println(boardUI.getInstruction() + "\nlegend");
        boardUI.legend();
        board.clearBoard();

        System.out.println();

        gameEngine.chooseSymbol();

        int enemy = gameEngine.chooseEnemy();
        gameEngine.game(enemy);
        System.out.println("You win");
    }

    public static void main(String[] args) {
        new TicTacToeRunner().startGame();
    }
}
