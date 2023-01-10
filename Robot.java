import java.util.Random;

public class Robot {
	
	private static Random rand = new Random();
	
	public Robot () {
		
	}
	
	public static int []  chooseTuile(Plateau p, Tuile courant) {
		int [][] possible = new int [3][20000];
		int [] coords = new int[3];
		int c = 0;
		for (int j = 1; j < 139; j++) {
			for (int k = 1; k < 139; k++) {
				if (p.getTuile(j, k) == null && courant.verif(p, j, k) && adjTuile(p, j, k)) {
					possible[0][c] = j;
					possible[1][c] = k;
					c++;
					possible[2][c] = 0;
				}
				else {
					courant.tourneDroite();
					if (p.getTuile(j, k) == null && courant.verif(p, j, k) && adjTuile(p, j, k)) {
						possible[0][c] = j;
						possible[1][c] = k;
						c++;
						possible[2][c] = 1;
					}

					else {
						courant.tourneDroite();
						if (p.getTuile(j, k) == null && courant.verif(p, j, k) && adjTuile(p, j, k)) {
							possible[0][c] = j;
							possible[1][c] = k;
							c++;
							possible[2][c] = 2;
					
						}
						else {
							courant.tourneDroite();
							if (p.getTuile(j, k) == null && courant.verif(p, j, k) && adjTuile(p, j, k)) {
								possible[0][c] = j;
								possible[1][c] = k;
								c++;
								possible[2][c] = 3;
							}
						}
					}
				}
			}
			while (courant.getCourant() != courant.getI0()) {
				courant.tourneDroite();
			}
		}
		
		int choix = rand.nextInt(c);
		coords[0] = possible[0][choix];
		coords[1] = possible[1][choix];
		coords[2] = possible[2][choix];
		return coords;
	}
	
	public static Meeple robotMeeple () {
		boolean mp = rand.nextBoolean();
		if (!mp)
			return null;
		int or = rand.nextInt(5);
		String os;
		switch (or) {
		case 0 : os = "haut";break;
		case 1 : os = "bas";break;
		case 2 : os = "droite";break;
		case 3 : os = "gauche";break;
		default : os = "milieu";break;
		}
		return new Meeple(Jeu.joueur, os);
	}
	
	private static boolean adjTuile (Plateau p, int x, int y) {
		return p.getTuile(x+1, y) != null || p.getTuile(x-1, y) != null || p.getTuile(x, y+1) != null || p.getTuile(x, y-1) != null;
	}
	
	
}
