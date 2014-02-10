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
public class TryLuck extends Field {
    // When Player Lands on this field he will get a card from the Luckcard class
    @Override
    public void Lands(Player P)
    {
        Luckcard.TryCard(P);
    }
}
