/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ztype;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
    ArrayList<Component> listComponentMenu = new ArrayList<>();
    ArrayList<Component> listComponentPlay = new ArrayList<>();
    ArrayList<Component> listComponentScore = new ArrayList<>();
    ArrayList<Component> listComponentShop = new ArrayList<>();
    
    public Game() {
        initComponents();
        initGameName();
    }
    
    public void initGameName(){
        JLabel judul = new JLabel();
        Font font = new Font("Arial", Font.BOLD, 60);
        judul.setText("ZTYPE");
        judul.setForeground(Color.WHITE);
        judul.setBounds(145,30,250,100);
        judul.setFont(font);
        jDesktopPane1.add(judul);
        listComponentMenu.add(judul);
        
        JLabel playLabel = new JLabel();
        Font fontmenu = new Font ("TImes New Roman", Font.PLAIN, 30);
        playLabel.setText("New Game");
        playLabel.setForeground(Color.WHITE);
        playLabel.setBounds(170,350, 150, 20);
        playLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        playLabel.setFont(fontmenu);
        jDesktopPane1.add(playLabel);
        listComponentMenu.add(playLabel);
        
        JLabel scoreLabel = new JLabel();
        scoreLabel.setText("Score Board");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(170,400,150,20);
        scoreLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        scoreLabel.setFont(fontmenu);
        jDesktopPane1.add(scoreLabel);
        listComponentMenu.add(scoreLabel);
        
        JLabel shopLabel = new JLabel();
        shopLabel.setText("Shop");
        shopLabel.setForeground(Color.WHITE);
        shopLabel.setBounds(170,450,150,20);
        shopLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        shopLabel.setFont(fontmenu);
        jDesktopPane1.add(shopLabel);
        listComponentMenu.add(shopLabel);
        
        JLabel exitLabel = new JLabel();
        exitLabel.setText("Exit");
        exitLabel.setForeground(Color.WHITE);
        exitLabel.setBounds(170,500,150,20);
        exitLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        exitLabel.setFont(fontmenu);
        jDesktopPane1.add(exitLabel);
        listComponentMenu.add(exitLabel);
        
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
    
    public void initPlayGame(){
//        for (int i = 0; i < listComponentMenu.size(); i++) {
//            jDesktopPane1.remove(listComponentMenu.get(i));
//        }
//        
//        for (int i = 0; i < listComponentMenu.size(); i++) {
//            listComponentMenu.remove(i);
//        }
//        
//        jDesktopPane1.revalidate();
//        jDesktopPane1.repaint();

 Timer timerJudul = new Timer(10, new ActionListener() {
            int yPosJudul = listComponentMenu.get(0).getY(); 

            @Override
            public void actionPerformed(ActionEvent e) {
                yPosJudul -= 2;
                listComponentMenu.get(0).setLocation(listComponentMenu.get(0).getX(), yPosJudul); 
                if (yPosJudul <= -listComponentMenu.get(0).getHeight()) { 
                    ((Timer) e.getSource()).stop();
                    jDesktopPane1.remove(listComponentMenu.get(0));
                }
            }
        });
        timerJudul.start(); 

        animateComponent(listComponentMenu,1);
        animateComponent(listComponentMenu,2);
        animateComponent(listComponentMenu,3);
        animateComponent(listComponentMenu,4);
        jDesktopPane1.remove(listComponentMenu.get(1));
        jDesktopPane1.remove(listComponentMenu.get(2));
        jDesktopPane1.remove(listComponentMenu.get(3));
        jDesktopPane1.remove(listComponentMenu.get(4));
       
    }
    
    public void initScoreBoard(){
//        for (int i = 0; i < listComponentMenu.size(); i++) {
//            jDesktopPane1.remove(listComponentMenu.get(i));
//        }
//        
//        for (int i = 0; i < listComponentMenu.size(); i++) {
//            listComponentMenu.remove(i);
//        }

        Timer timerJudul = new Timer(10, new ActionListener() {
            int yPosJudul = listComponentMenu.get(0).getY(); 

            @Override
            public void actionPerformed(ActionEvent e) {
                yPosJudul -= 2;
                listComponentMenu.get(0).setLocation(listComponentMenu.get(0).getX(), yPosJudul); 
                if (yPosJudul <= -listComponentMenu.get(0).getHeight()) { 
                    ((Timer) e.getSource()).stop();
                    jDesktopPane1.remove(listComponentMenu.get(0));
                }
            }
        });
        timerJudul.start(); 

        animateComponent(listComponentMenu,1);
        animateComponent(listComponentMenu,2);
        animateComponent(listComponentMenu,3);
        animateComponent(listComponentMenu,4);
        jDesktopPane1.remove(listComponentMenu.get(1));
        jDesktopPane1.remove(listComponentMenu.get(2));
        jDesktopPane1.remove(listComponentMenu.get(3));
        jDesktopPane1.remove(listComponentMenu.get(4));
        JLabel judulScore = new JLabel();
        judulScore.setText("SCORE");
        Font font = new Font("Arial", Font.BOLD, 60);
        judulScore.setForeground(Color.WHITE);
        judulScore.setBounds(120,30,250,100);
        judulScore.setFont(font);
        judulScore.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jDesktopPane1.add(judulScore);
        listComponentScore.add(judulScore);
        
        JButton back = new JButton("BACK");
        back.setBounds(205,500,100,40);
        jDesktopPane1.add(back);
        listComponentScore.add(back);
        
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listComponentScore.size(); i++) {
                    jDesktopPane1.remove(listComponentScore.get(i));
                }

                for (int i = 0; i < listComponentScore.size(); i++) {
                    listComponentScore.remove(i);
                }
                
                initGameName();
                jDesktopPane1.revalidate();
                jDesktopPane1.repaint();
            
            }  
        });
        jDesktopPane1.revalidate();
        jDesktopPane1.repaint();
    }
    
    public void initShop(){
//         for (int i = 0; i < listComponentMenu.size(); i++) {
//            jDesktopPane1.remove(listComponentMenu.get(i));
//        }
//        
//        for (int i = 0; i < listComponentMenu.size(); i++) {
//            listComponentMenu.remove(i);
//        }
        
            Timer timerJudul = new Timer(10, new ActionListener() {
            int yPosJudul = listComponentMenu.get(0).getY(); 

            @Override
            public void actionPerformed(ActionEvent e) {
                yPosJudul -= 2;
                listComponentMenu.get(0).setLocation(listComponentMenu.get(0).getX(), yPosJudul); 
                if (yPosJudul <= -listComponentMenu.get(0).getHeight()) { 
                    ((Timer) e.getSource()).stop();
                    jDesktopPane1.remove(listComponentMenu.get(0));
                }
            }
        });
        timerJudul.start(); 

        animateComponent(listComponentMenu,1);
        animateComponent(listComponentMenu,2);
        animateComponent(listComponentMenu,3);
        animateComponent(listComponentMenu,4);
        jDesktopPane1.remove(listComponentMenu.get(1));
        jDesktopPane1.remove(listComponentMenu.get(2));
        jDesktopPane1.remove(listComponentMenu.get(3));
        jDesktopPane1.remove(listComponentMenu.get(4));
    
        JLabel judulShop = new JLabel();
        judulShop.setText("SHOP");
        Font font = new Font("Arial", Font.BOLD, 60);
        judulShop.setForeground(Color.WHITE);
        judulShop.setBounds(125,30,250,100);
        judulShop.setFont(font);
        judulShop.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        jDesktopPane1.add(judulShop);
        listComponentShop.add(judulShop);
        
        JButton back = new JButton("BACK");
        back.setBounds(205,500,100,40);
        jDesktopPane1.add(back);
        listComponentShop.add(back);
        
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < listComponentShop.size(); i++) {
                    jDesktopPane1.remove(listComponentShop.get(i));
                }

                for (int i = 0; i < listComponentShop.size(); i++) {
                    listComponentShop.remove(i);
                }
                
                initGameName();
                jDesktopPane1.revalidate();
                jDesktopPane1.repaint();
            
            }  
        });
        jDesktopPane1.revalidate();
        jDesktopPane1.repaint();
    }
    
    
    private void animateComponent(ArrayList<Component> listComponent, int index) {
        Timer timer = new Timer(10, new ActionListener() {
            int yPos = listComponent.get(index).getY();

            @Override
            public void actionPerformed(ActionEvent e) {
                yPos += 2; 
                listComponent.get(index).setLocation(listComponent.get(index).getX(), yPos);
                if (yPos >= jDesktopPane1.getHeight()) {
                    ((Timer) e.getSource()).stop();
                    jDesktopPane1.remove(listComponent.get(index));
                }
            }
        });
        timer.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new Background();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 153));

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
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
