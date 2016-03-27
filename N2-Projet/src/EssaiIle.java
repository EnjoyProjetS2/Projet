
public class EssaiIle {
	
	public static void main(String[] args) {
			
			
			Parcelle[][] parcelle = new Parcelle[Constantes.TAILLEX][Constantes.TAILLEY];
			Ile ile = new Ile(parcelle, Constantes.NOMBREDEROCHER);	
			// Affichage mode texte			
			System.out.println(ile.toString());
			// affichage mode graphique
			SuperPlateau p = new SuperPlateau(ile);
			ile.ajoutPersonnage(new Explorateur("Arthur", 1, 3, 3, 1));
			p.setJeu(ile.getGrille());
			p.affichage();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ile.deplacement(ile.getListPerso().get(0), "gauche");
			p.setJeu(ile.getGrille());
			p.affichage();
		}

}