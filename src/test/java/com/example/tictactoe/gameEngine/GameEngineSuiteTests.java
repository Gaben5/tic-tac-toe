package com.example.tictactoe.gameEngine;

import com.example.tictactoe.board.Board;
import com.example.tictactoe.board.BoardUI;
import com.example.tictactoe.board.GameEngine;
import com.example.tictactoe.board.Symbol;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameEngineSuiteTests {
    @Test
    void WinsOInRow(){
        //Given
        Board board = new Board();
        BoardUI boardUI = new BoardUI(board);
        GameEngine gameEngine = new GameEngine(board, boardUI);
        //When
        board.userMove(0,0, Symbol.O);
        board.userMove(1,1, Symbol.X);
        board.userMove(1,0, Symbol.O);
        board.userMove(2,2, Symbol.X);
        board.userMove(2,0, Symbol.O);
        //Then
        Assertions.assertTrue(gameEngine.isGameOverHorizontal(Symbol.O));
    }

    @Test
    void WinsXInColumns(){
        //Given
        Board board = new Board();
        BoardUI boardUI = new BoardUI(board);
        GameEngine gameEngine = new GameEngine(board, boardUI);
        //When
        board.userMove(0,0, Symbol.X);
        board.userMove(1,1, Symbol.O);
        board.userMove(0,1, Symbol.X);
        board.userMove(2,2, Symbol.O);
        board.userMove(0,2, Symbol.X);
        //Then
        Assertions.assertTrue(gameEngine.isGameOverVertical(Symbol.X));
    }

    @Test
    void WinsXSlant(){
        //Given
        Board board = new Board();
        BoardUI boardUI = new BoardUI(board);
        GameEngine gameEngine = new GameEngine(board, boardUI);
        //When
        board.userMove(0,0, Symbol.X);
        board.userMove(1,1, Symbol.X);
        board.userMove(1,0, Symbol.O);
        board.userMove(2,2, Symbol.X);
        board.userMove(2,0, Symbol.O);
        //Then
        Assertions.assertTrue(gameEngine.isGameOverSlant(Symbol.X));
    }
}
