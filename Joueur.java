import java.util.Scanner;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.*;
public class Joueur{
    private String pseudos;
    private int score=0;
    private tuile deck;
    private boolean ia;
    
    
    public Joueur(){ 
            Scanner sc= new Scanner(System.in);
         //do{
           System.out.println("es ce un joueur ou une ia? (0 pour ia, 1 pour joueur )");
            String a= sc.nextLine();
            if(a.equals("0"))ia=true;if(a.equals("1")) ia=false;  
         //}while(a!="0" || a!="1");
            System.out.println("quel est son pseudo?");
            this.pseudos= sc.nextLine();
            this.deck=null; 
    }
    public Joueur(String a,boolean b){
        pseudos=a;
        ia=b;
        deck=null;
    }
    public int getscore(){
        return this.score;
    }
    public void addScore(int a){
        score+=a;
    }
    public tuile getDeck(){
        return this.deck;
    }
    
    public String getps(){
        return pseudos;
    }
    public void setDeck(tuile a){
        this.deck=a;
    }
    public boolean getIa(){
        return ia;
    }
    public void afficher(){
        System.out.println("score de "+pseudos+": "+this.score);
        if(deck!=null){
            deck.afficher();
        }else{
            System.out.println("pas de tuile");
        }
    }
    public void piocher(){
        int a= new Random().nextInt(3);
        int b= new Random().nextInt(3);
        int c= new Random().nextInt(3);
        triple d=new triple(a, b, c);
        a= new Random().nextInt(3);
        b= new Random().nextInt(3);
        c= new Random().nextInt(3);
        triple h=new triple(a,b,c);
        a= new Random().nextInt(3);
        b= new Random().nextInt(3);
        c= new Random().nextInt(3);
        triple bas=new triple(a,b,c);
        a= new Random().nextInt(3);
        b= new Random().nextInt(3);
        c= new Random().nextInt(3);
        triple g=new triple(a,b,c);
        tuile no=new tuile(h, bas, g, d);
        this.deck=no;
    }
    public void rotate(){
        tuile b=new tuile(deck.getgauche(), deck.getdroite(), deck.getbas(), deck.gethaut());
        deck=b;
    }
}