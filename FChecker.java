import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FChecker {

	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		
		for (int i = 0; i < jeu.pile.size(); i++) {
			Path p0 = Paths.get(jeu.pile.get(i).getI0());
			Path p1 = Paths.get(jeu.pile.get(i).getI1());
			Path p2 = Paths.get(jeu.pile.get(i).getI2());
			Path p3 = Paths.get(jeu.pile.get(i).getI3());
			if (! (Files.exists(p0)))
				System.out.println(jeu.pile.get(i).getI0());
			if (! (Files.exists(p1)))
				System.out.println(jeu.pile.get(i).getI1());
			if (! (Files.exists(p2)))
				System.out.println(jeu.pile.get(i).getI2());
			if (! (Files.exists(p3)))
				System.out.println(jeu.pile.get(i).getI3());
		}
	}

}
