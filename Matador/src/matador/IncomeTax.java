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
//When player lands the game looks how much money the Player have and finds the most optimal option
public class IncomeTax extends Field{
    @Override
    public void Lands(Player P)
    {
        JOptionPane.showMessageDialog(null, "Betal din inkomstskat, på 4000 eller 10%.\n(valgt den bedste værdig for dig)");
        // get how much money the player have
        int PMoney = P.GetMoney();
        if (PMoney >= 40000)
        {
            //takes mone from the palyer
            P.ChangeMoney(-4000);
        }else{
            int IT = (int)(PMoney * 0.1)*-1;
            P.ChangeMoney(IT);
        }
    }
}
