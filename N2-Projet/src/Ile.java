import java.util.Random;

public class Ile {

	private Parcelle[][] grille;
	private int ligne = 10;
	private int colonne = 10;
	private double tauxRocher = 0.1;

	private int posNav1;
	private int posNav2;

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
		setNavires();
		setRochers();
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
		setNavires();
		setRochers();
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
		setRochers();
		setNavires();

	}

	private void setNavires() {

		Random alea = new Random();
		this.posNav1 = alea.nextInt(grille.length - 3) + 1;
		this.posNav2 = alea.nextInt(grille[0].length - 3) + 1;

		grille[posNav1][1].setElement("navire1");
		grille[posNav2][grille.length - 2].setElement("navire2");
	}

	private void setRochers() {
		
		do {
			//C'est deguelasse !
			ileVierge();
			int nbroc = 0;
			while (nbroc < getNbRocher()) {
				Random alea = new Random();
				int i = alea.nextInt(ligne - 1);
				int j = alea.nextInt(colonne - 1);

				if (nbroc < getNbRocher() && grille[i][j].estSable()) {
					grille[i][j].setElement("rocher");
					nbroc++;

					if (Parcelle.poseClef == false) {
						grille[i][j].clef = true;
						Parcelle.poseClef = true;
					}

					if (Parcelle.poseCoffre == false && grille[i][j].clef == false) {
						grille[i][j].coffre = true;
						Parcelle.poseCoffre = true;
					}
				}
			}

		} while (!verifierIle());
		
	}

	private boolean verifierIle() {
		int cpt = 0;
		int[][] ile = new int[ligne][colonne];
		for (int l = 1; l < ile.length - 1; l++) {
			for (int c = 1; c < ile[l].length - 1; c++) {

				/*if (grille[l - 1][c - 1].estVide()) {
				} else {
					ile[l][c] = 0;
				}*/
				if (grille[l][c].estSable()) {
					if (grille[l][c - 1].estSable()) {
						ile[l][c] = 0;
					} else {
						ile[l][c] = 1;
					}
					if (grille[l][c + 1].estSable()) {
						ile[l][c] = 0;
					} else {
						ile[l][c] = 1;
					}
					if (grille[l - 1][c].estSable()) {
						ile[l][c] = 0;
					} else {
						ile[l][c] = 1;
					}
					if (grille[l + 1][c].estSable()) {
						ile[l][c] = 0;
					} else {
						ile[l][c] = 1;
					}
				}
			}
		}
		
		for (int i = 0; i < ile.length; i++) {
			for (int j = 0; j < ile[i].length; j++) {
				if (ile[i][j] == 1) {
					cpt++;

				}
				//System.out.print(ile[i][j]);
			}
			//System.out.println();
		}
		/* pour les tests
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("1: "+cpt);
		System.out.println("2: "+getNbRocher());
		*/
		if (cpt == getNbRocher()) {
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

	/**
	 * Vide l'ile : toutes les cases deviennent vierges
	 * 
	 * @param viderIle
	 */
	public void viderIle() {
		for (int i = 0; i < ligne; i++) {
			for (int j = 0; j < colonne; j++) {
				this.grille[i][j] = new Parcelle();
			}
		}
	}

	private void ileVierge() {

		viderIle();

		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille.length; j++) {
				if (i == 0 || i == grille.length - 1 || j == 0 || j == grille[i].length - 1) {
					grille[i][j].setElement("eau");
				} else {
					grille[i][j].setElement("sable");
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
					// test d'affichage de la clef et du coffre
					if (grille[lig / 2 - 1][col / 4].clef == true) {
						retour += "K";
					} else if (grille[lig / 2 - 1][col / 4].coffre == true) {
						retour += "C";
					} else {
						retour += grille[lig / 2 - 1][col / 4].toString();
					}
				} else {
					retour += " ";
				}
			}
			retour += "\n";
		}
		return retour;
	}

}
