/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Musuh;

/**
 *
 * @author jason
 */

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

public class Darah extends EnemyParent {
    Timer rotate;
    Timer turun;
    Timer pause;
    ImageIcon[] heartIcon;
    
    public Darah(String kata, JDesktopPane pane, int x, Play play){
        this.play=play;
        this.kata=kata;
        this.x=x;
        count=0;
        y=0;
        this.pane=pane;
        width=0;
        initIcon();
        initPause();
        init();
        turun();
        animasiRotate();
    }
    
    private void initIcon(){
        sizeGambarX=49;
        sizeGambarY=44;
        heartIcon = new ImageIcon[10];
        for(int i=0; i<10; i++){
            heartIcon[i]=new ImageIcon(new ImageIcon("src/Image/heart" + (i+1) + ".png").getImage().getScaledInstance(sizeGambarX, sizeGambarY, Image.SCALE_SMOOTH));
        }
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
        gambarLabel.setIcon(heartIcon[0]);
        gambarLabel.setBounds(xGambar, 0, sizeGambarX, sizeGambarY);
        
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
    
    public void animasiRotate(){
        rotate = new Timer(2, new ActionListener() {
            private int count = 0;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                if(count==10){
                    count=0;
                }
                gambarLabel.setIcon(heartIcon[count]);
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

