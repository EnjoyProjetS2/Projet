
public class SuperPlateau {
	
	private Plateau p;
	private int taille = Jeu.tailleX; 
	private int[][] jeu = new int[taille][taille];
	private String[] gifs = new String[] {"images/stone.png", "images/boat.png",  "images/sable.png", 
			"images/eau.png", "un.gif"};

	public SuperPlateau() {
		p = new Plateau(gifs, taille);
	}

	public SuperPlateau(Ile ile) {
		p = new Plateau(gifs, ile.getLigne(), true);
	}
	public Plateau getPlateau(){
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
				
				if (tablo[i][j] instanceof Rocher) {
					this.jeu[i][j] = 1;
				} else if (tablo[i][j] instanceof Navire) {
					this.jeu[i][j] = 2;
				} else if (tablo[i][j] instanceof Sable) {
					this.jeu[i][j] = 3;
				} else if(tablo[i][j] instanceof Eau) {
					this.jeu[i][j] = 4;
				} else if(tablo[i][j] instanceof Explorateur) {
					this.jeu[i][j] = 5;
				} 
			}
		}		
			
		p.setJeu(this.jeu);
	}
 
	public void affichage() {
		p.affichage();
	}
}
