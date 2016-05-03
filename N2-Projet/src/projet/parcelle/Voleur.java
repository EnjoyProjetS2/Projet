package projet.parcelle;
import java.util.Random;

import javax.swing.JOptionPane;

import projet.plateau.Ile;
import projet.plateau.Jeu;

public class Voleur extends Personnage {

	/**
	 * Constructeur: cree un voleur a partir de Personnage
	 * 
	 * @param nom
	 * @param e
	 * @param x
	 * @param y
	 */
	public Voleur(String nom, Equipe e, int x, int y) {
		super(nom, e, x, y);
		super.energie = 100;
		
		if (e.getID() == 1) {
			super.id = 6;
		} else if (e.getID() == 2) {
			super.id = 12;
		}
	}

	/**
	 * Action du voleur: il peut prendre la clef d'un autre participant
	 * 
	 * @param perso
	 */
	public boolean voler(Personnage perso){
        //si pas de la même équipe on vole
        if(perso.getEquipe() != this.getEquipe()){
            if(perso.possessionCoffre){
                perso.possessionCoffre = false;
                this.possessionCoffre = true;
            }else if(perso.possessionClef == true){ //si ce perso a la cle
                    perso.possessionClef = false;
                    this.possessionClef = true;
                    return true;
            }
        } 
        //si de la même équipe, on donne la clé
        else if(perso.getEquipe() == this.getEquipe()){
            if(this.possessionClef){
                perso.possessionClef = true;
                this.possessionClef = false;
            }
        }
        return false;
    }
	
	public boolean choixVoler(Ile ile, String direction) {
		if (direction == "haut") {
			return this.voler((Explorateur) ile.getGrille()[getX()-1][getY()]);
		} else if (direction == "bas") {
			return this.voler((Explorateur) ile.getGrille()[getX()+1][getY()]);
		} else if (direction == "droite") {
			return this.voler((Explorateur) ile.getGrille()[getX()][getY()+1]);
		} else if (direction == "gauche") {
			return this.voler((Explorateur) ile.getGrille()[getX()][getY()-1]);
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
