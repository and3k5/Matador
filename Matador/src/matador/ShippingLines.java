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
        * If enemy lands on yours, a payout must happen
        */
        else if (Owner != PID)
        {
            //int Pay;
            
            
            //P.ChangeMoney(Pay);
        }
        
    }   
}
