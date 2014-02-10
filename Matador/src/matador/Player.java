/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matador;

import java.awt.Color;

/**
 *
 * @author and3k5
 */
public class Player {
    /*
    * Sets the players Money, Playing Color, Name, Where he is on the board,
    * if hes in prison and if he have a get out of prison card.
    */
    private int Money;
    public Color Color;
    public String Name;
    public int Position;
    public boolean InPrison;
    public int GetOutCard;
    
    public Player()
    {
        /*
        * Sets the players starts variables
        */
        
        Money = 50000;
        Color = new Color(0,0,0);
        Name = "";
        Position = 0;
        InPrison = false;
        GetOutCard = 0;
    }
    
    public int GetMoney()
    {
        /*
        * Returns Money publicly
        */
        
        return Money;
    }
    
    public int ChangeMoney(int value)
    {
        /*
        * Changes the money you have if you pass start, are parking,
        * Income Tax or State Tax and so on.
        */
        
        Money = Money + value;
        return Money;
    } 
}
