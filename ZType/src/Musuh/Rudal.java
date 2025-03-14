package Musuh;

import Controller.Play;
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

public class Rudal extends EnemyParent {
    Timer turun;
    
    public Rudal(String kata, JDesktopPane pane, int x, int y, Play play){
        this.play=play;
        this.kata=kata;
        this.x=x;
        count=0;
        this.y=y;
        this.pane=pane;
        width=0;
        init();
        turun();
    }
    
    private void init(){
        label = new JLabel(kata);
        Font font = new Font("Arial", Font.BOLD, 18);
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(0,0,0,(float)0.5));
        label.setOpaque(true);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setFont(font);
        
        sizeGambarX=35;
        sizeGambarY=35;
        
        Dimension size = label.getPreferredSize();
        width=size.width;
        label.setBounds(x-width, y, size.width, size.height);
        
        gambarLabel = new JLabel();
        gambar = new ImageIcon(new ImageIcon("src/Image/rudal.png").getImage().getScaledInstance(sizeGambarX, sizeGambarY, Image.SCALE_SMOOTH));
        gambarLabel.setIcon(gambar);
        gambarLabel.setBounds(x, 0, sizeGambarX, sizeGambarY);
        
        pane.add(label);
        pane.add(gambarLabel);
        rotateSpaceship(248, 538);
    }

    @Override
    public void hapus() {
        super.hapus();
        turun.stop();
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
    
    public void rotateSpaceship(int x, int y){
        double deltaX = x - this.x;
        double deltaY = y - this.y;
        double targetAngle = Math.toDegrees(Math.atan2(deltaY, deltaX)) + 90;
        gambarLabel.setIcon(rotateImage(gambar.getImage(), targetAngle));
    }
    
    private void hapusDiriSendiri(){
        play.hapusMusuh(this);
        hapus();
    }
    
    private void turun() {
        turun = new Timer(10, new ActionListener() {
            int startX = x;
            int startY = y;
            int deltaX = 248 - startX;
            int deltaY = 535 - startY;
            double currentStep = 0;
            
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            double speed = 1.4;
            double steps = distance / speed;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStep >= steps - 15) {
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
