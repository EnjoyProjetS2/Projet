import javax.swing.JOptionPane;

public class EssaiIle {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		String[] choixmenu = { "Jouer", "Regles du Jeu", "Quitter" };
		String[] choixperso = { "Explorateur", "Voleur" };
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane(), jop3 = new JOptionPane(),
				jop4 = new JOptionPane(), jop5 = new JOptionPane(), jop6 = new JOptionPane(), jop7 = new JOptionPane();
		String nom, perso;

		Parcelle[][] parcelle = new Parcelle[Constantes.TAILLEX][Constantes.TAILLEY];
		Ile ile = new Ile(parcelle, Constantes.POURCENTAGEROCHER);

		Equipe un = null;
		Equipe deux = null;

		nom = (String) jop.showInputDialog(null, "Veuillez saisir un choix", "Menu", JOptionPane.QUESTION_MESSAGE, null,
				choixmenu, choixmenu[2]);
		if (nom == "Regles du Jeu") {
			jop2.showMessageDialog(null,
					"Le but du jeu est de trouver la clé cachée sous un rocher et de trouver le coffre puis ramener le trésor sur son navire. ",
					"Regles du Jeu", JOptionPane.INFORMATION_MESSAGE);
		}
		if (nom == "Jouer") {
			int nombre;
			String stringentrer;
			stringentrer = JOptionPane.showInputDialog("Combien de joueurs êtes-vous ?");
			nombre = Integer.parseInt(stringentrer);

			while (nombre > 2 || nombre < 0) {
				stringentrer = JOptionPane
						.showInputDialog("Ceci est un nombre non permis. \n" + " \n Entrez un autre nombre.");
				nombre = Integer.parseInt(stringentrer);
			}

			String[] pseudo = new String[nombre];
			for (int cpt = 0; cpt < nombre; cpt++) {
				String choix = "Explorateur";
			
			pseudo[cpt] = jop3.showInputDialog(null, "Quel est le nom du joueur " + (cpt + 1) + " ?", "Pseudo",
						JOptionPane.QUESTION_MESSAGE);

				un = new Equipe(pseudo[0], 1);
				deux = new Equipe(pseudo[1], 2);

				String prenom1, prenom2;

				// creer un explorateur

				prenom1 = jop7.showInputDialog("Vous avez un explorateur, quel est son nom ?");

				if (cpt == 0) { // equipe 1

					if (un.ajoutPersonnage(new Explorateur(prenom1, un, ile.getNav1(), 1))) {
						System.out.println("Explo1 ok");
					}
				} else if (cpt == 1) { // equipe 2
					if (deux.ajoutPersonnage(new Explorateur(prenom1, deux, ile.getNav2(), ile.getColonne() - 2))) {
						System.out.println("Explo2 ok");
					}
				}

				// choisir autres membres de l'equipe
				for (int i = 0; i < 2; i++) {
					perso = (String) jop4.showInputDialog(null,
							"Vous avez deja " + choix + " que voulez-vous d'autre ?", pseudo[cpt],
							JOptionPane.QUESTION_MESSAGE, null, choixperso, choixperso[1]);

					// les nommer
					prenom2 = jop6.showInputDialog("Nom du personnage");

					if (cpt == 0) {
						// creation des persos CHANGER LES COORDONNEES SUIVANT
						// POSITION DU NAVIRE LA CA MARCHE PAS !!!
						if (perso == "Explorateur") {
							if (un.ajoutPersonnage(new Explorateur(prenom2, un, ile.getNav1(), 1))) {
								System.out.println("Explo eq1 ok");
							}
						} else if (perso == "Voleur") {
							if (un.ajoutPersonnage(new Voleur(prenom2, un, ile.getNav1(), 1))) {
								System.out.println("Voleur1 ok");
							}
						}
					} else if (cpt == 1) {
						if (perso == "Explorateur") {
							deux.ajoutPersonnage(new Explorateur(prenom2, deux, 3, 3));
						} else if (perso == "Voleur") {
							deux.ajoutPersonnage(new Voleur(prenom2, deux, 3, 3));
						}
					}

					choix += " " + perso;
				}

				jop5.showMessageDialog(null, choix, "Recap choix", JOptionPane.INFORMATION_MESSAGE);

			}

			System.out.println("equipe 2");
			deux.afficherEquipe();
			System.out.println(deux.getListePersos().get(0));

			
			System.out.println("equipe 1");
			//un.afficherEquipe();
			System.out.println(un.getListePersos().get(0));
			
			// Affichage mode texte
			System.out.println(ile.toString());

			/*
			 * // affichage mode graphique SuperPlateau p = new
			 * SuperPlateau(ile);
			 * 
			 * //ile.ajoutPersonnage(new Explorateur("Arthur", un, 3, 3), un);
			 * //ile.ajoutPersonnage(new Explorateur("Vanessa", deux, 5, 5),
			 * deux); p.setJeu(ile.getGrille()); p.affichage();
			 * 
			 * // Personnage.afficherPersonnages();
			 * 
			 * try { Thread.sleep(3000); } catch (InterruptedException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); }
			 * ile.deplacement(un.getListePersos().get(0), "gauche");
			 * ile.deplacement(deux.getListePersos().get(0), "droite");
			 * p.setJeu(ile.getGrille()); p.affichage();
			 */

		}
	}
}