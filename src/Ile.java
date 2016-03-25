import java.util.Random;

public class Ile {

	private Parcelle[][] grille;
	private int ligne = Constantes.TAILLEX;
	private int colonne = Constantes.TAILLEY;
	private double tauxRocher = Constantes.TAUXDEROCHER;

	/**
	 * Constructeur par defaut : Cree une ile vide avec des parcelles
	 * 
	 * @param Ile
	 */
	public Ile() { // ile vide sans navires

		this.grille = new Parcelle[ligne][colonne];
		this.tauxRocher = 0;

		ileVierge();
	}

	/**
	 * Constructeur : cree une ile avec X colonnes et X lignes
	 * 
	 * @param Ile
	 */
	public Ile(int lig, int col) {

		this.ligne = lig;
		this.colonne = col;
		this.grille = new Parcelle[ligne][colonne];

		ileVierge();
		// setNavires();
		setElements();
	}

	/**
	 * Constructeur : cree une ile avec un tableau de parcelles en parametres
	 * 
	 * @param Ile
	 */
	public Ile(Parcelle[][] tablo) {

		this.ligne = tablo.length;
		this.colonne = tablo[0].length;
		this.grille = tablo;

		ileVierge();
		// setNavires();
		setElements();
	}

	/**
	 * Constructeur : cree une ile avec un tableau de parcelles et un
	 * pourcentage de rochers en parametres
	 * 
	 * @param Ile
	 */
	public Ile(Parcelle[][] tablo, int pourcent) {

		this.ligne = tablo.length;
		this.colonne = tablo[0].length;
		this.grille = tablo;
		this.tauxRocher = pourcent * 0.01;

		ileVierge();
		// setNavires();
		setElements();

	}

	// ajoute des navires sur le bord de l'ile
	private void setNavires() {
		
		Random alea = new Random();
		
		int posNav1 = alea.nextInt(grille.length - 2) + 1;
		int posNav2 = alea.nextInt(grille.length - 2) + 1;
		
		grille[posNav1][1] = new Navire(1, posNav1);
		grille[posNav2][grille[0].length - 2] = new Navire(2, posNav2);
	}

	// Place les rochers et les navires sur l'ile en respectant les possibilités
	// de passage
	private void setElements() {

		do {

			ileVierge();
			setNavires();

			Rocher.poseClef = false;
			Rocher.poseCoffre = false;

			int nbroc = 0;
			while (nbroc < getNbRocher()) {
				Random alea = new Random();
				int i = alea.nextInt(ligne - 1);
				int j = alea.nextInt(colonne - 1);

				if (nbroc < getNbRocher() && grille[i][j] instanceof Sable) {
					grille[i][j] = new Rocher();
					nbroc++;

				}
			}
		} while (!verifierIle());
	}

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

		// Bato
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
			grille[1][1].setTraversable(false);
			return true;
		}
		return false;

	}

	/**
	 * Retourne le pourcentage de rochers qui doit etre place sur l'ile
	 * 
	 * @param getNbRocher
	 * @return pourcentage de rochers
	 */
	public int getNbRocher() {
		return (int) (ligne * colonne * tauxRocher);
	}

	/**
	 * Retourne le nombre de lignes de l'ile
	 * 
	 * @param getLigne
	 * @return nombre de lignes
	 */
	public int getLigne() {
		return ligne;
	}

	/**
	 * Retourne le nombre de colonnes de l'ile
	 * 
	 * @param getColonne
	 * @return nombre de colonnes
	 */
	public int getColonne() {
		return colonne;
	}

	/**
	 * Retourne la grille
	 * 
	 * @param getGrille
	 * @return grille
	 */
	public Parcelle[][] getGrille() {
		return grille;
	}

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
	 * Affiche la grille de l'ile
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
					;
				} else {
					retour += " ";
				}
			}
			retour += "\n";
		}
		return retour;
	}

}
