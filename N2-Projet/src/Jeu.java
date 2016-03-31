import javax.swing.JOptionPane;
import javax.swing.JSlider;

public class Jeu {

	static int nbPerso = 3;
	static int pourcentageRocher = 10;
	static int tailleX = 10;
	static int tailleY = 10;
	static Equipe un;
	static Equipe deux;

	public Jeu() {

		if (accueil()) {
			
			parametres();
			
			this.un = new Equipe(null, 1);
			this.deux = new Equipe(null, 2);
			
			saisieEquipe(un);
			saisieEquipe(deux);
			
			//Affiche les membres des équipes
			un.afficherEquipe();
			deux.afficherEquipe();
			
			Ile ile = new Ile(new Parcelle[tailleX][tailleY], pourcentageRocher);
			
			System.out.println(ile.toString()); //Affichage texte
			
			SuperPlateau p = new SuperPlateau(ile); //Affichage graphique
			p.setJeu(ile.getGrille());
			p.affichage();
		
			//Les membres des équipes se dirigent dans leurs bateaux respectifs
			un.getNavire().embarquement();
			deux.getNavire().embarquement();
			
			//Test si les perso sont bien dans leurs bateaux
			for (int i=0; i<un.getNavire().getPersoDansNavire().size(); i++) {
				System.out.println(un.getNavire().getPersoDansNavire().get(i).toString());
			}
			System.out.println("------");
			for (int i=0; i<deux.getNavire().getPersoDansNavire().size(); i++) {
				System.out.println(deux.getNavire().getPersoDansNavire().get(i).toString());
			}
			
		}
	}	
	

	public boolean accueil() {

		String[] accueil = { "Jouer", "Regles", "Quitter" };

		String choix = (String) JOptionPane.showInputDialog(null, "Que faire:", "Bienvenue",
				JOptionPane.QUESTION_MESSAGE, null, accueil, accueil[0]);

		if (choix.equals("Jouer")) {
			return true;
		} else if (choix.equals("Regles")) {
			JOptionPane.showMessageDialog(null,
					"Le but du jeu est de trouver la clé cachée sous un rocher et de trouver le coffre puis ramener le trésor sur son navire. ",
					"Regles du Jeu", JOptionPane.INFORMATION_MESSAGE);
			return accueil();
		} else {
			return false;
		}

	}

	public void parametres() {

		String[] mode = { "1 contre 1" };

		String choix = (String) JOptionPane.showInputDialog(null, "Choisissez un mode de jeu:", "Parametres",
				JOptionPane.QUESTION_MESSAGE, null, mode, mode[0]);
		JOptionPane.showMessageDialog(null, "Bien enregistré !", "Information", JOptionPane.INFORMATION_MESSAGE);

		
		//JSLIDE:
		//ajouter taille de la carte
		//ajouter nombre de joueur par equipe
		//ajouter pourcentage de rochers

	}

	public void saisieEquipe(Equipe e) {

		String[] personnages = { "Explorateur", "Voleur" };

		e.setNom(JOptionPane.showInputDialog("Equipe " + e.getID() + " - Entrez un nom d'equipe:"));

		e.ajoutPersonnage(
				new Explorateur(JOptionPane.showInputDialog("Quel est le nom de votre explorateur?"), e, 0, 0));

		int cpt = 1;
		while (cpt < nbPerso) {
			String classe = (String) JOptionPane.showInputDialog(null,
					"Choisissez votre " + (cpt + 1) + "eme personnage:",
					"Equipe " + e.getID() + " - Personnage " + (cpt + 1), JOptionPane.QUESTION_MESSAGE, null,
					personnages, personnages[0]);

			if (classe.equals("Explorateur")) {
				e.ajoutPersonnage(
						new Explorateur(JOptionPane.showInputDialog("Quel est le nom de cet explorateur?"), e, 0, 0));
			} else if (classe.equals("Voleur")) {
				e.ajoutPersonnage(new Voleur(JOptionPane.showInputDialog("Quel est le nom de ce voleur?"), e, 0, 0));
			}
			
			cpt++;
		}
		JOptionPane.showMessageDialog(null, "Bien enregistré !", "Information", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {		
		
		new Jeu();
		
		

	}

}
