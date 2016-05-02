package projet.plateau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import projet.parcelle.Guerrier;
import projet.parcelle.Personnage;
import projet.parcelle.Piegeur;

public class Direction implements ActionListener {

	String choix = "";

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
	 * @param plateau
	 */
	public Direction(Plateau plateau) {

		gauche = new JButton(new ImageIcon("images/boutons/gauche.png"));
		gauche.setSize(37, 37);

		droite = new JButton(new ImageIcon("images/boutons/droite.png"));
		droite.setSize(37, 37);

		haut = new JButton(new ImageIcon("images/boutons/haut.png"));
		haut.setSize(37, 37);

		bas = new JButton(new ImageIcon("images/boutons/bas.png"));
		bas.setSize(37, 37);

		hautgauche = new JButton(new ImageIcon("images/boutons/hautgauche.png"));
		hautgauche.setSize(37, 37);

		hautdroite = new JButton(new ImageIcon("images/boutons/hautdroite.png"));
		hautdroite.setSize(37, 37);

		basgauche = new JButton(new ImageIcon("images/boutons/basgauche.png"));
		basgauche.setSize(37, 37);

		basdroite = new JButton(new ImageIcon("images/boutons/basdroite.png"));
		basdroite.setSize(37, 37);

	}

	private Plateau afficher(Plateau plateau, Personnage perso) {

		Plateau p = plateau;
		gauche.setLocation(plateau.getTaille() * 37 + 620, 100);
		p.getWindow().getContentPane().add(gauche);

		droite.setLocation(plateau.getTaille() * 37 + 620 + 2 * 37, 100);
		p.getWindow().getContentPane().add(droite);

		haut.setLocation(plateau.getTaille() * 37 + 620 + 37, 100 - 36);
		p.getWindow().getContentPane().add(haut);

		bas.setLocation(plateau.getTaille() * 37 + 620 + 37, 100 + 36);
		p.getWindow().getContentPane().add(bas);

		if (perso instanceof Guerrier || perso instanceof Piegeur) {

			hautgauche.setLocation(plateau.getTaille() * 37 + 620, 100 - 36);
			p.getWindow().getContentPane().add(hautgauche);

			hautdroite.setLocation(plateau.getTaille() * 37 + 620 + 2 * 37, 100 - 36);
			p.getWindow().getContentPane().add(hautdroite);

			basgauche.setLocation(plateau.getTaille() * 37 + 620, 100 + 36);
			p.getWindow().getContentPane().add(basgauche);

			basdroite.setLocation(plateau.getTaille() * 37 + 620 + 2 * 37, 100 + 36);
			p.getWindow().getContentPane().add(basdroite);

		}

		return p;

	}

	private Plateau effacer(Plateau plateau, Personnage perso) {

		Plateau p = plateau;
		p.getWindow().remove(gauche);
		p.getWindow().remove(droite);
		p.getWindow().remove(haut);
		p.getWindow().remove(bas);

		if (perso instanceof Guerrier || perso instanceof Piegeur) {
			p.getWindow().remove(hautgauche);
			p.getWindow().remove(hautdroite);
			p.getWindow().remove(basgauche);
			p.getWindow().remove(basdroite);
		}

		return p;

	}

	/**
	 * Demande a l'uilisateur via les boutons de selectionner la direction du
	 * personnage p selectionne au prealable.
	 * 
	 * @param p
	 * @param plateau
	 * @return
	 */
	public String choix(Personnage p, Plateau plateau) {

		plateau = afficher(plateau, p);
		plateau.affichage();

		plateau.println("Cliquez sur la direction de votre choix:", p.getEquipe().getID());

		this.choix = "";

		while (choix.equals("")) {

			gauche.addActionListener(this);
			droite.addActionListener(this);
			bas.addActionListener(this);
			haut.addActionListener(this);
			
			if (p instanceof Guerrier || p instanceof Piegeur) {
				hautgauche.addActionListener(this);
				hautdroite.addActionListener(this);
				basgauche.addActionListener(this);
				basdroite.addActionListener(this);

			}
			
			if (choix.equals("erreur")) {

				plateau = effacer(plateau, p);
				plateau.println("Erreur: la parcelle n'est pas traversable", p.getEquipe().getID());

				choix(p, plateau);
			}

		}

		plateau = effacer(plateau, p);

		return choix;

		/*
		 * if (avis.equals("Nord")) { return "gauche"; } else if
		 * (avis.equals("Sud")) { return "droite"; } else if
		 * (avis.equals("Ouest")) { return "haut"; } else if
		 * (avis.equals("Est")) { return "bas"; } else if
		 * (avis.equals("Nord-Ouest")) { return "hautgauche"; } else if
		 * (avis.equals("Nord-Est")) { return "basgauche"; } else if
		 * (avis.equals("Sud-Ouest")) { return "hautdroite"; } else if
		 * (avis.equals("Sud-Est")) { return "basdroite"; }
		 * 
		 * return "faux";
		 */
	}

	@Override
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

}
