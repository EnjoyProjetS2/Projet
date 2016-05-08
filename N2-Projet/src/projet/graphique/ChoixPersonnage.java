package projet.graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import projet.parcelle.Equipe;
import projet.parcelle.Explorateur;
import projet.parcelle.Guerrier;
import projet.parcelle.Personnage;
import projet.parcelle.Piegeur;
import projet.parcelle.Voleur;
import projet.plateau.Jeu;
import projet.plateau.Plateau;

public class ChoixPersonnage implements ActionListener {

	Equipe equipe;
	Personnage choix;

	JButton[] persos;
	JLabel[] vies;
	JLabel[] noms;

	/**
	 * Constructeur du choix de l'equipe
	 * @param equipe
	 */
	public ChoixPersonnage(int equipe) {

		String couleur = "";
		String classe = "";

		if (equipe == 1 || equipe == 2) {

			if (equipe == 1) {

				this.equipe = Jeu.un;
				couleur = "Bleu";

			} else if (equipe == 2) {

				this.equipe = Jeu.deux;
				couleur = "Rouge";
			}

			this.persos = new JButton[this.equipe.getNavire().getPersoDansNavire().size()];
			this.vies = new JLabel[this.persos.length];
			this.noms = new JLabel[this.persos.length];

			for (int i = 0; i < persos.length; i++) {

				if (this.equipe.getNavire().getPersoDansNavire().get(i) instanceof Explorateur) {
					classe = "Explorateur";
				} else if (this.equipe.getNavire().getPersoDansNavire().get(i) instanceof Voleur) {
					classe = "Voleur";
				} else if (this.equipe.getNavire().getPersoDansNavire().get(i) instanceof Guerrier) {
					classe = "Guerrier";
				} else if (this.equipe.getNavire().getPersoDansNavire().get(i) instanceof Piegeur) {
					classe = "Piegeur";
				}

				persos[i] = new JButton(new ImageIcon("images/" + classe + couleur + ".png"));
				persos[i].setSize(37, 37);

			}

		} else {
			System.out.println("Erreur: l'equipe n'existe pas.");
		}
	}

	/**
	 * Affiche le plateau avec les informations sur les equipes
	 * @param plateau
	 * @return le plateau
	 */
	public Plateau afficher(Plateau plateau) {

		Plateau p = plateau;

		int decallage = 0;

		for (int i = 0; i < persos.length; i++) {
			persos[i].setLocation(Tailles.PLATEAU + Tailles.CONSOLEx + 115 + decallage, 100);
			p.getWindow().getContentPane().add(persos[i]);

			vies[i] = new JLabel("" + equipe.getNavire().getPersoDansNavire().get(i).getEnergie() + "HP");
			vies[i].setSize(37, 37);
			vies[i].setLocation(Tailles.PLATEAU + Tailles.CONSOLEx + 115 + decallage, 100+50);
			p.getWindow().getContentPane().add(vies[i]);
			
			noms[i] = new JLabel("" + equipe.getNavire().getPersoDansNavire().get(i).getNom());
			noms[i].setSize(37, 37);
			noms[i].setLocation(Tailles.PLATEAU + Tailles.CONSOLEx + 115 + decallage, 100+30);
			p.getWindow().getContentPane().add(noms[i]);
			
			decallage += Tailles.PARCELLE + 6;
		}

		return p;
	}

	/**
	 * Efface les informations des equipes du plateau
	 * @param plateau
	 * @return le plateau
	 */
	public Plateau effacer(Plateau plateau) {

		Plateau p = plateau;

		for (int i = 0; i < persos.length; i++) {
			p.getWindow().remove(persos[i]);			
			p.getWindow().remove(vies[i]);
			p.getWindow().remove(noms[i]);		
		}
		return p;
	}

	/**
	 * Demande au joueur de choisir son personnage
	 * @param plateau
	 * @return Personnage
	 */
	public Personnage choix(Plateau plateau) {

		plateau = afficher(plateau);
		plateau.affichage();

		plateau.println("Cliquez sur le personnage de votre choix:", equipe.getID());

		while (choix == null) {
			for (int i = 0; i < persos.length; i++) {
				persos[i].addActionListener(this);
			}
		}
		plateau = effacer(plateau);
		return choix;
	}

	@Override
	/**
	 * Attend l'action au clic de la souris
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {

		Object bouton = e.getSource();

		for (int i = 0; i < persos.length; i++) {
			if (bouton == persos[i]) {
				choix = equipe.getNavire().getPersoDansNavire().get(i);
			}
		}
	}
}
