/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Musuh;

import Controller.Play;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author jason
 */
public abstract class EnemyParent {
    protected String kata;
    protected int x, y, count, width, sizeGambarX, sizeGambarY;
    protected JDesktopPane pane;
    protected JLabel label;
    protected ImageIcon gambar;
    protected JLabel gambarLabel;
    protected Play play;

    public char getChar() {
        return kata.charAt(count);
    }
    
    public boolean kurangHuruf(){
        count++;
        if(count>=kata.length()){
            hapus();
            pane.repaint();
            return true;
        }
        else{
            pane.setComponentZOrder(label, 0);
            label.setText(kata.substring(count, kata.length()));
            label.setForeground(Color.ORANGE);
            pane.repaint();
            return false;
        }
    }
    
    public boolean isAlive(){
        if(count>=kata.length()){
            return false;
        }
        return true;
    }
    
    public void pause(){
        
    }

    public JLabel getGambarLabel() {
        return gambarLabel;
    }
    
    public void hapus(){
        pane.remove(label);
        pane.remove(gambarLabel);
        pane.repaint();
    }

    public int getX() {
        return x - sizeGambarX/2;
    }

    public int getY() {
        return y - sizeGambarY/2;
    }

    public int getWidth() {
        return width;
    }
}
