import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

public class Jeu {

	static int nbPerso = 3;
	static int pourcentageRocher = 10;
	static int tailleX = 10;
	static int tailleY = 10;
	static boolean validerParametre = false;
	static Equipe un;
	static Equipe deux;

	public Jeu() {

		if (accueil()) {

			parametres();

			this.un = new Equipe(null, 1);
			this.deux = new Equipe(null, 2);

			saisieEquipe(un);
			saisieEquipe(deux);

			// Affiche les membres des équipes
			un.afficherEquipe();
			deux.afficherEquipe();

			Ile ile = new Ile(new Parcelle[tailleX][tailleY], pourcentageRocher);

			// Les membres des équipes se dirigent dans leurs bateaux respectifs
			un.getNavire().embarquement();
			deux.getNavire().embarquement();

			/*
			 * // Test si les perso sont bien dans leurs bateaux for (int i = 0;
			 * i < un.getNavire().getPersoDansNavire().size(); i++) {
			 * System.out.println(un.getNavire().getPersoDansNavire().get(i).
			 * toString()); } System.out.println("------"); for (int i = 0; i <
			 * deux.getNavire().getPersoDansNavire().size(); i++) {
			 * System.out.println(deux.getNavire().getPersoDansNavire().get(i).
			 * toString()); }
			 */

			SuperPlateau p = new SuperPlateau(ile); // Affichage graphique

			while (!un.getNavire().presenceDuCoffre() || !deux.getNavire().presenceDuCoffre()) {

				System.out.println(ile.toString()); // Affichage texte
				p.setJeu(ile.getGrille());
				p.affichage();

				System.out.println("Cliquez sur un navire ou un personnage");

				/*int clicX = -1;
				int clicY = -1;*/

				p.getPlateau().waitEvent();
				int clicX = p.getPlateau().getPosX();
				int clicY = p.getPlateau().getPosY();

				if (ile.getGrille()[clicY][clicX] instanceof Navire) {

					if (un.getNavire().dernierPassager() == -1) {
						System.out.println("Le navire est vide");
					} else {
						ile.deplacement(un, p);

					}
				}
				
				if (ile.getGrille()[clicY][clicX] instanceof Personnage) {
					
					Personnage perso = (Personnage) ile.getGrille()[clicY][clicX];
					
					ile.deplacement(perso, p);
					
				}

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

		// JSLIDE:
		// ajouter taille de la carte
		// ajouter nombre de joueur par equipe
		// ajouter pourcentage de rochers
		JFrame fen = new JFrame("Parametres de partie");
		fen.setPreferredSize(new Dimension(500, 500));
		fen.getContentPane().setLayout(new BorderLayout());
		JPanel panGauche = new JPanel();
		JPanel panCentre = new JPanel();
		JPanel panDroite = new JPanel();
		JPanel panHaut = new JPanel();
		JButton validation = new JButton("Valider");
		// PanHaut
		JLabel labHaut = new JLabel("Les parametres");
		// ajout panhaut
		panHaut.add(labHaut);
		// panGauche
		JLabel labGauche = new JLabel("Taille");
		JSlider sliderGauche = new JSlider(JSlider.VERTICAL, 5, 15, 10);
		sliderGauche.setMajorTickSpacing(10);
		sliderGauche.setMinorTickSpacing(1);
		sliderGauche.setPaintTicks(true);
		sliderGauche.setPaintLabels(true);

		// ajout a panGauche
		panGauche.setLayout(new BorderLayout());
		panGauche.add(sliderGauche, BorderLayout.CENTER);
		panGauche.add(labGauche, BorderLayout.NORTH);

		// panCentre
		JLabel labCentre = new JLabel("Nombre de Joueur");
		JSlider sliderCentre = new JSlider(JSlider.VERTICAL, 1, 5, 1);
		sliderCentre.setMajorTickSpacing(4);
		sliderCentre.setMinorTickSpacing(1);
		sliderCentre.setPaintTicks(true);
		sliderCentre.setPaintLabels(true);
		labCentre.setHorizontalAlignment(JLabel.CENTER);
		// ajout a panCentre
		panCentre.setLayout(new BorderLayout());
		panCentre.add(labCentre, BorderLayout.NORTH);
		panCentre.add(sliderCentre, BorderLayout.CENTER);
		// panDroit
		JLabel labDroit = new JLabel("Pourcentage de rocher");
		JSlider sliderDroite = new JSlider(JSlider.VERTICAL, 0, 40, 10);
		sliderDroite.setMajorTickSpacing(40);
		sliderDroite.setMinorTickSpacing(0);
		sliderDroite.setPaintTicks(true);
		sliderDroite.setPaintLabels(true);
		panDroite.setLayout(new BorderLayout());
		panDroite.add(labDroit, BorderLayout.NORTH);
		panDroite.add(sliderDroite, BorderLayout.CENTER);
		// ajout Validation
		validation.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {

				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				validerParametre = true;
				fen.setVisible(false);
			}
		});
		fen.getContentPane().add(validation, BorderLayout.SOUTH);
		fen.getContentPane().add(panGauche, BorderLayout.WEST);
		fen.getContentPane().add(panCentre, BorderLayout.CENTER);
		fen.getContentPane().add(panDroite, BorderLayout.EAST);
		fen.getContentPane().add(panHaut, BorderLayout.NORTH);
		fen.pack();
		fen.setLocationRelativeTo(null);
		fen.setVisible(true);
		// la tempo pour attendre que le bouton soit appuyé sans bouffer tout le
		// proco
		do {
			System.out.println();
		} while (!validerParametre);
		// modification desparametres
		nbPerso = sliderCentre.getValue();
		pourcentageRocher = sliderDroite.getValue();
		tailleX = sliderGauche.getValue();
		tailleY = tailleX;
		System.out.println(nbPerso + "  " + pourcentageRocher + "  " + tailleX);
	}

	public void saisieEquipe(Equipe e) {

		String[] personnages = { "Explorateur", "Voleur" };

		e.setNom(JOptionPane.showInputDialog("Equipe " + e.getID() + " - Entrez un nom d'equipe:"));

		e.ajoutPersonnage(

				new Explorateur(JOptionPane.showInputDialog("Quel est le nom de votre explorateur?"), e,
						e.getNavire().getX(), e.getNavire().getY()));

		int cpt = 1;
		while (cpt < nbPerso) {
			String classe = (String) JOptionPane.showInputDialog(null,
					"Choisissez votre " + (cpt + 1) + "eme personnage:",
					"Equipe " + e.getID() + " - Personnage " + (cpt + 1), JOptionPane.QUESTION_MESSAGE, null,
					personnages, personnages[0]);

			if (classe.equals("Explorateur")) {
				e.ajoutPersonnage(new Explorateur(JOptionPane.showInputDialog("Quel est le nom de cet explorateur?"), e,
						e.getNavire().getX(), e.getNavire().getY()));
			} else if (classe.equals("Voleur")) {
				e.ajoutPersonnage(new Voleur(JOptionPane.showInputDialog("Quel est le nom de ce voleur?"), e,
						e.getNavire().getX(), e.getNavire().getY()));
			}

			cpt++;
		}
		JOptionPane.showMessageDialog(null, "Bien enregistré !", "Information", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {

		new Jeu();

	}

}
