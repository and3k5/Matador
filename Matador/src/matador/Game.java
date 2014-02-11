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
    public static Dice[] dices;
    public static void main(String[] args) {
        // Initialize vars
        currentPlayer=-1;
        players=new ArrayList<>();
        fields=new ArrayList<>();
        streetgroups=new ArrayList<>();
        dices[0]=new Dice();
        dices[1]=new Dice();
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
            DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder=docBuildFactory.newDocumentBuilder();
            Document doc=docBuilder.parse(path);
            Element element = doc.getDocumentElement();
            NodeList dFields;
            dFields = element.getElementsByTagName("Fields").item(0).getChildNodes();
            NodeList dStreetGroups;
            dStreetGroups = element.getElementsByTagName("Fields").item(0).getChildNodes();
            for (int i=0;i<dFields.getLength();i++) {
                Node field=dFields.item(i);
                Element fieldnode = (Element) field;
                switch (getNodeValue("Type",fieldnode)) {
                    case "Street":
                        Street street = new Street();
                        street.GroupID = Integer.parseInt(getNodeValue("GroupID",fieldnode));
                        street.Name = getNodeValue("Name",fieldnode);
                        street.Price = Integer.parseInt(getNodeValue("Price",fieldnode));
                        break;
                    case "TryLuck":
                        break;
                    case "Start":
                        break;
                    case "IncomeTax":
                        break;
                    case "ShippingLines":
                        break;
                    case "Prison":
                        break;
                    case "Brewery":
                        break;
                    case "Parking":
                        break;
                    case "GoToPrison":
                        break;
                    case "StateTax":
                        break;
                    default:
                        System.out.println("Weird XML");
                        break;
                }
            }
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
    private static String getNodeValue(String property,Element e) {
        return e.getElementsByTagName(property).item(0).getChildNodes().item(0).getNodeValue();
    }
}
