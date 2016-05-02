package projet.graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import projet.parcelle.Navire;
import projet.parcelle.Parcelle;
import projet.parcelle.Personnage;
import projet.plateau.Plateau;

public class Action implements ActionListener {

	String choix = "";

	JButton deplacement;
	JButton action;
	JButton debarquer;

	public Action(Plateau plateau) {

		deplacement = new JButton(new ImageIcon("images/boutons/deplacement.png"));
		deplacement.setSize(150, 50);

		action = new JButton(new ImageIcon("images/boutons/action.png"));
		action.setSize(150, 50);

		debarquer = new JButton(new ImageIcon("images/boutons/debarquer.png"));
		debarquer.setSize(150, 50);

	}

	private Plateau afficher(Plateau plateau, Parcelle parcelle) {

		Plateau p = plateau;

		if (parcelle instanceof Personnage) {

			deplacement.setLocation(plateau.getTaille() * 37 + 620, 100);
			p.getWindow().getContentPane().add(deplacement);

			action.setLocation(plateau.getTaille() * 37 + 620, 200);
			p.getWindow().getContentPane().add(action);

		} else if (parcelle instanceof Navire) {

			debarquer.setLocation(plateau.getTaille() * 37 + 620, 100);
			p.getWindow().getContentPane().add(debarquer);

		}

		return p;

	}

	private Plateau effacer(Plateau plateau, Parcelle parcelle) {

		Plateau p = plateau;

		if (parcelle instanceof Personnage) {

			p.getWindow().remove(deplacement);
			p.getWindow().remove(action);

		} else if (parcelle instanceof Navire) {

			p.getWindow().remove(debarquer);

		}

		return p;

	}

	/**
	 * Propose a l'utilisateur de cliquer sur les actions possibles en fonction de la Parcelle selectionnee prealablement.
	 * @param plateau
	 * @param parcelle
	 * @param equipe
	 * @return
	 */
	public String choix(Plateau plateau, Parcelle parcelle, int equipe) {

		plateau = afficher(plateau, parcelle);
		plateau.affichage();

		plateau.println("Cliquez sur l'action de votre choix:", equipe);

		this.choix = "";

		while (choix.equals("")) {

			if (parcelle instanceof Personnage) {

				deplacement.addActionListener(this);
				action.addActionListener(this);

			} else if (parcelle instanceof Navire) {

				debarquer.addActionListener(this);
			}

			if (choix.equals("erreur")) {

				plateau = effacer(plateau, parcelle);
				plateau.println("Erreur", equipe);

				choix(plateau, parcelle, equipe);
			}

		}

		plateau = effacer(plateau, parcelle);

		return choix;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object bouton = e.getSource();

		if (bouton == deplacement) {
			choix = "Deplacement";
		} else if (bouton == action) {
			choix = "Action";
		} else if (bouton == debarquer) {
			choix = "Debarquer un personnage";
		} else {
			choix = "erreur";
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}

	}
}
