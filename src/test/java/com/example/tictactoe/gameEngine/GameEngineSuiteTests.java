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
        //When
        board.userMove(0,0, Symbol.O);
        board.userMove(1,1, Symbol.X);
        board.userMove(1,0, Symbol.O);
        board.userMove(2,2, Symbol.X);
        board.userMove(2,0, Symbol.O);
        //Then
        Assertions.assertEquals(Symbol.O,board.getSymbol(0,0));
        Assertions.assertEquals(Symbol.O,board.getSymbol(1,0));
        Assertions.assertEquals(Symbol.O,board.getSymbol(2,0));
    }

    @Test
    void WinsXInColumns(){
        //Given
        Board board = new Board();
        //When
        board.userMove(0,0, Symbol.X);
        board.userMove(1,1, Symbol.O);
        board.userMove(0,1, Symbol.X);
        board.userMove(2,2, Symbol.O);
        board.userMove(0,2, Symbol.X);
        //Then
        Assertions.assertEquals(Symbol.X,board.getSymbol(0,0));
        Assertions.assertEquals(Symbol.X,board.getSymbol(0,1));
        Assertions.assertEquals(Symbol.X,board.getSymbol(0,2));
    }

    @Test
    void WinsXSlant(){
        //Given
        Board board = new Board();
        //When
        board.userMove(0,0, Symbol.X);
        board.userMove(1,1, Symbol.X);
        board.userMove(1,0, Symbol.O);
        board.userMove(2,2, Symbol.X);
        board.userMove(2,0, Symbol.O);
        //Then
        Assertions.assertEquals(Symbol.X,board.getSymbol(0,0));
        Assertions.assertEquals(Symbol.X,board.getSymbol(1,1));
        Assertions.assertEquals(Symbol.X,board.getSymbol(2,2));
    }
}
