
public class Champs extends Lieu{

	@Override
	public boolean verif(Lieu l) {
		return (l instanceof Abbaye) || (l instanceof Champs) || l == null;
	}

	@Override
	protected void affiche(char c) {
		System.out.print("      ");
		
	}

}
