
package Musuh;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Meteor extends EnemyParent {
    public Meteor(String kata, JDesktopPane pane, int x){
        this.kata=kata;
        this.x=x;
        count=0;
        y=0;
        this.pane=pane;
        init();
        turun();
    }
    
    private void init(){
        label = new JLabel(kata);
        Font font = new Font("Arial", Font.BOLD, 18);
        label.setForeground(Color.WHITE);
        label.setBackground(new Color(0,0,0,(float)0.5));
        label.setOpaque(true);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setBounds(x, y, kata.length()*13, 28);
        label.setFont(font);
        pane.add(label);
    }
    
    private void turun(){
        Timer t = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                y+=2;
                label.setLocation(label.getX(), y); 
//                if (y > -600) { 
//                    ((Timer) e.getSource()).stop();
//                    pane.remove(label);
//                }
            }
        });
        t.start();
    }
}
