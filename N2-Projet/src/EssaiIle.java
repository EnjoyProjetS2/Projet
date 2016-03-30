public class EssaiIle {
	
	public static void main(String[] args) {
			
			
			Parcelle[][] parcelle = new Parcelle[Constantes.TAILLEX][Constantes.TAILLEY];
			Ile ile = new Ile(parcelle, Constantes.POURCENTAGEROCHER);
			
			// affichage mode graphique
			SuperPlateau p = new SuperPlateau(ile);
			
			
				
			
			ile.ajoutPersonnage(new Explorateur("Arthur", Constantes.EQUIPE1, 3, 3), Constantes.EQUIPE1);
			ile.ajoutPersonnage(new Explorateur("Vanessa", Constantes.EQUIPE2, 5, 5), Constantes.EQUIPE2);
			p.setJeu(ile.getGrille());
			p.affichage();
			System.out.println(ile.toString());
			System.out.println(Constantes.EQUIPE1.getListePersos().get(0).getX());
			System.out.println(Constantes.EQUIPE2.getListePersos().get(0).getY());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ile.deplacement(Constantes.EQUIPE1.getListePersos().get(0), "gauche");
			ile.deplacement(Constantes.EQUIPE2.getListePersos().get(0), "droite");

			p.setJeu(ile.getGrille());
			p.affichage();
			System.out.println(ile.toString());
			System.out.println(Constantes.EQUIPE1.getListePersos().get(0).getX());
			System.out.println(Constantes.EQUIPE2.getListePersos().get(0).getY());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ile.deplacement(Constantes.EQUIPE2.getListePersos().get(0), "droite");
			p.setJeu(ile.getGrille());
			p.affichage();
			System.out.println(ile.toString());
			System.out.println(Constantes.EQUIPE1.getListePersos().get(0).getX());
			System.out.println(Constantes.EQUIPE2.getListePersos().get(0).getY());
			
		}

}