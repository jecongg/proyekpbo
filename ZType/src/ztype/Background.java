/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ztype;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author Trevis Artagrantdy K
 */
public class Background extends JDesktopPane{

//    @Override
//    protected void paintComponent(Graphics g) {
//         Graphics2D grafik = (Graphics2D) g.create();
//         Image img = new ImageIcon(getClass().getResource("/Image/Background.jpg")).getImage();
//         grafik.drawImage(img, 0, 0, getWidth(),getHeight(),null);
//         grafik.dispose();
//    }
    private int yPos = 0; // posisi Y untuk menggambar background
    private Image background;
    public Background() {
        
        background = new ImageIcon(getClass().getResource("/Image/tes background.jpg")).getImage();
        Timer timer = new Timer(10, new ActionListener() { // Timer untuk menggerakkan background setiap 10 milidetik
            @Override
            public void actionPerformed(ActionEvent e) {
                yPos--; // Pergerakan ke atas
                if (yPos <= -getHeight()) {
                    yPos = 0; // Kembali ke bawah ketika mencapai ujung atas
                }
                repaint(); // Memanggil paintComponent untuk menggambar ulang background
            }
        });
        timer.start(); // Memulai timer
    }

    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Gambar background di posisi yPos
        g.drawImage(background, 0, yPos, getWidth(), getHeight(), this);
        // Gambar background lagi di bawah ketika mencapai ujung atas
        if (yPos < 0) {
            g.drawImage(background, 0, yPos + getHeight(), getWidth(), getHeight(), this);
        }
    }

//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(800, 600); // Ubah sesuai kebutuhan Anda
//    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Moving Background");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(new MovingBackground());
//        frame.pack();
//        frame.setVisible(true);
//    }
    
}
