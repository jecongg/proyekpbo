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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Play <T extends PesawatParent> {
    T player;
    JDesktopPane panel;
    JFrame frame;
    int wave, jumlahMeteor, jumlahPesawatBiasa, jumlahPesawatBesar;
    ArrayList<EnemyParent> listEnemy = new ArrayList<>();
    EnemyParent current;
    ArrayList<String> kataMeteor;
    ArrayList<String> kataPesawat;

    public Play(T player, JDesktopPane panel, JFrame frame){
        kataMeteor=new ArrayList<>();
        kataPesawat=new ArrayList<>();
        this.player = player;
        this.panel = panel;
        this.frame = frame;
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
    
    public void initAwal(){
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
