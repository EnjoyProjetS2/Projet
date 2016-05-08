package projet.parcelle;
import javax.swing.JOptionPane;

import projet.plateau.Ile;
import projet.plateau.Jeu;

public class Explorateur extends Personnage {

	/**
	 * Constructeur: cree un Explorateur a partir de Personnage
	 * 
	 * @param nom
	 * @param e
	 * @param x
	 * @param y
	 */
	public Explorateur(String nom, Equipe e, int x, int y) {
		super(nom, e, x, y);
		super.energie = 100;
		
		if (e.getID() == 1) {
			super.id = 5;
		} else if (e.getID() == 2) {
			super.id = 11;
		}
	}

	/**
	 * Retourne vrai si l'action est possible et effectuee
	 * Capacite de l'explorateur, il peut soulever les rocher et verifier dessous
	 * 
	 * @param terrain
	 * @return boolean
	 */
	public boolean souleverRocher(Rocher terrain) {
		super.energie -= 5;
		if (terrain.clef == true) { // si ce rocher a la cle
			terrain.clef = false;
			this.possessionClef = true;
			JOptionPane.showMessageDialog(null, "L'equipe "+super.equipe.getNom()+" a trouve la clef !");
			return true;
		} else if (terrain.coffre == true) {
			JOptionPane.showMessageDialog(null, "Oh...un coffre a l'air ici.");
			if(this.possessionClef){
				this.possessionCoffre = true;
				JOptionPane.showMessageDialog(null, "L'equipe "+super.equipe.getNom() +" vient de prendre le tresor !");
			}
			return true;
		}
		JOptionPane.showMessageDialog(null, super.equipe.getNom()+": malheureusement il n'y a rien sous ce rocher.");
		return false;
	}
	
	/**
	 * Choisit le rocher suivant ce qui a ete determine au prealable pour l'envoyer a souleverrocher
	 * @param ile
	 * @param direction
	 * @return boolean
	 */
	public boolean choixRocher(Ile ile, String direction) {
		
		if (direction == "haut") {
			return this.souleverRocher((Rocher) ile.getGrille()[getX()-1][getY()]);
		} else if (direction == "bas") {
			return this.souleverRocher((Rocher) ile.getGrille()[getX()+1][getY()]);
		} else if (direction == "droite") {
			return this.souleverRocher((Rocher) ile.getGrille()[getX()][getY()+1]);
		} else if (direction == "gauche") {
			return this.souleverRocher((Rocher) ile.getGrille()[getX()][getY()-1]);
		}
		
		return false;
	}
	

	/**
	 * Retourne les informations du personnage
	 * @return String
	 */
	public String toString() {		
		return "Explorateur "+this.nom+" (Equipe: "+super.getEquipe().getNom()+") - Vie: "+super.energie+"/"+Jeu.maxVie;
	}
}
