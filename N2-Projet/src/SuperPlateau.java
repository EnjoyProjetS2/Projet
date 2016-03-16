
public class SuperPlateau {
	
	private Plateau p;
	private int taille = 10;
	private int[][] jeu = new int[taille][taille];
	private String[] gifs = new String[] {"images/rocher.jpg", "images/navire.jpg", "images/coffre.png",
			"images/quatre.gif", "images/sol.gif", };

	public SuperPlateau() {
		p = new Plateau(gifs, taille);
	}

	public SuperPlateau(Ile ile) {
		p = new Plateau(gifs, ile.getLigne());
	}

	public int getTaille() {
		return taille;
	}

	public boolean deplacement(int x, int y, int a, int b) {
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

	public void setJeu(Parcelle[][] tablo) {
		
		for (int i = 0; i < tablo.length; i++) {
			for (int j = 0; j < tablo.length; j++) {
				
				if (tablo[i][j].getElement().equals("vide") || tablo[i][j].getElement().equals(null)) {
					this.jeu[i][j] = 0;
				} else if (tablo[i][j].getElement().equals("rocher")) {
					this.jeu[i][j] = 1;
				} else if (tablo[i][j].getElement().equals("navire1") || tablo[i][j].getElement().equals("navire2")) {
					this.jeu[i][j] = 2;
				} else if (tablo[i][j].getElement().equals("coffre")) {
					this.jeu[i][j] = 3;
				}
			}
		}
		
		//test: affichage du tableau d'entiers
		for (int i = 0; i < tablo.length; i++) {
			for (int j = 0; j < tablo.length; j++) {
			System.out.print(jeu[i][j]);
			}
			System.out.println();
		}
			
			
		p.setJeu(this.jeu);
	}
 
	public void affichage() {
		p.affichage();
	}
}
