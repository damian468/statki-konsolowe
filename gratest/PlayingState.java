/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorytmy.gratest;

/**
 *
 * @author damia
 */
class PlayingState implements GameState {
    @Override
    public void handleInput(String input) {
        Game game = Game.getInstance();
        if (input.startsWith("strzel")) {
            try {
                String[] parts = input.split(" ");
                String position = parts[1];
                 int x = position.charAt(0) - 'A' +1 ;
                int y = Integer.parseInt(position.substring(1));

                Command command = new ShootCommand(game, x, y);
                command.execute();
            } catch (IllegalArgumentException e) {
                System.out.println("Błąd: " + e.getMessage());
            }
        }
    }
}