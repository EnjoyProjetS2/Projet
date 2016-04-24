import java.util.Random;

import javax.swing.JOptionPane;

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
		super.id = 6;
	}

	/**
	 * Action du voleur: il peut prendre la clef d'un autre participant
	 * 
	 * @param perso
	 */
	public boolean voler(Personnage perso) {

		super.energie -= 10; // Le voleur perd 10 d'energie a chaque tentative

		if (new Random().nextInt(100) > 50) { // Le voleur a 50% de chance de
												// reussir son vol

			if (perso.possessionClef == true) { // si ce perso a la cle
				perso.possessionClef = false;
				this.possessionClef = true;
				JOptionPane.showMessageDialog(null,
						"Le voleur de l'equipe " + super.equipe.getNom() + " a pris la clef !");
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "La cible ne portait pas de clef.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Le voleur n'a pas reussi a voler la clef.");

		}

		return false;
	}

	/**
	 * Retourne les informations du personnage
	 * 
	 */
	public String toString() {
		return "Voleur " + this.nom + " (Equipe: " + super.getEquipe().getNom() + ") - Vie: " + super.energie + "/"
				+ Jeu.maxVie;
	}
}
