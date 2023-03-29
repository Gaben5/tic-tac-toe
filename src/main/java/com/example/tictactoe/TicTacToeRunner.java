package com.example.tictactoe;

import com.example.tictactoe.board.Board;
import com.example.tictactoe.board.BoardUI;
import com.example.tictactoe.board.GameEngine;
public class TicTacToeRunner{
    private void startGame(){
        Board board = new Board();
        BoardUI boardUI = new BoardUI(board);
        GameEngine gameEngine = new GameEngine(board, boardUI);
        System.out.println(boardUI.getDescription());
        System.out.println(boardUI.getInstruction() + "\nlegend");
        boardUI.legend();
        board.clearBoard();
        System.out.println();
        gameEngine.chooseSymbol();
        int enemy = gameEngine.chooseEnemy();
        gameEngine.game(enemy);
        gameEngine.whoWins();
    }

    public static void main(String[] args) {
        new TicTacToeRunner().startGame();
    }
}
