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
public class Dice {
    // Number of dice
    public int number;
    
    public Dice() 
    {
        //Sets the base number of dice to 1
        this.number = 1;
    }
    public void Throw() 
    {
        //Gives a random number from 1-6 (A normal 6 sided dice)
        this.number=(int)(1+Math.random()*6);
    }
}
