package projet.graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import projet.parcelle.Explorateur;
import projet.parcelle.Guerrier;
import projet.parcelle.Personnage;
import projet.parcelle.Piegeur;
import projet.parcelle.Rocher;
import projet.parcelle.Sable;
import projet.parcelle.Voleur;
import projet.plateau.Ile;
import projet.plateau.Plateau;

public class Direction implements ActionListener {

	String choix = "";
	int nbChoix = 0;

	JButton gauche;
	JButton droite;
	JButton haut;
	JButton bas;

	JButton hautgauche;
	JButton hautdroite;
	JButton basgauche;
	JButton basdroite;

	/**
	 * Constructeur: cree les boutons de deplacement
	 * 
	 * @param Direction
	 */
	public Direction() {

		gauche = new JButton(new ImageIcon("images/boutons/gauche.png"));
		gauche.setSize(Tailles.BOUTONDIRx, Tailles.BOUTONDIRy);

		droite = new JButton(new ImageIcon("images/boutons/droite.png"));
		droite.setSize(Tailles.BOUTONDIRx, Tailles.BOUTONDIRy);

		haut = new JButton(new ImageIcon("images/boutons/haut.png"));
		haut.setSize(Tailles.BOUTONDIRx, Tailles.BOUTONDIRy);

		bas = new JButton(new ImageIcon("images/boutons/bas.png"));
		bas.setSize(Tailles.BOUTONDIRx, Tailles.BOUTONDIRy);

		hautgauche = new JButton(new ImageIcon("images/boutons/hautgauche.png"));
		hautgauche.setSize(Tailles.BOUTONDIRx, Tailles.BOUTONDIRy);

		hautdroite = new JButton(new ImageIcon("images/boutons/hautdroite.png"));
		hautdroite.setSize(Tailles.BOUTONDIRx, Tailles.BOUTONDIRy);

		basgauche = new JButton(new ImageIcon("images/boutons/basgauche.png"));
		basgauche.setSize(Tailles.BOUTONDIRx, Tailles.BOUTONDIRy);

		basdroite = new JButton(new ImageIcon("images/boutons/basdroite.png"));
		basdroite.setSize(Tailles.BOUTONDIRx, Tailles.BOUTONDIRy);

	}

	/**
	 * Affiche le plateau avec les fleches de direction
	 * @param plateau
	 * @param perso
	 * @return le plateau
	 */
	private Plateau afficher(Plateau plateau, Personnage perso) {

		Plateau p = plateau;

		gauche.setLocation(Tailles.PLATEAU + Tailles.CONSOLEx + Tailles.BORDURE*3 + Tailles.TAILLE*14 - Tailles.PARCELLE, 100);
		p.getWindow().getContentPane().add(gauche);

		droite.setLocation(Tailles.PLATEAU + Tailles.CONSOLEx + Tailles.BORDURE*3 + Tailles.TAILLE*14 + Tailles.PARCELLE, 100);
		p.getWindow().getContentPane().add(droite);

		haut.setLocation(Tailles.PLATEAU + Tailles.CONSOLEx + Tailles.BORDURE*3 + Tailles.TAILLE*14, 100 - 36);
		p.getWindow().getContentPane().add(haut);

		bas.setLocation(Tailles.PLATEAU + Tailles.CONSOLEx + Tailles.BORDURE*3 + Tailles.TAILLE*14, 100 + 36);
		p.getWindow().getContentPane().add(bas);

		nbChoix += 4;

		if (perso instanceof Guerrier || perso instanceof Piegeur) {

			hautgauche.setLocation(Tailles.PLATEAU + Tailles.CONSOLEx + Tailles.BORDURE*3 + Tailles.TAILLE*14 - Tailles.PARCELLE, 100 - Tailles.PARCELLE);
			p.getWindow().getContentPane().add(hautgauche);

			hautdroite.setLocation(Tailles.PLATEAU + Tailles.CONSOLEx + Tailles.BORDURE*3 + Tailles.TAILLE*14 + Tailles.PARCELLE, 100 - Tailles.PARCELLE);
			p.getWindow().getContentPane().add(hautdroite);

			basgauche.setLocation(Tailles.PLATEAU + Tailles.CONSOLEx + Tailles.BORDURE*3+ Tailles.TAILLE*14 - Tailles.PARCELLE, 100 + Tailles.PARCELLE);
			p.getWindow().getContentPane().add(basgauche);

			basdroite.setLocation(Tailles.PLATEAU + Tailles.CONSOLEx + Tailles.BORDURE*3 + Tailles.TAILLE*14 + Tailles.PARCELLE, 100 + Tailles.PARCELLE);
			p.getWindow().getContentPane().add(basdroite);

			nbChoix += 4;

		}

		return p;

	}

	/**
	 * Verifie quelles parcelles sont traversables par le personnage et permet de n'afficher que celles ci
	 * De base, les quatre cases adjacentes
	 * Si guerrier ou piegeur : il peut egalement aller en diagonale
	 * @param plateau
	 * @param ile
	 * @param perso
	 * @return le plateau 
	 */
	private Plateau filtrerAccessibles(Plateau plateau, Ile ile, Personnage perso) {

		Plateau p = plateau;

		int posX = perso.getX();
		int posY = perso.getY();

		if (!ile.getGrille()[posX - 1][posY].estTraversablePar(perso)) {
			p.getWindow().remove(gauche);
			nbChoix--;
		}
		if (!ile.getGrille()[posX + 1][posY].estTraversablePar(perso)) {
			p.getWindow().remove(droite);
			nbChoix--;
		}
		if (!ile.getGrille()[posX][posY - 1].estTraversablePar(perso)) {
			p.getWindow().remove(haut);
			nbChoix--;
		}
		if (!ile.getGrille()[posX][posY + 1].estTraversablePar(perso)) {
			p.getWindow().remove(bas);
			nbChoix--;
		}

		if (perso instanceof Guerrier || perso instanceof Piegeur) {

			if (!ile.getGrille()[posX - 1][posY - 1].estTraversablePar(perso)) {
				p.getWindow().remove(hautgauche);
				nbChoix--;
			}
			if (!ile.getGrille()[posX + 1][posY - 1].estTraversablePar(perso)) {
				p.getWindow().remove(hautdroite);
				nbChoix--;
			}
			if (!ile.getGrille()[posX - 1][posY + 1].estTraversablePar(perso)) {
				p.getWindow().remove(basgauche);
				nbChoix--;
			}
			if (!ile.getGrille()[posX + 1][posY + 1].estTraversablePar(perso)) {
				p.getWindow().remove(basdroite);
				nbChoix--;
			}

		}

		return p;
	}

	/**
	 * Permet de connaitre les positions des rochers autour d'un personnage cible
	 * @param plateau
	 * @param ile
	 * @param perso
	 * @return plateau
	 */
	private Plateau filtrerRocher(Plateau plateau, Ile ile, Personnage perso) {

		Plateau p = plateau;

		int posX = perso.getX();
		int posY = perso.getY();

		if (!(ile.getGrille()[posX - 1][posY] instanceof Rocher)) {
			p.getWindow().remove(gauche);
			nbChoix--;
		}
		if (!(ile.getGrille()[posX + 1][posY] instanceof Rocher)) {
			p.getWindow().remove(droite);
			nbChoix--;
		}
		if (!(ile.getGrille()[posX][posY - 1] instanceof Rocher)) {
			p.getWindow().remove(haut);
			nbChoix--;
		}
		if (!(ile.getGrille()[posX][posY + 1] instanceof Rocher)) {
			p.getWindow().remove(bas);
			nbChoix--;
		}

		return p;

	}

	/**
	 * Filtre la position de l'explorateur autour d'un personnage cible
	 * @param plateau
	 * @param ile
	 * @param perso
	 * @return plateau
	 */
	private Plateau filtrerExplorateur(Plateau plateau, Ile ile, Personnage perso) {

		Plateau p = plateau;

		int posX = perso.getX();
		int posY = perso.getY();

		if (!(ile.getGrille()[posX - 1][posY] instanceof Explorateur)) {
			p.getWindow().remove(gauche);
			nbChoix--;
		}
		if (!(ile.getGrille()[posX + 1][posY] instanceof Explorateur)) {
			p.getWindow().remove(droite);
			nbChoix--;
		}
		if (!(ile.getGrille()[posX][posY - 1] instanceof Explorateur)) {
			p.getWindow().remove(haut);
			nbChoix--;
		}
		if (!(ile.getGrille()[posX][posY + 1] instanceof Explorateur)) {
			p.getWindow().remove(bas);
			nbChoix--;
		}

		return p;

	}

	/**
	 * Filtre la position d'un personnage quelconque autour d'un personnage cible
	 * @param plateau
	 * @param ile
	 * @param perso
	 * @return plateau
	 */
	private Plateau filtrerPersonnage(Plateau plateau, Ile ile, Personnage perso) {

		Plateau p = plateau;

		int posX = perso.getX();
		int posY = perso.getY();

		if (!(ile.getGrille()[posX - 1][posY] instanceof Personnage)) {
			p.getWindow().remove(gauche);
			nbChoix--;
		}
		if (!(ile.getGrille()[posX + 1][posY] instanceof Personnage)) {
			p.getWindow().remove(droite);
			nbChoix--;
		}
		if (!(ile.getGrille()[posX][posY - 1] instanceof Personnage)) {
			p.getWindow().remove(haut);
			nbChoix--;
		}
		if (!(ile.getGrille()[posX][posY + 1] instanceof Personnage)) {
			p.getWindow().remove(bas);
			nbChoix--;
		}

		if (perso instanceof Guerrier || perso instanceof Piegeur) {

			p.getWindow().remove(hautgauche);
			p.getWindow().remove(hautdroite);
			p.getWindow().remove(basgauche);
			p.getWindow().remove(basdroite);
			
			nbChoix -= 4;

		}

		return p;

	}

	/**
	 * Filtre la position des parcelles de sable autour d'un personnage cible
	 * @param plateau
	 * @param ile
	 * @param perso
	 * @return
	 */
	private Plateau filtrerSable(Plateau plateau, Ile ile, Personnage perso) {

		Plateau p = plateau;

		int posX = perso.getX();
		int posY = perso.getY();

		if (!(ile.getGrille()[posX - 1][posY] instanceof Sable)) {
			p.getWindow().remove(gauche);
			nbChoix--;
		}
		if (!(ile.getGrille()[posX + 1][posY] instanceof Sable)) {
			p.getWindow().remove(droite);
			nbChoix--;
		}
		if (!(ile.getGrille()[posX][posY - 1] instanceof Sable)) {
			p.getWindow().remove(haut);
			nbChoix--;
		}
		if (!(ile.getGrille()[posX][posY + 1] instanceof Sable)) {
			p.getWindow().remove(bas);
			nbChoix--;
		}
		
		if (perso instanceof Guerrier || perso instanceof Piegeur) {

			p.getWindow().remove(hautgauche);
			p.getWindow().remove(hautdroite);
			p.getWindow().remove(basgauche);
			p.getWindow().remove(basdroite);
			
			nbChoix -= 4;

		}

		return p;

	}

	/**
	 * Retire la visibilite des donnees sur le plateau
	 * @param plateau
	 * @param perso
	 * @return plateau
	 */
	private Plateau effacer(Plateau plateau, Personnage perso) {

		Plateau p = plateau;
		p.getWindow().remove(gauche);
		p.getWindow().remove(droite);
		p.getWindow().remove(haut);
		p.getWindow().remove(bas);

		nbChoix -= 4;

		if (perso instanceof Guerrier || perso instanceof Piegeur) {
			p.getWindow().remove(hautgauche);
			p.getWindow().remove(hautdroite);
			p.getWindow().remove(basgauche);
			p.getWindow().remove(basdroite);

			nbChoix -= 4;

		}

		return p;

	}

	/**
	 * Demande a l'uilisateur via les boutons de selectionner la direction du
	 * personnage p selectionne au prealable.
	 * 
	 * @param p
	 * @param plateau
	 * @return string du choix fait
	 */
	public String choixDeplacement(Personnage p, Ile ile, Plateau plateau) {

		plateau = afficher(plateau, p);
		//plateau = filtrerAccessibles(plateau, ile, p);
		plateau.affichage();

		if (nbChoix <= 0) {
			return choix;
		}

		plateau.println("Cliquez sur la direction de votre choix:", p.getEquipe().getID());

		this.choix = "";

		while (choix.equals("")) {

			gauche.addActionListener(this);
			droite.addActionListener(this);
			bas.addActionListener(this);
			haut.addActionListener(this);

			hautgauche.addActionListener(this);
			hautdroite.addActionListener(this);
			basgauche.addActionListener(this);
			basdroite.addActionListener(this);

			if (choix.equals("erreur")) {

				plateau = effacer(plateau, p);
				plateau.println("Erreur: la parcelle n'est pas traversable", p.getEquipe().getID());

				choixDeplacement(p, ile, plateau);
			}

		}

		plateau = effacer(plateau, p);

		return choix;
	}

	/**
	 * Demande a l'utilisateur via bouton de selectionner l'endroit ou il veut agir suivant les positions possibles
	 * @param p
	 * @param ile
	 * @param plateau
	 * @return plateau
	 */
	public String choixAction(Personnage p, Ile ile, Plateau plateau) {

		plateau = afficher(plateau, p);

		if (p instanceof Explorateur) {
			plateau = filtrerRocher(plateau, ile, p);
		} else if (p instanceof Voleur) {
			plateau = filtrerExplorateur(plateau, ile, p);
		} else if (p instanceof Guerrier) {
			plateau = filtrerPersonnage(plateau, ile, p);
		} else if (p instanceof Piegeur) {
			plateau = filtrerSable(plateau, ile, p);
		}

		plateau.affichage();

		if (nbChoix <= 0) {
			return choix;
		}

		while (choix.equals("")) {

			gauche.addActionListener(this);
			droite.addActionListener(this);
			bas.addActionListener(this);
			haut.addActionListener(this);

			if (choix.equals("erreur")) {

				plateau = effacer(plateau, p);
				plateau.println("Erreur", p.getEquipe().getID());

				choixDeplacement(p, ile, plateau);
			}

		}

		plateau = effacer(plateau, p);

		return choix;
	}

	@Override
	/**
	 * Attend un clic puis modifie la variable choix avec le choix effectue par bouton
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {

		Object bouton = e.getSource();

		if (bouton == gauche) {
			this.choix = "haut";
		} else if (bouton == droite) {
			this.choix = "bas";
		} else if (bouton == haut) {
			this.choix = "gauche";
		} else if (bouton == bas) {
			this.choix = "droite";
		} else if (bouton == hautgauche) {
			this.choix = "hautgauche";
		} else if (bouton == hautdroite) {
			this.choix = "basgauche";
		} else if (bouton == basgauche) {
			this.choix = "hautdroite";
		} else if (bouton == basdroite) {
			this.choix = "basdroite";
		} else {
			this.choix = "erreur";
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}

	}
	
	/**
	 * Donne le nombre de choix possibme
	 * @return nbChoix
	 */
	public int getNbChoix() {
		return this.nbChoix;
	}

}
