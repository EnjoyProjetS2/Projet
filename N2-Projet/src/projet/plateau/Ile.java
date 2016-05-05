package projet.plateau;
import java.util.Random;

import javax.swing.JOptionPane;

import projet.parcelle.Eau;
import projet.parcelle.Navire;
import projet.parcelle.Parcelle;
import projet.parcelle.Personnage;
import projet.parcelle.Rocher;
import projet.parcelle.Sable;

public class Ile {

	private Parcelle[][] grille;
	private int ligne;
	private int colonne;
	private double tauxRocher;
	private int posNav1;
	private int posNav2;

	/**
	 * Constructeur par defaut : Cree une ile vide avec des parcelles
	 */
	public Ile() { // ile vide sans navires

		this.grille = new Parcelle[ligne][colonne];
		this.tauxRocher = 0;

		ileVierge();
	}

	/**
	 * Constructeur : cree une ile avec un tableau de parcelles en parametres
	 * 
	 * @param tablo
	 */
	public Ile(Parcelle[][] tablo) {

		this.ligne = tablo.length;
		this.colonne = tablo[0].length;
		this.grille = tablo;

		ileVierge();
		setElements();
	}

	/**
	 * Constructeur : cree une ile avec un tableau de parcelles et un
	 * pourcentage de rochers en parametres
	 * 
	 * @param tablo
	 * @param pourcent
	 */
	public Ile(Parcelle[][] tablo, int pourcent) {

		this.ligne = tablo.length;
		this.colonne = tablo[0].length;
		this.grille = tablo;
		this.tauxRocher = pourcent * 0.01;

		ileVierge();
		setElements();

	}

	/**
	 * Place deux navires aleatoirement sur des bords opposes de l'ile
	 */
	private void setNavires() {

		Random alea = new Random();
		this.posNav1 = alea.nextInt(grille.length - 4) + 2;
		this.posNav2 = alea.nextInt(grille[0].length - 4) + 2;

		Jeu.un.setNavire(new Navire(posNav1, 1));
		Jeu.deux.setNavire(new Navire(posNav2, grille.length - 2));

		grille[posNav1][1] = Jeu.un.getNavire();
		grille[posNav2][grille.length - 2] = Jeu.deux.getNavire();

	}

	/**
	 * Verifie que les parcelles autour des navires sont vides : retourne vrai si c'est le cas
	 * @return boolean
	 */
	private boolean verifierNavires() {

		if (!(grille[posNav1 + 1][1] instanceof Sable)) {
			return false;
		}
		if (!(grille[posNav1][2] instanceof Sable)) {
			return false;
		}
		if (!(grille[posNav1 - 1][1] instanceof Sable)) {
			return false;
		}
		if (!(grille[posNav2 + 1][grille.length - 2] instanceof Sable)) {
			return false;
		}
		if (!(grille[posNav2 - 1][grille.length - 2] instanceof Sable)) {
			return false;
		}
		if (!(grille[posNav2][grille.length - 3] instanceof Sable)) {
			return false;
		}

		return true;
	}

	/**
	 * Retourne la position du navire 1
	 * 
	 * @return posNav1
	 */
	public int getNav1() {
		return posNav1;
	}

	/**
	 * Retourne la position du navire 2
	 * 
	 * @return posNav2
	 */
	public int getNav2() {
		return posNav2;
	}

	/**
	 * Ajoute des rochers en fonction du pourcentage en parametre a la construction
	 */
	private void setElements() {

		do {

			ileVierge();
			int nbroc = 0;
			Random alea = new Random();
			while (nbroc < getNbRocher()) {
				int i = alea.nextInt(ligne - 1);
				int j = alea.nextInt(colonne - 1);

				if (nbroc < getNbRocher() && grille[i][j] instanceof Sable) {
					grille[i][j] = new Rocher();
					
					if (nbroc == 0) {
						((Rocher) grille[i][j]).setClef(true);
					} else if (nbroc == 1) {
						((Rocher) grille[i][j]).setCoffre(true);
					}
					
					nbroc++;

				}
			}
			setNavires();
		} while (!verifierIle() && !verifierNavires());

	}

	/**
	 * Retourne true si tous les rochers sont accessibles, false sinon
	 * @return boolean
	 */
	private boolean verifierIle() {
		int[][] tablo = new int[ligne][colonne];

		for (int i = 0; i < tablo.length; i++) {
			for (int j = 0; j < tablo[i].length; j++) {

				if (grille[i][j] instanceof Eau) {
					tablo[i][j] = 3;
				} else {
					tablo[i][j] = 0;
				}
			}
		}

		// Bateau
		tablo[1][1] = 1;
		grille[1][1].setTraversable(true);

		for (int i = 1; i < tablo.length - 1; i++) {

			for (int j = 1; j < tablo[i].length - 1; j++) {

				if (tablo[i][j] == 1) {

					if (grille[i + 1][j].estTraversable()) {
						tablo[i + 1][j] = 1;
					} else {
						tablo[i + 1][j] = 2;
					}

					if (grille[i - 1][j].estTraversable()) {
						tablo[i - 1][j] = 1;
					} else {
						tablo[i - 1][j] = 2;
					}

					if (grille[i][j + 1].estTraversable()) {
						tablo[i][j + 1] = 1;
					} else {
						tablo[i][j + 1] = 2;
					}

					if (grille[i][j - 1].estTraversable()) {
						tablo[i][j - 1] = 1;
					} else {
						tablo[i][j - 1] = 2;
					}
				}
			}
		}

		int nbZero = 0;

		for (int i = 0; i < tablo.length; i++) {
			for (int j = 0; j < tablo[i].length; j++) {
				if (tablo[i][j] == 0) {
					nbZero++;
				}
			}
		}

		if (nbZero == 0) {
			grille[1][1].setTraversable(true);
			return true;
		}
		return false;

	}

	/**
	 * Retourne le pourcentage de rochers qui doit etre place sur l'ile
	 * @return pourcentage de rochers
	 */
	public int getNbRocher() {
		return (int) (ligne * colonne * tauxRocher);
	}

	/**
	 * Retourne le nombre de lignes de l'ile
	 * @return nombre de lignes
	 */
	public int getLigne() {
		return ligne;
	}

	/**
	 * Retourne le nombre de colonnes de l'ile
	 * @return nombre de colonnes
	 */
	public int getColonne() {
		return colonne;
	}

	/**
	 * Retourne la grille
	 * @return grille
	 */
	public Parcelle[][] getGrille() {
		return grille;
	}

	/**
	 * Cree une ile de sable et entouree d'eau
	 */
	private void ileVierge() {

		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				if (i == 0 || i == grille.length - 1 || j == 0 || j == grille[i].length - 1) {
					grille[i][j] = new Eau();
				} else {
					grille[i][j] = new Sable();
				}
			}
		}
	}

	/**
	 * Affiche l'ile Chaque parcelle a son charactere particulier Les
	 * majuscules/minuscules correspondent aux equipes 1 et 2
	 * 
	 * @param toString
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String retour = "";
		for (int lig = 1; lig <= ligne * 2 + 1; lig++) {
			for (int col = 1; col <= colonne * 4 + 1; col++) {

				if (lig % 2 == 1) {
					if (col % 4 == 1) {
						retour += "+";
					} else {
						retour += "-";
					}
				} else if (col % 4 == 1) {
					retour += "|";
				} else if (col % 4 == 3) {
					retour += grille[lig / 2 - 1][col / 4].toString();
				} else {
					retour += " ";
				}
			}
			retour += "\n";
		}
		return retour;
	}

}
