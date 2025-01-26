/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorytmy.gratest;

/**
 *
 * @author damia
 */
class MenuState implements GameState {

    MenuState() {
       
    }
    @Override
    public void handleInput(String input) {
        Game game = Game.getInstance();
        switch (input) {
            case "1": // Graj
                game.setState(new PlacingShipsState());
                System.out.println("Rozpoczynanie nowej gry...");
                break;
            case "2": // Wczytaj
                if (game.getSavedGame() != null) {
                    game.loadGame(game.getSavedGame());
                    System.out.println("Wczytywanie zapisanej gry...");
                } else {
                    System.out.println("Brak zapisanej gry.");
                }
                break;
            case "3": // Ustawienia
                 game.setState(new Settings());
               
                break;
            default:
                System.out.println("Nieprawidłowa opcja.");
        }
    }
}