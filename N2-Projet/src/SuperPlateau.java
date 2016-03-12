import java.util.Random;

public class SuperPlateau {
	private Plateau p;
	private Random ran = new Random();
	private int taille = 10;
	private int[][] jeu = new int[taille][taille];
	private String[] gifs = new String[] { "images/un.gif", "images/deux.gif", "images/trois.gif",
			"images/quatre.gif" };

	public SuperPlateau() {
		// TODO Auto-generated constructor stub
		p = new Plateau(gifs, taille);
	}

	public SuperPlateau(Ile ile) {
		p = new Plateau(gifs, ile.getLigne());
	}

	public int getTaille() {
		return taille;
	}

	boolean deplacement(int x, int y, int a, int b) {
		int[][] tmp = p.getJeu();
		if (tmp[a][b] == 0) {
			tmp[a][b] = tmp[x][y];
			tmp[x][y] = 0;
			return true;
		} else {
			return false;
		}
	}

	public int[][] getJeu() {
		return jeu;
	}

	public void setJeu(Parcelle[][] jeu) {
		for (int i = 0; i < jeu.length; i++) {
			for (int j = 0; j < jeu.length; j++) {
				if (jeu[i][j].getElement().equals("Rocher")) {
					this.jeu[i][j] = 1;
				} else if (jeu[i][j].getElement().equals("Vide")) {
					this.jeu[i][j] = 0;
				} else if (jeu[i][j].getElement().equals("Navire1") || jeu[i][j].getElement().equals("Navire2")) {
					this.jeu[i][j] = 2;
				} else if (jeu[i][j].getElement().equals("Coffre")) {
					this.jeu[i][j] = 3;
				}
			}
		}
		p.setJeu(this.jeu);
	}

	void affichage() {
		p.affichage();
	}
}
