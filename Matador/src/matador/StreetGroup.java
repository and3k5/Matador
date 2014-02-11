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
public class StreetGroup {
    // Color for the street group.
    // This makes it easy to lookup if players have all houses in the same group
    public Color color;
    public StreetGroup() {
        color = new Color(0,0,0);
    }
}
