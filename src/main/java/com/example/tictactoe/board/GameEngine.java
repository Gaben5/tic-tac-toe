package com.example.tictactoe.board;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.*;
public class GameEngine {
    private Symbol userSymbol = null;
    private Symbol playerTwoSymbol = null;
    private Board board;
    private BoardUI boardUI;
    private final Scanner sc = new Scanner(System.in);
    private final Random random = new Random();
    private boolean end = false;
    private boolean endMove = false;
    private int symbolNumber;
    File savedHashMaps = new File("ranking.txt");
    public GameEngine(Board board, BoardUI boardUI) {
        this.board = board;
        this.boardUI = boardUI;
    }
    public void chooseSymbol() {
        while (!end) {
            System.out.println("Choose your symbol 0->O ; 1->X ; 2-> load last game");
            symbolNumber = sc.nextInt();
            if (symbolNumber == 0 || symbolNumber == 1 || symbolNumber == 2) {
                end = true;
            }
        }
        switch (symbolNumber) {
            case 0 -> {
                userSymbol = Symbol.O;
                playerTwoSymbol = Symbol.X;
                board.setPlayer1Symbol(userSymbol);
                board.setPlayer2Symbol(playerTwoSymbol);
            }
            case 1 -> {
                userSymbol = Symbol.X;
                playerTwoSymbol = Symbol.O;
                board.setPlayer1Symbol(userSymbol);
                board.setPlayer2Symbol(playerTwoSymbol);
            }
            case 2 -> {
                board = loadGame();
                boardUI = new BoardUI(board);
                userSymbol = board.getPlayer1Symbol();
                playerTwoSymbol = board.getPlayer2Symbol();
                boardUI.printBoard();

            }
        }
        System.out.println("Your symbol: " + userSymbol);
        System.out.println("Player 2 symbol: " + playerTwoSymbol);
    }
    public int chooseEnemy() {
        int x = 100000;
        end = false;
        while (!end) {
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
    public void userMove() {
        System.out.println("Player1 move");
        while (!endMove) {
            System.out.println("Choose column or write 's' to save and exit");
            String colStr = sc.next();
            if (colStr.equals("s")){
                mapToJson(board);
                saveGame();
                System.out.println("Game Saved");
                System.exit(0);
            }
            int col = Integer.parseInt(colStr);
            System.out.println("Choose row");
            int row = sc.nextInt();
            if (board.getSymbol(col, row) == null) {
                board.userMove(col, row, getUserSymbol());
                endMove = true;
            }
        }
        endMove = false;
        mapToJson(board);
        boardUI.printBoard();
    }
    public void playerTwoMove() {
        System.out.println("Player2 move");
        while (!endMove) {
            System.out.println("Choose column");
            int col = sc.nextInt();
            System.out.println("Choose row");
            int row = sc.nextInt();
            if (board.getSymbol(col, row) == null) {
                board.userMove(col, row, getPlayerTwoSymbol());
                endMove = true;
            }
        }
        endMove = false;
        boardUI.printBoard();
    }
    public void computerMove() {
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
    public void game(int enemy) {
        switch (enemy) {
            case 1 -> {
                while (board.isAvaliableMove() && isGameWinner() == null) {
                    userMove();
                    if (board.isAvaliableMove() && isGameWinner() == null) {
                        playerTwoMove();
                    }
                }
            }
            case 2 -> {
                while (board.isAvaliableMove() && isGameWinner() == null) {
                    userMove();
                    if (board.isAvaliableMove() && isGameWinner() == null) {
                        computerMove();
                    }
                }
            }
        }
    }
    public void whoWins() {
        if (isGameWinner() != null) {
            if (isGameWinner() == userSymbol) {
                System.out.println("You Win!");
            } else {
                System.out.println("Player 2 wins");
            }
        } else {
            System.out.println("Draw!");
        }
    }
    public Symbol isGameWinner() {
        if (isGameOverHorizontal(Symbol.X) || isGameOverVertical(Symbol.X) || isGameOverSlant(Symbol.X)) {
            return Symbol.X;
        } else if (isGameOverHorizontal(Symbol.O) || isGameOverVertical(Symbol.O) || isGameOverSlant(Symbol.O)) {
            return Symbol.O;
        } else {
            return null;
        }
    }

    public boolean isGameOverVertical(Symbol symbol) {
        for (int x = 0; x < Board.BOARD_SIZE; x++) {
            int check = 0;
            for (int y = 0; y < Board.BOARD_SIZE; y++) {
                if (symbol.equals(board.getSymbol(x, y))) {
                    check += 1;
                }
            }
            if (check == 3) {
                return true;
            }
        }
        return false;
    }
    public boolean isGameOverHorizontal(Symbol symbol) {

        for (int y = 0; y < Board.BOARD_SIZE; y++) {
            int check = 0;
            for (int x = 0; x < Board.BOARD_SIZE; x++) {
                if (symbol.equals(board.getSymbol(x, y))) {
                    check += 1;
                }
            }
            if (check == 3) {
                return true;
            }
        }
        return false;
    }
    public boolean isGameOverSlant(Symbol symbol) {
        return (board.getSymbol(0, 0) == symbol && board.getSymbol(1, 1) == symbol && board.getSymbol(2, 2) == symbol) ||
                (board.getSymbol(2, 0) == symbol && board.getSymbol(1, 1) == symbol && board.getSymbol(0, 2) == symbol);
    }
    public void saveGame(){
        try {
            FileWriter fileWriter = new FileWriter(savedHashMaps);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(mapToJson(board));
            bufferedWriter.close();
        }catch (Exception e){
            System.out.println("Exception: "+e);
        }
    }
    public Board loadGame(){
        try {
            FileReader fileReader = new FileReader(savedHashMaps);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            bufferedReader.close();
            return mapFromJson(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Board mapFromJson(String line){
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();
        return gson.fromJson(line,Board.class);
    }
    public String mapToJson(Board board) {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();
        return gson.toJson(board);
    }
    public Symbol getUserSymbol() {
        return userSymbol;
    }
    public Symbol getPlayerTwoSymbol() {
        return playerTwoSymbol;
    }
}
