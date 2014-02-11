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
public class Brewery extends Field {
    
    public int Owner;
    public int Price;
    public String Name;
    public boolean Mortgage;
    
    public Brewery()
    {
        Owner = -1;
        Price = 0;
        Mortgage = false;
    }
    
    
    private int CountBrewery()
    {
        int CountBrew = 0;
        for (Field F: Game.fields )
        {
            if(F.getClass()==Brewery.class)
            {
                if (((Brewery)F).Owner==this.Owner)
                {
                    CountBrew++;               
                }
            }
        }
        return CountBrew;
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
        * If you land on a enemy's enemy lands on yours, a payout must happen 
        * (Once or twice the eyes on the dices)
        */
        else if (Owner != Game.players.indexOf(P))
        {
            if ((Game.players.get(Owner)).InPrison == false)
            {
                //BREWERY ISNT A SHIPPING LINE! (CHANGE MONEY AMOUNT AND FIX DICES)
                int cnt = CountBrewery();
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
            }
        }
    }
}
