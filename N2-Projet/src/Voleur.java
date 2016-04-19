
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
		super.energie -= 10;
		if (perso.possessionClef == true) { // si ce perso a la cle
			perso.possessionClef = false;
			this.possessionClef = true;
			return true;
		}
		return false;
	}

	/**
	 * Retourne V ou v selon son equipe
	 * 
	 */
	public String toString() {
		if (super.getEquipe().getID() == 1) {
			return "V";
		} else {
			return "v";
		}
	}
}
