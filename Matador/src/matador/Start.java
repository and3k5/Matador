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
public class Start extends Field{
    @Override
    public void Lands(Player P)
    {
        P.ChangeMoney(4000);
    }
    @Override
    public void Passed(Player P)
    {
        P.ChangeMoney(4000);
    }
    
}
