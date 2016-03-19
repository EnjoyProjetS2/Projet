
public class EssaiIle {
	
	public static void main(String[] args) {
			
			
			Parcelle[][] parcelle = new Parcelle[Constantes.TAILLEX][Constantes.TAILLEY];
			Ile ile = new Ile(parcelle, Constantes.NOMBREDEROCHER);	
			// Affichage mode texte			
			System.out.println(ile.toString());
			// affichage mode graphique
			SuperPlateau p = new SuperPlateau(ile);
			p.setJeu(ile.getGrille());
			p.affichage();
		}

}