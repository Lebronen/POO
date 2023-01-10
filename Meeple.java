
public class Meeple {
	
	private String img;
	private final String orientation;
	private int x;
	private int y;
	
	public Meeple (int joueur, String orientation) {
		this.orientation = orientation;
		img = ((joueur == 1 ? "blueMeeple.png" : "redMeeple.png"));
	}
	public String getImg() {
		return img;
	}
	
	int getPosX () {
		switch (orientation) {
		case "haut" :
		case "bas" :
		case "milieu" : return 200;
		case "gauche" : return 25;
		case "droite" : return 375;
		}
		return 0;
	}
	int getPosY () {
		switch (orientation) {
		case "gauche" :
		case "droite" :
		case "milieu" : return 220;
		case "haut" : return 125;
		case "bas" : return 375;
		}
		return 0;
	}

}
