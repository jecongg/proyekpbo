/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ztype;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Trevis Artagrantdy K
 */
public class Game extends javax.swing.JFrame {
    ArrayList<Component> listComponent = new ArrayList<>();
    
    public Game() {
        initComponents();
        initGameName();
        initMenuGame();
    }
    
    public void initGameName(){
        JLabel judul = new JLabel();
        Font font = new Font("Arial", Font.BOLD, 60);
        judul.setText("ZTYPE");
        judul.setForeground(Color.WHITE);
        judul.setBounds(150,30,250,100);
        judul.setFont(font);
        jDesktopPane1.add(judul);
        listComponent.add(judul);
    }
    
    //Procedure dibawah ini untuk init label bagian menu
    //Ada new game, score board, shop, exit
    //Apabila diclick maka akan berjalan sesuai menunya
    public void initMenuGame(){
        JLabel playLabel = new JLabel();
        Font fontmenu = new Font ("TImes New Roman", Font.PLAIN, 30);
        playLabel.setText("New Game");
        playLabel.setForeground(Color.WHITE);
        playLabel.setBounds(170,350, 150, 20);
        playLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        playLabel.setFont(fontmenu);
        jDesktopPane1.add(playLabel);
        listComponent.add(playLabel);
        
        JLabel scoreLabel = new JLabel();
        scoreLabel.setText("Score Board");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(170,400,150,20);
        scoreLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        scoreLabel.setFont(fontmenu);
        jDesktopPane1.add(scoreLabel);
        listComponent.add(scoreLabel);
        
        JLabel shopLabel = new JLabel();
        shopLabel.setText("Shop");
        shopLabel.setForeground(Color.WHITE);
        shopLabel.setBounds(170,450,150,20);
        shopLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        shopLabel.setFont(fontmenu);
        jDesktopPane1.add(shopLabel);
        listComponent.add(shopLabel);
        
        JLabel exitLabel = new JLabel();
        exitLabel.setText("Exit");
        exitLabel.setForeground(Color.WHITE);
        exitLabel.setBounds(170,500,150,20);
        exitLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        exitLabel.setFont(fontmenu);
        jDesktopPane1.add(exitLabel);
        listComponent.add(exitLabel);
        
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
                System.exit(0);
                temp.dispose();
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
                System.exit(0);
                temp.dispose();
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
        for (int i = 0; i < listComponent.size(); i++) {
            jDesktopPane1.remove(listComponent.get(i));
        }
        
        for (int i = 0; i < listComponent.size(); i++) {
            listComponent.remove(i);
        }
        
        jDesktopPane1.revalidate();
        jDesktopPane1.repaint();
    }
    
    public void initScoreBoard(){
        
    }
    
    public void initShop(){
        
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
