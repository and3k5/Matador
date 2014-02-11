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
    
    public int Taxes;
    public int Owner;
    public String Name;
    public boolean Mortgage;
    
    @Override
    public void Lands (Player P)
    {
                /*
        * If nobody owns it      
        */
        if (Owner == 0)
        {
            
        }
        /*
        * If you land on a enemy's enemy lands on yours, a payout must happen 
        * (Once or twice the eyes on the dices)
        */
        else if (Owner != PID)
        {
            if (P.InPrison = false)
            {
                /*if (BOTH OWNED)
                {
                    int Pay;
                
                    P.ChangeMoney(Pay);
                }
                else if (only 1 owned)
                {
                    int Pay;
                        
                    P.ChangeMoney(Pay);
                }
                */
            }
        }
    }
}
