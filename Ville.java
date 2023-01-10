
public class Ville extends Lieu{

	@Override
	public boolean verif(Lieu l) {
		return (l instanceof Ville) || l == null;
	}

	@Override
	protected void affiche(char c) {
		System.out.print("::::::");
		
		
	}

}
