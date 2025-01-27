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
// Klasa Board
class Board {
    private int rows;
    private int cols;
    private char[][] grid;
    private List<Ship> ships;

      public void removeShip(Ship ship) {
        this.ships.remove(ship); // Usunięcie statku z listy
    }
    
    public static class Builder {
        private int rows = 9;
        private int cols = 10;
       
        public Builder size(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            return this;
        }

        public Board build() {
            return new Board(this);
        }
    }

    private Board(Builder builder) {
        this.rows = builder.rows;
        this.cols = builder.cols;
       this.ships = new ArrayList<>();
        
        this.grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = '~';
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void addShip(Ship ship){
        ships.add(ship);
    }
    
  
    public List<Ship> getShips() {
        return ships;
    }
    
    public char[][] getGrid() {
        return grid;
    }

    public void setGrid(int x, int y, char s) {
        this.grid[x-1][y-1] = s;
    }
}