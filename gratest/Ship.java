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
class Ship {
    private String name;
    private int size;
    private List<int[]> coordinates;
    public boolean isSunked = false;
      public boolean isHit(int x, int y) {
        for (int[] coordinate : coordinates) {
            if (coordinate[0] == x && coordinate[1] == y) {
                return true; // Statek został trafiony w podanych współrzędnych
            }
        }
    return false; // Statek nie został trafiony w podanych współrzędnych
}
      
    public void removeCoordinate(int x, int y){
       coordinates.removeIf(coordinate -> coordinate[0] == x && coordinate[1] == y);
     
        if (coordinates.isEmpty()) {
           System.out.println("Zatopiony!");
           isSunked = true;
        }
    }
    
  
    
    
    public static class Builder {
        private String name;
        private int size;
        private List<int[]> coordinates = new ArrayList<>();
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder size(int size) {
            this.size = size;
            return this;
        }

         public Builder partsShip(int[][] parts) {
            for (int[] part : parts) {
                this.coordinates.add(part);
            }
            return this;
        }


        public Ship build() {
            Ship ship = new Ship(this);
            ship.coordinates = this.coordinates; // Przypisz listę współrzędnych do statku
            return ship;
        }
    }
    
    public List<int[]> getCoordinates() {
        return coordinates;
    }
    private Ship(Builder builder) {
        this.name = builder.name;
        this.size = builder.size;
    }

    public String getName() {
        return name;
    }
    
  

    public int getSize() {
        return size;
    }
}