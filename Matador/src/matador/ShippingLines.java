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
public class ShippingLines extends Field {
    
    public int Owner;
    public String Name;
    public String SubName;
    public int Price;
    public boolean Mortgage;
    
    public ShippingLines()
    {
        Owner = -1;
        Price = 0;
        Mortgage = false;
    }
    
    
    public int CountShippingLines()
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
        //If nobody owns it      
        if (Owner == -1)
        {
            Game.requestBuy(P, this);
        }
        //If you land on enemy players shipping line
        else if (Owner != Game.players.indexOf(P))
        {
            if ((Game.players.get(Owner)).InPrison == false)
            {
                int Pay = 0;
                int cnt = CountShippingLines();
                Player OPlayer = Game.players.get(Owner);
                if (cnt == 1)
                {
                    Pay = 500;
                    JOptionPane.showMessageDialog(null, "Du betaler leje: " + Pay + " til ejeren");
                    P.ChangeMoney(-Pay);
                    OPlayer.ChangeMoney(Pay);
                }
                else if (cnt == 2)
                {
                    Pay = 1000;    
                    JOptionPane.showMessageDialog(null, "Du betaler leje: " + Pay + " til ejeren");
                    P.ChangeMoney(-Pay);
                    OPlayer.ChangeMoney(Pay);
                }
                else if (cnt == 3)
                {
                    Pay = 2000;    
                    JOptionPane.showMessageDialog(null, "Du betaler leje: " + Pay + " til ejeren");
                    P.ChangeMoney(-Pay);
                    OPlayer.ChangeMoney(Pay);
                }
                else if (cnt == 4)
                {
                    Pay = 4000;
                    JOptionPane.showMessageDialog(null, "Du betaler leje: " + Pay + " til ejeren");
                    P.ChangeMoney(-Pay);
                    OPlayer.ChangeMoney(Pay);
                }
                
            }
        }
        
    }   
}
