
public class EssaiIle {

	public static void main(String[] args) {
			
			int tailleX = 5;
			int tailleY = 5;			
		
			Parcelle[][] parcelle = new Parcelle[tailleX][tailleY];
			
			Ile ile = new Ile(parcelle, 20);
			
			//Test de modifications des parcelles
			parcelle[1][0] = new Parcelle();
			parcelle[3][4] = new Parcelle("rocher");	
						
			System.out.println(ile.toString());			
			
			SuperPlateau p = new SuperPlateau(ile);
			p.setJeu(ile.getGrille());
			p.affichage();
			
			
			
		}

}