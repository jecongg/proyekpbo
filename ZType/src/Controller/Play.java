/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Musuh.EnemyParent;
import Musuh.Meteor;
import Musuh.PesawatBesar;
import Musuh.PesawatBiasa;
import Pesawat.PesawatParent;
import Projectile.LaserController;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.Timer;
import ztype.Game;


public class Play <T extends PesawatParent> {
    T player;
    JDesktopPane panel;
    Game frame;
    int wave, jumlahMeteor, jumlahPesawatBiasa, jumlahPesawatBesar;
    ArrayList<EnemyParent> listEnemy = new ArrayList<>();
    ImageIcon[] healthIcon;
    EnemyParent current;
    ArrayList<String> kataMeteor;
    ArrayList<String> kataPesawat;
    ImageIcon gambarSpaceship;
    JLabel labelSpaceship;
    JLabel labelHealth;
    int xSpace, ySpace;
    int lives;
    double angleSpace;

    public Play(T player, JDesktopPane panel, Game frame){
        kataMeteor=new ArrayList<>();
        kataPesawat=new ArrayList<>();
        healthIcon = new ImageIcon[5];
        this.player = player;
        this.panel = panel;
        this.frame = frame;
        angleSpace=0;
        wave=1;
        jumlahMeteor=0;
        jumlahPesawatBiasa=1;
        jumlahPesawatBesar=1;
        lives=4;
        current=null;
        initHealth();
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
    
    private void initHealth(){
        for(int i=0; i<5; i++){
            healthIcon[i] = new ImageIcon(new ImageIcon("src/Image/health" + i + ".png").getImage().getScaledInstance(120, 39, Image.SCALE_SMOOTH));
        }
        labelHealth = new JLabel();
        labelHealth.setIcon(healthIcon[4]);
        labelHealth.setBounds(30, 515, 120, 39);
        panel.add(labelHealth);
    }
    
    public void kurangHealth(){
        lives--;
        labelHealth.setIcon(healthIcon[lives]);
        if(lives==0){
            gameOver();
        }
    }
    
    public void tambahHealth(){
        lives++;
        labelHealth.setIcon(healthIcon[lives]);
    }
    
    public void gameOver(){
        frame.initGameName();
    }
    
    public <T extends EnemyParent> void hapusMusuh(T e){
        listEnemy.remove(e);
        if(current==e){
            current=null;
        }
        if(listEnemy.size()==0 && lives>0){
            nextWave();
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
    
    public void rotateSpaceship(int x, int y){
        double deltaX = x - xSpace;
        double deltaY = y - ySpace;
        double targetAngle = Math.toDegrees(Math.atan2(deltaY, deltaX)) + 90;
        targetAngle%=360;
        boolean tambah=false;
        if(angleSpace<=targetAngle){
            tambah=true;
        }
        animasiRotate(targetAngle, angleSpace, tambah);
        
        angleSpace=targetAngle;
    }
    
    public void animasiRotate(double target, double current, boolean tambah){
        Timer timer = new Timer(1, new ActionListener() {
            private double rotationAngle = current;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tambah){
                    rotationAngle += 2;
                    if (rotationAngle >= target) {
                        ((Timer) e.getSource()).stop();
                    }
                }
                else{
                    rotationAngle -= 2;
                    if (rotationAngle <= target) {
                        ((Timer) e.getSource()).stop();
                    }
                }
                labelSpaceship.setIcon(rotateImage(gambarSpaceship.getImage(), rotationAngle));
            }
        });
        timer.start();
    }
    
    public boolean isAlive(){
        if(lives>0){
            return true;
        }
        return false;
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
            if(current!=null){
                if(typed == current.getChar()){
                    LaserController l = new LaserController(248, 538, current, panel);
                    rotateSpaceship(current.getGambarLabel().getX(), current.getGambarLabel().getY());
                    if(current.kurangHuruf()){
                        listEnemy.remove(current);
                        current=null;
                        if(listEnemy.size()==0){
                            nextWave();
                        }
                    }
                    panel.repaint();
                }
            }
        }
        else{
            if(typed == current.getChar()){
                LaserController l = new LaserController(248, 538, current, panel);
                if(current.kurangHuruf()){
                    listEnemy.remove(current);
                    current=null;
                    if(listEnemy.size()==0){
                        nextWave();
                    }
                }
            }
        }
        panel.repaint();
    }
    
    public void nextWave(){
        System.out.println("Wave " + wave);
        Random r = new Random();
        
        Timer t = new Timer(300, new ActionListener() {
            int tempMeteor=jumlahMeteor;
            int tempBesar=jumlahPesawatBesar;
            int tempBiasa=jumlahPesawatBiasa;
            boolean pertama=true;
            ArrayList<String> list=new ArrayList<>();
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!pertama && list.size()==0){
                    ((Timer)e.getSource()).stop();
                }
                if(pertama){
                    if(tempBesar>0){
                        list.add("Besar");
                    }
                    if(tempBiasa>0){
                        list.add("Biasa");
                    }
                    if(tempMeteor>0){
                        list.add("Meteor");
                    }
                    pertama=false;
                }
                if(list.size()>0){
                    int gacha=r.nextInt(list.size());
                    if(list.get(gacha).equals("Meteor")){
                        tambahMeteor();
                        tempMeteor--;
                        if(tempMeteor==0){
                            list.remove("Meteor");
                        }
                    }
                    else if(list.get(gacha).equals("Biasa")){
                        tambahPesawatBiasa();
                        tempBiasa--;
                        if(tempBiasa==0){
                            list.remove("Biasa");
                        }
                    }
                    else{
                        tambahPesawatBesar();
                        tempBesar--;
                        if(tempBesar==0){
                            list.remove("Besar");
                        }
                    }
                }
            }
        });
        wave++;
        if(wave % 3 == 0){
            jumlahPesawatBiasa++;
            if(wave == 12){
                jumlahPesawatBiasa = 3;
                jumlahPesawatBesar = 1;
                jumlahMeteor = 0;
            }
        }
        else{
            jumlahMeteor++;
            if(wave == 7){
                jumlahMeteor--;
                jumlahPesawatBiasa--;
                jumlahPesawatBesar++;
            }
            if(wave == 10){
                jumlahPesawatBesar = 0;
                jumlahPesawatBiasa = 0;
            }
            if(wave == 11){
                jumlahPesawatBesar = 0;
                jumlahPesawatBiasa = 0;
            }
        }
        Timer delay = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.start();
                ((Timer)e.getSource()).stop();
            }
        });
        if(wave!=2){
            delay.start();
        }
        else{
            t.start();
        }
    }
    
    public void tambahMeteor(){
        Random r = new Random();
        int gacha=r.nextInt(kataMeteor.size());
        int x = r.nextInt(500);

        Meteor m = new Meteor(kataMeteor.get(gacha), panel, x, this);
        listEnemy.add(m);
    }
    
    public void tambahPesawatBiasa(){
        Random r = new Random();
        int gacha=r.nextInt(kataPesawat.size());
        int x = r.nextInt(500);

        PesawatBiasa p = new PesawatBiasa(kataPesawat.get(gacha), panel, x, listEnemy, kataMeteor, this);
        listEnemy.add(p);
    }
    
    public void tambahPesawatBesar(){
        Random r = new Random();
        int gacha=r.nextInt(kataPesawat.size());
        int x = r.nextInt(500);

        PesawatBesar pb = new PesawatBesar(kataPesawat.get(gacha), panel, x, this, listEnemy);
        listEnemy.add(pb);
    }
}
