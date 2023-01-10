

public class Route extends Lieu{

	@Override
	public boolean verif(Lieu l) {
		return (l instanceof Route) || l == null;
	}

	@Override
	protected void affiche(char c) {
		if (c == 'o')
		{
			System.out.print("==|");
		}
		else if (c == 'e')
		{
			System.out.print("|==");
		}
		else if (c == 'a') {
			System.out.print("==");
		}
		else {
			System.out.println("  ||  ");
		}
	}

	
	

}
