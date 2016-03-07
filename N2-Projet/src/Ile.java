
public class Ile { //pour l'instant je vide les Iles � chaque cr�ation pour afficher toutes les cases, c'est modifiable

	private Parcelle[][] grille;
	private int ligne = 10;
	private int colonne = 10;
	
	public Ile() {
		this.grille = new Parcelle[ligne][colonne];
		this.viderIle();
	}
			
	public Ile(int lig, int col) {
		this.ligne = lig;
		this.colonne = col;	
		this.grille = new Parcelle[ligne][colonne];
		this.viderIle();
		this.setNavire();
	}
	
	public Ile(Parcelle[][] tablo) {
		this.ligne = tablo.length;
		this.colonne = tablo[0].length;
		this.grille = tablo;
		this.viderIle();
		this.setNavire();
	}
	
	private void setNavire() {
		grille[grille.length-1][0].setElement("navire1");
		grille[0][grille[0].length-1].setElement("navire2");
	}
	
	public int getLigne() {
		return ligne;
	}	

	public int getColonne() {
		return colonne;
	}	

	public Parcelle[][] getGrille() {
		return grille;
	}

	public void setGrille(Parcelle[][] tablo) {
		if (ligne==tablo.length && colonne==tablo[0].length) {
			this.grille = tablo;
		} else {
			System.out.println("Erreur: taille invalide");
		}
	}

	public void viderIle() { 
		for (int i=0; i<ligne; i++) {
			for (int j=0; j<colonne; j++) {
				this.grille[i][j] = new Parcelle();
			}
		}
	}
	
	public String toString() {
		
		String retour = "";
		for (int lig=1; lig<=ligne*2+1; lig++) {
			for (int col=1; col<=colonne*4+1; col++) {
				
				if (lig%2 == 1) {
					if (col%4 == 1) {
						retour+="+";
					} else {			
						retour+="-";						
					}
				} else if (col%4 == 1) {
					retour+="|";
				} else if (col%4 == 3) {					
					retour+=grille[lig/2-1][col/4].toString();
				} else {					
					retour+=" ";
				}
			}
			retour+="\n";			
		}
		return retour;		
	}
	
}
