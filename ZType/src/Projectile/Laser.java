package Projectile;

import Musuh.EnemyParent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Laser extends JPanel {
    private BufferedImage projectileImage;
    private int projectileX;
    private int projectileY;
    private int projectileSpeed = 5;
    private EnemyParent target;
    private Timer turun;
    private JDesktopPane pane;

    public Laser(int x, int y, EnemyParent target, JDesktopPane pane) {
        projectileX=x;
        projectileY=y;
        this.target=target;
        this.pane=pane;
        
        try {
            projectileImage = ImageIO.read(new File("src/Image/laser.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tambahKePane();
        turun();
    }
    
    public void tambahKePane(){
        pane.add(this);
    }
    
    private void turun() {
        turun = new Timer(10, new ActionListener() {
            int startX = projectileX;
            int startY = projectileY;
            int deltaX = 248 - startX;
            int deltaY = 535 - startY;
            double steps = 300;
            double currentStep = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                if (currentStep <= steps) {
                    double progress = currentStep / steps;
                    projectileX = (int) (startX + deltaX * progress);
                    projectileY = (int) (startY + deltaY * progress);
                    currentStep++;
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        turun.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (projectileImage != null) {
            g.drawImage(projectileImage, projectileX, projectileY, null);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
}


