import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Jeu extends JFrame{
	
	private Tuile courant;
	private Random rand = new Random();
	ArrayList<Tuile> pile = new ArrayList<Tuile>();
	JLabel tuileCourante = new JLabel(" ");
	Plateau p = new Plateau();
	JPanel grille = new JPanel(new GridBagLayout());
	static int joueur = 1;
	public static JLabel tj = new JLabel("joueur " + joueur);
	JPanel titre = new JPanel();
	private boolean ia = false;
	
	public Jeu() {
		super("Carcassone");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initPile();
		//Jeu this = new Jeu();
		int result = JOptionPane.showOptionDialog(this, "Mode de jeu : ", "Adversaire", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Humain", "IA"}, null);
		if (result == JOptionPane.NO_OPTION)
					this.ia = true;
				this.build();
				Tuile depart = this.p.getDepart();
				this.pose(depart);
				this.pioche();
	}
	
	public static void main (String [] args) {
	
		//initialisation de JFrame
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				
			}
		});
	}
	
	
	private void initPile() {
		//initialisation des tuiles
		pile.add(new Tuile(new Ville(), new Ville(), new Ville(), new Ville(), new Ville(), false, "CastleCenter0.png", "CastleCenter0.png", "CastleCenter0.png", "CastleCenter0.png", p));
		pile.add(new Tuile(new Ville(), new Ville(), new Ville(), new Champs(), new Ville(), false, "CastleCenterSide0.png", "CastleCenterSide1.png", "CastleCenterSide2.png", "CastleCenterSide3.png", p));
		pile.add(new Tuile(new Ville(), new Ville(), new Ville(), new Champs(), new Ville(), false, "CastleCenterSide0.png", "CastleCenterSide1.png", "CastleCenterSide2.png", "CastleCenterSide3.png", p));
		pile.add(new Tuile(new Ville(), new Ville(), new Ville(), new Champs(), new Ville(), false, "CastleCenterSide0.png", "CastleCenterSide1.png", "CastleCenterSide2.png", "CastleCenterSide3.png", p));
		pile.add(new Tuile(new Ville(), new Ville(), new Ville(), new Champs(), new Ville(), true, "CastleCenterSide0.png", "CastleCenterSide1.png", "CastleCenterSide2.png", "CastleCenterSide3.png", p));
		pile.add(new Tuile(new Ville(), new Ville(), new Ville(), new Route(), new Ville(), false, "CastleCenterEntry0.png", "CastleCenterEntry1.png", "CastleCenterEntry2.png", "CastleCenterEntry3.png", p));
		pile.add(new Tuile(new Ville(), new Ville(), new Ville(), new Route(), new Ville(), true, "CastleCenterEntry0.png", "CastleCenterEntry1.png", "CastleCenterEntry2.png", "CastleCenterEntry3.png", p));
		pile.add(new Tuile(new Ville(), new Ville(), new Ville(), new Route(), new Ville(), true, "CastleCenterEntry0.png", "CastleCenterEntry1.png", "CastleCenterEntry2.png", "CastleCenterEntry3.png", p));
		pile.add(new Tuile(new Ville(), new Ville(), new Champs(), new Champs(), new Ville(), false, "CastleTube0.png", "CastleTube1.png", "CastleTube2.png", "CastleTube3.png", p));
		pile.add(new Tuile(new Ville(), new Ville(), new Champs(), new Champs(), new Ville(), true, "CastleTube0.png", "CastleTube1.png", "CastleTube2.png", "CastleTube3.png", p));
		pile.add(new Tuile(new Ville(), new Ville(), new Champs(), new Champs(), new Ville(), true, "CastleTube0.png", "CastleTube1.png", "CastleTube2.png", "CastleTube3.png", p));
		pile.add(new Tuile(new Champs(), new Ville(), new Ville(), new Champs(), new Champs(), false, "CastleEdge3.png", "CastleEdge0.png", "CastleEdge1.png", "CastleEdge2.png", p));
		pile.add(new Tuile(new Champs(), new Ville(), new Ville(), new Champs(), new Champs(), false, "CastleEdge3.png", "CastleEdge0.png", "CastleEdge1.png", "CastleEdge2.png", p));
		pile.add(new Tuile(new Champs(), new Ville(), new Ville(), new Champs(), new Champs(), false, "CastleEdge3.png", "CastleEdge0.png", "CastleEdge1.png", "CastleEdge2.png", p));
		pile.add(new Tuile(new Champs(), new Ville(), new Ville(), new Champs(), new Champs(), true, "CastleEdge3.png", "CastleEdge0.png", "CastleEdge1.png", "CastleEdge2.png", p));
		pile.add(new Tuile(new Champs(), new Ville(), new Ville(), new Champs(), new Champs(), true, "CastleEdge3.png", "CastleEdge0.png", "CastleEdge1.png", "CastleEdge2.png", p));
		pile.add(new Tuile(new Route(), new Ville(), new Ville(), new Route(), new Route(), false, "CastleEdgeRoad3.png", "CastleEdgeRoad0.png", "CastleEdgeRoad1.png", "CastleEdgeRoad2.png", p));
		pile.add(new Tuile(new Route(), new Ville(), new Ville(), new Route(), new Route(), false, "CastleEdgeRoad3.png", "CastleEdgeRoad0.png", "CastleEdgeRoad1.png", "CastleEdgeRoad2.png", p));
		pile.add(new Tuile(new Route(), new Ville(), new Ville(), new Route(), new Route(), false, "CastleEdgeRoad3.png", "CastleEdgeRoad0.png", "CastleEdgeRoad1.png", "CastleEdgeRoad2.png", p));
		pile.add(new Tuile(new Route(), new Ville(), new Ville(), new Route(), new Route(), true, "CastleEdgeRoad3.png", "CastleEdgeRoad0.png", "CastleEdgeRoad1.png", "CastleEdgeRoad2.png", p));
		pile.add(new Tuile(new Route(), new Ville(), new Ville(), new Route(), new Route(), true, "CastleEdgeRoad3.png", "CastleEdgeRoad0.png", "CastleEdgeRoad1.png", "CastleEdgeRoad2.png", p));
		pile.add(new Tuile(new Champs(), new Ville(), new Ville(), new Champs(), new Champs(), false, "CastleSidesEdge0.png", "CastleSidesEdge1.png", "CastleSidesEdge2.png", "CastleSidesEdge3.png", p));
		pile.add(new Tuile(new Champs(), new Ville(), new Ville(), new Champs(), new Champs(), false, "CastleSidesEdge0.png", "CastleSidesEdge1.png", "CastleSidesEdge2.png", "CastleSidesEdge3.png", p));
		pile.add(new Tuile(new Ville(), new Ville(), new Champs(), new Champs(), new Champs(), false, "CastleSides1.png", "CastleSides0.png", "CastleSides1.png", "CastleSides0.png", p));
		pile.add(new Tuile(new Ville(), new Ville(), new Champs(), new Champs(), new Champs(), false, "CastleSides1.png", "CastleSides0.png", "CastleSides1.png", "CastleSides0.png", p));
		pile.add(new Tuile(new Ville(), new Ville(), new Champs(), new Champs(), new Champs(), false, "CastleSides1.png", "CastleSides0.png", "CastleSides1.png", "CastleSides0.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Ville(), new Champs(), new Champs(), false, "CastleWall0.png", "CastleWall1.png", "CastleWall2.png", "CastleWall3.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Ville(), new Champs(), new Champs(), false, "CastleWall0.png", "CastleWall1.png", "CastleWall2.png", "CastleWall3.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Ville(), new Champs(), new Champs(), false, "CastleWall0.png", "CastleWall1.png", "CastleWall2.png", "CastleWall3.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Ville(), new Champs(), new Champs(), false, "CastleWall0.png", "CastleWall1.png", "CastleWall2.png", "CastleWall3.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Ville(), new Champs(), new Champs(), false, "CastleWall0.png", "CastleWall1.png", "CastleWall2.png", "CastleWall3.png", p));
		pile.add(new Tuile(new Route(), new Champs(), new Ville(), new Route(), new Route(), false, "CastleWallCurveRight0.png", "CastleWallCurveRight1.png", "CastleWallCurveRight2.png", "CastleWallCurveRight3.png", p));
		pile.add(new Tuile(new Route(), new Champs(), new Ville(), new Route(), new Route(), false, "CastleWallCurveRight0.png", "CastleWallCurveRight1.png", "CastleWallCurveRight2.png", "CastleWallCurveRight3.png", p));
		pile.add(new Tuile(new Route(), new Champs(), new Ville(), new Route(), new Route(), false, "CastleWallCurveRight0.png", "CastleWallCurveRight1.png", "CastleWallCurveRight2.png", "CastleWallCurveRight3.png", p));
		pile.add(new Tuile(new Champs(), new Route(), new Ville(), new Route(), new Route(), false, "CastleWallCurveLeft0.png", "CastleWallCurveLeft1.png", "CastleWallCurveLeft2.png", "CastleWallCurveLeft3.png", p));
		pile.add(new Tuile(new Champs(), new Route(), new Ville(), new Route(), new Route(), false, "CastleWallCurveLeft0.png", "CastleWallCurveLeft1.png", "CastleWallCurveLeft2.png", "CastleWallCurveLeft3.png", p));
		pile.add(new Tuile(new Champs(), new Route(), new Ville(), new Route(), new Route(), false, "CastleWallCurveLeft0.png", "CastleWallCurveLeft1.png", "CastleWallCurveLeft2.png", "CastleWallCurveLeft3.png", p));
		pile.add(new Tuile(new Route(), new Route(), new Ville(), new Route(), new Champs(), false, "CastleWallJunction0.png", "CastleWallJunction1.png", "CastleWallJunction2.png", "CastleWallJunction3.png", p));
		pile.add(new Tuile(new Route(), new Route(), new Ville(), new Route(), new Champs(), false, "CastleWallJunction0.png", "CastleWallJunction1.png", "CastleWallJunction2.png", "CastleWallJunction3.png", p));
		pile.add(new Tuile(new Route(), new Route(), new Ville(), new Route(), new Champs(), false, "CastleWallJunction0.png", "CastleWallJunction1.png", "CastleWallJunction2.png", "CastleWallJunction3.png", p));
		pile.add(new Tuile(new Route(), new Route(), new Ville(), new Champs(), new Route(), false, "CastleWallRoad0.png", "CastleWallRoad1.png", "CastleWallRoad2.png", "CastleWallRoad3.png", p));
		pile.add(new Tuile(new Route(), new Route(), new Ville(), new Champs(), new Route(), false, "CastleWallRoad0.png", "CastleWallRoad1.png", "CastleWallRoad2.png", "CastleWallRoad3.png", p));
		pile.add(new Tuile(new Route(), new Route(), new Ville(), new Champs(), new Route(), false, "CastleWallRoad0.png", "CastleWallRoad1.png", "CastleWallRoad2.png", "CastleWallRoad3.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Champs(), new Champs(), new Abbaye(), false, "Monastery0.png", "Monastery0.png", "Monastery0.png", "Monastery0.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Champs(), new Champs(), new Abbaye(), false, "Monastery0.png", "Monastery0.png", "Monastery0.png", "Monastery0.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Champs(), new Champs(), new Abbaye(), false, "Monastery0.png", "Monastery0.png", "Monastery0.png", "Monastery0.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Champs(), new Champs(), new Abbaye(), false, "Monastery0.png", "Monastery0.png", "Monastery0.png", "Monastery0.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Champs(), new Route(), new Abbaye(), false, "MonasteryRoad0.png", "MonasteryRoad1.png", "MonasteryRoad2.png", "MonasteryRoad3.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Champs(), new Route(), new Abbaye(), false, "MonasteryRoad0.png", "MonasteryRoad1.png", "MonasteryRoad2.png", "MonasteryRoad3.png", p));
		pile.add(new Tuile(new Route(), new Route(), new Route(), new Route(), new Champs(), false, "RoadJunctionLarge0.png", "RoadJunctionLarge0.png", "RoadJunctionLarge0.png", "RoadJunctionLarge0.png", p));
		pile.add(new Tuile(new Route(), new Route(), new Champs(), new Route(), new Champs(), false, "RoadJunctionSmall0.png", "RoadJunctionSmall1.png", "RoadJunctionSmall2.png", "RoadJunctionSmall3.png", p));
		pile.add(new Tuile(new Route(), new Route(), new Champs(), new Route(), new Champs(), false, "RoadJunctionSmall0.png", "RoadJunctionSmall1.png", "RoadJunctionSmall2.png", "RoadJunctionSmall3.png", p));
		pile.add(new Tuile(new Route(), new Route(), new Champs(), new Route(), new Champs(), false, "RoadJunctionSmall0.png", "RoadJunctionSmall1.png", "RoadJunctionSmall2.png", "RoadJunctionSmall3.png", p));
		pile.add(new Tuile(new Route(), new Route(), new Champs(), new Route(), new Champs(), false, "RoadJunctionSmall0.png", "RoadJunctionSmall1.png", "RoadJunctionSmall2.png", "RoadJunctionSmall3.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Route(), new Route(), new Champs(), false, "Road0.png", "Road1.png", "Road2.png", "Road3.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Route(), new Route(), new Champs(), false, "Road0.png", "Road1.png", "Road2.png", "Road3.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Route(), new Route(), new Champs(), false, "Road0.png", "Road1.png", "Road2.png", "Road3.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Route(), new Route(), new Champs(), false, "Road0.png", "Road1.png", "Road2.png", "Road3.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Route(), new Route(), new Champs(), false, "Road0.png", "Road1.png", "Road2.png", "Road3.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Route(), new Route(), new Champs(), false, "Road0.png", "Road1.png", "Road2.png", "Road3.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Route(), new Route(), new Champs(), false, "Road0.png", "Road1.png", "Road2.png", "Road3.png", p));
		pile.add(new Tuile(new Champs(), new Champs(), new Route(), new Route(), new Champs(), false, "Road0.png", "Road1.png", "Road2.png", "Road3.png", p));
		pile.add(new Tuile(new Champs(), new Route(), new Champs(), new Route(), new Route(), false, "RoadCurve0.png", "RoadCurve1.png", "RoadCurve2.png", "RoadCurve3.png", p));
		pile.add(new Tuile(new Champs(), new Route(), new Champs(), new Route(), new Route(), false, "RoadCurve0.png", "RoadCurve1.png", "RoadCurve2.png", "RoadCurve3.png", p));
		pile.add(new Tuile(new Champs(), new Route(), new Champs(), new Route(), new Route(), false, "RoadCurve0.png", "RoadCurve1.png", "RoadCurve2.png", "RoadCurve3.png", p));
		pile.add(new Tuile(new Champs(), new Route(), new Champs(), new Route(), new Route(), false, "RoadCurve0.png", "RoadCurve1.png", "RoadCurve2.png", "RoadCurve3.png", p));
		pile.add(new Tuile(new Champs(), new Route(), new Champs(), new Route(), new Route(), false, "RoadCurve0.png", "RoadCurve1.png", "RoadCurve2.png", "RoadCurve3.png", p));
		pile.add(new Tuile(new Champs(), new Route(), new Champs(), new Route(), new Route(), false, "RoadCurve0.png", "RoadCurve1.png", "RoadCurve2.png", "RoadCurve3.png", p));
		pile.add(new Tuile(new Champs(), new Route(), new Champs(), new Route(), new Route(), false, "RoadCurve0.png", "RoadCurve1.png", "RoadCurve2.png", "RoadCurve3.png", p));
		pile.add(new Tuile(new Champs(), new Route(), new Champs(), new Route(), new Route(), false, "RoadCurve0.png", "RoadCurve1.png", "RoadCurve2.png", "RoadCurve3.png", p));
		pile.add(new Tuile(new Champs(), new Route(), new Champs(), new Route(), new Route(), false, "RoadCurve0.png", "RoadCurve1.png", "RoadCurve2.png", "RoadCurve3.png", p));
		
	}
	
	public void build() {
		Tuile tile = p.getDepart();
		JPanel plateau = new JPanel();
		JPanel depart = tile.getPanel();
		tile.entoure();
		plateau.setLayout(new BorderLayout());
		plateau.add(grille, BorderLayout.CENTER);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = tile.getY();
		gbc.gridy = tile.getX();
		grille.add(depart, gbc);
		JPanel txt = new JPanel();
		txt.add(new JLabel("plateau : "), BorderLayout.CENTER);
		plateau.add(txt, BorderLayout.NORTH);
		JPanel tour = new JPanel();
		JButton left = new JButton(" <- ");
		JButton right = new JButton(" -> ");
		tour.setLayout(new BorderLayout());
		tour.add(right, BorderLayout.EAST);
		tour.add(left, BorderLayout.WEST);
		tour.add(tuileCourante, BorderLayout.CENTER);
		titre.add(tj, BorderLayout.CENTER);
		tour.add(titre, BorderLayout.NORTH);
		JScrollPane scb = new JScrollPane(plateau);
		this.add(tour, BorderLayout.EAST);
		this.add(scb, BorderLayout.CENTER);
		tour.setBorder(new EmptyBorder(0, 25, 0, 0));
		right.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				courant.tourneDroite();
				ImageIcon img = new ImageIcon(courant.getCourant());
				tuileCourante.setIcon(img);
				
			}
		});
		left.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				courant.tourneGauche();
				ImageIcon img = new ImageIcon(courant.getCourant());
				tuileCourante.setIcon(img);
				
			}
		});
		this.pack();
		this.setVisible(true);
	}

	public void pioche() {
		if (pile.isEmpty()) {
			System.out.println("fin de la partie");
			System.exit(0);
		}
		int i = rand.nextInt(pile.size());
		courant = pile.remove(i);
		ImageIcon img = new ImageIcon(courant.getCourant());
		tuileCourante.setIcon(img);
		pose(courant);
		if (ia && joueur == 2) {
			
			int [] coords = Robot.chooseTuile(p, courant);
			int or = coords[2];
			
			for (int j = 0; j < or; j++) {
				courant.tourneDroite();
			}
			courant.poseTuile(this, p, grille, coords[0], coords[1]);
			
		}
		pack();
	}
	
	public void pose( Tuile t) {
		t.bouton(this, p, grille);
		joueur = (joueur == 1 ? 2 : 1);
		tj.setText("joueur " + joueur);
		titre.revalidate();
		titre.repaint();
		
		
	}
	
	public Tuile getTuileCourante() {
		return this.courant;
	}
	public boolean isIa() {
		return this.ia;
	}
	
	
}
