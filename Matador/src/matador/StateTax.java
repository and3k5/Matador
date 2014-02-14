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
public class StateTax extends Field{
    @Override
    public void Lands(Player P)
    {
        //Takes money from the player when it lands on the field
        JOptionPane.showMessageDialog(null, "Betal din Statsskat, på 2000 kr.");
        P.ChangeMoney(-2000);
    }
}
