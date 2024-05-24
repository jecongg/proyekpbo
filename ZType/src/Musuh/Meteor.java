
package Musuh;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Meteor extends EnemyParent {
    Timer rotate;
    Timer turun;
    
    public Meteor(String kata, JDesktopPane pane, int x){
        this.kata=kata;
        this.x=x;
        count=0;
        y=0;
        this.pane=pane;
        width=0;
        init();
        turun();
        animasiRotate();
    }
    
    private void init(){
        label = new JLabel(kata);
        Font font = new Font("Arial", Font.BOLD, 18);
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(0,0,0,(float)0.5));
        label.setOpaque(true);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setFont(font);
        
        Dimension size = label.getPreferredSize();
        width=size.width;
        label.setBounds(x, y, size.width, size.height);
        
        
        gambarLabel = new JLabel();
        gambar = new ImageIcon(new ImageIcon("src/Image/meteor.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        gambarLabel.setIcon(gambar);
        gambarLabel.setBounds(x+width, 0, 30, 30);
        
        
        pane.add(label);
        pane.add(gambarLabel);
        
    }

    @Override
    public void hapus() {
        super.hapus();
        turun.stop();
        rotate.stop();
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
    
    public void animasiRotate(){
        rotate = new Timer(2, new ActionListener() {
            private double rotationAngle = 0;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                rotationAngle += 1;
                    
                gambarLabel.setIcon(rotateImage(gambar.getImage(), rotationAngle));
            }
        });
        rotate.start();
    }
    
    private void turun() {
        turun = new Timer(10, new ActionListener() {
            int startX = label.getX();
            int startY = label.getY();
            int deltaX = 248 - startX;
            int deltaY = 535 - startY;
            double currentStep = 0;
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            double speed = 1;
            double steps = distance / speed;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStep <= steps) {
                    double progress = currentStep / steps;
                    x = (int) (startX + deltaX * progress);
                    y = (int) (startY + deltaY * progress);
                    label.setLocation(x, y);
                    gambarLabel.setLocation(x - 10, y + 20);
                    currentStep++;
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        turun.start();
    }
    
}
