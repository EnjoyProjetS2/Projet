
public class Ile { //pour l'instant je vide les Iles � chaque cr�ation pour afficher toutes les cases, c'est modifiable

	public Parcelle[][] grille;
	public int ligne = 10;
	public int colonne = 10;
	
	public Ile() {
		this.grille = new Parcelle[ligne][colonne];
		this.viderIle();
	}
			
	public Ile(int lig, int col) {
		this.ligne = lig;
		this.colonne = col;	
		this.grille = new Parcelle[ligne][colonne];
		this.viderIle();
	}
	
	public Ile(Parcelle[][] tablo) {
		this.ligne = tablo.length;
		this.colonne = tablo[0].length;
		this.grille = tablo;
		this.viderIle();
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
