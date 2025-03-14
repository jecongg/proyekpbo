package Projectile;

import Musuh.EnemyParent;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author jason
 */
public class LaserController {
    private JLabel laserLabel;
    private ImageIcon laserGambar;
    private int projectileX;
    private int projectileY;
    private int projectileSpeed = 10;
    private int width, height;
    private EnemyParent target;
    private Timer turun;
    private JDesktopPane pane;
    private Clip clip;
    
    public LaserController(int x, int y, EnemyParent target, JDesktopPane pane) {       
        height=83;
        width=48;
        projectileX=x-width/2;
        projectileY=(y-height/2)-5;
        this.target=target;
        this.pane=pane;
        initAwal();
        turun();
        rotateSpaceship();
    }
    
    private void initAwal(){
        laserLabel = new JLabel();
        laserGambar = new ImageIcon(new ImageIcon("src/Image/laser.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        laserLabel.setIcon(laserGambar);
        laserLabel.setBounds(projectileX, projectileY, width, height);
        pane.add(laserLabel);
        playSound();
    }
    
    public ImageIcon rotateImage(Image image, double angleDegrees) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(angleDegrees), width / 2.0, height / 2.0);

        g2d.setTransform(tx);

        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return new ImageIcon(bufferedImage);
    }
    
    public void rotateSpaceship(){
        double deltaX = projectileX - target.getX();
        double deltaY = projectileY - target.getY();
        double targetAngle = Math.toDegrees(Math.atan2(deltaY, deltaX)) -90;
        laserLabel.setIcon(rotateImage(laserGambar.getImage(), targetAngle));
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
                    laserLabel.setLocation((int)projectileX, (int)projectileY);
                    if(target.isAlive()){
                        target.pause();
                    }
                    ((Timer) e.getSource()).stop();
                    pane.remove(laserLabel);
                } else {
                    double stepX = (deltaX / distance) * projectileSpeed;
                    double stepY = (deltaY / distance) * projectileSpeed;

                    projectileX += stepX;
                    projectileY += stepY;

                    laserLabel.setLocation((int)projectileX, (int)projectileY);
                }
            }
        });
        turun.start();
    }
    
    
    public void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/Sounds/tembak.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
}
