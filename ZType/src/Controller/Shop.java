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
    public JButton actionButton; 
    public List<ImageIcon> shipImages;
    public int currentShipIndex;
    public JLabel coinImageLabel;
    public JLabel coinCountLabel;
    public boolean[] shipTerbeli;
    public Game game;
    public int shipPakai;

    public Shop(Game game) {
        this.game= game;
        shipImages = loadShipImages();
        currentShipIndex = 0;
        shipTerbeli = new boolean[shipImages.size()];
        shipTerbeli[0] = true;
        shipPakai = 0;
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
       
        ImageIcon coinIcon = new ImageIcon("src/Image/coin.png");
        int coinWidth = 30;
        int coinHeight = 30;
        Image scaledCoinImage = coinIcon.getImage().getScaledInstance(coinWidth, coinHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledCoinIcon = new ImageIcon(scaledCoinImage);
        coinImageLabel = new JLabel(scaledCoinIcon);
        coinImageLabel.setBounds(jDesktopPane1.getWidth() - 50, 10, coinWidth, coinHeight);
        jDesktopPane1.add(coinImageLabel);
        
        coinCountLabel = new JLabel();
        String jumCoin = Integer.toString(game.getCoin());
        coinCountLabel.setText(jumCoin);
        coinCountLabel.setForeground(Color.WHITE); 
        coinCountLabel.setBounds(jDesktopPane1.getWidth() - 100, 10, 100, 40); 
        coinCountLabel.setFont(new Font("Arial", Font.BOLD, 20)); 
        jDesktopPane1.add(coinCountLabel);

        actionButton = new JButton();
        actionButton.setBounds(175, 350, 150, 30);
        actionButton.setFont(new Font("Arial", Font.PLAIN, 12)); 
        jDesktopPane1.add(actionButton);

        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleActionButtonPress();
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
        if (shipTerbeli[currentShipIndex]) {
            if (currentShipIndex == shipPakai) {
                actionButton.setText("Equipped");
            } else {
                actionButton.setText("Equip");
            }
        } else {
            if (currentShipIndex == 1) {
                actionButton.setText("250 Coins");
            } else if (currentShipIndex == 2) {
                actionButton.setText("500 Coins");
            }
        }
    }

    private void purchaseShip() {
        int cost = 0;
        if (currentShipIndex == 1) {
            cost = 250;
        } else if (currentShipIndex == 2) {
            cost = 500;
        }

        if (game.getCoin() >= cost) {
            game.updateCoin(-cost);
            shipTerbeli[currentShipIndex] = true;
            coinCountLabel.setText(Integer.toString(game.getCoin()));
            updateActionButtonText();
        } else {
            JOptionPane.showMessageDialog(null, "Not enough coins to purchase this ship.");
        }
    }
    
    private void handleActionButtonPress() {
        if (shipTerbeli[currentShipIndex]) {
            equipShip();
        } 
        else {
            purchaseShip();
        }
    }
    
    private void equipShip() {
        shipPakai = currentShipIndex;
        game.setIndeksShip(shipPakai);
        JOptionPane.showMessageDialog(null, "Ship equipped!");
        updateActionButtonText();
    }

    public int getShipPakai() {
        return shipPakai;
    }

    public void setShipPakai(int shipPakai) {
        this.shipPakai = shipPakai;
    }
    
}