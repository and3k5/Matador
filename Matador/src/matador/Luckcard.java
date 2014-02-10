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
public class Luckcard {
    
    
    static void TryCard(Player P)
    {
        String[] Luckcards = {"Gå til fængsel",""};
    
        int length = Luckcards.length;
        int randomcard;
        randomcard = (int) (length * Math.random() + 1);
        
        switch (Luckcards[randomcard]){
            case "Gå til fængsel":
                P.InPrison = true;
                P.Position = 10;
                break;
            case "":
                break;
        }
    }
    
}
