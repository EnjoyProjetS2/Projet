import java.util.Random;

public class Voleur extends Personnage {

	/**
	 * Constructeur: cree un voleur a partir de Personnage
	 * 
	 * @param nom
	 * @param e
	 * @param x
	 * @param y
	 */
	Voleur(String nom, Equipe e, int x, int y) {
		super(nom, e, x, y);
		super.energie = 100;
	}

	/**
	 * Action du voleur: il peut prendre la clef d'un autre participant
	 * 
	 * @param perso
	 */
	public boolean voler(Personnage perso) {
		
		super.energie -= 10; //Le voleur perd 10 d'energie a chaque tentative	
		
		if (new Random().nextInt(100) > 50) { //Le voleur a 50% de chance de reussir son vol
			
			if (perso.possessionClef == true) { // si ce perso a la cle
				perso.possessionClef = false;
				this.possessionClef = true;
				return true;
			}		
		} else {
			System.out.println("Le vol n'a ete reussi");
		}
		
		return false;
	}

	/**
	 * Retourne V ou v selon son equipe
	 * 
	 */
	public String toString() {
		return "Voleur "+this.nom+" (Equipe: "+super.getEquipe().getNom()+") - Vie: "+super.energie+"/"+Jeu.maxVie;
	}
}
