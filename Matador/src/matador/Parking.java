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
public class Parking extends Field
{
    //Increases your money with 4000 if you land on it.
    public int Payout = 4000;
    @Override
    public void Lands(Player P)
    {
        P.ChangeMoney(Payout);
    }
}
