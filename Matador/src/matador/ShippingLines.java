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
public class ShippingLines extends Field {
    
    public int Owner;
    public String Name;
    public int Prices;
    public int Taxes;
    public boolean Mortgage;
    
    private int CountShippingLines()
    {
        int CountShips = 0;
        for (Field F: Game.fields )
        {
            if(F.getClass()==ShippingLines.class)
            {
                if (((ShippingLines)F).Owner==this.Owner)
                {
                    CountShips++;
                    
                }
            }
        }
        return CountShips;
    }
    
    
    @Override
    public void Lands (Player P)
    {
        /*
        * If nobody owns it      
        */
        if (Owner == -1)
        {
            
        }
        /*
        * If enemy lands on yours, a payout must happen
        */
        else if (Owner != PID)
        {
            if ((Game.players.get(Owner)).InPrison == false)
            {
                int cnt = CountShippingLines();
                if (cnt == 1)
                {
                    int Pay;
                    Pay = 500;
                    P.ChangeMoney(Pay);
                }
                else if (cnt == 2)
                {
                    int Pay;
                    Pay = 1000;    
                    P.ChangeMoney(Pay);
                }
                else if (cnt == 3)
                {
                    int Pay;
                    Pay = 2000;    
                    P.ChangeMoney(Pay);
                }
                else if (cnt == 4)
                {
                    int Pay;
                    Pay = 4000;
                    P.ChangeMoney(Pay);
                }
                
            }
        }
        
    }   
}
