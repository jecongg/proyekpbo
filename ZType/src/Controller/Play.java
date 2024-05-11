/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Musuh.EnemyParent;
import Musuh.Meteor;
import Pesawat.PesawatParent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;


public class Play <T extends PesawatParent> {
    T player;
    JDesktopPane panel;
    JFrame frame;
    int wave, jumlahMeteor, jumlahPesawatBiasa, jumlahPesawatBesar;
    ArrayList<EnemyParent> listEnemy = new ArrayList<>();
    EnemyParent current;
    ArrayList<String> kataMeteor;
    ArrayList<String> kataPesawat;
    ImageIcon gambarSpaceship;
    JLabel labelSpaceship;
    int xSpace, ySpace;
    double angleSpace;

    public Play(T player, JDesktopPane panel, JFrame frame){
        kataMeteor=new ArrayList<>();
        kataPesawat=new ArrayList<>();
        this.player = player;
        this.panel = panel;
        this.frame = frame;
        angleSpace=0;
        wave=0;
        jumlahMeteor=4;
        jumlahPesawatBiasa=0;
        jumlahPesawatBesar=0;
        current=null;
        initAwal();
        readFile();
        nextWave();
    }
    
    public void readFile(){
        try {
            File myObj = new File("src/File/text.txt");
            Scanner myReader = new Scanner(myObj);
            String data="";
            while (myReader.hasNext()){
                data = myReader.next();
                if(data.length()<7){
                    kataMeteor.add(data);
                }
                else{
                    kataPesawat.add(data);
                }
            }
            myReader.close();
        }
        catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    private void panggilMeteor(){
        Random r = new Random();
        int x = r.nextInt(400);
        int gacha = r.nextInt(kataMeteor.size());
        Meteor m = new Meteor(kataMeteor.get(gacha), panel, x);
        listEnemy.add(m);
    }
    
    public ImageIcon rotateImage(Image image, double angleDegrees) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        // Set the rotation angle
        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(angleDegrees), width / 2.0, height / 2.0);

        // Apply transformation
        g2d.setTransform(tx);

        // Draw the rotated image
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return new ImageIcon(bufferedImage);
    }
    
    public void rotateSpaceship(int x, int y){
        double deltaX = xSpace - x + 25;
        double deltaY = ySpace - y + 25;
        double targetAngle = Math.toDegrees(Math.atan2(deltaY, deltaX));
        double angleDiff = targetAngle - angleSpace;
        angleSpace=angleDiff;
        labelSpaceship.setIcon(rotateImage(gambarSpaceship.getImage(), angleDiff));
        
//                Timer timer = new Timer(50, new ActionListener() {
//            private double rotationAngle = 0;
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Update the rotation angle
//                rotationAngle += 5;
//                if (rotationAngle >= 360) {
//                    rotationAngle -= 360;
//                }
//
//                // Redraw the panel
//                tes.setIcon(rotateImage(gambarSpaceship.getImage(), rotationAngle));
//            }
//        });
//        timer.start();
    }
    
    public void initAwal(){
        labelSpaceship = new JLabel();
        gambarSpaceship = new ImageIcon(new ImageIcon("src/Image/img_spaceship.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        labelSpaceship.setIcon(gambarSpaceship);
        xSpace=223;
        ySpace=510;
        labelSpaceship.setBounds(223, 510, 50, 50);
        panel.add(labelSpaceship);
        
        frame.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                frameTyped(evt);
            }
        });
    }
    
    private void frameTyped(KeyEvent evt){
        char typed = evt.getKeyChar();
        if(current==null){
            boolean sudah=false;
            for(int i=0; i<listEnemy.size(); i++){
                if(listEnemy.get(i).getChar()==typed && !sudah){
                    sudah=false;
                    current=listEnemy.get(i);
                }
            }
            if(typed == current.getChar()){
                current.kurangHuruf();
                rotateSpaceship(current.getX(), current.getY());
            }
        }
        else{
            if(typed == current.getChar()){
                if(current.kurangHuruf()){
                    listEnemy.remove(current);
                    current=null;
                    if(listEnemy.size()==0){
                        nextWave();
                    }
                }
            }
        }
    }
    
    public void nextWave(){
        wave++;
        System.out.println("Wave " + wave);
        Random r = new Random();
        for(int i=0; i<jumlahMeteor; i++){
            int gacha=r.nextInt(kataMeteor.size());
            int x =r.nextInt(300);
            Meteor m = new Meteor(kataMeteor.get(gacha), panel, x);
            listEnemy.add(m);
        }
    }
    
}
