/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matador;

import javax.swing.JOptionPane;

/**
 *
 * @author and3k5
 */
public class Street extends Field {
    
    public String Name;
    public int Price;
    public int[] Taxes;
    public int GroupID;
    public int HousePrice;
    public int HotelPrice;
    public int Owner;
    public int Houses;
    public boolean Mortgage;
    
    public Street()
    {
        //Constructor for some base values
        Price = 0;
        Owner = -1;
        Houses = 0;
        Mortgage = false;
        Taxes = new int[]{0,0,0,0,0,0};
        GroupID=-1;
        HousePrice=0;
        HotelPrice=0;
        Name="";
    }
    
    @Override
    public void Lands (Player P)
    {
        //If nobody owns it      
        if (Owner == -1)
        {
            //Ask if you want to buy it
            Game.requestBuy(P, this);
        }
        //If enemy lands on yours, a payout must happen
        else if (Owner != Game.players.indexOf(P))
        {
            if ((Game.players.get(Owner)).InPrison == false)
            {
                //Changes the pay to the rent for the street
                int Pay;
                Pay = Taxes[0];
                //Shows message with how much you pay
                JOptionPane.showMessageDialog(null, "Du betaler leje: " + Pay + " til ejeren");
                //Reduces current players money
                P.ChangeMoney(-Pay);
                //Increases owners money
                (Game.players.get(Owner)).ChangeMoney(Pay);
            }
        }
    }
}
