import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tuile {
	
	private Lieu est;
	private Lieu ouest;
	private Lieu nord;
	private Lieu sud;
	private Lieu milieu;
	private boolean bouclier;
	private String i0;
	private String i1;
	private String i2;
	private String i3;
	private String courant;
	private JLabel img;
	private JPanel panel;
	private int x;
	private int y;
	private JButton bn;
	private JButton bs;
	private JButton bo;
	private JButton be;
	private Tuile pioche;
	private Plateau p;
	private Meeple meeple;
	
	public Tuile (Lieu e, Lieu o, Lieu n, Lieu s, Lieu m, boolean b, String i0, String i1, String i2, String i3, Plateau p) {
		this.est = e;
		this.ouest = o;
		this.nord = n;
		this.sud = s;
		this.milieu = m;
		this.bouclier = b;
		this.i0 = i0;
		this.i1 = i1;
		this.i2 = i2;
		this.i3 = i3;
		this.courant = i0;
		this.img = new JLabel(new ImageIcon(this.courant));
		this.panel = new JPanel(new BorderLayout());
		this.pioche = null;
		this.p = p;
		this.bs = new JButton("placer");
		this.bn = new JButton("placer");
		this.be = new JButton("placer");
		this.bo = new JButton("placer");
		this.panel.add(img, BorderLayout.CENTER);
		this.x = -1;
		this.y = -1;
	}

	public boolean verif(Plateau p, int x, int y) {
		return (p.getTuile(x, y+1) == null || est.verif(p.getTuile(x,y+1).getOuest())) 
				&& (p.getTuile(x, y-1) == null || ouest.verif(p.getTuile(x, y-1).getEst())) 
				&& (p.getTuile(x-1, y) == null ||  nord.verif(p.getTuile(x-1, y).getSud())) 
				&& (p.getTuile(x+1, y) == null || sud.verif(p.getTuile(x+1, y).getNord()));
	
				//true;
	}
	
	
	public Lieu getEst() {
		return est;
	}
	public Lieu getMilieu() {
		return milieu;
	}
	public Lieu getNord() {
		return nord;
	}
	public Lieu getOuest() {
		return ouest;
	}
	public Lieu getSud() {
		return sud;
	}
	public boolean getB() {
		return this.bouclier;
	}
	
	public void entoure () {
		if (p.getTuile(x+1, y) == null)
			this.panel.add(bs, BorderLayout.SOUTH);
		if (p.getTuile(x-1, y) == null)
			this.panel.add(bn, BorderLayout.NORTH);
		if (p.getTuile(x, y+1) == null)
			this.panel.add(be, BorderLayout.EAST);
		if (p.getTuile(x, y-1) == null)
			this.panel.add(bo, BorderLayout.WEST);
	}

	public void updateImage() {
		ImageIcon icon = new ImageIcon(courant);
		if (meeple != null) {
			BufferedImage i = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
			Graphics g = i.getGraphics();
			g.drawImage(icon.getImage(), 0, 0, 500, 500, 0, 0, 500, 500, null);
			ImageIcon meepleImage = new ImageIcon(meeple.getImg());
			int mx = meeple.getPosX();
			int my = meeple.getPosY();
			g.drawImage(meepleImage.getImage(), mx, my, mx+100, my+60, 0, 0, 100, 60, null);
			icon = new ImageIcon(i);
		}
		img.setIcon(icon);
	}
	
	public void tourneDroite () {
		Lieu tmp = ouest;
		ouest = sud;
		sud = est;
		est = nord;
		nord = tmp;
		if (courant == i3)
			courant = i0;
		else if (courant == i0)
			courant = i1;
		else if (courant == i1)
			courant = i2;
		else
			courant = i3;
		updateImage();
	}
	
	public void tourneGauche () {
		Lieu tmp = ouest;
		ouest = nord;
		nord = est;
		est = sud;
		sud = tmp;
		if (courant == i0)
			courant = i3;
		else if (courant == i3)
			courant = i2;
		else if (courant == i2)
			courant = i1;
		else
			courant = i0;
		updateImage();
	}

	public void affiche() {
		System.out.println("  ______");
		System.out.print(" |");
		nord.affiche('a');
		System.out.println("| ");
		System.out.print(" |");
		if (ouest instanceof Route && !(milieu instanceof Route)) 
			ouest.affiche('o');
		else
			ouest.affiche('a');
		milieu.affiche('a');
		if (est instanceof Route && !(milieu instanceof Route)) 
			est.affiche('e');
		else
			est.affiche('a');
		System.out.println("|");
		System.out.print(" |");
		sud.affiche('a');
		System.out.println("|");
		System.out.println(" |______|");
	}
	public String getCourant() {
		return courant;
	}
	public void setCourant(String courant) {
		this.courant = courant;
	}
	public JLabel getImg() {
		return img;
	}
	public JPanel getPanel() {
		return panel;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Tuile getPioche() {
		return pioche;
	}
	public String getI0() {
		return i0;
	}
	public String getI1() {
		return i1;
	}
	public String getI2() {
		return i2;
	}public String getI3() {
		return i3;
	}
	
	public void setPioche(Tuile pioche) {
		this.pioche = pioche;
	}

	public void bouton(Jeu jeu, Plateau p, JPanel grille) {
		this.bs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("sud");
				poseTuile(jeu, p, grille, x+1, y);
		
			}
		});
		this.bn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("nord");
				poseTuile(jeu, p, grille, x-1, y);
		
			}
		});
		this.be.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("est");
				poseTuile(jeu, p, grille, x, y+1);
		
			}
		});
		this.bo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ouest");
				poseTuile(jeu, p, grille, x, y-1);
			}
		});
	}
	public void poseTuile(Jeu jeu, Plateau p, JPanel grille, int px, int py) {
		Tuile t = jeu.getTuileCourante();
		if (p.ajouter(t, px, py)) {
			if (!jeu.isIa() || Jeu.joueur == 1) {
				Dialog dialog = new Dialog();
				t.meeple = dialog.show(grille);
			}
			else {
				t.meeple = Robot.robotMeeple();
			}
		}
			t.updateImage();
			suppBouton(p, px, py);
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = py;
			gbc.gridy = px;
			grille.add(t.getPanel(), gbc);
			grille.revalidate();
			grille.repaint();
			jeu.pioche();

	}
	private void suppBouton (Plateau p, int px, int py) {
		Tuile n = p.getTuile(px-1, py);
		if (n != null)
			n.panel.remove(n.bs);
		Tuile s = p.getTuile(px+1, py);
		if (s != null)
			s.panel.remove(s.bn);
		Tuile e = p.getTuile(px, py+1);
		if (e != null)
			e.panel.remove(e.bo);
		Tuile o = p.getTuile(px, py-1);
		if (o != null)
			o.panel.remove(o.be);
	}
		

}
