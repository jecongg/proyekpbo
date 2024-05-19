package Projectile;

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

    public Laser(int x, int y) {
        projectileX=x;
        projectileY=y;
        
        try {
            projectileImage = ImageIO.read(new File("src/Image/laser.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                projectileX += projectileSpeed;
                
                repaint();
            }
        });
        timer.start();
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


