import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.MouseEvent;
import java.awt.*;
public class tuile{
    private triple haut;
    private triple bas;
    private triple gauche;
    private triple droite;
    public Domino domino;
    public boolean vraietuile;

    public tuile(triple h,triple b,triple g,triple d){
        haut=h;
        bas=b;
        gauche=g;
        droite=d;
        vraietuile=true;
        domino=new Domino(this);
    }
    public tuile(){
        vraietuile=false;
        domino=new Domino(null);
    }
    public triple gethaut(){
        return this.haut;
    }
    public triple getbas(){
        return this.bas;
    }
    public triple getgauche(){
        return this.gauche;
    }
    public triple getdroite(){
        return this.droite;
    }
    public void afficher(){
        System.out.println(" "+this.haut.nbr[0]+" "+this.haut.nbr[1]+" "+this.haut.nbr[2]+" ");
        System.out.println(this.gauche.nbr[0]+"     "+this.droite.nbr[0]);
        System.out.println(this.gauche.nbr[1]+"     "+this.droite.nbr[1]);
        System.out.println(this.gauche.nbr[2]+"     "+this.droite.nbr[2]);
        System.out.println(" "+this.bas.nbr[0]+" "+this.bas.nbr[1]+" "+this.bas.nbr[2]+" ");
    }
    public void rotation(){
        triple a=gauche;triple b=droite;triple c=haut;triple d=bas;
        this.haut=a;
        this.droite=c;
        this.gauche=d;
        this.bas=b;
        this.domino.removeAll();this.domino.revalidate();this.domino.repaint();
        this.domino=new Domino(this);
    }
        public class Domino extends JPanel {

            public Domino(tuile a){
                    this.setLayout(new GridLayout(5,5));
                    this.setBounds(0, 0, 10,10);
                if(a!=null){
                    
                    JPanel b=new JPanel();b.setBackground(Color.GRAY);
                    JPanel c=new JPanel();JLabel c1=new JLabel(String.valueOf(a.haut.nbr[0]));c.add(c1);
                    JPanel d=new JPanel();JLabel d1=new JLabel(String.valueOf(a.haut.nbr[1]));d.add(d1);
                    JPanel e=new JPanel();JLabel e1=new JLabel(String.valueOf(a.haut.nbr[2]));e.add(e1);
                    JPanel f=new JPanel();f.setBackground(Color.GRAY);
                    this.add(b);this.add(c);this.add(d);this.add(e);this.add(f);

                    JPanel g=new JPanel();JLabel g1=new JLabel(String.valueOf(a.gauche.nbr[0]));g.add(g1);
                    JPanel h=new JPanel();h.setBackground(Color.GRAY);
                    JPanel i=new JPanel();i.setBackground(Color.GRAY);
                    JPanel j=new JPanel();j.setBackground(Color.GRAY);
                    JPanel k=new JPanel();JLabel k1=new JLabel(String.valueOf(a.droite.nbr[0]));k.add(k1);
                    this.add(g);this.add(h);this.add(i);this.add(j);this.add(k);


                    g=new JPanel();g1=new JLabel(String.valueOf(a.gauche.nbr[1]));g.add(g1);
                    h=new JPanel();h.setBackground(Color.GRAY);
                    i=new JPanel();i.setBackground(Color.GRAY);
                    j=new JPanel();j.setBackground(Color.GRAY);
                    k=new JPanel(); k1=new JLabel(String.valueOf(a.droite.nbr[1]));k.add(k1);
                    this.add(g);this.add(h);this.add(i);this.add(j);this.add(k);


                    g=new JPanel(); g1=new JLabel(String.valueOf(a.gauche.nbr[2]));g.add(g1);
                    h=new JPanel();h.setBackground(Color.GRAY);
                    i=new JPanel();i.setBackground(Color.GRAY);
                    j=new JPanel();j.setBackground(Color.GRAY);
                    k=new JPanel(); k1=new JLabel(String.valueOf(a.droite.nbr[2]));k.add(k1);
                    this.add(g);this.add(h);this.add(i);this.add(j);this.add(k);

                    b=new JPanel();b.setBackground(Color.GRAY);
                    c=new JPanel(); c1=new JLabel(String.valueOf(a.bas.nbr[0]));c.add(c1);
                    d=new JPanel(); d1=new JLabel(String.valueOf(a.bas.nbr[1]));d.add(d1);
                    e=new JPanel(); e1=new JLabel(String.valueOf(a.bas.nbr[2]));e.add(e1);
                    f=new JPanel();f.setBackground(Color.GRAY);
                    this.add(b);this.add(c);this.add(d);this.add(e);this.add(f);
                
                }else{
                    JPanel b=new JPanel();b.setBackground(Color.GRAY);
                    JPanel c=new JPanel();JLabel c1=new JLabel("x");c.add(c1);
                    JPanel d=new JPanel();JLabel d1=new JLabel("x");d.add(d1);
                    JPanel e=new JPanel();JLabel e1=new JLabel("x");e.add(e1);
                    JPanel f=new JPanel();f.setBackground(Color.GRAY);
                    this.add(b);this.add(c);this.add(d);this.add(e);this.add(f);

                    JPanel g=new JPanel();JLabel g1=new JLabel("x");g.add(g1);
                    JPanel h=new JPanel();h.setBackground(Color.GRAY);
                    JPanel i=new JPanel();i.setBackground(Color.GRAY);
                    JPanel j=new JPanel();j.setBackground(Color.GRAY);
                    JPanel k=new JPanel();JLabel k1=new JLabel("x");k.add(k1);
                    this.add(g);this.add(h);this.add(i);this.add(j);this.add(k);


                    g=new JPanel();g1=new JLabel("x");g.add(g1);
                    h=new JPanel();h.setBackground(Color.GRAY);
                    i=new JPanel();i.setBackground(Color.GRAY);
                    j=new JPanel();j.setBackground(Color.GRAY);
                    k=new JPanel(); k1=new JLabel("x");k.add(k1);
                    this.add(g);this.add(h);this.add(i);this.add(j);this.add(k);


                    g=new JPanel(); g1=new JLabel("x");g.add(g1);
                    h=new JPanel();h.setBackground(Color.GRAY);
                    i=new JPanel();i.setBackground(Color.GRAY);
                    j=new JPanel();j.setBackground(Color.GRAY);
                    k=new JPanel(); k1=new JLabel("x");k.add(k1);
                    this.add(g);this.add(h);this.add(i);this.add(j);this.add(k);

                    b=new JPanel();b.setBackground(Color.GRAY);
                    c=new JPanel(); c1=new JLabel("x");c.add(c1);
                    d=new JPanel(); d1=new JLabel("x");d.add(d1);
                    e=new JPanel(); e1=new JLabel("x");e.add(e1);
                    f=new JPanel();f.setBackground(Color.GRAY);
                    this.add(b);this.add(c);this.add(d);this.add(e);this.add(f);
                }
                
            }   
            
            
           
        }
       

}
