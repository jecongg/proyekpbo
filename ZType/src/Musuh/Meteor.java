
package Musuh;

import Controller.Play;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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
    Timer pause;
    ImageIcon ledakanGambar;
    JLabel ledakanLabel;
    
    public Meteor(String kata, JDesktopPane pane, int x, Play play){
        this.play=play;
        this.kata=kata;
        this.x=x;
        count=0;
        ledakanGambar = new ImageIcon(scaleImage(new ImageIcon("src/Image/ledakan.png").getImage(), 200, 200)); 
        y=0;
        this.pane=pane;
        width=0;
        initPause();
        init();
        turun();
        animasiRotate();
    }
    
    private Image scaleImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
    
    private void initPause(){
        pause= new Timer(2000, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                turun.start();
                ((Timer) e.getSource()).stop();
            }
        });
    }
    
    private void init(){
        label = new JLabel(kata);
        Font font = new Font("Arial", Font.BOLD, 18);
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(0,0,0,(float)0.5));
        label.setOpaque(true);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setFont(font);
        int xGambar = x-sizeGambarX/2;
        int yGambar = y-sizeGambarY/2;

        Dimension size = label.getPreferredSize();
        width=size.width;
        label.setBounds(xGambar + 10, yGambar - 20, size.width, size.height);
        
        sizeGambarX=30;
        sizeGambarY=30;
        
        gambarLabel = new JLabel();
        gambar = new ImageIcon(new ImageIcon("src/Image/meteor.png").getImage().getScaledInstance(sizeGambarX, sizeGambarY, Image.SCALE_SMOOTH));
        gambarLabel.setIcon(gambar);
        gambarLabel.setBounds(xGambar, 0, sizeGambarX, sizeGambarY);
        
        pane.add(label);
        pane.add(gambarLabel);
        
    }

    @Override
    public void hapus() {
        showExplosion();
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeExplosion();
                Meteor.super.hapus();
                pause.stop();
                turun.stop();
                rotate.stop();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
     private void showExplosion() {
        ledakanLabel = new JLabel(ledakanGambar);
        ledakanLabel.setBounds(gambarLabel.getBounds());
        pane.add(ledakanLabel);
        pane.setComponentZOrder(ledakanLabel, 0); 
        pane.repaint();
    }

    private void removeExplosion() {
        pane.remove(ledakanLabel);
        pane.repaint();
    }

    @Override
    public void pause() {
        turun.stop();
        if(pause.isRunning()){
            pause.restart();
        }
        else{
            pause.start();
        }
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
    
    private void hapusDiriSendiri(){
        play.hapusMusuh(this);
        hapus();
    }
    
    private void turun() {
        turun = new Timer(10, new ActionListener() {
            int startX = gambarLabel.getX();
            int startY = gambarLabel.getY();
            int deltaX = 248 - startX;
            int deltaY = 535 - startY;
            double currentStep = 0;
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            double speed = 1;
            double steps = distance / speed;
            
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentStep >= steps - 20) {
                if (play.isAlive()) {
                    play.kurangHealth();
                    hapusDiriSendiri();
                    ((Timer) e.getSource()).stop();
                } 
                else {
                    double progress = currentStep / steps;
                    x = (int) (startX + deltaX * progress);
                    y = (int) (startY + deltaY * progress);
                    int xGambar = x-sizeGambarX/2;
                    int yGambar = y-sizeGambarY/2;
                    label.setLocation(xGambar + 10, yGambar - 20);
                    gambarLabel.setLocation(xGambar, yGambar);
                    currentStep++;
                }
            } 
            else {
                double progress = currentStep / steps;
                x = (int) (startX + deltaX * progress);
                y = (int) (startY + deltaY * progress);
                
                int xGambar = x-sizeGambarX/2;
                int yGambar = y-sizeGambarY/2;
                label.setLocation(xGambar + 10, yGambar - 20);
                gambarLabel.setLocation(xGambar, yGambar);
                currentStep++;
            }
        }
        });
        turun.start();
    }
    
}
