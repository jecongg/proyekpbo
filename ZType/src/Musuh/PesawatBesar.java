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

public class PesawatBesar extends EnemyParent {
    ArrayList<EnemyParent> listMusuh;
    Timer tembak;
    Timer turun;
    Timer pause;
    int countTembak;
    ImageIcon ledakanGambar;
    JLabel ledakanLabel;
    
    public PesawatBesar(String kata, JDesktopPane pane, int x, Play play, ArrayList<EnemyParent> listMusuh){
        this.play=play;
        this.kata=kata;
        this.x=x;
        this.listMusuh=listMusuh;
        count=0;
        countTembak=0;
        y=0;
        this.pane=pane;
        ledakanGambar = new ImageIcon(scaleImage(new ImageIcon("src/Image/ledakan.png").getImage(), 200, 200)); 
        width=0;
        init();
        turun();
        nyalakanTembak();
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
        label.setBounds(sizeGambarX/2+30, sizeGambarY/2-18, size.width, size.height);
        
        sizeGambarX=92;
        sizeGambarY=106;
        gambarLabel = new JLabel();
        gambar = new ImageIcon(new ImageIcon("src/Image/musuh gede.png").getImage().getScaledInstance(sizeGambarX, sizeGambarY, Image.SCALE_SMOOTH));
        gambarLabel.setIcon(gambar);
        gambarLabel.setBounds(x - sizeGambarX/2, 0, sizeGambarX, sizeGambarY);
        
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
                PesawatBesar.super.hapus();
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
    
    private void hapusDiriSendiri(){
        play.hapusMusuh(this);
        hapus();
    }
    
    public void tembak() {
        int banyakPeluru = 12;
        double startAngle = 0;
        double endAngle = 180;
        double angleIncrement = (endAngle - startAngle) / (banyakPeluru-1);
        String[] huruf = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        Random r = new Random();
        for (int i = 0; i < banyakPeluru; i++) {
            double angle = startAngle + i * angleIncrement;
            int gacha = r.nextInt(26);
            RudalMuter rm = new RudalMuter(huruf[gacha], pane, x, y, play, angle);
            listMusuh.add(rm);
        }

        pane.repaint();
    }
    
    private void nyalakanTembak(){
        tembak = new Timer(4500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(countTembak==2){
                    ((Timer) e.getSource()).stop();
                }
                if(play.isAlive()){
                    countTembak++;
                    tembak();
                }
            }
        });
        tembak.start();
    }
    
    private void turun() {
        turun = new Timer(10, new ActionListener() {
            int startX = x;
            int startY = y;
            int deltaX = 248 - startX;
            int deltaY = 535 - startY;
            double currentStep = 0;
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            double speed = 0.6;
            double steps = distance / speed;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStep >= steps-15) {
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
                        label.setLocation(xGambar + 30, yGambar - 18);
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
                    label.setLocation(xGambar + 30, yGambar - 18);
                    gambarLabel.setLocation(xGambar, yGambar);
                    currentStep++;
                }
            }
        });
        turun.start();
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