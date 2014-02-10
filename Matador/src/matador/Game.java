/*
 * This weird stuff is made in educational purpose
 * Use it to whatever you want BUT give us credit plz
 * https://github.com/and3k5/Matador
 * - Le Group Un
 */

package matador;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

/**
 * @author Le Group Un
 */
public class Game {
    public static int currentPlayer;
    public static ArrayList<Player> players;
    public static ArrayList<Field> fields;
    public static ArrayList<StreetGroup> streetgroups;
    public static Dice dice;
    public static void main(String[] args) {
        // Initialize vars
        currentPlayer=-1;
        players=new ArrayList<>();
        fields=new ArrayList<>();
        streetgroups=new ArrayList<>();
        dice=new Dice();
        PlayerNames pnForm = new PlayerNames();
        pnForm.setVisible(true);
    }
    
    public static void StartGame(ArrayList<String> names) {
        System.out.println("StartGame");
        System.out.println(names);
        // More to come XML
        // fields.add(.....)
        ArrayList<Color> usedColors = new ArrayList<>();
        int divisor=4;
        for (String name : names) {
            Player player = new Player();
            Color tmp;
            Random rand = new Random();
            boolean breakLoop;
            while (true) {
                breakLoop=true;
                tmp = new Color(rand.nextInt(255/divisor)*4,rand.nextInt(255/divisor)*4,rand.nextInt(255/divisor)*4);
                for (Color c : usedColors) {
                    if (c.getRGB()==tmp.getRGB()) {
                        breakLoop=false;
                    }
                }
                if (breakLoop) break;
            }
            player.Color = new Color(tmp.getRGB());
            players.add(player);
        }
        System.out.println(players);
        readXML();
    }
    
    public static void readXML() {
        try {
            // read xml entries
            String path="src/matador/Cards.xml";
            Document builder = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(path);
            System.out.println(builder.getDocumentElement().getTagName());
        } catch (ParserConfigurationException ex) {
            System.out.println("Could not read XML file..");
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            System.out.println("Could not read XML file..");
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Could not read XML file..");
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
