/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorytmy.gratest;

/**
 *
 * @author damia
 */
class GameMemento {
    private GameState state;
    private Board[] boardsGame;
    private int currentPlayer;

    public GameMemento(GameState state, Board board1, Board board2, int currentPlayer) {
        this.state = state;
        this.boardsGame = new Board[2];
        this.boardsGame[0] = board1;
        this.boardsGame[1] = board2;
        this.currentPlayer = currentPlayer;
    }

    public GameState getState() {
        return state;
    }

    public Board[] getBoardGrid() {
        return boardsGame;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
}