package projet.plateau;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import projet.parcelle.Equipe;
import projet.parcelle.Personnage;

public class TourOrdinateur {

	private Equipe equipe;
	private static int nbSurSable = 0;
	private static int nbDansNavire = 0;
	private static List<Personnage> persoHorsNavire = new ArrayList<>();	

	public TourOrdinateur(Ile ile, Plateau p, Equipe e) {			

		this.equipe = e;
		this.nbDansNavire = equipe.getNavire().getPersoDansNavire().size();
		this.nbSurSable = persoHorsNavire.size();

		while (!jouer(ile, p, equipe)) {
		}

	}

	private boolean jouer(Ile ile, Plateau p, Equipe e) {

		// Debarquement
		if (nbSurSable == 0 && nbDansNavire > 0) {

			Personnage perso = equipe.getNavire().getPersoDansNavire()
					.get(new Random().nextInt(equipe.getNavire().getPersoDansNavire().size()));
			Deplacement d = new Deplacement(ile, perso);
			d.debarquementAleatoire();
			persoHorsNavire.add(perso);
			return true;

		}

		// Deplacement
		if (nbSurSable >= nbDansNavire) {

			Personnage perso = persoHorsNavire.get(new Random().nextInt(persoHorsNavire.size()));
			Deplacement d = new Deplacement(ile, perso);
			d.deplacementAleatoire();
			
			if (perso.getX() == equipe.getNavire().getX() && perso.getY() == equipe.getNavire().getY()) {
				persoHorsNavire.remove(perso);
			}
			
			return true;

		}

		return false;
	}

}
