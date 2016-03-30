
import javax.swing.JOptionPane;

public class menu {
	public static void main(String[] args) {
		String[] choixmenu = { "Jouer", "R�gles du Jeu", "Quitter" };
		String[] choixperso = { "explorateur", "voleur" };
		JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane(), jop3 = new JOptionPane(),
				jop4 = new JOptionPane(), jop5 = new JOptionPane(), jop6 = new JOptionPane();
		String nom;
		String perso, prenom;

		do {
			nom = (String) jop.showInputDialog(null, "Veuillez saisir un choix", "Menu", JOptionPane.QUESTION_MESSAGE,
					null, choixmenu, choixmenu[2]);
			if (nom == "R�gles du Jeu") {
				jop2.showMessageDialog(null, "blbl ", "R�gles du Jeu", JOptionPane.INFORMATION_MESSAGE);
			}
			if (nom == "Jouer") {

				int nombre;
				String stringentrer;

				stringentrer = JOptionPane.showInputDialog("Enter nombre de joueur");

				nombre = Integer.parseInt(stringentrer);

				while (nombre > 2 || nombre < 0) {

					stringentrer = JOptionPane
							.showInputDialog("Ceci est un nombre non permis \n" + " \n Entrer un autre nombre");

					nombre = Integer.parseInt(stringentrer);

				}
				String pseudo[] = new String[nombre];
				for (int cpt = 0; cpt < nombre; cpt++) {
					String choix = "explorateur";
					pseudo[cpt] = jop3.showInputDialog(null, "pseudo joueur " + (cpt + 1), "Pseudo",
							JOptionPane.QUESTION_MESSAGE);
					for (int i = 0; i < 2; i++) {
						perso = (String) jop4.showInputDialog(null,
								"Vous avez d�j� " + choix + " que voulez-vous d'autre ?", pseudo[cpt],
								JOptionPane.QUESTION_MESSAGE, null, choixperso, choixperso[1]);
						prenom = (String) jop6.showInputDialog(null, "Quel est son nom ?", pseudo[cpt],
								JOptionPane.QUESTION_MESSAGE);
						// creation du perso
						// if(perso == "explorateur") { ile.ajoutPersonnage(new
						// Explorateur(prenom, un, 3, 3), un); } else if (perso
						// == "voleur") { ile.ajoutPersonnage(new Voleur(prenom,
						// un, 3, 3), un); }
						choix += " " + perso;
					}
					jop5.showMessageDialog(null, choix, "R�cap choix", JOptionPane.INFORMATION_MESSAGE);

				}

				// * lancer le jeux /

			}
		} while (nom != "Quitter");
	}
}
