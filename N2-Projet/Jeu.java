import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Jeu {

	static int nbPerso = 1;
	static int pourcentageRocher = 10;
	static int tailleX = 10;
	static int tailleY = 10;
	static boolean validerParametre = false;
	static Equipe un;
	static Equipe deux;

	/**
	 * Constructeur: cree la partie, la parametre, cree les equipes, leurs
	 * joueurs et s'occupe du deroulement du jeu
	 * 
	 */
	public Jeu() {

		if (accueil()) {

			// parametres();

			this.un = new Equipe(null, 1);
			this.deux = new Equipe(null, 2);

			Ile ile = new Ile(new Parcelle[tailleX][tailleY], pourcentageRocher);

			saisieEquipe(un);
			saisieEquipe(deux);

			// Affiche les membres des équipes
			un.afficherEquipe();
			deux.afficherEquipe();

			ile.getGrille()[8][1] = new Explorateur("billy", un, 8, 1);
			ile.getGrille()[7][8] = new Voleur("swag", deux, 7, 8);

			// Les membres des équipes se dirigent dans leurs bateaux respectifs
			un.getNavire().embarquement();
			deux.getNavire().embarquement();

			System.out.println(ile.toString()); // Affichage texte
			SuperPlateau p = new SuperPlateau(ile); // Affichage graphique
			p.setJeu(ile.getGrille());
			p.affichage();

			Random alea = new Random();
			int equipe = alea.nextInt(2) + 1;

			while (!un.getNavire().presenceDuCoffre() || !deux.getNavire().presenceDuCoffre()) {

				boolean joue = false;
				boolean restart = false;

				while (!joue) {

					if (restart == false) {
						JOptionPane.showMessageDialog(null,
								"Equipe " + equipe + " - Cliquez sur un de vos navires ou personnages:", "Jouer",
								JOptionPane.DEFAULT_OPTION);
					}

					System.out.println("Equipe " + equipe + " - Cliquez sur un navire ou un personnage:");

					p.getPlateau().waitEvent();
					int clicX = p.getPlateau().getPosX();
					int clicY = p.getPlateau().getPosY();

					if (ile.getGrille()[clicY][clicX] instanceof Personnage) {

						Personnage perso = (Personnage) ile.getGrille()[clicY][clicX];

						if (perso.getEquipe().getID() == equipe) {

							if (choisir(ile.getGrille()[clicY][clicX]).equals("Deplacement")) {
								while (!ile.deplacement(perso, direction())) {
									JOptionPane.showMessageDialog(null, "Erreur: la parcelle n'est pas traversable",
											"Erreur", JOptionPane.DEFAULT_OPTION);
									System.out.println("Erreur: la parcelle n'est pas traversable");
								}
								joue = true;
							} else {
								// action
								joue = true;
							}

						} else {
							JOptionPane.showMessageDialog(null, "Erreur: ce personnage appartient a l'autre equipe",
									"Erreur", JOptionPane.DEFAULT_OPTION);

							System.out.println("Erreur: ce personnage appartient a l'autre equipe");
							restart = true;
						}

					} else if (ile.getGrille()[clicY][clicX] instanceof Navire) {

						choisir(ile.getGrille()[clicY][clicX]);

						Navire nav = (Navire) ile.getGrille()[clicY][clicX];

						if (nav.getEquipe().getID() == equipe) {

							if (!nav.estVide()) {

								Personnage[] choix = new Personnage[nav.getPersoDansNavire().size()];
								for (int i = 0; i < choix.length; i++) {
									choix[i] = nav.getPersoDansNavire().get(i);
								}

								Personnage perso = (Personnage) JOptionPane.showInputDialog(null, "Que faire:",
										"Que faire ?", JOptionPane.DEFAULT_OPTION, null, choix, choix[0]);

								while (!ile.debarquement(perso, direction())) {
									System.out.println("Erreur: la parcelle n'est pas traversable");
								}
								joue = true;
							} else {
								System.out.println("Le navire est vide");
							}

						} else {
							JOptionPane.showMessageDialog(null, "Erreur: ce navire appartient a l'autre equipe",
									"Erreur", JOptionPane.DEFAULT_OPTION);

							System.out.println("Erreur: ce navire appartient a l'autre equipe");
							restart = true;
						}

					}

					System.out.println(ile.toString());
					p.setJeu(ile.getGrille());
					p.affichage();

				}
				
				
				

				if (equipe == 1 && !restart) {
					equipe = 2;
				} else if (equipe == 2 && !restart) {
					equipe = 1;
				}

			}

		}
	}

	private String choisir(Parcelle parcelle) {

		if (parcelle instanceof Personnage) {
			String[] choix = { "Deplacement", "Action" };
			return (String) JOptionPane.showInputDialog(null, "Que faire:", "Que faire ?", JOptionPane.DEFAULT_OPTION,
					null, choix, choix[0]);

		} else if (parcelle instanceof Navire) {

			String[] choix = { "Debarquer un personnage" };
			return (String) JOptionPane.showInputDialog(null, "Que faire:", "Que faire ?", JOptionPane.DEFAULT_OPTION,
					null, choix, choix[0]);

		}

		return null;

	}

	private String action() {

		return "a faire";

	}

	private String direction() {

		String[] direction = { "Gauche", "Droite", "Haut", "Bas" };

		String avis = (String) JOptionPane.showInputDialog(null, "Que faire:", "Déplacement du personnage",
				JOptionPane.DEFAULT_OPTION, null, direction, direction[0]);

		if (avis.equals("Haut")) {
			return "gauche";
		} else if (avis.equals("Bas")) {
			return "droite";
		} else if (avis.equals("Gauche")) {
			return "haut";
		} else if (avis.equals("Droite")) {
			return "bas";
		}

		return "faux";
	}

	/*
	 * public String menuDeplacement(){ JFrame fenetre = new JFrame(
	 * "Actions du personnage"); JButton gauche = new JButton("Gauche");
	 * //bouton gauche gauche.addMouseListener(new MouseListener() { public void
	 * mouseClicked(MouseEvent arg0) { choix = "gauche"; }
	 * 
	 * @Override public void mouseEntered(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseExited(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mousePressed(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseReleased(MouseEvent e) { // TODO
	 * Auto-generated method stub
	 * 
	 * } });
	 * 
	 * JButton droite = new JButton("Droite"); droite.addMouseListener(new
	 * MouseListener() { public void mouseClicked(MouseEvent arg0) { choix =
	 * "droite"; }
	 * 
	 * @Override public void mouseEntered(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseExited(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mousePressed(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseReleased(MouseEvent e) { // TODO
	 * Auto-generated method stub
	 * 
	 * } });
	 * 
	 * JButton haut = new JButton("Haut"); haut.addMouseListener(new
	 * MouseListener() { public void mouseClicked(MouseEvent arg0) { choix =
	 * "haut"; }
	 * 
	 * @Override public void mouseEntered(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseExited(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mousePressed(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseReleased(MouseEvent e) { // TODO
	 * Auto-generated method stub
	 * 
	 * } });
	 * 
	 * JButton bas = new JButton("Bas"); bas.addMouseListener(new
	 * MouseListener() { public void mouseClicked(MouseEvent arg0) { choix =
	 * "bas"; }
	 * 
	 * @Override public void mouseEntered(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseExited(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mousePressed(MouseEvent e) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void mouseReleased(MouseEvent e) { // TODO
	 * Auto-generated method stub
	 * 
	 * } });
	 * 
	 * 
	 * fenetre.setPreferredSize(new Dimension(500, 500));
	 * fenetre.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
	 * 
	 * //la fenetre fenetre.getContentPane().add(gauche);
	 * fenetre.getContentPane().add(droite); fenetre.getContentPane().add(haut);
	 * fenetre.getContentPane().add(bas);
	 * 
	 * fenetre.pack(); fenetre.setLocationRelativeTo(null);
	 * fenetre.setVisible(true);
	 * 
	 * return choix; }
	 */

	private boolean accueil() {

		String[] accueil = { "Jouer", "Regles", "Quitter" };

		String choix = (String) JOptionPane.showInputDialog(null, "Que faire:", "Bienvenue", JOptionPane.DEFAULT_OPTION,
				null, accueil, accueil[0]);

		if (choix.equals("Jouer")) {
			return true;
		} else if (choix.equals("Regles")) {
			JOptionPane.showMessageDialog(null,
					"Le but du jeu est de trouver la clé cachée sous un rocher et de trouver le coffre puis ramener le trésor sur son navire. ",
					"Regles du Jeu", JOptionPane.DEFAULT_OPTION);
			return accueil();
		} else {
			return false;
		}

	}

	private void parametres() {

		String[] mode = { "1 contre 1" };

		String choix = (String) JOptionPane.showInputDialog(null, "Choisissez un mode de jeu:", "Parametres",
				JOptionPane.DEFAULT_OPTION, null, mode, mode[0]);
		JOptionPane.showMessageDialog(null, "Bien enregistré !", "Information", JOptionPane.DEFAULT_OPTION);

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
		JLabel labCentre = new JLabel("Nombre de personnages");
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
		// System.out.println(nbPerso + " " + pourcentageRocher + " " +
		// tailleX);
	}

	private void saisieEquipe(Equipe e) {

		String[] personnages = { "Explorateur", "Voleur" };

		e.setNom(JOptionPane.showInputDialog("Equipe " + e.getID() + " - Entrez un nom d'equipe:"));

		e.ajoutPersonnage(

				new Explorateur(JOptionPane.showInputDialog("Quel est le nom de votre explorateur?"), e,
						e.getNavire().getX(), e.getNavire().getY()));

		int cpt = 1;
		while (cpt < nbPerso) {
			String classe = (String) JOptionPane.showInputDialog(null,
					"Choisissez votre " + (cpt + 1) + "eme personnage:",
					"Equipe " + e.getID() + " - Personnage " + (cpt + 1), JOptionPane.DEFAULT_OPTION, null, personnages,
					personnages[0]);

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
