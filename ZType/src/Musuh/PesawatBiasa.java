
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
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class PesawatBiasa extends EnemyParent {
    private ArrayList<EnemyParent> listMusuh;
    private ArrayList<String> listKata;
    Timer tembak;
    Timer turun;
    ImageIcon ledakanGambar;
    JLabel ledakanLabel;
    int startX,startY,deltaX,deltaY;
    double currentStep,distance,speed,steps;
    
    public PesawatBiasa(String kata, JDesktopPane pane, int x, ArrayList<EnemyParent> listMusuh, ArrayList<String> listKata, Play play){
        this.play=play;
        this.kata=kata;
        this.x=x;
        count=0;
        y=0;
        this.pane=pane;
        currentStep=0;
        ledakanGambar = new ImageIcon(scaleImage(new ImageIcon("src/Image/ledakan.png").getImage(), 200, 200)); 
        this.listMusuh=listMusuh;
        this.listKata=listKata;
        width=0;
        init();
        turun();
        nyalakanTembak();
    }
    
    private void init(){
        label = new JLabel(kata);
        Font font = new Font("Arial", Font.BOLD, 18);
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(0,0,0,(float)0.5));
        label.setOpaque(true);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setFont(font);
        
        sizeGambarX=38;
        sizeGambarY=85;
        
        int xGambar = x-sizeGambarX/2;
        int yGambar = y-sizeGambarY/2;

        Dimension size = label.getPreferredSize();
        width=size.width;
        label.setBounds(xGambar+10, yGambar-20, size.width, size.height);

        gambarLabel = new JLabel();
        gambar = new ImageIcon(new ImageIcon("src/Image/pesawat_biasa.png").getImage().getScaledInstance(sizeGambarX, sizeGambarY, Image.SCALE_SMOOTH));
        gambarLabel.setIcon(gambar);
        gambarLabel.setBounds(xGambar, 0, sizeGambarX, sizeGambarY);
        
        pane.add(label);
        pane.add(gambarLabel);
        rotateSpaceship(248, 538);
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

    @Override
     public void hapus() {
        showExplosion();
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeExplosion();
                PesawatBiasa.super.hapus();
                turun.stop();
                tembak.stop();
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
        double deltaX = x - gambarLabel.getX();
        double deltaY = y - gambarLabel.getY();
        double targetAngle = Math.toDegrees(Math.atan2(deltaY, deltaX)) + 90;
        boolean tambah=false;
        gambarLabel.setIcon(rotateImage(gambar.getImage(), targetAngle));
    }
    
    private void nyalakanTembak(){
        tembak = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(play.isAlive()){
                    tembak();
                }
            }
        });
        tembak.start();
    }
    
    public void tembak(){
        Random r = new Random();
        int gacha=r.nextInt(listKata.size());
        double tempStep = currentStep + 50;
        double progress = tempStep / steps;
        if(tempStep<steps){
            int tempX = (int) (startX + deltaX * progress);
            int tempY = (int) (startY + deltaY * progress);
            Rudal ru = new Rudal(listKata.get(gacha), pane, (tempX), (tempY), play);
            listMusuh.add(ru);
        }
    }
    
    private void hapusDiriSendiri(){
        play.hapusMusuh(this);
        hapus();
    }
    
    private void turun() {
        startX = x;
        startY = y;
        deltaX = 248 - startX;
        deltaY = 535 - startY;
        distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        speed = 0.7;
        steps = distance / speed;
        turun = new Timer(10, new ActionListener() {
            

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
