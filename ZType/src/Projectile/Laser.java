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

    public Laser(int x, int y, EnemyParent target, JDesktopPane pane) {
        try {
            projectileImage = ImageIO.read(new File("src/Image/laser.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (projectileImage != null) {
            g.drawImage(projectileImage, 0, 0, 50, 50, null);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
}


