/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorytmy.gratest;

/**
 *
 * @author damia
 */
class PlacingShipsState implements GameState {
    private int[] shipsPlaced;
   
    Ship[] ships;
    public PlacingShipsState() {
        resetShipsPlaced();
        printAvailableShips();
    }
    
    private void resetShipsPlaced(){
        Game game = Game.getInstance();
        this.shipsPlaced = new int[game.getShipCount() + 1]; // Tworzenie tablicy o odpowiednim rozmiarze
        for (int i = 2; i < shipsPlaced.length; i++) { // Inicjalizacja tablicy
            shipsPlaced[i] = 1; 
        }
    
    }

    public  void printAvailableShips(){
    System.out.print("Do ustawienia statki:");
        for(int i = 2; i < shipsPlaced.length; i++){
            if(shipsPlaced[i] != 0){
                System.out.print(i+", ");
            }
        }
        System.out.println();
    }
    @Override
    public void handleInput(String input) {
        Game game = Game.getInstance();
        if (input.startsWith("ustaw statek")) {
            try {
                String[] parts = input.split(" ");
                int size = Integer.parseInt(parts[2]);
                if(size > shipsPlaced.length || shipsPlaced[size] != 1){
                    throw new IllegalArgumentException("Nieprawidłowa wielkosc statku.");
                }
               
                String position = parts[3];
               
                boolean isVertical = parts[4].equals("pionowo");
                int x = position.charAt(0) - 'A' +1;
               
                int y = Integer.parseInt(position.substring(1));
               
              
            if (x < 1 || x > game.getBoard().getRows() || y < 1 || y > game.getBoard().getCols()) {
                throw new IllegalArgumentException("Nieprawidlowa pozycja statku.");
            }

            
            if (isVertical && y + size - 1 > game.getBoard().getRows() ||
                    !isVertical && x + size - 1 > game.getBoard().getCols()) {
                throw new IllegalArgumentException("Statek nie miesci się na planszy.");
            }

          
            if (isVertical) {
                for (int i = y; i < y + size; i++) {
                    if (game.getBoard().getGrid()[i - 1][x - 1] != '~') {
                        throw new IllegalArgumentException("Statek nachodzi na inny statek");
                    }
                }
            } else {
                for (int i = x; i < x + size; i++) {
                    if (game.getBoard().getGrid()[y - 1][i - 1] != '~') {
                        throw new IllegalArgumentException("Statek nachodzi na inny statek");
                    }
                }
            }

              

              
                Command command = new PlaceShipCommand(game.getBoard(), size, x, y, isVertical);
                command.execute();
                shipsPlaced[size] = 0;
                printAvailableShips();
                
            if (game.getCurrentPlayer() == 1) {
                   
                   // Gracz 1 ustawił wszystkie statki
                    if (areAllShipsPlaced(shipsPlaced)) { // Sprawdzenie, czy wszystkie statki zostały wykorzystane
                            game.switchPlayer(); // Zmiana na gracza 2
                            System.out.println("Gracz 1 ustawił wszystkie statki. Teraz kolej na gracza 2.");
                            resetShipsPlaced();// Reset tablicy statków dla gracza 2
                           
                            printAvailableShips(); // Wyświetl dostępne statki dla gracza 2
                    }
                    
                }else {
                    if (areAllShipsPlaced(shipsPlaced)) { // Sprawdzenie dla gracza 2
                        game.setState(new PlayingState());// Zmiana stanu na PlayingState
                        
                        game.switchPlayer(); // Zmiana na gracza 1 na początek fazy strzelania
                        System.out.println("Wszystkie statki ustawione. Rozpoczynamy grę!");
                    }
                }
                // --- Koniec 

            } catch (IllegalArgumentException e) {
                System.out.println("Błąd: " + e.getMessage());
            }
        } else if (input.equals("zapisz")) {
            game.saveGame();
        }
    }
    
    private boolean areAllShipsPlaced(int[] shipsPlaced) {
        for (int count : shipsPlaced) {
            if (count > 0) {
                return false;
            }
        }
        return true;
    }
}
