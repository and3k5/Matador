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
public class Prison extends Field{
    @Override
    public void Lands(Player P)
    {
        if(P.InPrison == true)
        {
            Prisoner(P);
        }
    }
    private void Prisoner(Player P)
    {
        Object[] options = new Object[3];
        options[0] = "Kast med Terningerne";
        options[1] = "Betal 1000";
        int GetOutCard = 1;
        if (GetOutCard > 0)
        {
            options[2] = "Brug lykke kort";
        }
        JOptionPane.showOptionDialog(null, "Tryk på en knap", "Valg",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
        
    }
}
