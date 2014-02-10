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
    
    public Dice() {
        this.number = 0;
    }
    public void Throw() {
        this.number=(int)(1+Math.random()*6);
    }
}
