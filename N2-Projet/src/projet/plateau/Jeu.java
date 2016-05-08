package projet.plateau;

import java.util.Random;

import javax.swing.JOptionPane;

import projet.graphique.Action;
import projet.graphique.ChoixModeDeJeu;
import projet.graphique.ChoixPersonnage;
import projet.graphique.Direction;
import projet.graphique.MenuDemarre;
import projet.graphique.ParametreGraph;
import projet.graphique.SaisieEquipe;
import projet.ia.TourOrdinateur;
import projet.parcelle.Equipe;
import projet.parcelle.Explorateur;
import projet.parcelle.Guerrier;
import projet.parcelle.Navire;
import projet.parcelle.Parcelle;
import projet.parcelle.Personnage;
import projet.parcelle.Piegeur;
import projet.parcelle.Rocher;
import projet.parcelle.Sable;
import projet.parcelle.Voleur;

public class Jeu {

	public static int nbPerso = 1; // nombre de personnages par equipes
	public static int pourcentageRocher = 10; // pourcentage de rochers sur la
												// carte
	public static int tailleX = 10; // longueur
	public static int tailleY = 10; // largeur
	public static int maxVie = 100; // vie des personnages
	public static int regenParTour = 10; // vie regeneree par tour dans les
											// navires
	public static Equipe un;
	public static Equipe deux;
	public static boolean modeCreatif = false;
	public static boolean solo = false;

	/**
	 * Constructeur: cree la partie, la parametre, cree les equipes, leurs
	 * joueurs et s'occupe du deroulement du jeu
	 * 
	 */
	public Jeu() {

		// if (accueil()) {
		new MenuDemarre();
		parametres();

		this.un = new Equipe(null, 1);
		this.deux = new Equipe(null, 2);

		Ile ile = new Ile(new Parcelle[tailleX][tailleY], pourcentageRocher);

		saisieEquipe(un);
		saisieEquipe(deux);
		/*MODE GRAPHIQUE (ne fonctionne pas)
		do {
			new SaisieEquipe(un);
			if (!SaisieEquipe.retry) {
				new SaisieEquipe(deux);
			}
		} while (!SaisieEquipe.retry);*/
		// System.out.println(ile.toString()); // Affichage texte
		SuperPlateau p = new SuperPlateau(ile); // Affichage graphique

		if (this.modeCreatif) {

			p.setJeu(ile.getGrille());
			p.affichage();

			modeCreatif(p, ile, un);
			modeCreatif(p, ile, deux);
		} else {
			// Les membres des équipes se dirigent dans leurs bateaux
			// respectifs
			un.getNavire().embarquement();
			deux.getNavire().embarquement();
		}

		Random alea = new Random();
		int equipe = alea.nextInt(2) + 1;

		informations(un, p);
		informations(deux, p);
		p.getPlateau().println("clear", equipe);
		if (un.getID() == equipe) {
			p.setJeu(un.setVision(ile));
			p.getPlateau().println("Début de la partie ! (equipe " + un.getID() + ")", un.getID());
		} else if (deux.getID() == equipe) {
			p.setJeu(deux.setVision(ile));
			p.getPlateau().println("Début de la partie ! (equipe " + deux.getID() + ")", deux.getID());
		}
		p.affichage();

		boolean coffreAuBateau = false;
		boolean equipeMorte = false;

		while (!coffreAuBateau && !equipeMorte) {

			if (equipe == 1) {
				informations(un, p);
				soigner(un);
			} else if (equipe == 2) {
				informations(deux, p);
				soigner(deux);
			}
			p.getPlateau().println("Cliquez sur un de vos navires ou personnages:", equipe);

			boolean joue = false;

			Direction direction = new Direction();
			while (!joue) {

				Action action = new Action(p.getPlateau());

				if (this.solo && equipe == 2) {
					new TourOrdinateur(ile, p.getPlateau(), Jeu.deux);
					//modeIA(p, ile, deux);
					joue = true;

				} else {
					p.getPlateau().waitEvent();
					int clicX = p.getPlateau().getPosX();
					int clicY = p.getPlateau().getPosY();

					if (ile.getGrille()[clicY][clicX] instanceof Personnage) {

						Personnage perso = (Personnage) ile.getGrille()[clicY][clicX];
						if (perso.getEquipe().getID() == equipe) {

							if (action.choix(p.getPlateau(), ile.getGrille()[clicY][clicX], equipe)
									.equals("Deplacement")) {

								Deplacement deplacement = new Deplacement(ile, perso);

								while (!deplacement
										.deplacement(direction.choixDeplacement(perso, ile, p.getPlateau()))) {
								}

								joue = true;
							} else if (action.choix(p.getPlateau(), ile.getGrille()[clicY][clicX], equipe)
									.equals("Action")) {
								action(perso, ile, p);
								joue = true;
							} else if ((action.choix(p.getPlateau(), ile.getGrille()[clicY][clicX], equipe)
									.equals("passer"))) {
								try {
									Thread.sleep(250);
								} catch (InterruptedException ex) {
									// TODO Auto-generated catch block
									ex.printStackTrace();
								}
								joue = true;
							}

						} else {

							p.getPlateau().println("Erreur: ce personnag appartient a l'autre equipe", equipe);
						}

					} else if (ile.getGrille()[clicY][clicX] instanceof Navire) {

						Navire nav = (Navire) ile.getGrille()[clicY][clicX];
						p.getPlateau().println(nav.toString(), equipe);

						if (nav.getEquipe().getID() == equipe) {

							if (!nav.estVide()) {

								Deplacement deplacement = new Deplacement(ile);

								action.choix(p.getPlateau(), ile.getGrille()[clicY][clicX], equipe);

								Personnage perso = new ChoixPersonnage(equipe).choix(p.getPlateau());

								deplacement.setPersonnage(perso);

								while (!deplacement
										.debarquement(direction.choixDeplacement(perso, ile, p.getPlateau()))) {
									p.getPlateau().println("Erreur: la parcelle n'est pas traversable", equipe);
								}
								joue = true;
							} else {
								p.getPlateau().println("Erreur: le navire est vide", equipe);
							}

						} else {
							p.getPlateau().println("Erreur: ce navire appartient a l'autre equipe", equipe);
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
				p.getPlateau().println("clear", equipe);
				equipe = 2;
				p.getPlateau().println("clear", equipe);
			} else if (equipe == 2) {
				p.getPlateau().println("clear", equipe);
				equipe = 1;
				p.getPlateau().println("clear", equipe);

			}

			if (un.getID() == equipe) {
				p.setJeu(un.setVision(ile));
			} else if (deux.getID() == equipe) {
				p.setJeu(deux.setVision(ile));
			}
			p.affichage();

		} // fin du jeu

		int gagnant = 0;

		// Victoire par mort de l'equipe adverse
		if (!un.survie()) {
			p.getPlateau().println("Tous les personnages de l'equipe " + un.getNom() + " sont morts.", un.getID());
			gagnant = 2;
		} else if (!deux.survie()) {
			p.getPlateau().println("Tous les personnages de l'equipe " + deux.getNom() + " sont morts.", deux.getID());
			gagnant = 1;
		}

		// Victoire par depot du tresor
		if (un.getNavire().presenceDuCoffre()) {
			p.getPlateau().println("L'equipe " + un.getNom() + " a ramene le tresor !", un.getID());
			gagnant = 1;
		} else if (deux.getNavire().presenceDuCoffre()) {
			p.getPlateau().println("L'equipe " + deux.getNom() + " a ramene le tresor !", deux.getID());
			gagnant = 2;
		}

		if (gagnant == 1) {
			p.getPlateau().println("L'equipe " + un.getNom() + " remporte la partie !", un.getID());
		} else if (gagnant == 2) {
			p.getPlateau().println("L'equipe " + deux.getNom() + " remporte la partie !", deux.getID());
		} else {
			p.getPlateau().println("Erreur : mais que font les developpeurs ?", un.getID());
		}

	}
	// }

	/**
	 * Affiche la liste des informations utiles en debut de jeu (nom des equipes
	 * et personnages qui la composent)
	 * 
	 * @param e
	 * @param p
	 */
	private void informations(Equipe e, SuperPlateau p) {

		p.getPlateau().println("Equipe: " + e.getNom(), e.getID());

		for (int i = 0; i < e.getListePersos().size(); i++) {
			p.getPlateau().println(e.getListePersos().get(i).toString(), e.getID());
		}

	}

	/**
	 * Mise en place du mode creatif pour placer ses personnages
	 * 
	 * @param p
	 * @param ile
	 * @param equipe
	 */
	private void modeCreatif(SuperPlateau p, Ile ile, Equipe equipe) {
		p.getPlateau().println("[Mode Creatif] C'est a l'equipe " + equipe.getNom() + " de placer ses personnages:",
				equipe.getID());
		int cpt = 0;
		while (cpt < equipe.getListePersos().size()) {

			p.getPlateau().println("[Mode Creatif] Clique ou tu veux placer: " + equipe.getListePersos().get(cpt),
					equipe.getID());
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
		int nombre;
		Personnage perso;

		// si le navire est plein, on doit debarquer un perso
		if (equipe.getNavire().getPersoDansNavire().size() == equipe.getListePersos().size()) {

			perso = equipe.getNavire().getPersoDansNavire().get(choixPerso);

			Deplacement deplacement = new Deplacement(ile, perso);

			deplacement.debarquement(mouvementIA(perso, ile, choixMouvement));
		} else { // sinon, on fait n'importe quelle autre action
			// 3 chances sur 5 de se deplacer
			perso = equipe.getListePersos().get(choixPerso);

			Deplacement deplacement = new Deplacement(ile, perso);

			if (choixAction == 0 || choixAction == 1 || choixAction == 2) {
				// deplacer un perso PRESENT SUR LA MAP
				deplacement.deplacement(mouvementIA(perso, ile, choixMouvement));
			} else if (choixAction == 3) {

				// selection d'un perso qui est sur l'ile
				for (Personnage personnage : equipe.getListePersos()) {
					for (Personnage personnageDans : equipe.getNavire().getPersoDansNavire()) {
						if (personnage == personnageDans) {
							perso = personnage;
						}
					}
				}

				if (perso instanceof Explorateur) {
					Rocher[] roche = new Rocher[4];
					int nb = 0;
					if (ile.getGrille()[perso.getX()][perso.getY() - 1] instanceof Rocher) {
						roche[nb] = (Rocher) ile.getGrille()[perso.getX()][perso.getY() - 1];
						nb++;
					}
					if (ile.getGrille()[perso.getX()][perso.getY() + 1] instanceof Rocher) {
						roche[nb] = (Rocher) ile.getGrille()[perso.getX()][perso.getY() + 1];
						nb++;
					}
					if (ile.getGrille()[perso.getX() - 1][perso.getY()] instanceof Rocher) {
						roche[nb] = (Rocher) ile.getGrille()[perso.getX() - 1][perso.getY()];
						nb++;
					}
					if (ile.getGrille()[perso.getX() + 1][perso.getY()] instanceof Rocher) {
						roche[nb] = (Rocher) ile.getGrille()[perso.getX() + 1][perso.getY()];
					}
					nombre = rand.nextInt(roche.length);
					if (nombre == 0) {
						return ((Explorateur) perso).souleverRocher(roche[0]);
					} else if (nombre == 1) {
						return ((Explorateur) perso).souleverRocher(roche[1]);
					} else if (nombre == 2) {
						return ((Explorateur) perso).souleverRocher(roche[2]);
					} else if (nombre == 3) {
						return ((Explorateur) perso).souleverRocher(roche[3]);
					}

				} else if (perso instanceof Voleur) {
					Explorateur[] explo = new Explorateur[4];
					int nb = 0;
					if (ile.getGrille()[perso.getX()][perso.getY() - 1] instanceof Explorateur) {
						explo[nb] = (Explorateur) ile.getGrille()[perso.getX()][perso.getY() - 1];
						nb++;
					}
					if (ile.getGrille()[perso.getX()][perso.getY() + 1] instanceof Explorateur) {
						explo[nb] = (Explorateur) ile.getGrille()[perso.getX()][perso.getY() + 1];
						nb++;
					}
					if (ile.getGrille()[perso.getX() - 1][perso.getY()] instanceof Explorateur) {
						explo[nb] = (Explorateur) ile.getGrille()[perso.getX() - 1][perso.getY()];
						nb++;
					}
					if (ile.getGrille()[perso.getX() + 1][perso.getY()] instanceof Explorateur) {
						explo[nb] = (Explorateur) ile.getGrille()[perso.getX() + 1][perso.getY()];
					}
					nombre = rand.nextInt(explo.length);
					if (nombre == 0) {
						return ((Voleur) perso).voler(explo[0]);
					} else if (nombre == 1) {
						return ((Voleur) perso).voler(explo[1]);
					} else if (nombre == 2) {
						return ((Voleur) perso).voler(explo[2]);
					} else if (nombre == 3) {
						return ((Voleur) perso).voler(explo[3]);
					}
				} else if (perso instanceof Guerrier) {
					Personnage[] personnage = new Personnage[4];
					int nb = 0;
					if (ile.getGrille()[perso.getX()][perso.getY() - 1] instanceof Personnage) {
						personnage[nb] = (Personnage) ile.getGrille()[perso.getX()][perso.getY() - 1];
						nb++;
					}
					if (ile.getGrille()[perso.getX()][perso.getY() + 1] instanceof Personnage) {
						personnage[nb] = (Personnage) ile.getGrille()[perso.getX()][perso.getY() + 1];
						nb++;
					}
					if (ile.getGrille()[perso.getX() - 1][perso.getY()] instanceof Personnage) {
						personnage[nb] = (Personnage) ile.getGrille()[perso.getX() - 1][perso.getY()];
						nb++;
					}
					if (ile.getGrille()[perso.getX() + 1][perso.getY()] instanceof Personnage) {
						personnage[nb] = (Personnage) ile.getGrille()[perso.getX() + 1][perso.getY()];
					}
					nombre = rand.nextInt(personnage.length);
					if (nombre == 0) {
						return ((Guerrier) perso).attaquer(personnage[0]);
					} else if (nombre == 1) {
						return ((Guerrier) perso).attaquer(personnage[1]);
					} else if (nombre == 2) {
						return ((Guerrier) perso).attaquer(personnage[2]);
					} else if (nombre == 3) {
						return ((Guerrier) perso).attaquer(personnage[3]);
					}
				} else if (perso instanceof Piegeur) {
					Sable[] sable = new Sable[4];
					int nb = 0;
					if (ile.getGrille()[perso.getX()][perso.getY() - 1] instanceof Sable) {
						sable[nb] = (Sable) ile.getGrille()[perso.getX()][perso.getY() - 1];
						nb++;
					}
					if (ile.getGrille()[perso.getX()][perso.getY() + 1] instanceof Sable) {
						sable[nb] = (Sable) ile.getGrille()[perso.getX()][perso.getY() + 1];
						nb++;
					}
					if (ile.getGrille()[perso.getX() - 1][perso.getY()] instanceof Sable) {
						sable[nb] = (Sable) ile.getGrille()[perso.getX() - 1][perso.getY()];
						nb++;
					}
					if (ile.getGrille()[perso.getX() + 1][perso.getY()] instanceof Sable) {
						sable[nb] = (Sable) ile.getGrille()[perso.getX() + 1][perso.getY()];
					}
					nombre = rand.nextInt(sable.length);
					if (nombre == 0) {
						return ((Piegeur) perso).pieger(sable[0]);
					} else if (nombre == 1) {
						return ((Piegeur) perso).pieger(sable[1]);
					} else if (nombre == 2) {
						return ((Piegeur) perso).pieger(sable[2]);
					} else if (nombre == 3) {
						return ((Piegeur) perso).pieger(sable[3]);
					}
				}
			} else {
				// debarquer un personnage
				perso = equipe.getNavire().getPersoDansNavire().get(choixPerso);
				Deplacement dep = new Deplacement(ile, perso);
				dep.debarquement(mouvementIA(perso, ile, choixMouvement));
			}

		}
		return true;
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
					"Le but du jeu est de trouver la clé cachée sous un rocher et de trouver le coffre puis ramener le trésor sur son navire. ",
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
		//Comme son nom l'indique
		new ChoixModeDeJeu();
		// JSLIDE:
		// ajouter taille de la carte
		// ajouter nombre de joueur par equipe
		// ajouter pourcentage de rochers
		new ParametreGraph();

	}

	/**
	 * Effectue une action par un personnage
	 * @param perso
	 * @param ile
	 * @param plato
	 * @return boolean
	 */
	private boolean action(Personnage perso, Ile ile, SuperPlateau plato) {

		if (perso instanceof Explorateur) {
			return ((Explorateur) perso).choixRocher(ile, new Direction().choixAction(perso, ile, plato.getPlateau()));
		} else if (perso instanceof Voleur) {
			return ((Voleur) perso).choixVoler(ile, new Direction().choixAction(perso, ile, plato.getPlateau()));
		} else if (perso instanceof Guerrier) {
			return ((Guerrier) perso).choixAttaquer(ile, new Direction().choixAction(perso, ile, plato.getPlateau()));
		} else if (perso instanceof Piegeur) {
			return ((Piegeur) perso).choixPieger(ile, new Direction().choixAction(perso, ile, plato.getPlateau()));
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
		JOptionPane.showMessageDialog(null, "Bien enregistré !", "Information", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {

		new Jeu();

	}

}
