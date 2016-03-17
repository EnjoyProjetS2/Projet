
public class EssaiIle {

	public static void main(String[] args) {
			
			int tailleX = 10;
			int tailleY = 10;			
		
			Parcelle[][] parcelle = new Parcelle[tailleX][tailleY];
			
			Ile ile = new Ile(parcelle, 20);	
						
			System.out.println(ile.toString());			
			
			SuperPlateau p = new SuperPlateau(ile);
			System.out.println();
			p.setJeu(ile.getGrille());
			p.affichage();
			
			
			
		}

}