/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ztype;

import Controller.Play;
import Controller.Shop;
import Pesawat.ShipSatu;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Trevis Artagrantdy K
 */
public class Game extends javax.swing.JFrame {
    JLabel judul;
    JLabel playLabel;
    JLabel scoreLabel;
    JLabel shopLabel;
    JLabel exitLabel;
    JLabel judulScore;
    JButton backScore;
    JLabel judulShop;
    JButton backShop;
    JLabel scoreDisplayLabel; 
    int coin;
    ArrayList<String> gameData = new ArrayList<>(); 
    JLabel[] labelLB= new JLabel[5];
    int gamecount = 1;
    int indeksShip;
    Clip clip;
    
    public Game() {
        coin=0;
        initComponents();
        initawal();
        initGameName();
    }
    
    public void initawal(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.pack();
        this.setTitle("ZType");
    }
    
    public void initGameName(){ 
        try {
            File audioFile = new File("src/Sounds/MenuSound.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        clip.start();
        
        jDesktopPane1.removeAll();
        judul = new JLabel();
        Font font = new Font("Arial", Font.BOLD, 60);
        judul.setText("ZTYPE");
        judul.setForeground(Color.WHITE);
        judul.setBounds(155,30,250,100);
        judul.setFont(font);
        jDesktopPane1.add(judul);
        
        playLabel = new JLabel();
        Font fontmenu = new Font ("TImes New Roman", Font.PLAIN, 30);
        playLabel.setText("New Game");
        playLabel.setForeground(Color.WHITE);
        playLabel.setBounds(180,350, 150, 20);
        playLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        playLabel.setFont(fontmenu);
        jDesktopPane1.add(playLabel);
        
        scoreLabel = new JLabel();
        scoreLabel.setText("Score Board");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(180,400,150,20);
        scoreLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        scoreLabel.setFont(fontmenu);
        jDesktopPane1.add(scoreLabel);
        
        shopLabel = new JLabel();
        shopLabel.setText("Shop");
        shopLabel.setForeground(Color.WHITE);
        shopLabel.setBounds(180,450,150,20);
        shopLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        shopLabel.setFont(fontmenu);
        jDesktopPane1.add(shopLabel);
        
        exitLabel = new JLabel();
        exitLabel.setText("Exit");
        exitLabel.setForeground(Color.WHITE);
        exitLabel.setBounds(180,500,150,20);
        exitLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        exitLabel.setFont(fontmenu);
        jDesktopPane1.add(exitLabel);
        
        JFrame temp = this;
        
        //Dibawah ini mouselistener itu untuk animasi mouse
        //Apabila diklik maka menuju halaman tujuan yang diklik
        //Apabila dihover maka akan berubah warna
        exitLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
                temp.dispose();
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                exitLabel.setForeground(Color.ORANGE); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitLabel.setForeground(Color.WHITE); 
            }
        });
        
        playLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                initPlayGame();
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                playLabel.setForeground(Color.ORANGE); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                playLabel.setForeground(Color.WHITE); 
            }
        });
        
        shopLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                initShop();
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                shopLabel.setForeground(Color.ORANGE); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                shopLabel.setForeground(Color.WHITE); 
            }
        });
        
        scoreLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                initScoreBoard();
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                scoreLabel.setForeground(Color.ORANGE); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                scoreLabel.setForeground(Color.WHITE); 
            }
        });
    }
    
    public void updateScoreLabel(int score) {
        scoreDisplayLabel.setText("Score: " + score);
    }
    
    public void initPlayGame(){
        Timer timerJudul = new Timer(10, new ActionListener() {
            int yPosJudul = judul.getY(); 

            @Override
            public void actionPerformed(ActionEvent e) {
                yPosJudul -= 3;
                judul.setLocation(judul.getX(), yPosJudul); 
                if (yPosJudul <= -judul.getHeight()) { 
                    ((Timer) e.getSource()).stop();
                    jDesktopPane1.remove(judul);
                }
            }
        });
        timerJudul.start(); 

        animateComponent(playLabel, 1, true);
        animateComponent(scoreLabel, 2, false);
        animateComponent(shopLabel, 3, false);
        animateComponent(exitLabel, 4, false);
        
        int delayMilliseconds = 1400; 
        Timer timer = new Timer(delayMilliseconds, e -> {
            Font font = new Font("Arial", Font.BOLD, 25);
            scoreDisplayLabel = new JLabel("Score: 0");
            scoreDisplayLabel.setForeground(Color.WHITE);
            scoreDisplayLabel.setFont(font);
            scoreDisplayLabel.setBounds(340, 10, 300, 50);  
            jDesktopPane1.add(scoreDisplayLabel);
            int equippedShipIndex = getIndeksShip();
            Play p = new Play(equippedShipIndex, jDesktopPane1, this);
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    public void initScoreBoard(){
        Timer timerJudul = new Timer(10, new ActionListener() {
            int yPosJudul = judul.getY(); 

            @Override
            public void actionPerformed(ActionEvent e) {
                yPosJudul -= 3;
                judul.setLocation(judul.getX(), yPosJudul); 
                if (yPosJudul <= -judul.getHeight()) { 
                    ((Timer) e.getSource()).stop();
                    jDesktopPane1.remove(judul);
                }
            }
        });
        timerJudul.start(); 

        animateComponent(scoreLabel, 1, true);
        animateComponent(playLabel, 2, false);
        animateComponent(shopLabel, 3, false);
        animateComponent(exitLabel, 4, false);

        int delayMilliseconds = 1400; 
        Timer timer = new Timer(delayMilliseconds, e -> {
        
        judulScore = new JLabel();
        judulScore.setText("SCORE");
        Font font = new Font("Arial", Font.BOLD, 60);
        judulScore.setForeground(Color.WHITE);
        judulScore.setBounds(120,30,250,100);
        judulScore.setFont(font);
        judulScore.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jDesktopPane1.add(judulScore);        
        

        for (int i = 0; i < 5; i++) {
            if (labelLB[i] != null) {
                labelLB[i].setBounds(130, 160 + i * 50, 250, 50);
                jDesktopPane1.add(labelLB[i]);
            }
        }
        
        backScore = new JButton("BACK");
        backScore.setBounds(205,500,100,40);
        jDesktopPane1.add(backScore);
        
        backScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDesktopPane1.remove(judulScore);
                jDesktopPane1.remove(backScore);
                initGameName();
                jDesktopPane1.revalidate();
                jDesktopPane1.repaint();
            }  
        });
        jDesktopPane1.revalidate();
        jDesktopPane1.repaint();
    });
    timer.setRepeats(false); 
    timer.start();
}
    
    public void animasiGameover() {
        JLabel gameOverLabel = new JLabel("GAME OVER");
        Font font = new Font("Arial", Font.BOLD, 60);
        gameOverLabel.setFont(font);
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setBounds((jDesktopPane1.getWidth() - 400) / 2, 200, 400, 100);
        gameOverLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jDesktopPane1.add(gameOverLabel);

        Timer timer = new Timer(3000, new ActionListener() {
            int yPos = gameOverLabel.getY();

            @Override
            public void actionPerformed(ActionEvent e) {
                yPos -= 3;
                gameOverLabel.setLocation(gameOverLabel.getX(), yPos);
                if (yPos <= -judul.getHeight()) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }
    
    public void gameOver() {
        animasiGameover();

        Timer delayTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initGameName();
            }
        });
        delayTimer.setRepeats(false); 
        delayTimer.start();
    }
    
    public void recordScore(int score) {
        String gameRecord = gamecount + ". Permainan " + gamecount  + " - " + score;
        gameData.add(gameRecord); 
        updateScoreBoard();
        gamecount++;
    }

    public void updateScoreBoard() {
        int startIndex = 0;
        if (gameData.size() >= 5) {
            startIndex = gameData.size() - 5;
        }
        
        for (int i = 0; i < labelLB.length; i++) {
            labelLB[i] = new JLabel();
        }

        for (int i = 0; i < 5; i++) {
            if (startIndex + i < gameData.size()) {
                labelLB[i].setText(gameData.get(startIndex + i));
                labelLB[i].setForeground(Color.WHITE);
                Font font = new Font("Arial", Font.BOLD, 25);
                labelLB[i].setFont(font);
            } 
            else {
                labelLB[i].setText((i + 1) + ". -");
            }
        }
    }
    
    public void updateCoin(int tambahCoin) {
        coin += tambahCoin;
    }
    
    public void initShop() {
        Shop shop = new Shop(this); 
        Timer timerJudul = new Timer(10, new ActionListener() {
            int yPosJudul = judul.getY();

            @Override
            public void actionPerformed(ActionEvent e) {
                yPosJudul -= 3;
                judul.setLocation(judul.getX(), yPosJudul);
                if (yPosJudul <= -judul.getHeight()) {
                    ((Timer) e.getSource()).stop();
                    jDesktopPane1.remove(judul);
                }
            }
        });
        timerJudul.start();

        animateComponent(shopLabel, 1, true);
        animateComponent(playLabel, 2, false);
        animateComponent(scoreLabel, 3, false);
        animateComponent(exitLabel, 4, false);

        int delayMilliseconds = 1400;
        Timer timer = new Timer(delayMilliseconds, e -> {
            panggilShop(shop); 
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void panggilShop(Shop shop) {
        judulShop = new JLabel();
        judulShop.setText("SHOP");
        Font font = new Font("Arial", Font.BOLD, 60);
        judulShop.setForeground(Color.WHITE);
        judulShop.setBounds(125, 30, 250, 100);
        judulShop.setFont(font);
        judulShop.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jDesktopPane1.add(judulShop);

        backShop = new JButton("BACK");
        backShop.setBounds(205, 500, 100, 40);
        jDesktopPane1.add(backShop);

        backShop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDesktopPane1.remove(judulShop);
                jDesktopPane1.remove(backShop);
                jDesktopPane1.remove(shop.buttonLeft);
                jDesktopPane1.remove(shop.buttonRight);
                jDesktopPane1.remove(shop.shipImageLabel);
                jDesktopPane1.remove(shop.coinCountLabel);
                jDesktopPane1.remove(shop.actionButton);
                jDesktopPane1.remove(shop.coinImageLabel);
                
                initGameName();
                jDesktopPane1.revalidate();
                jDesktopPane1.repaint();
            }
        });

        shop.initAwal(jDesktopPane1, this); 
        jDesktopPane1.revalidate();
        jDesktopPane1.repaint();
    }
    
    private void animateComponent(JLabel component, int index, boolean play) {
        Timer timer = new Timer(10, new ActionListener() {
            int yPos = component.getY();
            
            @Override
            public void actionPerformed(ActionEvent e) {
                yPos += 3; 
                component.setLocation(component.getX(), yPos);
                if (yPos >= jDesktopPane1.getHeight()) {
                    ((Timer) e.getSource()).stop();
                    jDesktopPane1.remove(component);
                    if(play){
                        
                    }
                }
            }
        });
        timer.start();
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getIndeksShip() {
        return indeksShip;
    }

    public void setIndeksShip(int indeksShip) {
        this.indeksShip = indeksShip;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new Background();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 153));
        jDesktopPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jDesktopPane1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jDesktopPane1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDesktopPane1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jDesktopPane1KeyTyped

    
    public static void main() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    // End of variables declaration//GEN-END:variables

    
}
