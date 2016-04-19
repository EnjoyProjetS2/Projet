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

			//parametres();

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
						p.getPlateau().println("Equipe " + equipe + " - Cliquez sur un de vos navires ou personnages:");
					}

					System.out.println("Equipe " + equipe + " - Cliquez sur un navire ou un personnage:");

					p.getPlateau().waitEvent();
					int clicX = p.getPlateau().getPosX();
					int clicY = p.getPlateau().getPosY();

					if (ile.getGrille()[clicY][clicX] instanceof Personnage) {
						
						Personnage perso = (Personnage) ile.getGrille()[clicY][clicX];
						System.out.println("Vie de ce perso: " + perso.getEnergie());
						if (perso.getEquipe().getID() == equipe) {

							if (choisir(ile.getGrille()[clicY][clicX]).equals("Deplacement")) {
								while (!ile.deplacement(perso, direction())) {

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

					System.out.println(ile.toString());
					p.setJeu(ile.getGrille());
					p.affichage();

				}
				
				
				

				if (equipe == 1) {
					equipe = 2;
				} else if (equipe == 2) {
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
	private boolean action(Personnage perso, Ile ile) {
		Rocher haut = null, bas = null, gauche = null, droite = null;
		Personnage phaut = null, pbas = null, pgauche = null, pdroite = null;
		
		
		//action de l'explorateur
		if(perso instanceof Explorateur){
			String[] roche = new String[4];
			int nb = 0;
			if(ile.getGrille()[perso.getX()][perso.getY()-1] instanceof Rocher){
				haut = (Rocher) ile.getGrille()[perso.getX()][perso.getY()-1];
				roche[nb] = "Haut";
				nb++;
			}
			
			if(ile.getGrille()[perso.getX()][perso.getY()+1] instanceof Rocher){
				bas = (Rocher) ile.getGrille()[perso.getX()][perso.getY()+1];
				roche[nb] = "Bas";
				nb++;
			}
			
			if(ile.getGrille()[perso.getX()-1][perso.getY()] instanceof Rocher){
				gauche = (Rocher) ile.getGrille()[perso.getX()-1][perso.getY()];
				roche[nb] = "Gauche";
				nb++;
			}
			
			if(ile.getGrille()[perso.getX()+1][perso.getY()] instanceof Rocher){
				droite = (Rocher) ile.getGrille()[perso.getX()+1][perso.getY()];
				roche[nb] = "Droite";
			}
			
			String aSoulever = (String) JOptionPane.showInputDialog(null, "Quel rocher soulever ?", "Rocher Ã  soulever",
					JOptionPane.DEFAULT_OPTION, null, roche, roche[0]);
			
			//faire l'action
			if(aSoulever == "Haut"){
				return ((Explorateur) perso).souleverRocher(haut);
			} else if(aSoulever == "Bas"){
				return ((Explorateur) perso).souleverRocher(bas);
			} else if(aSoulever == "Gauche"){
				return ((Explorateur) perso).souleverRocher(gauche);
			} else if(aSoulever == "Droite"){
				return ((Explorateur) perso).souleverRocher(droite);
			}

			
			//action du voleur
		} else if(perso instanceof Voleur){
			String[] adversaire = new String[4];
			int nb = 0;
			if(ile.getGrille()[perso.getX()][perso.getY()-1] instanceof Explorateur){
				phaut = (Personnage) ile.getGrille()[perso.getX()][perso.getY()-1];
				adversaire[nb] = "Haut";
				nb++;
			}
			
			if(ile.getGrille()[perso.getX()][perso.getY()+1] instanceof Explorateur){
				pbas = (Personnage) ile.getGrille()[perso.getX()][perso.getY()+1];
				adversaire[nb] = "Bas";
				nb++;
			}
			
			if(ile.getGrille()[perso.getX()-1][perso.getY()] instanceof Explorateur){
				pgauche = (Personnage) ile.getGrille()[perso.getX()-1][perso.getY()];
				adversaire[nb] = "Gauche";
				nb++;
			}
			
			if(ile.getGrille()[perso.getX()+1][perso.getY()] instanceof Explorateur){
				pdroite = (Personnage) ile.getGrille()[perso.getX()+1][perso.getY()];
				adversaire[nb] = "Droite";
			}
			
			String aVoler = (String) JOptionPane.showInputDialog(null, "Qui voler ?", "Personnage Ã  voler : ",
					JOptionPane.DEFAULT_OPTION, null, adversaire, adversaire[0]);
			
			//faire l'action
			if(aVoler == "Haut"){
				return ((Voleur) perso).voler(phaut);
			} else if(aVoler == "Bas"){
				return ((Voleur) perso).voler(pbas);
			} else if(aVoler == "Gauche"){
				return ((Voleur) perso).voler(pgauche);
			} else if(aVoler == "Droite"){
				return ((Voleur) perso).voler(pdroite);
			}	
			
		}
		return false;

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
