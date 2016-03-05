
public class Grille {

	public int ligne = 10;
	public int colonne = 10;
	private char[][] tablo;
	
	Grille() {
		this.tablo = new char[ligne][colonne];
	}	
	Grille(int lig, int col) {
		this.ligne = lig;
		this.colonne = col;
		this.tablo = new char[ligne][colonne];
	}
	Grille(char[][] tab) {
		this.ligne = tab.length;
		this.colonne = tab[0].length;
		this.tablo = tab;
	}
	
	
	public void afficher() {		
		
		for (int lig=1; lig<=ligne*2+1; lig++) {
			for (int col=1; col<=colonne*4+1; col++) {
				
				if (lig%2 == 1) {
					if (col%4 == 1) {
						System.out.print("+");
					} else {			
						System.out.print("-");						
					}
				} else if (col%4 == 1) {
					System.out.print("|");
				} else {
					System.out.print(" ");
				}				
			}
			System.out.println();			
		}		
	}	

	
}
