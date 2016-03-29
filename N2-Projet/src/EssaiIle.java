
public class EssaiIle {
	
	public static void main(String[] args) {
			
			
			Parcelle[][] parcelle = new Parcelle[Constantes.TAILLEX][Constantes.TAILLEY];
			Ile ile = new Ile(parcelle, Constantes.POURCENTAGEROCHER);	
			// Affichage mode texte			
			System.out.println(ile.toString());
			// affichage mode graphique
			SuperPlateau p = new SuperPlateau(ile);
			
			
			
			//temporaire en attendant le menu
			Equipe un = new Equipe("swag", 1);
			Equipe deux = new Equipe("yolo", 2);			
			
			ile.ajoutPersonnage(new Explorateur("Arthur", un, 3, 3), un);
			ile.ajoutPersonnage(new Explorateur("Vanessa", deux, 5, 5), deux);
			p.setJeu(ile.getGrille());
			p.affichage();
			
			//Personnage.afficherPersonnages();

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ile.deplacement(un.getListePersos().get(0), "gauche");
			ile.deplacement(deux.getListePersos().get(0), "droite");
			p.setJeu(ile.getGrille());
			p.affichage();
			
			
		}

}