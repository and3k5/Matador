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
public class GoToPrison extends Field{
    @Override
    public void Lands(Player P){
        //forces the player over to the prison and changes to InPrison
        JOptionPane.showMessageDialog(null, "Du er nu kommet i fængsel.");
        P.Position = 10;
        P.InPrison = true;
    }
}
