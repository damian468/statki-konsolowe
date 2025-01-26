/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorytmy.gratest;

/**
 *
 * @author damia
 */

import java.io.IOException;
import java.util.Scanner;

public class GraWStatki {

    public static void main(String[] args) throws IOException {
        Game game = Game.getInstance(); 
       

        Scanner scanner = new Scanner(System.in);
        System.out.println("Witaj w grze w statki!");

        while (true) {
            
            game.printView();
            System.out.print("Wprowadź komendę: ");
            String input = scanner.nextLine();
            game.handleInput(input);

            if (input == "exit") {
                break;
            }
            
        }
        scanner.close();
    }
    
    
}


