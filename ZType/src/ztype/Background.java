/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ztype;

import javax.swing.JDesktopPane;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;


/**
 *
 * @author Trevis Artagrantdy K
 */
public class Background extends JDesktopPane{

    @Override
    protected void paintComponent(Graphics g) {
         Graphics2D grafik = (Graphics2D) g.create();
         Image img = new ImageIcon(getClass().getResource("/Image/Galaxy.jpg")).getImage();
         grafik.drawImage(img, 0, 0, getWidth(),getHeight(),null);
         grafik.dispose();
    }
    
    
}
