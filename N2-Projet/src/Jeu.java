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

import jdk.internal.dynalink.DefaultBootstrapper;

public class Jeu {

	static int nbPerso = 1; // nombre de personnages par equipes
	static int pourcentageRocher = 10; // pourcentage de rochers sur la carte
	static int tailleX = 10; // longueur
	static int tailleY = 10; // largeur
	static int maxVie = 100; // vie des personnages
	static int regenParTour = 10; // vie regeneree par tour dans les navires
	static boolean validerParametre = false;
	static Equipe un;
	static Equipe deux;
	boolean modeCreatif = false;
	static boolean solo = false;

	/**
	 * Constructeur: cree la partie, la parametre, cree les equipes, leurs
	 * joueurs et s'occupe du deroulement du jeu
	 * 
	 */
	public Jeu() {

		if (accueil()) {

			parametres();

			this.un = new Equipe(null, 1);
			this.deux = new Equipe(null, 2);

			Ile ile = new Ile(new Parcelle[tailleX][tailleY], pourcentageRocher);

			saisieEquipe(un);
			saisieEquipe(deux);

			// Affiche les membres des �quipes
			un.afficherEquipe();
			deux.afficherEquipe();

			// System.out.println(ile.toString()); // Affichage texte
			SuperPlateau p = new SuperPlateau(ile); // Affichage graphique

			if (this.modeCreatif) {

				p.setJeu(ile.getGrille());
				p.affichage();

				modeCreatif(p, ile, un);
				modeCreatif(p, ile, deux);
			} else {
				// Les membres des �quipes se dirigent dans leurs bateaux
				// respectifs
				un.getNavire().embarquement();
				deux.getNavire().embarquement();
			}

			Random alea = new Random();
			int equipe = alea.nextInt(2) + 1;

			informations(un, p);
			informations(deux, p);

			p.getPlateau().println("D�but de la partie !");

			if (un.getID() == equipe) {
				p.setJeu(un.setVision(ile));
			} else if (deux.getID() == equipe) {
				p.setJeu(deux.setVision(ile));
			}
			p.affichage();

			boolean coffreAuBateau = false;
			boolean equipeMorte = false;

			while (!coffreAuBateau && !equipeMorte) {

				soigner(un);
				soigner(deux);

				p.getPlateau().println("Equipe " + equipe + " - Cliquez sur un de vos navires ou personnages:");
				System.out.println("Equipe " + equipe + " - Cliquez sur un navire ou un personnage:");

				boolean joue = false;

				while (!joue) {

					if (this.solo && equipe == 2) {
						modeIA(p, ile, deux);
						joue = true;
					} else {
						p.getPlateau().waitEvent();
						int clicX = p.getPlateau().getPosX();
						int clicY = p.getPlateau().getPosY();

						if (ile.getGrille()[clicY][clicX] instanceof Personnage) {

							Personnage perso = (Personnage) ile.getGrille()[clicY][clicX];
							p.getPlateau().println(perso.toString());
							if (perso.getEquipe().getID() == equipe) {

								if (choisir(ile.getGrille()[clicY][clicX]).equals("Deplacement")) {
									while (!ile.deplacement(perso, direction(perso))) {

										p.getPlateau().println("Erreur: la parcelle n'est pas traversable");
										System.out.println("Erreur: la parcelle n'est pas traversable");
									}
									joue = true;
								} else {
									action(perso, ile);
									joue = true;
								}

							} else {

								p.getPlateau().println("Erreur: ce personnage appartient a l'autre equipe");
								System.out.println("Erreur: ce personnage appartient a l'autre equipe");
							}

						} else if (ile.getGrille()[clicY][clicX] instanceof Navire) {

							Navire nav = (Navire) ile.getGrille()[clicY][clicX];
							p.getPlateau().println(nav.toString());

							if (nav.getEquipe().getID() == equipe) {

								if (!nav.estVide()) {

									choisir(ile.getGrille()[clicY][clicX]);

									Personnage[] choix = new Personnage[nav.getPersoDansNavire().size()];
									for (int i = 0; i < choix.length; i++) {
										choix[i] = nav.getPersoDansNavire().get(i);
									}

									Personnage perso = (Personnage) JOptionPane.showInputDialog(null, "Que faire:",
											"Que faire ?", JOptionPane.DEFAULT_OPTION, null, choix, choix[0]);

									while (!ile.debarquement(perso, direction(perso))) {
										p.getPlateau().println("Erreur: la parcelle n'est pas traversable");
										System.out.println("Erreur: la parcelle n'est pas traversable");
									}
									joue = true;
								} else {
									p.getPlateau().println("Erreur: le navire est vide");

									System.out.println("Le navire est vide");
								}

							} else {
								p.getPlateau().println("Erreur: ce navire appartient a l'autre equipe");

								System.out.println("Erreur: ce navire appartient a l'autre equipe");
							}

						}

						if (!un.survie() || !deux.survie()) {
							equipeMorte = true;
						} else if (un.getNavire().presenceDuCoffre() || deux.getNavire().presenceDuCoffre()) {
							coffreAuBateau = true;
						} else {
							tuer(un, ile);
							tuer(deux, ile);
						}

					} // fin du tour
				}
				if (equipe == 1) {
					equipe = 2;
				} else if (equipe == 2) {
					equipe = 1;
				}

				if (un.getID() == equipe) {
					p.setJeu(un.setVision(ile));
				} else if (deux.getID() == equipe) {
					p.setJeu(deux.setVision(ile));
				}
				p.affichage();

			} // fin du jeu

			int gagnant = 0;

			System.out.println("test");

			// Victoire par mort de l'equipe adverse
			if (!un.survie()) {
				p.getPlateau().println("Tous les personnages de l'equipe " + un.getNom() + " sont morts.");
				gagnant = 2;
			} else if (!deux.survie()) {
				p.getPlateau().println("Tous les personnages de l'equipe " + deux.getNom() + " sont morts.");
				gagnant = 1;
			}

			// Victoire par depot du tresor
			if (un.getNavire().presenceDuCoffre()) {
				p.getPlateau().println("L'equipe " + un.getNom() + " a ramene le tresor !");
				gagnant = 1;
			} else if (deux.getNavire().presenceDuCoffre()) {
				p.getPlateau().println("L'equipe " + deux.getNom() + " a ramene le tresor !");
				gagnant = 2;
			}

			if (gagnant == 1) {
				p.getPlateau().println("L'equipe " + un.getNom() + " remporte la partie !");
			} else if (gagnant == 2) {
				p.getPlateau().println("L'equipe " + deux.getNom() + " remporte la partie !");
			} else {
				p.getPlateau().println("Erreur : mais que font les developpeurs ?");
			}

		}
	}

	/**
	 * Affiche la liste des informations utiles en debut de jeu (nom des equipes
	 * et personnages qui la composent)
	 * 
	 * @param e
	 * @param p
	 */
	private void informations(Equipe e, SuperPlateau p) {

		p.getPlateau().println("Equipe: " + e.getNom());

		for (int i = 0; i < e.getListePersos().size(); i++) {
			p.getPlateau().println(e.getListePersos().get(i).toString());
		}

		p.getPlateau().println("-------------------------");

	}

	/**
	 * Fenetre qui demande a l'utilisateur de choisir l'action qu'il veut
	 * effectuer
	 * 
	 * @param parcelle
	 * @return choix
	 */
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

	/**
	 * Mise en place du mode creatif pour placer ses personnages
	 * 
	 * @param p
	 * @param ile
	 * @param equipe
	 */
	private void modeCreatif(SuperPlateau p, Ile ile, Equipe equipe) {
		p.getPlateau().println("[Mode Creatif] C'est a l'equipe " + equipe.getNom() + " de placer ses personnages:");
		int cpt = 0;
		while (cpt < equipe.getListePersos().size()) {
			/*
			 * String[] tabChoix = { "Explorateur", "Voleur" }; String choix =
			 * (String) JOptionPane.showInputDialog(null,
			 * "Choisir un perso a placer (equipe " + equipe.getID() + " )",
			 * "Mode Creatif", JOptionPane.DEFAULT_OPTION, null, tabChoix,
			 * tabChoix[0]);
			 */
			p.getPlateau().println("[Mode Creatif] Clique ou tu veux placer: " + equipe.getListePersos().get(cpt));
			p.getPlateau().waitEvent();
			if (ile.getGrille()[p.getPlateau().getPosY()][p.getPlateau().getPosX()] instanceof Sable) {
				ile.getGrille()[p.getPlateau().getPosY()][p.getPlateau().getPosX()] = equipe.getListePersos().get(cpt);
				equipe.getListePersos().get(cpt).setX(p.getPlateau().getPosY());
				equipe.getListePersos().get(cpt).setY(p.getPlateau().getPosX());
				cpt++;
			}
			p.setJeu(ile.getGrille());
			p.affichage();
		}

	}

	/**
	 * Choix de la direction de l'intelligence artificielle
	 * 
	 * @param perso
	 * @param ile
	 * @param choixMouvement
	 * @return direction
	 */
	private String mouvementIA(Personnage perso, Ile ile, int choixMouvement) {
		if (choixMouvement == 1) {
			return "haut";
		} else if (choixMouvement == 2) {
			return "bas";
		} else if (choixMouvement == 3) {
			return "gauche";
		} else if (choixMouvement == 4) {
			return "droite";
		}

		return "Impossible";
	}

	/**
	 * Mise en place de l'intelligence artificielle
	 * 
	 * @param p
	 * @param ile
	 * @param equipe
	 * @return
	 */
	private boolean modeIA(SuperPlateau p, Ile ile, Equipe equipe) {
		Random rand = new Random();

		int choixPerso = rand.nextInt(equipe.getListePersos().size());
		int choixAction = rand.nextInt(5);
		int choixMouvement = rand.nextInt(4) + 1;
		Personnage perso;

		// si le navire est plein, on doit debarquer un perso
		if (equipe.getNavire().getPersoDansNavire().size() == equipe.getListePersos().size()) {
			perso = equipe.getNavire().getPersoDansNavire().get(choixPerso); // un
																				// personnage
																				// au
																				// hasard
																				// dans
																				// le
																				// navire
			ile.debarquement(perso, mouvementIA(perso, ile, choixMouvement));
		} else { // sinon, on fait n'importe quelle autre action
			// 3 chances sur 5 de se deplacer
			perso = equipe.getListePersos().get(choixPerso);

			if (choixAction == 0 || choixAction == 1 || choixAction == 2) {
				// deplacer un perso PRESENT SUR LA MAP
				ile.deplacement(perso, mouvementIA(perso, ile, choixMouvement));
			} else if (choixAction == 3) {
				// faire une action
			} else {
				// debarquer un personnage
				perso = equipe.getNavire().getPersoDansNavire().get(choixPerso);
				ile.debarquement(perso, mouvementIA(perso, ile, choixMouvement));
			}

		}

		return true;

	}

	/**
	 * Choix de la direction demandee a l'utilisateur
	 * 
	 * @return String : direction
	 */
	private String direction(Personnage p) {

		String avis;

		if (p instanceof Guerrier || p instanceof Piegeur) {
			String[] direction = { "Ouest", "Est", "Nord", "Sud", "Nord-Ouest", "Nord-Est", "Sud-Ouest", "Sud-Est" };
			avis = (String) JOptionPane.showInputDialog(null, "Que faire:", "D�placement du personnage",
					JOptionPane.DEFAULT_OPTION, null, direction, direction[0]);
		} else {
			String[] direction = { "Ouest", "Est", "Nord", "Sud" };
			avis = (String) JOptionPane.showInputDialog(null, "Que faire:", "D�placement du personnage",
					JOptionPane.DEFAULT_OPTION, null, direction, direction[0]);
		}

		if (avis.equals("Nord")) {
			return "gauche";
		} else if (avis.equals("Sud")) {
			return "droite";
		} else if (avis.equals("Ouest")) {
			return "haut";
		} else if (avis.equals("Est")) {
			return "bas";
		} else if (avis.equals("Nord-Ouest")) {
			return "hautgauche";
		} else if (avis.equals("Nord-Est")) {
			return "basgauche";
		} else if (avis.equals("Sud-Ouest")) {
			return "hautdroite";
		} else if (avis.equals("Sud-Est")) {
			return "basdroite";
		}

		return "faux";
	}

	/**
	 * Soigne les personnages dans le navire
	 * 
	 * @param e
	 */
	private void soigner(Equipe e) {

		for (int i = 0; i < e.getNavire().getPersoDansNavire().size(); i++) {

			if (e.getNavire().getPersoDansNavire().get(i).getEnergie() < maxVie - regenParTour
					&& e.getNavire().getPersoDansNavire().get(i).getEnergie() >= 1) {
				e.getNavire().getPersoDansNavire().get(i)
						.setEnergie(e.getNavire().getPersoDansNavire().get(i).getEnergie() + regenParTour);
			} else {
				e.getNavire().getPersoDansNavire().get(i).setEnergie(maxVie);
			}

		}

	}

	/**
	 * Tue un personnage
	 * 
	 * @param e
	 * @param ile
	 */
	private void tuer(Equipe e, Ile ile) {

		for (int i = 0; i < e.getListePersos().size(); i++) {

			if (e.getListePersos().get(i).getEnergie() <= 0) {

				if (e.getListePersos().get(i).getX() == e.getNavire().getX()
						&& e.getListePersos().get(i).getY() == e.getNavire().getY()) {
					e.getNavire().getPersoDansNavire().remove(i);
				} else {
					ile.getGrille()[e.getListePersos().get(i).getX()][e.getListePersos().get(i).getY()] = new Sable();
				}
				e.getListePersos().remove(i);
			}
		}

	}

	/**
	 * Menu d'accueil de choix de jeu
	 * 
	 * @return
	 */
	private boolean accueil() {

		String[] accueil = { "Jouer", "Regles", "Quitter" };

		String choix = (String) JOptionPane.showInputDialog(null, "Que faire:", "Bienvenue", JOptionPane.DEFAULT_OPTION,
				null, accueil, accueil[0]);

		if (choix.equals("Jouer")) {
			return true;
		} else if (choix.equals("Regles")) {
			JOptionPane.showMessageDialog(null,
					"Le but du jeu est de trouver la cl� cach�e sous un rocher et de trouver le coffre puis ramener le tr�sor sur son navire. ",
					"Regles du Jeu", JOptionPane.DEFAULT_OPTION);
			return accueil();
		} else {
			return false;
		}

	}

	/**
	 * Initialisation des parametres par l'utilisateur
	 */
	private void parametres() {

		String[] mode = { "1 contre 1", "1 contre 1 (Mode creatif)", "Mode solo" };

		String choix = (String) JOptionPane.showInputDialog(null, "Choisissez un mode de jeu:", "Parametres",
				JOptionPane.DEFAULT_OPTION, null, mode, mode[0]);
		JOptionPane.showMessageDialog(null, "Bien enregistr� !", "Information", JOptionPane.DEFAULT_OPTION);

		if (choix.equals("1 contre 1 (Mode creatif)")) {
			this.modeCreatif = true;
		} else if (choix.equals("Mode solo")) {
			this.solo = true;
		}

		// JSLIDE:
		// ajouter taille de la carte
		// ajouter nombre de joueur par equipe
		// ajouter pourcentage de rochers
		JFrame fen = new JFrame("Parametres de partie");
		fen.setPreferredSize(new Dimension(500, 500));
		fen.getContentPane().setLayout(new BorderLayout());
		JPanel pangauche = new JPanel();
		JPanel panCentre = new JPanel();
		JPanel pandroite = new JPanel();
		JPanel panhaut = new JPanel();
		JButton validation = new JButton("Valider");
		// Panhaut
		JLabel labhaut = new JLabel("Les parametres");
		// ajout panhaut
		panhaut.add(labhaut);
		// pangauche
		JLabel labgauche = new JLabel("Taille");
		JSlider slidergauche = new JSlider(JSlider.VERTICAL, 5, 15, 10);
		slidergauche.setMajorTickSpacing(10);
		slidergauche.setMinorTickSpacing(1);
		slidergauche.setPaintTicks(true);
		slidergauche.setPaintLabels(true);

		// ajout a pangauche
		pangauche.setLayout(new BorderLayout());
		pangauche.add(slidergauche, BorderLayout.CENTER);
		pangauche.add(labgauche, BorderLayout.NORTH);

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
		JSlider sliderdroite = new JSlider(JSlider.VERTICAL, 0, 40, 10);
		sliderdroite.setMajorTickSpacing(40);
		sliderdroite.setMinorTickSpacing(0);
		sliderdroite.setPaintTicks(true);
		sliderdroite.setPaintLabels(true);
		pandroite.setLayout(new BorderLayout());
		pandroite.add(labDroit, BorderLayout.NORTH);
		pandroite.add(sliderdroite, BorderLayout.CENTER);
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
		fen.getContentPane().add(pangauche, BorderLayout.WEST);
		fen.getContentPane().add(panCentre, BorderLayout.CENTER);
		fen.getContentPane().add(pandroite, BorderLayout.EAST);
		fen.getContentPane().add(panhaut, BorderLayout.NORTH);
		fen.pack();
		fen.setLocationRelativeTo(null);
		fen.setVisible(true);
		// la tempo pour attendre que le bouton soit appuy� sans bouffer tout le
		// proco
		do {
			System.out.println();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (!validerParametre);
		// modification desparametres
		nbPerso = sliderCentre.getValue();
		pourcentageRocher = sliderdroite.getValue();
		tailleX = slidergauche.getValue();
		tailleY = tailleX;
		// System.out.println(nbPerso + " " + pourcentageRocher + " " +
		// tailleX);
	}

	/**
	 * Retourne vrai si l'action est realisee Action du personnage selectionne
	 * 
	 * @param perso
	 * @param ile
	 * @return boolean
	 */
	private boolean action(Personnage perso, Ile ile) {
		Rocher haut = null, bas = null, gauche = null, droite = null;
		Sable shaut = null, sbas = null, sgauche = null, sdroite = null;
		Personnage phaut = null, pbas = null, pgauche = null, pdroite = null;

		// action de l'explorateur
		if (perso instanceof Explorateur) {
			String[] roche = new String[4];
			int nb = 0;
			if (ile.getGrille()[perso.getX()][perso.getY() - 1] instanceof Rocher) {
				haut = (Rocher) ile.getGrille()[perso.getX()][perso.getY() - 1];
				roche[nb] = "Nord";
				nb++;
			}

			if (ile.getGrille()[perso.getX()][perso.getY() + 1] instanceof Rocher) {
				bas = (Rocher) ile.getGrille()[perso.getX()][perso.getY() + 1];
				roche[nb] = "Sud";
				nb++;
			}

			if (ile.getGrille()[perso.getX() - 1][perso.getY()] instanceof Rocher) {
				gauche = (Rocher) ile.getGrille()[perso.getX() - 1][perso.getY()];
				roche[nb] = "Ouest";
				nb++;
			}

			if (ile.getGrille()[perso.getX() + 1][perso.getY()] instanceof Rocher) {
				droite = (Rocher) ile.getGrille()[perso.getX() + 1][perso.getY()];
				roche[nb] = "Est";
			}

			String aSoulever = (String) JOptionPane.showInputDialog(null, "Quel rocher soulever ?",
					"Rocher a� soulever", JOptionPane.DEFAULT_OPTION, null, roche, roche[0]);

			// faire l'action
			if (aSoulever == "Nord") {
				return ((Explorateur) perso).souleverRocher(haut);
			} else if (aSoulever == "Sud") {
				return ((Explorateur) perso).souleverRocher(bas);
			} else if (aSoulever == "Ouest") {
				return ((Explorateur) perso).souleverRocher(gauche);
			} else if (aSoulever == "Est") {
				return ((Explorateur) perso).souleverRocher(droite);
			}

			// action du voleur
		} else if (perso instanceof Voleur) {
			String[] adversaire = new String[4];
			int nb = 0;
			if (ile.getGrille()[perso.getX()][perso.getY() - 1] instanceof Explorateur) {
				phaut = (Personnage) ile.getGrille()[perso.getX()][perso.getY() - 1];
				adversaire[nb] = "Nord";
				nb++;
			}

			if (ile.getGrille()[perso.getX()][perso.getY() + 1] instanceof Explorateur) {
				pbas = (Personnage) ile.getGrille()[perso.getX()][perso.getY() + 1];
				adversaire[nb] = "Sud";
				nb++;
			}

			if (ile.getGrille()[perso.getX() - 1][perso.getY()] instanceof Explorateur) {
				pgauche = (Personnage) ile.getGrille()[perso.getX() - 1][perso.getY()];
				adversaire[nb] = "Ouest";
				nb++;
			}

			if (ile.getGrille()[perso.getX() + 1][perso.getY()] instanceof Explorateur) {
				pdroite = (Personnage) ile.getGrille()[perso.getX() + 1][perso.getY()];
				adversaire[nb] = "Est";
			}

			String aVoler = (String) JOptionPane.showInputDialog(null, "Qui voler ?", "Personnage a� voler : ",
					JOptionPane.DEFAULT_OPTION, null, adversaire, adversaire[0]);

			// faire l'action
			if (aVoler == "Nord") {
				return ((Voleur) perso).voler(phaut);
			} else if (aVoler == "Sud") {
				return ((Voleur) perso).voler(pbas);
			} else if (aVoler == "Ouest") {
				return ((Voleur) perso).voler(pgauche);
			} else if (aVoler == "Est") {
				return ((Voleur) perso).voler(pdroite);
			}

		} else if (perso instanceof Guerrier) {
			String[] adversaire = new String[4];
			int nb = 0;
			if (ile.getGrille()[perso.getX()][perso.getY() - 1] instanceof Personnage) {
				phaut = (Personnage) ile.getGrille()[perso.getX()][perso.getY() - 1];
				adversaire[nb] = "Nord";
				nb++;
			}

			if (ile.getGrille()[perso.getX()][perso.getY() + 1] instanceof Personnage) {
				pbas = (Personnage) ile.getGrille()[perso.getX()][perso.getY() + 1];
				adversaire[nb] = "Sud";
				nb++;
			}

			if (ile.getGrille()[perso.getX() - 1][perso.getY()] instanceof Personnage) {
				pgauche = (Personnage) ile.getGrille()[perso.getX() - 1][perso.getY()];
				adversaire[nb] = "Ouest";
				nb++;
			}

			if (ile.getGrille()[perso.getX() + 1][perso.getY()] instanceof Personnage) {
				pdroite = (Personnage) ile.getGrille()[perso.getX() + 1][perso.getY()];
				adversaire[nb] = "Est";
			}

			String aAttaquer = (String) JOptionPane.showInputDialog(null, "Qui attaquer ?", "Personnage a attaquer : ",
					JOptionPane.DEFAULT_OPTION, null, adversaire, adversaire[0]);

			// faire l'action
			if (aAttaquer == "Nord") {
				return ((Guerrier) perso).attaquer(phaut);
			} else if (aAttaquer == "Sud") {
				return ((Guerrier) perso).attaquer(pbas);
			} else if (aAttaquer == "Ouest") {
				return ((Guerrier) perso).attaquer(pgauche);
			} else if (aAttaquer == "Est") {
				return ((Guerrier) perso).attaquer(pdroite);
			}

		} else if (perso instanceof Piegeur) {
			String[] adversaire = new String[4];
			int nb = 0;
			if (ile.getGrille()[perso.getX()][perso.getY() - 1] instanceof Sable) {
				shaut = (Sable) ile.getGrille()[perso.getX()][perso.getY() - 1];
				adversaire[nb] = "Nord";
				nb++;
			}

			if (ile.getGrille()[perso.getX()][perso.getY() + 1] instanceof Sable) {
				sbas = (Sable) ile.getGrille()[perso.getX()][perso.getY() + 1];
				adversaire[nb] = "Sud";
				nb++;
			}

			if (ile.getGrille()[perso.getX() - 1][perso.getY()] instanceof Sable) {
				sgauche = (Sable) ile.getGrille()[perso.getX() - 1][perso.getY()];
				adversaire[nb] = "Ouest";
				nb++;
			}

			if (ile.getGrille()[perso.getX() + 1][perso.getY()] instanceof Sable) {
				sdroite = (Sable) ile.getGrille()[perso.getX() + 1][perso.getY()];
				adversaire[nb] = "Est";
			}

			String aPieger = (String) JOptionPane.showInputDialog(null, "Quelle parcelle pieger ?",
					"Parcelle a pieger : ", JOptionPane.DEFAULT_OPTION, null, adversaire, adversaire[0]);

			// faire l'action
			if (aPieger == "Nord") {
				return ((Piegeur) perso).pieger(shaut);
			} else if (aPieger == "Sud") {
				return ((Piegeur) perso).pieger(sbas);
			} else if (aPieger == "Ouest") {
				return ((Piegeur) perso).pieger(sgauche);
			} else if (aPieger == "Est") {
				return ((Piegeur) perso).pieger(sdroite);
			}
		}

		return false;

	}

	/**
	 * Menu de dialogue avec l'utilisateur pour selectionner son equipe
	 * 
	 * @param e
	 */
	private void saisieEquipe(Equipe e) {

		String[] personnages = { "Explorateur", "Voleur", "Guerrier", "Piegeur" };

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
			} else if (classe.equals("Guerrier")) {
				e.ajoutPersonnage(new Guerrier(JOptionPane.showInputDialog("Quel est le nom de ce guerrier?"), e,
						e.getNavire().getX(), e.getNavire().getY()));
			} else if (classe.equals("Piegeur")) {
				e.ajoutPersonnage(new Piegeur(JOptionPane.showInputDialog("Quel est le nom de ce piegeur?"), e,
						e.getNavire().getX(), e.getNavire().getY()));
			}

			cpt++;
		}
		JOptionPane.showMessageDialog(null, "Bien enregistr� !", "Information", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {

		new Jeu();

	}

}
