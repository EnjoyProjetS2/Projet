
public class SuperPlateau {

	private Plateau p;
	private int taille = Jeu.tailleX;
	private int[][] jeu = new int[taille][taille];
	private String[] gifs = new String[] { "images/Rocher.png", "images/NavireBleu.png", "images/Sable.png",
			"images/Eau.png", "images/ExplorateurBleu.png", "images/VoleurBleu.png", "images/SableOff.png",
			"images/GuerrierBleu.png", "images/PiegeurBleu.png", "images/EauOff.png", "images/ExplorateurRouge.png",
			"images/VoleurRouge.png", "images/GuerrierRouge.png", "images/PiegeurRouge.png", "images/NavireRouge.png" };

	/**
	 * Constructeur de base: cree un super plateau a partir de plateau
	 */
	public SuperPlateau() {
		p = new Plateau(gifs, taille);
	}

	/**
	 * Constructeur : cree un super plateau a partir de plateau
	 * 
	 * @param ile
	 */
	public SuperPlateau(Ile ile) {
		p = new Plateau(gifs, ile.getLigne(), true);
	}

	/**
	 * retourne le plateau
	 * 
	 * @return Plateau : plateau
	 */
	public Plateau getPlateau() {
		return p;
	}

	/**
	 * retourne la taille
	 * 
	 * @return int : taille
	 */
	public int getTaille() {
		return taille;
	}

	/**
	 * retourne le jeu en tableau
	 * 
	 * @return int[][] jeu
	 */
	public int[][] getJeu() {
		return jeu;
	}

	/**
	 * Modifie le tableau de jeu avec des parcelles
	 * 
	 * @param tablo
	 */
	public void setJeu(Parcelle[][] tablo) {

		for (int i = 0; i < tablo.length; i++) {
			for (int j = 0; j < tablo[i].length; j++) {

				this.jeu[i][j] = tablo[i][j].getId();
			}
		}
		p.setJeu(this.jeu);
	}

	/**
	 * Modifie le tableau de jeu avec des chiffres
	 * 
	 * @param tablo
	 */
	public void setJeu(int[][] tablo) {
		p.setJeu(tablo);
	}

	/**
	 * Affiche le plateau
	 */
	public void affichage() {
		p.affichage();
	}
}
