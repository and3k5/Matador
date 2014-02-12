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
        
    }
    private void Prisoner(Player P)
    {
        Object[] options = new Object[2];
        options[0] = "Kast med Terningerne";
        options[1] = "Betal 1000";
        int GetOutCard = 0;
        if (GetOutCard > 0)
        {
            Object[] tmp = new Object[3];
            tmp[0]=options[0];
            tmp[1]=options[1];
            tmp[2] = "Brug lykke kort";
            options=tmp;
        }
        int OBP = JOptionPane.showOptionDialog(null, "Tryk på en knap", "Valg",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
        if (OBP == 0)
        {
            if (Game.dices[0].number == Game.dices[1].number)
            {
                P.InPrison = false;
                P.Position = 10 + (Game.dices[0].number + Game.dices[1].number);
                Game.fields.get(P.Position).Lands(P);
            }
        }else if (OBP == 1){
            P.InPrison = false;
            P.ChangeMoney(-1000);
        }else if (OBP == 2){
            P.InPrison = false;
            P.GetOutCard = P.GetOutCard - 1;
        }
    }
}
