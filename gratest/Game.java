/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorytmy.gratest;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author damia
 */
// Klasa Game
class Game {

    private int shipCount = 5;
    private static Board boardPlayer1, boardPlayer2;
    private GameState state;
    private int currentPlayer;
    private GameMemento savedGame;

    public Game() {
       this.boardPlayer1 = new Board.Builder().size(9, 10).build();
       this.boardPlayer2 = new Board.Builder().size(9, 10).build();
       this.state = new MenuState(); 
       this.currentPlayer = 1;
    }
    
    private static Game instance;
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
    
  
    
    public GameMemento getSavedGame(){
        return savedGame;
    }
    public void setShipCount(int shipCount) {
        this.shipCount = shipCount;
       
    }
    
    public int getShipCount(){
        return shipCount;
    }
  
    public Board getBoard() {
        if(state instanceof PlayingState){
            return currentPlayer == 1 ? boardPlayer2 : boardPlayer1;
        }
        return currentPlayer == 1 ? boardPlayer1 : boardPlayer2;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
        System.out.println("Teraz kolej gracza " + currentPlayer);
    }

   

    public void setState(GameState state) {
        this.state = state;
    }

    public void handleInput(String input) {
        if (input.equals("zapisz")) {
            saveGame();
            this.state = new MenuState();
        } else if (input.equals("wczytaj")) {
            loadGame(savedGame);
        } else if (!isGameOver()) {
            state.handleInput(input);
        }
    }

    public void saveGame() {
        savedGame = new GameMemento(state, boardPlayer1, boardPlayer2, currentPlayer);
       
    }

    public void loadGame(GameMemento memento) {
        this.state = memento.getState();
        this.currentPlayer = memento.getCurrentPlayer();
       
    }

    public boolean isGameOver() {
     
        if(state instanceof PlayingState && this.getBoard().getShips().isEmpty()){
            return true;
        }

        return false;
    }
    
    public void setBoardSize(int rows, int cols) {
        this.boardPlayer1 = new Board.Builder().size(rows, cols).build();
        this.boardPlayer2 = new Board.Builder().size(rows, cols).build();
        
    }
    

    public void printView() {
         if (state instanceof MenuState) { // Wyświetl menu, jeśli stan to MenuState
            System.out.println("\nMenu:");
            System.out.println("1. Graj");
            System.out.println("2. Wczytaj");
            System.out.println("3. Ustawienia");
            System.out.print("Wybierz opcję: ");
         }
        else if (state instanceof Settings) { // Wyświetl menu ustawień
            System.out.println("\nUstawienia:");
            System.out.println("1. Rozmiar planszy");
            System.out.println("2. Liczba statków");
            System.out.println("3. Zapisz i wyjdz");
            System.out.print("Wybierz opcję: ");
        }
        else if (state instanceof PlayingState) { 
            // Pobierz planszę przeciwnika
           
            Board boardToPrint = this.getBoard();
            
            
            System.out.print("  ");
            for (int i = 0; i < boardToPrint.getCols(); i++) {
                System.out.print((char) ('A' + i) + " ");
            }
            System.out.println();

            for (int i = 0; i < boardToPrint.getRows(); i++) {
                System.out.print((i + 1) + " ");
                for (int j = 0; j < boardToPrint.getCols(); j++) {
                    char cell = boardToPrint.getGrid()[i][j];

                    if(cell == 'O' || cell == 'X'){
                        //nothing
                    }
                    else{
                        cell = '?'; 
                    }
                    // Ukryj pola planszy przeciwnika
                    

                    System.out.print(cell + " ");
                }
                System.out.println();
            }
             if (isGameOver()) {

                 System.out.println("Gracz " + getCurrentPlayer() + " wygrywa!");
               
            }
             else{
              System.out.println("Gracz " + getCurrentPlayer() + ", Twój ruch:");
             }
           
            
        } else {
            // W fazie PlacingShipsState wyświetlaj normalnie planszę aktualnego gracza
          
            
            System.out.print("  ");
            for (int i = 0; i < getBoard().getCols(); i++) {
                System.out.print((char) ('A' + i) + " ");
            }
            System.out.println();

            for (int i = 0; i < getBoard().getRows(); i++) {
                System.out.print((i + 1) + " ");
                for (int j = 0; j < getBoard().getCols(); j++) {
                    char cell = getBoard().getGrid()[i][j];
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
            System.out.println("Gracz " + getCurrentPlayer() + ", Twój ruch:");
        }
    }

    
}