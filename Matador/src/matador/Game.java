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
import javax.swing.JOptionPane;
import javax.xml.parsers.*;
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
        dices = new Dice[] {new Dice(),new Dice()};
        // Shows a form to make players
        PlayerNames pnForm = new PlayerNames();
        pnForm.setVisible(true);
    }
    public static GameBoard gameboard;
    public static void StartGame(ArrayList<String> names) {
        // When player form is done, this function is called
        // Colors for players.
        // This ensures that the players don't have the same color
        ArrayList<Color> usedColors = new ArrayList<>();
        int divisor=4;
        for (String name : names) {
            Player player = new Player();
            player.Name = name;
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
        // Read XML
        readXML();
        GameRunning = true;
        trackDices=false;
        dicesEqual=0;
        gameboard=new GameBoard();
        gameboard.initGame();
    }
    public static int requestBuy(Player theCustomer,Field field) {
        Object[] options = new Object[2];
        options[0] = "Køb stedet";
        options[1] = "Køb ikke";
        String name="";
        String type="";
        int price=0;
        int ftype=-1;
        Brewery b = null;
        ShippingLines sh = null;
        Street st = null;
        if (field.getClass()==Brewery.class) {
            b = (Brewery)field;
            name=b.Name;
            ftype=0;
            type="Bryggeriet";
            price = b.Price;
        }else if(field.getClass()==ShippingLines.class) {
            sh = (ShippingLines)field;
            name=sh.Name+" ("+sh.SubName+")";
            type="Redderiet";
            ftype=1;
            price = sh.Price;
        }else if(field.getClass()==Street.class) {
            st = (Street)field;
            name=st.Name;
            type="Gaden";
            ftype=2;
            price = st.Price;
        }else{
            name ="fejl";
            type = "fejl";
            price = -1;
        }
        int choice=-1;
        if (theCustomer.GetMoney()>price) {
            choice=JOptionPane.showOptionDialog(null, theCustomer.Name+":\n"+type+" '"+name+"' er til salg for "+price+" kr.\nVil du købe stedet?", "Valg",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
        }
        if (choice==0) {
            // User wants to buy
            switch (ftype) {
                case 0:
                    // brewery
                    b.Owner = Game.players.indexOf(theCustomer);
                    theCustomer.ChangeMoney(-price);
                    break;
                case 1:
                    // ShippingLines
                    sh.Owner = Game.players.indexOf(theCustomer);
                    theCustomer.ChangeMoney(-price);
                    break;
                case 2:
                    // Street
                    st.Owner = Game.players.indexOf(theCustomer);
                    theCustomer.ChangeMoney(-price);
                    break;
            }
        }
        return choice;
    }
    public static int JailDiceTries=0;
    public static void GA_JailThrowDice() {
        if (JailDiceTries<3) {
            Player player = players.get(currentPlayer);
            dices[0].Throw();
            dices[1].Throw();
            //dices[0].number = 1+((dices[1].number+1)%6);
            if (dices[0].number == dices[1].number)
            {
                player.InPrison = false;
                player.PrisonTurns=0;
                player.ChangePosition((dices[0].number + dices[1].number));
                Game.fields.get(player.Position).Lands(player);
            }else{
                JailDiceTries++;
            }
        }else{
            
            //JailDiceTries=0;
            //currentPlayer=(currentPlayer+1)%players.size();
        }
    }
    public static void GA_ThrowDice() {
        Player player = players.get(currentPlayer);
        dices[0].Throw();
        dices[1].Throw();
        //if (dicesEqual<3) {
        //    dices[0].number = dices[1].number; // HAX
        //}
        if (!player.InPrison) {
            // Player is not in prison
            if (dices[0].number==dices[1].number) {
                if (!trackDices) {
                    // If this is the first time
                    trackDices=true;
                    dicesEqual=0;
                }
                dicesEqual++;
                if (dicesEqual==3) {
                    new GoToPrison().Lands(player);
                    trackDices=false;
                    dicesEqual=0;
                    //currentPlayer=(currentPlayer+1)%players.size();
                    gameboard.showJailFreeCardBtn=false;
                    gameboard.showJailPayBailBtn=false;
                    gameboard.showJailThrowDiceBtn=false;
                    gameboard.showMortgageBtn=false;
                    gameboard.showNextPlayerBtn=true;
                    gameboard.showThrowDiceBtn=false;
                    gameboard.refreshGameControl();
                }else{
                    // TODO
                    // still his turn;
                    gameboard.showThrowDiceBtn=true;
                    gameboard.showNextPlayerBtn=false;
                    gameboard.refreshGameControl();
                }
            }else{
                trackDices=false;
                dicesEqual=0;
            }
            if (player.InPrison!=true) {
                // Doing events after
                int diceValue=dices[0].number+dices[1].number;
                for (int tmpPos=0;tmpPos<diceValue-1;tmpPos++) {
                    fields.get(player.ChangePosition(1)).Passed(player);
                }
                fields.get(player.ChangePosition(1)).Lands(player);
                if (trackDices) {
                   // currentPlayer=(currentPlayer+1)%players.size();
                }
                gameboard.refreshGameControl();
            }
            if (player.InPrison) {
                // You are now in prison. haha!
                gameboard.showJailFreeCardBtn=false;
                gameboard.showJailPayBailBtn=false;
                gameboard.showJailThrowDiceBtn=false;
                gameboard.showMortgageBtn=false;
                gameboard.showNextPlayerBtn=true;
                gameboard.showThrowDiceBtn=false;
                gameboard.refreshGameControl();
            }else if (trackDices!=true) {
                // You can still use mortgage..
                gameboard.showJailFreeCardBtn=false;
                gameboard.showJailPayBailBtn=false;
                gameboard.showJailThrowDiceBtn=false;
                gameboard.showMortgageBtn=false;
                gameboard.showNextPlayerBtn=true;
                gameboard.showThrowDiceBtn=false;
                for (Field field : Game.fields) {
                    if (field.getClass()==Brewery.class) {
                        Brewery brew = ((Brewery)field);
                        if (brew.Owner==currentPlayer) {
                            gameboard.showMortgageBtn=true;
                        }
                    }else if (field.getClass()==Street.class) {
                        Street street = ((Street)field);
                        if (street.Owner==currentPlayer) {
                            gameboard.showMortgageBtn=true;
                        }
                    }else if (field.getClass()==ShippingLines.class) {
                        ShippingLines sl = ((ShippingLines)field);
                        if (sl.Owner==currentPlayer) {
                            gameboard.showMortgageBtn=true;
                        }
                    }
                }
                gameboard.refreshGameControl();
            }
        }else{
            // Player is in prison
        }
    }
    private static boolean GameRunning;
    private static boolean trackDices;
    public static int dicesEqual;
    
    private static void GameLoop() {
        // The game loop
        
        
        while (GameRunning) {
            for (int i=0;i<players.size();i++) { 
                currentPlayer = i;
                
                
            }
        }
    }
    
    private static void readXML() {
        try {
            // read xml entries
            // Reinitialize arrays
            fields = new ArrayList<>();
            streetgroups = new ArrayList<>();
            // Path to the XML file
            String path="src/matador/Cards.xml";
            // Document builder factory, document builder, and so on...
            DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder=docBuildFactory.newDocumentBuilder();
            Document doc=docBuilder.parse(path);
            // Root element
            Element element = doc.getDocumentElement();
            // This is the node containing all fields
            NodeList dFields;
            dFields = element.getElementsByTagName("Fields").item(0).getChildNodes();
            // This is the node containing all streetgroups
            NodeList dStreetGroups;
            dStreetGroups = element.getElementsByTagName("StreetGroups").item(0).getChildNodes();
            // Import street groups
            for (int i=0;i<dStreetGroups.getLength();i++) {
                Node sNode = dStreetGroups.item(i);
                // This if case ensures that the node is a node.. (Confusing)
                if (sNode.getNodeType()==Node.ELEMENT_NODE) {
                    Element groupnode = (Element) sNode;
                    StreetGroup sgrp = new StreetGroup();
                    Element color = (Element)groupnode.getElementsByTagName("Color").item(0);
                    sgrp.color = new Color(Integer.parseInt(getNodeValue("R",color)),Integer.parseInt(getNodeValue("G",color)),Integer.parseInt(getNodeValue("B",color)));
                    // add the streetgroup to the arraylist
                    streetgroups.add(sgrp);
                }
            }
            // Import all fields
            for (int i=0;i<dFields.getLength();i++) {
                Node fNode = dFields.item(i);
                // If the node is a node...
                if (fNode.getNodeType()==Node.ELEMENT_NODE) {
                    Element fieldnode = (Element) dFields.item(i);
                    // This switch case sort out the type of fields
                    switch (getNodeValue("Type",fieldnode)) {
                        case "Street":
                            // Street (Gade)
                            Street street = new Street();
                            street.GroupID = Integer.parseInt(getNodeValue("GroupID",fieldnode));
                            street.Name = getNodeValue("Name",fieldnode);
                            street.Price = Integer.parseInt(getNodeValue("Price",fieldnode));
                            // Taxes for other users landing on an owned field
                            Element prices = (Element)fieldnode.getElementsByTagName("Taxes").item(0);
                            street.Taxes[0]=Integer.parseInt(getNodeValue("NoHouse",prices));
                            street.Taxes[1]=Integer.parseInt(getNodeValue("OneHouse",prices));
                            street.Taxes[2]=Integer.parseInt(getNodeValue("TwoHouse",prices));
                            street.Taxes[3]=Integer.parseInt(getNodeValue("ThreeHouse",prices));
                            street.Taxes[4]=Integer.parseInt(getNodeValue("FourHouse",prices));
                            street.Taxes[5]=Integer.parseInt(getNodeValue("Hotel",prices));
                            street.HousePrice = Integer.parseInt(getNodeValue("HousePrice",fieldnode));
                            street.HotelPrice = Integer.parseInt(getNodeValue("HotelPrice",fieldnode));
                            // add street to array
                            fields.add(street);
                            break;
                        case "TryLuck":
                            // TryLuck (prøv lykken)
                            fields.add(new TryLuck());
                            break;
                        case "Start":
                            // Start field..
                            fields.add(new Start());
                            break;
                        case "IncomeTax":
                            // IncomeTax (Inkomstskat)
                            fields.add(new IncomeTax());
                            break;
                        case "ShippingLines":
                            // Shipping Lines (Redderier);
                            ShippingLines ship = new ShippingLines();
                            ship.Name = getNodeValue("Name",fieldnode);
                            ship.SubName = getNodeValue("Subname",fieldnode);
                            ship.Price = Integer.parseInt(getNodeValue("Price",fieldnode));
                            fields.add(ship);
                            break;
                        case "Prison":
                            // Prison (fængsel)
                            fields.add(new Prison());
                            break;
                        case "Brewery":
                            // Brewery (Bryggeri)
                            Brewery brew = new Brewery();
                            brew.Name = getNodeValue("Name", fieldnode);
                            brew.Price = Integer.parseInt(getNodeValue("Price", fieldnode));
                            fields.add(brew);
                            break;
                        case "Parking":
                            // Parking (Parkering)
                            fields.add(new Parking());
                            break;
                        case "GoToPrison":
                            // GoToPrison (Gå til fængsel)
                            fields.add(new GoToPrison());
                            break;
                        case "StateTax":
                            // StateTax (Stats Skat)
                            fields.add(new StateTax());
                            break;
                        default:
                            // If this should appear in the console, then we're screwed
                            System.out.println("Warning - Weird XML");
                            break;
                    }
                }
            }
        } catch (ParserConfigurationException ex) {
            System.out.println("Could not read XML file..");
            JOptionPane.showMessageDialog(gameboard, "Error reading XML \n"+ex.getMessage());
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            System.out.println("Could not read XML file..");
            JOptionPane.showMessageDialog(gameboard, "Error reading XML \n"+ex.getMessage());
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Could not read XML file..");
            JOptionPane.showMessageDialog(gameboard, "Error reading XML \n"+ex.getMessage());
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("XML File Read done");
    }
    private static String getNodeValue(String property,Element e) {
        // An easy way to read properties from a node
        try {
            NodeList nodes;
            nodes = e.getElementsByTagName(property).item(0).getChildNodes();
            Node node = (Node)nodes.item(0);
            return node.getNodeValue();
        }
        catch (NullPointerException err) {
            JOptionPane.showMessageDialog(gameboard, "Error reading XML node: "+property+" of "+e.getNodeName()+"\n"+err.getMessage());
        }
        return null;
    }
}
