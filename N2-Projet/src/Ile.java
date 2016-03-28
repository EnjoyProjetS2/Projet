import java.util.Random;

public class Ile {

	private Parcelle[][] grille;
	private int ligne = Constantes.TAILLEX;
	private int colonne = Constantes.TAILLEY;
	private double tauxRocher = Constantes.TAUXDEROCHER;
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

	}
	

	public boolean ajoutPersonnage(Personnage e, Equipe t){		
		
		if (!t.getListePersos().contains(e) && grille[e.getX()][e.getY()] instanceof Sable) {
			t.getListePersos().add(e);
			grille[e.getX()][e.getY()] = e;
			return true;
		}
		return false;
	}
	// Le parametre deplacement est provisoire 
	
	/**Déplace un personnage vers une direction précise
	 * 
	 * @param e
	 * @param deplacement
	 * @return
	 */
	public boolean deplacement(Personnage e, String deplacement){
		Parcelle tmp = new Sable();
		switch (deplacement) {
		case "gauche":
			if(grille[e.getX()][e.getY()-1].estTraversable(e)){
			grille[e.getX()][e.getY()-1] = grille[e.getX()][e.getY()];
			grille[e.getX()][e.getY()] = tmp;
			e.setX(e.getY()-1);
			return true;
			}
			break;
		case "droite":
			if (grille[e.getX()][e.getY()+1].estTraversable(e)) {
				grille[e.getX()][e.getY()+1] = grille[e.getX()][e.getY()];
				grille[e.getX()][e.getY()] = tmp;
				e.setX(e.getY()+1);
				return true;
			}
			break;
		case "haut":
			if (grille[e.getX()-1][e.getY()].estTraversable(e)) {
				grille[e.getX()-1][e.getY()] = grille[e.getX()][e.getY()];
				grille[e.getX()][e.getY()] = tmp;
				e.setX(e.getX()-1);
				return true;
			}
			break;
		case "bas":
			if (grille[e.getX()+1][e.getY()].estTraversable(e)) {
				grille[e.getX()+1][e.getY()] = grille[e.getX()][e.getY()];
				grille[e.getX()][e.getY()] = tmp;
				e.setX(e.getX()+1);
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}
	// ajoute des navires sur le bord de l'ile
	private void setNavires() {

		Random alea = new Random();
		this.posNav1 = alea.nextInt(grille.length - 4) + 2;
		this.posNav2 = alea.nextInt(grille[0].length - 4) + 2;

		grille[posNav1][1] = new Navire(1);
		grille[posNav2][grille.length - 2] = new Navire(2);
		
	}
	
	private boolean verifierNavires() {
		
		if (!(grille[posNav1+1][1] instanceof Sable)) { return false; }
		if (!(grille[posNav1][2] instanceof Sable)) { return false; }
		if (!(grille[posNav1-1][1] instanceof Sable)) { return false; }		
		if (!(grille[posNav2+1][grille.length - 2] instanceof Sable)) { return false; }
		if (!(grille[posNav2-1][grille.length - 2] instanceof Sable)) { return false; }
		if (!(grille[posNav2][grille.length - 3] instanceof Sable)) { return false; }
		
		return true;
	}

	// ajoute des rochers sur l'ile
	private void setRochers() {

		do {

			ileVierge();
			setNavires();
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
		} while (!verifierIle() && !verifierNavires());

	}

	// retourne true si tous les rochers sont accessible false sinon
	private boolean verifierIle() {
		int cpt = 0;
		int[][] ile = new int[ligne][colonne];
		for (int l = 1; l < ile.length - 1; l++) {
			for (int c = 1; c < ile[l].length - 1; c++) {
				if (grille[l][c] instanceof Sable) {
					if (grille[l][c - 1] instanceof Sable) {
						ile[l][c] = 0;
					} else {
						ile[l][c] = 1;
					}
					if (grille[l][c + 1] instanceof Sable) {
						ile[l][c] = 0;
					} else {
						ile[l][c] = 1;
					}
					if (grille[l - 1][c] instanceof Sable) {
						ile[l][c] = 0;
					} else {
						ile[l][c] = 1;
					}
					if (grille[l + 1][c] instanceof Sable) {
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
				// System.out.print(ile[i][j]);
			}
			// System.out.println();
		}
		/*
		 * pour les tests try { Thread.sleep(1000); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } System.out.println("1: "+cpt);
		 * System.out.println("2: "+getNbRocher());
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

	/**
	 * Cree une ile faite de sable et entouree d'eau
	 * 
	 * @param ileVierge
	 */
	private void ileVierge() {

		// viderIle();

		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[i].length; j++) {
				if (i == 0 || i == grille.length - 1 || j == 0
						|| j == grille[i].length - 1) {
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
				} else {
					retour += " ";
				}
			}
			retour += "\n";
		}
		return retour;
	}

}
