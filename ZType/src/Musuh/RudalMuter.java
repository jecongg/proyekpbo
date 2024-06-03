
package Musuh;

import Controller.Play;
import java.awt.Color;
import java.awt.Container;
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

public class RudalMuter extends EnemyParent {
    Timer rotate;
    Timer turun;
    Timer pause;
    double angle;
    
    public RudalMuter(String kata, JDesktopPane pane, int x, int y, Play play, double angle){
        this.play=play;
        this.kata=kata;
        this.x=x;
        this.y=y;
        this.angle=angle;
        count=0;
        this.pane=pane;
        width=0;
        initPause();
        init();
        turun();
        animasiRotate();
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
        
        sizeGambarX=30;
        sizeGambarY=30;
        
        Dimension size = label.getPreferredSize();
        width=size.width;
        label.setBounds(x-width, y, size.width, size.height);
        
        
        gambarLabel = new JLabel();
        gambar = new ImageIcon(new ImageIcon("src/Image/peluru_pesawat.png").getImage().getScaledInstance(sizeGambarX, sizeGambarY, Image.SCALE_SMOOTH));
        gambarLabel.setIcon(gambar);
        gambarLabel.setBounds(x, 0, sizeGambarX, sizeGambarY);
        
        
        pane.add(label);
        pane.add(gambarLabel);
        
    }

    @Override
    public void hapus() {
        super.hapus();
        pause.stop();
        turun.stop();
        rotate.stop();
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
            double speed=3.4;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                x += speed * Math.cos(Math.toRadians(angle));
                y += speed * Math.sin(Math.toRadians(angle));
                if(x<=0){
                    x-=1;
                }
                gambarLabel.setLocation(x, y);
                label.setLocation(x + 10, y - 20);
                if(x>223 && x<273 && y>510 && y<560){
                    play.kurangHealth();
                    hapusDiriSendiri();
                    ((Timer) e.getSource()).stop();
                }
                if (x <= -25 || x > 515 || y > 625) {
                    hapusDiriSendiri();
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        turun.start();
    }
}
