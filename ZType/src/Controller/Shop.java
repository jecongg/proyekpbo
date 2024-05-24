/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import ztype.Game;

/**
 *
 * @author Trevis Artagrantdy K
 */

public class Shop {
    public JLabel buttonLeft;
    public JLabel buttonRight;
    public JLabel shipImageLabel;
    public JButton actionButton; // Menggunakan JButton untuk tombol aksi
    public List<ImageIcon> shipImages;
    public int currentShipIndex;
    public JLabel coinLabel;

    public Shop() {
        shipImages = loadShipImages();
        currentShipIndex = 0;
    }

    private List<ImageIcon> loadShipImages() {
        List<ImageIcon> images = new ArrayList<>();
        images.add(new ImageIcon("src/Image/img_spaceship.png")); 
        images.add(new ImageIcon("src/Image/img_spaceship2.png")); 
        images.add(new ImageIcon("src/Image/img_spaceship3.png")); 
        return images;
    }

    public void initAwal(JDesktopPane jDesktopPane1, Game game) {
        buttonLeft = new JLabel("<");
        buttonLeft.setForeground(Color.WHITE);
        buttonLeft.setBounds(100, 200, 50, 50);
        buttonLeft.setFont(new Font("Arial", Font.BOLD, 40)); 
        buttonLeft.setHorizontalAlignment(SwingConstants.CENTER);
        jDesktopPane1.add(buttonLeft);

        buttonRight = new JLabel(">");
        buttonRight.setForeground(Color.WHITE);
        buttonRight.setBounds(350, 200, 50, 50);
        buttonRight.setFont(new Font("Arial", Font.BOLD, 40)); 
        buttonRight.setHorizontalAlignment(SwingConstants.CENTER);
        jDesktopPane1.add(buttonRight);

        shipImageLabel = new JLabel();
        shipImageLabel.setBounds(175, 170, 150, 150); 
        shipImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jDesktopPane1.add(shipImageLabel);

        actionButton = new JButton();
        actionButton.setBounds(175, 350, 150, 30);
        actionButton.setFont(new Font("Arial", Font.PLAIN, 12)); 
        jDesktopPane1.add(actionButton);
        
//        ImageIcon coinIcon = new ImageIcon("src/Image/coin.png"); // Ganti path dengan path sebenarnya
//        JLabel coinImg = new JLabel(coinIcon);
//        coinLabel.setBounds(jDesktopPane1.getWidth() - 50, 10, 30, 30); // Atur posisi dan ukuran gambar koin
//        jDesktopPane1.add(coinImg);
        
        coinLabel = new JLabel();
        String jumCoin = Integer.toString(game.getCoin());
        coinLabel.setText(jumCoin);// Ambil nilai koin dari objek Game
        coinLabel.setBounds(200, 10, 50, 30);
        coinLabel.setBackground(Color.WHITE);// Atur posisi dan ukuran label jumlah koin
        jDesktopPane1.add(coinLabel);
        
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentShipIndex == 0) {
                    
                } 
                else if (currentShipIndex == 1) {
                   
                } 
                else if (currentShipIndex == 2) {
                   
                }
            }
        });

        buttonLeft.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentShipIndex = (currentShipIndex - 1 + shipImages.size()) % shipImages.size();
                updateShipImage();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                buttonLeft.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonLeft.setForeground(Color.WHITE);
            }
        });

        buttonRight.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentShipIndex = (currentShipIndex + 1) % shipImages.size();
                updateShipImage();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                buttonRight.setForeground(Color.ORANGE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonRight.setForeground(Color.WHITE);
            }
        });

        updateShipImage();
    }

    private void updateShipImage() {
        ImageIcon shipIcon = shipImages.get(currentShipIndex);
        Image image = shipIcon.getImage().getScaledInstance(shipImageLabel.getWidth(), shipImageLabel.getHeight(), Image.SCALE_SMOOTH);
        shipImageLabel.setIcon(new ImageIcon(image));

        updateActionButtonText();
    }

    private void updateActionButtonText() {
        if (currentShipIndex == 0) {
            actionButton.setText("Equipped");
        } 
        else if (currentShipIndex == 1) {
            actionButton.setText("250 Coins");
        } 
        else if (currentShipIndex == 2) {
            actionButton.setText("500 Coins");
        }
    }
}