package projet.parcelle;
import javax.swing.JOptionPane;

import projet.plateau.Ile;
import projet.plateau.Jeu;

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
		if (e.getID() == 1) {
			super.id = 9;
		} else if (e.getID() == 2) {
			super.id = 14;
		}
	}

	/**
	 * Piege une parcelle adjacente
	 * @param p
	 * @return boolean : piegee ou non
	 */
	public boolean pieger(Parcelle p) {

		if (p instanceof Sable) {

			((Sable) p).setPiege();
			JOptionPane.showMessageDialog(null, "Une parcelle a ete piegee !");
			return true;

		} else {
			JOptionPane.showMessageDialog(null, "La parcelle doit être du sable");
		}

		return false;

	}
	
	public boolean choixPieger(Ile ile, String direction) {
		if (direction == "haut") {
			return this.pieger((Sable) ile.getGrille()[getX()-1][getY()]);
		} else if (direction == "bas") {
			return this.pieger((Sable) ile.getGrille()[getX()+1][getY()]);
		} else if (direction == "droite") {
			return this.pieger((Sable) ile.getGrille()[getX()][getY()+1]);
		} else if (direction == "gauche") {
			return this.pieger((Sable) ile.getGrille()[getX()][getY()-1]);
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
