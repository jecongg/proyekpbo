/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projectile;

import Musuh.EnemyParent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.Timer;

/**
 *
 * @author jason
 */
public class LaserController {
    private Laser l;
    private int projectileX;
    private int projectileY;
    private int projectileSpeed = 5;
    private EnemyParent target;
    private Timer turun;
    private JDesktopPane pane;
    
    public LaserController(int x, int y, EnemyParent target, JDesktopPane pane) {
        projectileX=x;
        projectileY=y;
        this.target=target;
        this.pane=pane;
        l = new Laser(x, y, target, pane);
        pane.add(l);
        l.setBounds(x, y, 50, 50);
        l.setBackground(new Color(0, 0, 0, 0));
        turun();
    }
    
    private void turun() {
        turun = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double deltaX = target.getX() - projectileX;
                double deltaY = target.getY() - projectileY;
                double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

                if (distance < projectileSpeed) {
                    projectileX = target.getX();
                    projectileY = target.getY();
                    l.setLocation((int)projectileX, (int)projectileY);
                    ((Timer) e.getSource()).stop();
                    pane.remove(l);
                } else {
                    double stepX = (deltaX / distance) * projectileSpeed;
                    double stepY = (deltaY / distance) * projectileSpeed;

                    projectileX += stepX;
                    projectileY += stepY;

                    l.setLocation((int)projectileX, (int)projectileY);
                }
            }
        });
        turun.start();
    }
}
