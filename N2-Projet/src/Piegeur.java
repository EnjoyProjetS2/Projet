import javax.swing.JOptionPane;

public class Piegeur extends Personnage {

	/**
	 * Constructeur: cree un Piegeur a partir de Personnage
	 * 
	 * @param nom
	 * @param e
	 * @param x
	 * @param y
	 */
	public Piegeur(String nom, Equipe e, int x, int y) {
		super(nom, e, x, y);
		super.energie = 100;
		super.id = 9;
	}

	public boolean pieger(Parcelle p) {

		if (p instanceof Sable) {

			((Sable) p).setPiege();
			return true;

		} else {
			JOptionPane.showMessageDialog(null, "La parcelle doit être du sable");
		}

		return false;

	}

	/**
	 * Retourne les informations du personnage
	 * 
	 */
	public String toString() {
		return "Piegeur " + this.nom + " (Equipe: " + super.getEquipe().getNom() + ") - Vie: " + super.energie + "/"
				+ Jeu.maxVie;
	}

}
