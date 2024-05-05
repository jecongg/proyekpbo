/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Musuh.EnemyParent;
import Pesawat.PesawatParent;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Play <T extends PesawatParent> {
    T player;
    JDesktopPane panel;
    JFrame frame;
    int wave, jumlahAsteroid, jumlahPesawatBiasa, jumlahPesawatBesar;
    ArrayList<EnemyParent> listEnemy = new ArrayList<>();
    EnemyParent current;

    public Play(T player, JDesktopPane panel, JFrame frame){
        this.player = player;
        this.panel = panel;
        this.frame = frame;
        wave=1;
        current=null;
        initAwal();
    }
    
    public void initAwal(){
        frame.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                frameTyped(evt);
            }
        });
    }
    
    private void frameTyped(KeyEvent evt){
        System.out.println(evt.getKeyChar());
    }
    
//    private void 
    
    public void mulaiPermainan(){
        boolean run=true;
        while(run){
            
        }
    }
}
