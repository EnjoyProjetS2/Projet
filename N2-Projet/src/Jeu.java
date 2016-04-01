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

			System.out.println(ile.toString()); // Affichage texte

			SuperPlateau p = new SuperPlateau(ile); // Affichage graphique
			p.setJeu(ile.getGrille());
			p.affichage();

			// Les membres des équipes se dirigent dans leurs bateaux respectifs
			un.getNavire().embarquement();
			deux.getNavire().embarquement();

			// Test si les perso sont bien dans leurs bateaux
			for (int i = 0; i < un.getNavire().getPersoDansNavire().size(); i++) {
				System.out.println(un.getNavire().getPersoDansNavire().get(i).toString());
			}
			System.out.println("------");
			for (int i = 0; i < deux.getNavire().getPersoDansNavire().size(); i++) {
				System.out.println(deux.getNavire().getPersoDansNavire().get(i).toString());
			}
			// TEST CYCLE DE JEU
			/*while (!un.getNavire().presenceDuCoffre() && !deux.getNavire().presenceDuCoffre()) {
				System.out.println("k");
				Personnage po = null;
				boolean personnageSelectionner = false;
				do {
					System.out.println(p.getPlateau().getPosX()+"   "+p.getPlateau().getPosY());
					//Marche pas je sais pas pourquoi !
					if (ile.getGrille()[p.getPlateau().getPosX()][p.getPlateau().getPosY()] instanceof Personnage) {
						System.out.println("ni");
						for (Personnage perso : un.getListePersos()) {
							if (perso.getX() == p.getPlateau().getPosX() && perso.getY() == p.getPlateau().getPosY()) {
								po = perso;
								personnageSelectionner = true;
							}
						}
					}
				} while (!personnageSelectionner);
				ile.deplacement(po, p);
			}
			//*/
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
		/*JFrame fen = new JFrame("Parametres de partie");
		fen.setPreferredSize(new Dimension(500, 500));
		fen.getContentPane().setLayout(new BorderLayout());
		JPanel panGauche = new JPanel();
		JPanel panCentre = new JPanel();
		JPanel panDroite = new JPanel();
		JButton validation = new JButton("Valider");
		//panGauche
		
		JLabel labGauche = new JLabel("Taille ");
		JSlider sliderGauche= new JSlider();
		sliderGauche.setMaximum(20);
		sliderGauche.setMinimum(5);
		
		// ajout a panGauche
		panGauche.setLayout(new BorderLayout());
		panGauche.add(sliderGauche, BorderLayout.CENTER);
		panGauche.add(labGauche, BorderLayout.NORTH);
		
		// panCentre
		JLabel labCentre = new JLabel("Nombre de Joueur");
		JSlider sliderCentre = new JSlider();
		sliderCentre.
		// ajout a panCentre
		panCentre.setLayout(new BorderLayout());
		
		
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
			}
		});
		fen.getContentPane().add(validation, BorderLayout.SOUTH);
		fen.getContentPane().add(panGauche, BorderLayout.WEST);
		fen.getContentPane().add(panCentre, BorderLayout.CENTER);
		fen.getContentPane().add(panDroite, BorderLayout.EAST);
		fen.pack();
		fen.setLocationRelativeTo(null);
		fen.setVisible(true);
		//la tempo pour attendre que le bouton soit appuyé
		do {
			System.out.println();
		} while (!validerParametre);*/
		
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
