/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorytmy.gratest;

/**
 *
 * @author damia
 */
class PlaceShipCommand implements Command {
    
   private int size;
    private int x;
    private int y;
    private boolean isVertical;

    public PlaceShipCommand(Board board, int size, int x, int y, boolean isVertical) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.isVertical = isVertical;
    }

            @Override
        public void execute() {
            Game game = Game.getInstance();
            int[][] coordinates = new int[size][2]; 
            if (!isVertical) {
                for (int i = 0; i < size; i++) {
                    game.getBoard().setGrid(y, (x+i), 'S' );
                    coordinates[i][0] = y - 1; // Przypisz y do pierwszego elementu tablicy
                    coordinates[i][1] = x + i -1 ; // Przypisz x+i do drugiego elementu tablicy
                }
            } else {
                for (int i = 0; i < size; i++) {
                    game.getBoard().setGrid((y+i),x , 'S' );
                    // Tutaj brakuje przypisania do coordinates, dodaj je analogicznie jak wyżej
                    coordinates[i][0] = y + i - 1; // Przypisz y+i do pierwszego elementu tablicy
                    coordinates[i][1] = x - 1; // Przypisz x do drugiego elementu tablicy
                }
            }

            // Utwórz statek z obliczonymi współrzędnymi
            Ship ship = new Ship.Builder()
                    .size(size)
                    .partsShip(coordinates)
                    .build();
            
            game.getBoard().addShip(ship);
        }
}
