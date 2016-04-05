import java.util.Random;

import javax.swing.JOptionPane;

public class Ile {

	private Parcelle[][] grille;
	private int ligne;
	private int colonne;
	private double tauxRocher;
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
	 * Constructeur : cree une ile avec un tableau de parcelles en parametres
	 * 
	 * @param Ile
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
	 * @param Ile
	 */
	public Ile(Parcelle[][] tablo, int pourcent) {

		this.ligne = tablo.length;
		this.colonne = tablo[0].length;
		this.grille = tablo;
		this.tauxRocher = pourcent * 0.01;

		ileVierge();
		setElements();

	}

	// Le parametre deplacement est provisoire

	/**
	 * Déplace un personnage vers une direction précise
	 * 
	 * @param e
	 * @param deplacement
	 * @return
	 */
	public boolean deplacement(Personnage e, String deplacement) {
		Parcelle tmp = new Sable();
		switch (deplacement) {
		case "gauche":
			if (grille[e.getX()][e.getY() - 1].estTraversablePar(e)) {
				grille[e.getX()][e.getY() - 1] = grille[e.getX()][e.getY()];
				grille[e.getX()][e.getY()] = tmp;
				e.setX(e.getY() - 1);
				return true;
			}
			break;
		case "droite":
			if (grille[e.getX()][e.getY() + 1].estTraversablePar(e)) {
				grille[e.getX()][e.getY() + 1] = grille[e.getX()][e.getY()];
				grille[e.getX()][e.getY()] = tmp;
				e.setX(e.getY() + 1);
				return true;
			}
			break;
		case "haut":
			if (grille[e.getX() - 1][e.getY()].estTraversablePar(e)) {
				grille[e.getX() - 1][e.getY()] = grille[e.getX()][e.getY()];
				grille[e.getX()][e.getY()] = tmp;
				e.setX(e.getX() - 1);
				return true;
			}
			break;
		case "bas":
			if (grille[e.getX() + 1][e.getY()].estTraversablePar(e)) {
				grille[e.getX() + 1][e.getY()] = grille[e.getX()][e.getY()];
				grille[e.getX()][e.getY()] = tmp;
				e.setX(e.getX() + 1);
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}
	
	
	/**Deplace un personnage grace au clic
	 * 
	 * @param perso
	 * @param p
	 * @return
	 */
	public boolean deplacement(Personnage perso, SuperPlateau p) {
		
		System.out.println("Destination?");

		/*
		p.getPlateau().waitEvent();
		int clicX = p.getPlateau().getPosX();
		int clicY = p.getPlateau().getPosY();

		if (grille[clicY][clicX] instanceof Navire) {
			System.out.println("Erreur: navire");
			return deplacement(perso, p);
		}

		if (grille[clicY][clicX].estTraversable()) {

			int persoX = perso.getY();
			int persoY = perso.getX();

			if ((clicX == persoX || clicX == persoX + 1 || clicX == persoX - 1) && (clicY == persoY || clicY == persoY + 1 || clicY == persoY - 1 )) {
				grille[perso.getX()][perso.getY()] = new Sable();
				grille[clicY][clicX] = perso;
				perso.setX(clicY);
				perso.setY(clicX);
				
				
				//test
				System.out.println("perso");
				System.out.println(perso.getX());
				System.out.println(perso.getY());
				
				//
				
				p.setJeu(grille);
				p.affichage();
				return true;
				
			} else {
				System.out.println("Erreur: trop loin");
				return deplacement(perso, p);
			}		
		}
		System.out.println("Erreur: parcelle non traversable");
		return deplacement(perso, p);
		*/
		
		return false;
	}

	/**Sort un personnage de son navire grace au clic
	 * 
	 * @param e
	 * @param p
	 * @return
	 */
	public boolean deplacement(Equipe e, SuperPlateau p) {

		System.out.println("Destination?");

		p.getPlateau().waitEvent();
		int clicX = p.getPlateau().getPosX();
		int clicY = p.getPlateau().getPosY();
	    
		
		if (grille[clicY][clicX].estTraversable() && !(grille[clicY][clicX] instanceof Navire)) {

			// int persoX =
			// e.getNavire().getPersoDansNavire().get(e.getNavire().dernierPassager()).getX();
			// int persoY =
			// e.getNavire().getPersoDansNavire().get(e.getNavire().dernierPassager()).getY();

			int navX = e.getNavire().getY();
			int navY = e.getNavire().getX();

			if ( (clicX == navX || clicX == navX + 1 || clicX == navX - 1) && (clicY == navY || clicY == navY + 1 || clicY == navY - 1 )) {
				
				e.getNavire().getPersoDansNavire().get(e.getNavire().dernierPassager()).setX(clicY);
				e.getNavire().getPersoDansNavire().get(e.getNavire().dernierPassager()).setY(clicX);

				grille[e.getNavire().getPersoDansNavire().get(e.getNavire().dernierPassager()).getX()][e.getNavire()
						.getPersoDansNavire().get(e.getNavire().dernierPassager()).getY()] = e.getNavire()
								.getPersoDansNavire().get(e.getNavire().dernierPassager());
				e.getNavire().getPersoDansNavire().remove(e.getNavire().dernierPassager());
				System.out.println("Le perso a été deplacé");
				
				p.setJeu(grille);
				p.affichage();
				return true;

			} else {
				System.out.println("Erreur: trop loin.");
				return deplacement(e, p);
			}

		}

		System.out.println("Erreur: la parcelle n'est pas traversable");
		return deplacement(e, p);
		
	}

	// Place deux navires aleatoirement sur des bords opposes de l'ile
	private void setNavires() {

		Random alea = new Random();
		this.posNav1 = alea.nextInt(grille.length - 4) + 2;
		this.posNav2 = alea.nextInt(grille[0].length - 4) + 2;

		Jeu.un.setNavire(new Navire(posNav1, 1));
		Jeu.deux.setNavire(new Navire(posNav2, grille.length - 2));

		grille[posNav1][1] = Jeu.un.getNavire();
		grille[posNav2][grille.length - 2] = Jeu.deux.getNavire();

	}

	// Verifie que les parcelles autour des navires sont vides
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
	
	/**Retourne la position du navire 1
	 * 
	 * @return
	 */
	public int getNav1() {
		return posNav1;
	}
	
	/**Retourne la position du navire 2
	 * 
	 * @return
	 */
	public int getNav2() {
		return posNav2;
	}

	// Ajoute des rochers en fonction du pourcentage en parametre a la
	// construction
	private void setElements() {

		do {

			ileVierge();
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
			setNavires();
		} while (!verifierIle() && !verifierNavires());

	}

	// Retourne true si tous les rochers sont accessibles, false sinon
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

	// Cree une ile de sable et entouree d'eau
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
