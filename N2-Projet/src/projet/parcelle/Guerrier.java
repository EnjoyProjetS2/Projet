package projet.parcelle;
import java.util.Random;

import javax.swing.JOptionPane;

import projet.plateau.Ile;
import projet.plateau.Jeu;

public class Guerrier extends Personnage {

	/**
	 * Constructeur: cree un Guerrier a partir de Personnage
	 * 
	 * @param nom
	 * @param e
	 * @param x
	 * @param y
	 */
	public Guerrier(String nom, Equipe e, int x, int y) {
		super(nom, e, x, y);
		super.energie = 100;
		
		if (e.getID() == 1) {
			super.id = 8;
		} else if (e.getID() == 2) {
			super.id = 13;
		}
	}

	/**
	 * Action du guerrier : retourne vrai si elle réussit
	 * @param perso
	 * @return boolean
	 */
	public boolean attaquer(Personnage perso) {

		super.energie -= 10; // Le guerrier perd 10 d'energie a chaque tentative

		if (!perso.equipe.equals(this.equipe)) {

			if (new Random().nextInt(100) > 66) { // Le guerrier a 33% de chance reussir son attaque

				perso.setEnergie(0);

				JOptionPane.showMessageDialog(null,
						"Le guerrier de l'equipe " + super.equipe.getNom() + " a tue " + perso.getNom() + " !");
				return true;
			} else {
				JOptionPane.showMessageDialog(null,
						"Le guerrier de l'equipe " + super.equipe.getNom() + " a rate son attaque.");

			}

		} else {
			System.out.println("Erreur: Impossible d'attaquer un personnage de son équipe");
		}

		return false;

	}
	
	/**
	 * Choix de la position de l'attaque comme definit au prealable et renvoie le choix a l'action d'attaque
	 * @param ile
	 * @param direction
	 * @return
	 */
	public boolean choixAttaquer(Ile ile, String direction) {
		if (direction == "haut") {
			return this.attaquer((Personnage) ile.getGrille()[getX()-1][getY()]);
		} else if (direction == "bas") {
			return this.attaquer((Personnage) ile.getGrille()[getX()+1][getY()]);
		} else if (direction == "droite") {
			return this.attaquer((Personnage) ile.getGrille()[getX()][getY()+1]);
		} else if (direction == "gauche") {
			return this.attaquer((Personnage) ile.getGrille()[getX()][getY()-1]);
		}
		
		return false;
	}

	/**
	 * Retourne les informations du personnage
	 * @return String
	 */
	public String toString() {
		return "Guerrier " + this.nom + " (Equipe: " + super.getEquipe().getNom() + ") - Vie: " + super.energie + "/"
				+ Jeu.maxVie;
	}

}
