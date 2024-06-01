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

public class PesawatBesar extends EnemyParent {
    
    public PesawatBesar(String kata, JDesktopPane pane, int x, Play play){
        this.play=play;
        this.kata=kata;
        this.x=x;
        count=0;
        y=0;
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
        
        Dimension size = label.getPreferredSize();
        width=size.width;
        label.setBounds(x-width, y, size.width, size.height);
        
        
        gambarLabel = new JLabel();
        gambar = new ImageIcon(new ImageIcon("src/Image/musuh gede.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        gambarLabel.setIcon(gambar);
        gambarLabel.setBounds(x, 0, 100, 100);

        
        pane.add(label);
        pane.add(gambarLabel);
        
    }
    
    private void hapusDiriSendiri(){
        play.hapusMusuh(this);
        hapus();
    }
    

    
    private void turun() {
        Timer t = new Timer(20, new ActionListener() {
            int startX = label.getX();
            int startY = label.getY();
            int deltaX = 248 - startX;
            int deltaY = 535 - startY;
            double steps = 300;
            double currentStep = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentStep >= steps - 60){
                    play.kurangHealth();
                    hapusDiriSendiri();
                    ((Timer) e.getSource()).stop();
                }
                else {
                    double progress = currentStep / steps;
                    x = (int) (startX + deltaX * progress);
                    y = (int) (startY + deltaY * progress);
                    label.setLocation(x + 10, y - 20);
                    gambarLabel.setLocation(x, y);
                    currentStep++;
                }
            }
        });
        t.start();
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
    

}