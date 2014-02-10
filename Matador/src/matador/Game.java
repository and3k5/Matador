/*
 * This weird stuff is made in educational purpose
 * Use it to whatever you want BUT give us credit plz
 * https://github.com/and3k5/Matador
 * - Le Group Un
 */

package matador;

import java.util.ArrayList;

/**
 * @author Le Group Un
 */
public class Game {
    public static int turn;
    public static ArrayList<Player> players;
    public static ArrayList<Field> fields;
    public static ArrayList<StreetGroup> streetgroups;
    public static Dice dice;
    public static void main(String[] args) {
        // Initialize vars
        turn=0;
        PlayerNames pnForm = new PlayerNames();
        pnForm.setVisible(true);
        
    }
}
