/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matador;

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
            
        }
        //If enemy lands on yours, a payout must happen
        else if (Owner != Game.players.indexOf(P))
        {
            if (P.InPrison = false)
            {
                //int Pay;
            
                //P.ChangeMoney(Pay);
            }
        }
    }
}
