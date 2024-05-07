/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Musuh;

import java.awt.Color;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author jason
 */
public abstract class EnemyParent {
    protected String kata;
    protected int x, y, count;
    protected JDesktopPane pane;
    protected JLabel label;

    public char getChar() {
        return kata.charAt(count);
    }
    
    public boolean kurangHuruf(){
        count++;
        if(count>=kata.length()){
            pane.remove(label);
            pane.repaint();
            return true;
        }
        else{
            pane.setComponentZOrder(label, 0);
            label.setText(kata.substring(count, kata.length()));
//            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setForeground(Color.ORANGE);
            pane.repaint();
            return false;
        }
    }
}
