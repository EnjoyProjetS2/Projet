
public class SuperPlateau {

	private Plateau p;
	private int taille = Jeu.tailleX;
	private int[][] jeu = new int[taille][taille];
	private String[] gifs = new String[] { "images/stone.png", "images/boat.png", "images/sable.png", "images/eau.png",
			"images/Explo.png", "images/Voleur.png", "images/vision.png", "images/Guerrier.png", "images/Piegeur.png" };

	public SuperPlateau() {
		p = new Plateau(gifs, taille);
	}

	public SuperPlateau(Ile ile) {
		p = new Plateau(gifs, ile.getLigne(), true);
	}

	public Plateau getPlateau() {
		return p;
	}

	public int getTaille() {
		return taille;
	}

	public int[][] getJeu() {
		return jeu;
	}

	public void setJeu(Parcelle[][] tablo) {

		for (int i = 0; i < tablo.length; i++) {
			for (int j = 0; j < tablo[i].length; j++) {

				this.jeu[i][j] = tablo[i][j].getId();
			}
		}
		p.setJeu(this.jeu);
	}
	
	public void setJeu(int[][] tablo) {
		p.setJeu(tablo);
	}

	public void affichage() {
		p.affichage();
	}
}
