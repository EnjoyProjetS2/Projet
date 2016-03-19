
public class SuperPlateau {
	
	private Plateau p;
	private int taille = Constantes.TAILLEX;
	private int[][] jeu = new int[taille][taille];
	private String[] gifs = new String[] {"images/stone.png", "images/boat.png",  "images/sable.png", 
			"images/eau.png"};

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
			for (int j = 0; j < tablo[i].length; j++) {
				
				if (tablo[i][j].estRocher()) {
					this.jeu[i][j] = 1;
				} else if (tablo[i][j].estNavire()) {
					this.jeu[i][j] = 2;
				} else if (tablo[i][j].estSable()) {
					this.jeu[i][j] = 3;
				} else if(tablo[i][j].estEau()){
					this.jeu[i][j] = 4;
				}
			}
		}
		
		/*//test: affichage du tableau d'entiers
		for (int i = 0; i < tablo.length; i++) {
			for (int j = 0; j < tablo.length; j++) {
			System.out.print(jeu[i][j]);
			}
			System.out.println();
		}*/
			
			
		p.setJeu(this.jeu);
	}
 
	public void affichage() {
		p.affichage();
	}
}
