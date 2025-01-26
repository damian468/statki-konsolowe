/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorytmy.gratest;

import java.util.Scanner;

/**
 *
 * @author damia
 */
class Settings implements GameState {

    Settings() {
    }

    @Override
    public void handleInput(String input) {
        Game game = Game.getInstance();
        Scanner scanner = new Scanner(System.in);
        switch (input) {
            case "1":
                System.out.println("Podaj nową szerokość planszy:");
                int newCols = scanner.nextInt();
                scanner.nextLine(); // Konsumujemy newline
                System.out.println("Podaj nową wysokość planszy:");
                int newRows = scanner.nextInt();
                scanner.nextLine(); // Konsumujemy newline
                game.setBoardSize(newRows, newCols);
                System.out.println("Zmieniono rozmiar planszy na " + newRows + "x" + newCols);
                break;
            case "2":
                System.out.println("Podaj nową liczbę statków:");
                int newShipCount = scanner.nextInt();
                scanner.nextLine(); // Konsumujemy newline
                newShipCount++;
                if(newShipCount < game.getBoard().getCols() && newShipCount < game.getBoard().getRows()){
                    game.setShipCount(newShipCount);
                    System.out.println("Zmieniono liczbę statków na " + (newShipCount-1));
                }
                else  System.out.println("Liczba statkow za duza!");
                break;
             case "3":
               
                game.setState(new MenuState());
  
                break;
            // ... (inne case'y dla pozostałych ustawień) ...
            default:
                System.out.println("Nieprawidłowa opcja.");
        }
    }
}
