import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.*;


public class menu extends JFrame {

    public menu(){
        
        this.setSize(800, 800);this.setVisible(true);this.setLayout(new GridLayout(1,2));this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JButton carcassone=new JButton("lancer une partie de Carcassone?");
        JButton domino=new JButton("lancer une partie de Domino?");
        this.add(carcassone);this.add(domino);
        domino.addActionListener(event -> { 
            this.getContentPane().removeAll();this.setLayout(new GridLayout(1,2));
            JButton graphique=new JButton("lancer une version graphique");
            JButton terminal=new JButton("lancer une partie de terminal");
            this.add(terminal);this.add(graphique);this.revalidate();this.repaint();

            graphique.addActionListener(event1 -> { 
            this.dispose();jeux g=new jeux(false);
        
         });
         terminal.addActionListener(event1 -> { 
            this.dispose();jeux g=new jeux(true);
        
         });
         
         });
         carcassone.addActionListener(event -> { 
            this.dispose();
            Jeu g=new Jeu();
        
         });
    }
    public static void main(String []args){
        javax.swing.SwingUtilities.invokeLater(
        new Runnable() {
            public void run() { /*Votre code ici*/ 
                menu a=new menu();
            }
        }
        );
    }
   
}