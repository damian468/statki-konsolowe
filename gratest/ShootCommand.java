/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorytmy.gratest;

/**
 *
 * @author damia
 */
class ShootCommand implements Command {
    private Game game;
    private int x;
    private int y;

    public ShootCommand(Game game, int x, int y) {
      
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        Game game = Game.getInstance(); 
       
       System.out.println(y+"."+x);
        if (game.getBoard().getGrid()[y-1][x-1] == 'S') {
            game.getBoard().setGrid(y,x, 'X');
            System.out.println("Trafiony!");
            
             for (Ship ship : game.getBoard().getShips()) {
                if (ship.isHit(y-1, x-1)) {
                    ship.removeCoordinate(y-1, x-1); // Usuń współrzędne trafionej części statku
                   
                }
                if(ship.isSunked){
                    game.getBoard().removeShip(ship);
                }
                 break;
            }
          
        
            
            
            
        } else {
            game.getBoard().setGrid(y,x, 'O');
            System.out.println("Pudło!");
            game.switchPlayer();
        }
        
    }
}