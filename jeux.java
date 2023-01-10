import java.util.Scanner;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.*;
public class jeux{
    public tuile[][] plateau;
    public Joueur[] participant;
    public int tailleSac;
    public JFrame frame;
    public static void main(String[] args) {
        
        jeux a=new jeux(true);
    }

    public jeux(boolean terminal){
        plateau=new tuile[9][9];
        triple a=new triple(new Random().nextInt(3), new Random().nextInt(3),new Random().nextInt(3));
        triple b=new triple(new Random().nextInt(3), new Random().nextInt(3),new Random().nextInt(3));
        triple c=new triple(new Random().nextInt(3), new Random().nextInt(3),new Random().nextInt(3));            
        triple d=new triple(new Random().nextInt(3), new Random().nextInt(3),new Random().nextInt(3));
        
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                plateau[i][j]=new tuile();
            }
        }plateau[4][4]=new tuile(a, b, c, d);
        //afficher();
        if(terminal){
            System.out.println("nombre de joueur?");
            Scanner sc= new Scanner(System.in);
            participant=new Joueur[sc.nextInt()];
            for(int i=0;i<participant.length;i++){
                participant[i]=new Joueur();
            }
            System.out.println("taille du sac?");
            tailleSac=sc.nextInt();
            
            while(tailleSac>0 && !plateauRempli()){
                for(int i=0;i<participant.length;i++){
                    //System.out.println(participant[i].getIa());
                    if (tailleSac==0 || plateauRempli()) break;
                    if(!participant[i].getIa()){
                        participant[i].piocher();tailleSac=tailleSac-1;
                        participant[i].afficher();
                        afficher();
                        System.out.println("vous avez le choix entre jouer votre tuile(ecrire j), la défaussez (ecrire d) ou tourner la carte sur la droite (ecrire r)");
                        String s=sc.nextLine();
                        while(!s.equals("j") && !s.equals("d")){ 
                            //System.out.println(s);
                            //System.out.println(!s.equals("j"));
                            //System.out.println(!s.equals("d"));
                            if(s.equals("r")){
                                participant[i].rotate();
                                participant[i].afficher();
                                System.out.println("vous avez le choix entre jouer votre tuile(ecrire j), la défaussez (ecrire d) ou retourner la carte sur la droite (ecrire r)");
                                s=sc.nextLine();
                            }else{
                                System.out.println("vous n'avez pas écrit un choix connu veuillez réécrire");
                            s=sc.nextLine();
                            }
                            
                        }
                        if(s.equals("j")){ //s.equals("j")
                            System.out.println("donnez la ligne (sachant que la premiére ligne est 0");
                            int x=sc.nextInt();
                            System.out.println("donnez la colonne (sachant que la premiére colonne est 0");
                            int y=sc.nextInt();
                            while( !estPossible(x,y) || plateau[x][y].vraietuile ||!tuileAutour(x, y) || !jouable(x, y, participant[i])){
                                System.out.println("votre tuile ne correspond pas voulez vous rejouez (ecrire j), défaussez cette carte(ecrire d) ou la tourner(ecrire r) ? ");
                                s=sc.nextLine();
                                if(s.equals("j")){
                                    System.out.println("donnez la ligne (sachant que la premiére ligne est 0");
                                    x=sc.nextInt();
                                    System.out.println("donnez la colonne (sachant que la premiére colonne est 0");
                                    y=sc.nextInt(); 
                                }
                                if(s.equals("d")){ //s.equals("d")
                                    break;
                                }
                                if(s.equals("r")){
                                    participant[i].rotate();participant[i].afficher();
                                }
                            }
                            if(s.equals("j")){
                                plateau[x][y]=participant[i].getDeck();
                                editScore(x, y, participant[i]);
                                participant[i].setDeck(null);
                            }
                            if(s.equals("d")) participant[i].setDeck(null);
                        }else{
                            participant[i].setDeck(null);
                            System.out.println("vous venez de défaussez la carte participant du nom de :"+participant[i].getps()+" au tour du joueur suivant");
                        }
                        participant[i].afficher();
                    }else{ // cas ou ce n'est pas un joueur mais une ia
                    participant[i].piocher();tailleSac=tailleSac-1;
                    jeuxIa(participant[i]);
                    afficher();
                    participant[i].afficher();
                    }
                    
                }
            }
            classement(true);
        }else{
            
            
                frame=new JFrame();
                frame.setSize(1450,800);frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);frame.setLayout(new GridLayout(1,3));
                Slidershow o=new Slidershow(1, 5,true);frame.add(o);
                Slidershow p=new Slidershow(1, 100,false);frame.add(p);JButton t=new JButton("enregistrer");
                frame.add(t);
                t.addActionListener(event -> { 
                    tailleSac=p.getValue();System.out.println(tailleSac);
                    participant=new Joueur[o.getValue()];System.out.println(participant.length);
                    frame.getContentPane().removeAll();frame.revalidate();frame.repaint();
                    createJoeur(participant.length,participant);
                    System.out.println("test 1");
                    
                }); 
                
                
             
             
            
         

        }
        

    }
    public void tour(int a){ // pour l'interface graphique seulement
        //System.out.println(tailleSac);
        //System.out.println(tailleSac);
        frame.getContentPane().removeAll();
        if(tailleSac>0 && !plateauRempli()){
            if(participant[a].getIa()==false){
                frame.setLayout(new GridLayout(1,2));
                participant[a].piocher();tailleSac=tailleSac-1;
                JPanel d=new JPanel();JPanel g=new JPanel();
                frame.add(d);frame.add(g);d.setLayout(new GridLayout(9,9));
                remplir(d);profilPlayer(participant[a], g);
                JButton jouer=new JButton("jouer");g.add(jouer,BorderLayout.WEST);
                JButton défaussez=new JButton("défaussez");g.add(défaussez,BorderLayout.EAST);g.revalidate();g.repaint();
                frame.getContentPane().revalidate();d.revalidate();d.repaint();
                jouer.addActionListener(event -> { 
                    g.removeAll();
                    BorderLayout border=new BorderLayout();
                    g.setLayout(border);
                    JTextArea s=new JTextArea("veuillez écrire ici la colonne ou vous voulez placer votre tuile(sachant que la premiére colonne est la colonne 0)");JTextArea o=new JTextArea("veuillez écrire ici la ligne ou vous voulez placer votre tuile(sachant que la premiére colonne est la ligne 0)");
                    g.add(s,BorderLayout.NORTH);g.add(o,BorderLayout.SOUTH);
                    JPanel f=new JPanel();f.setLayout(new GridLayout(2,1));
                    f.add(participant[a].getDeck().domino);JButton jouer2=new JButton("jouer vos coordonnés(doivent être remplis au préalable)");f.add(jouer2);
                    g.add(f,BorderLayout.CENTER);
                    s.setPreferredSize(new Dimension(700,100));o.setPreferredSize(new Dimension(700,100));
                    JButton rotation=new JButton("rotation");g.add(rotation,BorderLayout.WEST);
                    JButton défaussez2=new JButton("défaussez");g.add(défaussez2,BorderLayout.EAST);
                    g.revalidate();g.repaint(); 
                    défaussez2.addActionListener(event1 -> { 
                        if(a==participant.length-1){
                            tour(0);
                        }else{
                            tour(a+1);
                        }
                    });
                    rotation.addActionListener(event1 -> { 
                        participant[a].rotate();
                        f.removeAll();f.setLayout(new GridLayout(2,1));
                        f.add(participant[a].getDeck().domino);f.add(jouer2);
                        f.revalidate();f.repaint();
                        //g.add(f,BorderLayout.CENTER);g.revalidate();g.repaint();
                    });
                    jouer2.addActionListener(event1 -> { 
                        int x=Integer.parseInt(o.getText());
                        int y=Integer.parseInt(s.getText());
                        if(plateau[x][y].vraietuile || !estPossible(x,y) || !tuileAutour(x, y) || !jouable(x, y, participant[a])){
                        
                        }else{
                            plateau[x][y]=participant[a].getDeck();
                            editScore(x, y, participant[a]);
                            participant[a].setDeck(null);
                                    if(a==participant.length-1){
                                tour(0);
                            }else{
                                tour(a+1);
                            }
                        }
                    });
                }); 
                défaussez.addActionListener(event -> { 
                    if(a==participant.length-1){
                        tour(0);
                    }else{
                        tour(a+1);
                    }
                });
            }else{
                participant[a].piocher();tailleSac=tailleSac-1;
                    jeuxIa(participant[a]);
                    if(a==participant.length-1){
                        tour(0);
                    }else{
                        tour(a+1);
                    }
            }
        }else{
            //System.out.print("fin");
            classement(false);
             
        }  
        
    }

    public void profilPlayer(Joueur a,JPanel b){ // interface graphique uniquement 
        b.removeAll();b.setLayout(new BorderLayout());
        JLabel s=new JLabel("pseudos du joueur: "+a.getps());JLabel o=new JLabel("score: "+String.valueOf(a.getscore()));
        s.setHorizontalAlignment(SwingConstants.CENTER);
        o.setHorizontalAlignment(SwingConstants.CENTER);
        b.add(s,BorderLayout.NORTH);b.add(o,BorderLayout.SOUTH);
        JPanel f=a.getDeck().domino;
        b.add(f,BorderLayout.CENTER);
        f.setPreferredSize(new Dimension(200,500));s.setPreferredSize(new Dimension(700,100));o.setPreferredSize(new Dimension(700,100));
        b.revalidate();b.repaint(); 
    }

    public boolean plateauRempli(){// condition d'arrêt du jeu
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(!plateau[i][j].vraietuile)return false;
            }
        }
        return true;
    }
    public void afficher(){// affichage terminal uniquement
        for(int i=0;i<plateau.length;i++){
            for(int j=0;j<plateau[i].length;j++){//for pour afficher le haut des tuiles 
                if(plateau[i][j].vraietuile){
                    System.out.print(" "+plateau[i][j].gethaut().nbr[0]+" "+plateau[i][j].gethaut().nbr[1]+" "+plateau[i][j].gethaut().nbr[2]+" ");
                }else{
                    System.out.print(" x x x ");
                }
            }
            System.out.println();
            for(int j=0;j<plateau[i].length;j++){//le premier triple de gauche et droite
                if(plateau[i][j].vraietuile){
                    System.out.print(plateau[i][j].getgauche().nbr[0]+"     "+plateau[i][j].getdroite().nbr[0]);
                }else{
                    System.out.print("x     x");
                }
            }
            System.out.println();
            for(int j=0;j<plateau[i].length;j++){//le deuxiéme triple de gauche et droite
                if(plateau[i][j].vraietuile){
                    System.out.print(plateau[i][j].getgauche().nbr[1]+"     "+plateau[i][j].getdroite().nbr[1]);
                }else{
                    System.out.print("x     x");
                }
            }
            System.out.println();
            for(int j=0;j<plateau[i].length;j++){//le dernier triple de gauche et droite
                if(plateau[i][j].vraietuile){
                    System.out.print(plateau[i][j].getgauche().nbr[2]+"     "+plateau[i][j].getdroite().nbr[2]);
                }else{
                    System.out.print("x     x");
                }
            }
            
            System.out.println();
            for(int j=0;j<plateau[i].length;j++){//le triple bas de la tuile
                if(plateau[i][j].vraietuile){
                    System.out.print(" "+plateau[i][j].getbas().nbr[0]+" "+plateau[i][j].getbas().nbr[1]+" "+plateau[i][j].getbas().nbr[2]+" ");
                }else{
                    System.out.print(" x x x ");
                }
                
            }
            System.out.println();
        }
    }
    public void remplir(JPanel b){//interface graphique uniquement
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                b.add(plateau[i][j].domino);
            }
        }
    }
    public void createJoeur(int b,Joueur[] c){//interface grahique uniquement
        if(b>0){  
            int e=c.length-b;
            frame.setLayout(new GridLayout(3,1));
            JButton i=new JButton("ia");JButton joueur=new  JButton("joueur");JTextArea d=new JTextArea("veuillez écrire ici le pseudos du participant");
            
            frame.add(d);frame.add(joueur);frame.add(i);frame.revalidate();frame.repaint();
            i.addActionListener(event -> { 
                c[e]=new Joueur(d.getText(),true);
                frame.getContentPane().removeAll();frame.validate();frame.repaint();
                createJoeur(b-1, c);
             });
             joueur.addActionListener(event -> { 
                c[e]=new Joueur(d.getText(),false);
                frame.getContentPane().removeAll();frame.validate();frame.repaint();
                createJoeur( b-1, c);
             });
        } 
        if(b==0){
            tour(0);
        } 
    }
    public boolean estPossible(int i,int j){// pour les deux modes
        if(i<0 || j<0 || i>plateau.length || j>plateau.length){
            return false;
        }
        return true;
    }
    public boolean tuileAutour(int i,int j){//pour les deux modes de jeux
        if((i-1<0 && j-1<0)){//coin en haut a gauche
            if( plateau[i+1][j].vraietuile || plateau[i][j+1].vraietuile ) return true;
        }
        if(j<plateau.length-1&&j>0&&i-1<0){ //bordure en haut hors les deux coins du hauts
            if(  plateau[i+1][j].vraietuile || plateau[i][j+1].vraietuile || plateau[i][j-1].vraietuile) return true;
        }
        if((i-1<0 && j+1>plateau.length)){ //coin en haut a droite du plateau
            if( plateau[i+1][j].vraietuile || plateau[i][j-1].vraietuile ) return true;
        }
        if(( i<plateau.length-1 && i>0 && j+1>plateau.length)){//bordure droite hors coin droit
            if(  plateau[i+1][j].vraietuile || plateau[i][j-1].vraietuile || plateau[i-1][j].vraietuile) return true;
        }
        if((i+1>plateau.length && j+1>plateau.length)){//coin en bas a droite
            if(  plateau[i-1][j].vraietuile || plateau[i][j-1].vraietuile ) return true;
        }
        if((j<plateau.length&&j>0&&i+1>plateau.length )){//bordure du bas hors coin du bas
            if(  plateau[i-1][j].vraietuile || plateau[i][j+1].vraietuile ||  plateau[i][j-1].vraietuile) return true;
        }
        if((i+1>plateau.length && j-1<0)){//coin en bas a gauche
            if(  plateau[i-1][j].vraietuile || plateau[i][j+1].vraietuile ) return true;
        }
        if((i<plateau.length-1&&i>0 &&j-1<0)){//bordure de gauche hors coin de gauche
            if(  plateau[i+1][j].vraietuile || plateau[i][j+1].vraietuile || plateau[i-1][j].vraietuile) return true;
        }
        
        if((i>0 && i<plateau.length-1 && j>0 && j<plateau.length-1) && (plateau[i+1][j].vraietuile || plateau[i-1][j].vraietuile || plateau[i][j+1].vraietuile || plateau[i][j-1].vraietuile)){// tout l'intérieur du plateau sauf coin et bordure
            return true;
        }
        return false;
    }
    public boolean jouable(int i,int j,Joueur a){//pour les deux modes
        if((i-1<0 && j-1<0)){//coin en haut a gauche
            if(( (plateau[i][j+1].vraietuile && a.getDeck().getdroite().equalTriple(plateau[i][j+1].getgauche())) || !plateau[i][j+1].vraietuile || j==plateau.length-1)
            && ((plateau[i+1][j].vraietuile && a.getDeck().getbas().equalTriple(plateau[i+1][j].gethaut())) || !plateau[i+1][j].vraietuile || i==plateau.length-1)){
                return true;
            }else{
                return false;
            }
        }
        if(j<plateau.length-1&&j>0&&i-1<0){ //bordure en haut hors les deux coins du hauts
            if(((plateau[i][j+1].vraietuile && a.getDeck().getdroite().equalTriple(plateau[i][j+1].getgauche())) || !plateau[i][j+1].vraietuile || j==plateau.length-1)
            && ((plateau[i+1][j].vraietuile && a.getDeck().getbas().equalTriple(plateau[i+1][j].gethaut())) || !plateau[i+1][j].vraietuile || i==plateau.length-1)
            && ((plateau[i][j-1].vraietuile && a.getDeck().getgauche().equalTriple(plateau[i][j-1].getdroite())) || !plateau[i][j-1].vraietuile || j==0)){
                return true;
            }else{
                return false;
            } 
        }
        if((i-1<0 && j+1>plateau.length)){ //coin en haut a droite du plateau
            if(((plateau[i+1][j].vraietuile && a.getDeck().getbas().equalTriple(plateau[i+1][j].gethaut())) || !plateau[i+1][j].vraietuile || i==plateau.length-1)
            && ((plateau[i][j-1].vraietuile && a.getDeck().getgauche().equalTriple(plateau[i][j-1].getdroite())) || !plateau[i][j-1].vraietuile || j==0)){
                return true;
            }else{
                return false;
            }
        }
        if(( i<plateau.length-1 && i>0 && j+1>plateau.length)){//bordure droite hors coin droit
            if( ((plateau[i][j-1].vraietuile && a.getDeck().getgauche().equalTriple(plateau[i][j-1].getdroite())) || !plateau[i][j-1].vraietuile || j==0) //gauche
            && ( (plateau[i-1][j].vraietuile && a.getDeck().gethaut().equalTriple(plateau[i-1][j].getbas())) || !plateau[i-1][j].vraietuile || i==0) //haut
            && ( (plateau[i+1][j].vraietuile && a.getDeck().getbas().equalTriple(plateau[i+1][j].gethaut())) || !plateau[i+1][j].vraietuile || i==plateau.length-1)){
                return true;
            }else{
                return false;
            }
        }
        if((i+1>plateau.length && j+1>plateau.length)){//coin en bas a droite
            if(( (plateau[i][j-1].vraietuile && a.getDeck().getgauche().equalTriple(plateau[i][j-1].getdroite())) || !plateau[i][j-1].vraietuile || j==0) //gauche
            && ( (plateau[i-1][j].vraietuile && a.getDeck().gethaut().equalTriple(plateau[i-1][j].getbas())) || !plateau[i-1][j].vraietuile || i==0)){
                return true;
            }else{
                return false;
            }  
        }
        if((j<plateau.length&&j>0&&i+1>plateau.length )){//bordure du bas hors coin du bas
            if(((plateau[i][j+1].vraietuile && a.getDeck().getdroite().equalTriple(plateau[i][j+1].getgauche())) || !plateau[i][j+1].vraietuile || j==plateau.length-1) //droite
            && ( (plateau[i][j-1].vraietuile && a.getDeck().getgauche().equalTriple(plateau[i][j-1].getdroite())) || !plateau[i][j-1].vraietuile || j==0) //gauche
            && ( (plateau[i-1][j].vraietuile && a.getDeck().gethaut().equalTriple(plateau[i-1][j].getbas())) || !plateau[i-1][j].vraietuile || i==0)  ){
                return true;
            }else{
                return false;
            } 
        }
        if((i+1>plateau.length && j-1<0)){//coin en bas a gauche
            if(( (plateau[i][j+1].vraietuile && a.getDeck().getdroite().equalTriple(plateau[i][j+1].getgauche())) || !plateau[i][j+1].vraietuile || j==plateau.length-1)   
            && ( (plateau[i-1][j].vraietuile && a.getDeck().gethaut().equalTriple(plateau[i-1][j].getbas())) || !plateau[i-1][j].vraietuile || i==0)){
                return true;
            }else{
                return false;
            }
        }
        if((i<plateau.length-1&&i>0 &&j-1<0)){//bordure de gauche hors coin de gauche
            if(( (plateau[i][j+1].vraietuile && a.getDeck().getdroite().equalTriple(plateau[i][j+1].getgauche())) || !plateau[i][j+1].vraietuile || j==plateau.length-1)
            && ( (plateau[i-1][j].vraietuile && a.getDeck().gethaut().equalTriple(plateau[i-1][j].getbas())) || !plateau[i-1][j].vraietuile || i==0) //haut
            && ( (plateau[i+1][j].vraietuile && a.getDeck().getbas().equalTriple(plateau[i+1][j].gethaut())) || !plateau[i+1][j].vraietuile || i==plateau.length-1)){
                return true;
            }else{
                return false;
            }
        }
        
        
        if( ( (plateau[i][j+1].vraietuile && a.getDeck().getdroite().equalTriple(plateau[i][j+1].getgauche())) || !plateau[i][j+1].vraietuile || j==plateau.length-1) //droite
             && ( (plateau[i][j-1].vraietuile && a.getDeck().getgauche().equalTriple(plateau[i][j-1].getdroite())) || !plateau[i][j-1].vraietuile || j==0) //gauche
             && ( (plateau[i-1][j].vraietuile && a.getDeck().gethaut().equalTriple(plateau[i-1][j].getbas())) || !plateau[i-1][j].vraietuile || i==0) //haut
             && ( (plateau[i+1][j].vraietuile && a.getDeck().getbas().equalTriple(plateau[i+1][j].gethaut())) || !plateau[i+1][j].vraietuile || i==plateau.length-1)){ //bas
                return true;
             }else{
                return false;
             }
    }
    public void editScore(int i,int j,Joueur a){// pour les deux modes
        if((i-1<0 && j-1<0)){//coin en haut a gauche
            if(plateau[i][j+1].vraietuile){
                a.addScore(plateau[i][j+1].getgauche().somme());
            }
            if(plateau[i+1][j].vraietuile){
                a.addScore(plateau[i+1][j].gethaut().somme());
            }
            return;
        }
        if(j<plateau.length-1&&j>0&&i-1<0){ //bordure en haut hors les deux coins du hauts
            if(plateau[i][j+1].vraietuile){
                a.addScore(plateau[i][j+1].getgauche().somme());
            }
            if(plateau[i][j-1].vraietuile){
                a.addScore(plateau[i][j-1].getdroite().somme());
            }
            if(plateau[i+1][j].vraietuile){
                a.addScore(plateau[i+1][j].gethaut().somme());
            }
            return;
        }
        if((i-1<0 && j+1>plateau.length)){ //coin en haut a droite du plateau
            if(plateau[i][j-1].vraietuile){
                a.addScore(plateau[i][j-1].getdroite().somme());
            }
            if(plateau[i+1][j].vraietuile){
                a.addScore(plateau[i+1][j].gethaut().somme());
            }
            return;
        }
        if(( i<plateau.length-1 && i>0 && j+1>plateau.length)){//bordure droite hors coin droit
            if(plateau[i][j-1].vraietuile){
                a.addScore(plateau[i][j-1].getdroite().somme());
            }
            if(plateau[i+1][j].vraietuile){
                a.addScore(plateau[i+1][j].gethaut().somme());
            }
            if(plateau[i-1][j].vraietuile){
                a.addScore(plateau[i-1][j].getbas().somme());
            }
            return;
        }
        if((i+1>plateau.length && j+1>plateau.length)){//coin en bas a droite
            if(plateau[i][j-1].vraietuile){
                a.addScore(plateau[i][j-1].getdroite().somme());
            }     
            if(plateau[i-1][j].vraietuile){
                a.addScore(plateau[i-1][j].getbas().somme());
            }
            return;
        }
        if((j<plateau.length&&j>0&&i+1>plateau.length )){//bordure du bas hors coin du bas
            if(plateau[i][j+1].vraietuile){
                a.addScore(plateau[i][j+1].getgauche().somme());
            }
            if(plateau[i][j-1].vraietuile){
                a.addScore(plateau[i][j-1].getdroite().somme());
            }
            if(plateau[i-1][j].vraietuile){
                a.addScore(plateau[i-1][j].getbas().somme());
            }
            return;
        }
        if((i+1>plateau.length && j-1<0)){//coin en bas a gauche
            if(plateau[i][j+1].vraietuile){
                a.addScore(plateau[i][j+1].getgauche().somme());
            }
            if(plateau[i-1][j].vraietuile){
                a.addScore(plateau[i-1][j].getbas().somme());
            }
           return;
        }
        if((i<plateau.length-1&&i>0 &&j-1<0)){//bordure de gauche hors coin de gauche
            if(plateau[i][j+1].vraietuile){
                a.addScore(plateau[i][j+1].getgauche().somme());
            }
            if(plateau[i+1][j].vraietuile){
                a.addScore(plateau[i+1][j].gethaut().somme());
            }
            if(plateau[i-1][j].vraietuile){
                a.addScore(plateau[i-1][j].getbas().somme());
            }
            return;
        }
        
        
        if(plateau[i][j+1].vraietuile){
            a.addScore(plateau[i][j+1].getgauche().somme());
        }
        if(plateau[i][j-1].vraietuile){
            a.addScore(plateau[i][j-1].getdroite().somme());
        }
        if(plateau[i+1][j].vraietuile){
            a.addScore(plateau[i+1][j].gethaut().somme());
        }
        if(plateau[i-1][j].vraietuile){
            a.addScore(plateau[i-1][j].getbas().somme());
        }

    }
    public void jeuxIa(Joueur a){// pour les deux modes
        for(int b=0;b<4;b++){    
            for(int i=0;i<plateau.length;i++){
                for(int j=0;j<plateau.length;j++){
                    //System.out.print(i+" "+ j);
                    if(!plateau[i][j].vraietuile && tuileAutour(i, j) && jouable(i, j, a)){
                        plateau[i][j]=a.getDeck();
                        editScore(i, j, a);
                        System.out.println(a.getps()+" a jouer");
                        return;
                        
                    }
                }
            }
            a.rotate();
        }
        a.setDeck(null);
        System.out.println(a.getps()+" n'a pas jouer");
    }
    public void classement(boolean t){
        tri_bulle(participant);
        if(t){
            System.out.println("le gagnant est :"+participant[participant.length-1].getps()+" avec u score de : " + participant[participant.length-1].getscore());
        }else{
            frame.getContentPane().removeAll();frame.revalidate();frame.repaint();
            JLabel b=new JLabel("le gagnant est : "+participant[participant.length-1].getps()+" avec un score : "+ participant[participant.length-1].getscore());frame.add(b);
            frame.revalidate();frame.repaint();;
        }
    }
    static void tri_bulle(Joueur[] tab){  
        int taille = tab.length;  
        Joueur tmp = tab[0];  
        for(int i=0; i < taille; i++) {
            for(int j=1; j < (taille-i); j++){  
                if(tab[j-1].getscore() > tab[j].getscore()){
                                 tmp = tab[j-1];  
                                 tab[j-1] = tab[j];  
                                 tab[j] = tmp;  
                }
  
            }
        }
    }
    public class Slidershow extends JPanel implements ChangeListener{
        boolean y;
        JLabel label;
        JSlider slider;
        Slidershow(int a,int b,boolean c){
            y=c;
            label=new JLabel();
            slider= new JSlider(a,b);
            this.add(slider);this.add(label);
            slider.setPreferredSize(new Dimension(400,200));
            slider.setPaintTicks(true);
            if(y){
                slider.setMinorTickSpacing(1);
                label.setText("nombre de joueur: "+slider.getValue());
            }else{
                slider.setMinorTickSpacing(10);
                label.setText("taille du sac: "+slider.getValue());
            }

            slider.setPaintLabels(true);
            slider.setPaintTrack(true);
            slider.addChangeListener(this);
        }
        public int getValue(){
            return slider.getValue();
        }
        public void stateChanged(ChangeEvent e){
            if(y){
                label.setText("nombre de joueur: "+slider.getValue());

            }else{
                label.setText("taille du sac: "+slider.getValue());

            }
            
        }
    }
    
}