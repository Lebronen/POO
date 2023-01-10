import java.util.ArrayList;

public class Plateau {
	
	private Tuile [][] l;
	private Tuile depart;
	
	public Plateau () {
	
		
		l = new Tuile[140][140];
		depart = new Tuile (new Route (), new Route (), new Ville (), new Champs (), new Route (), false, "CastleWallRoad0.png", "CastleWallRoad1.png", "CastleWallRoad2.png", "CastleWallRoad3.png", this);
		l[69][69] = depart;
		depart.setX(69);
		depart.setY(69);
	}
	
	public boolean ajouter (Tuile tuile, int x, int y) {
		if (l[x][y] != null)
			return false;
		if (tuile.verif (this, x, y)) {
			l[x][y] = tuile;
			tuile.setX(x);
			tuile.setY(y);
			tuile.entoure();
			return true;
		}
		return false;
	}
	
	public void afficher () {
		for (int i = 0; i < l.length; i++) {
			for (int j = 0; j < l[i].length; j++) {
				l[i][j].affiche();
			}
		}
	}
	
	public Tuile getDepart() {
		return depart;
	}
	
	public Tuile getTuile(int x, int y) {
		return l[x][y];
	}

}
