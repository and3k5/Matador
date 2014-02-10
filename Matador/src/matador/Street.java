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
    public int Taxes;
    public int GroupID;
    public int Owner;
    public int Houses;
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
            if (P.InPrison = false)
            {
                //int Pay;
            
                //P.ChangeMoney(Pay);
            }
        }
}
