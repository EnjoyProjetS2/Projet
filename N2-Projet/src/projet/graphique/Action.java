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
	boolean passerTour = false;

	JButton deplacement;
	JButton action;
	JButton debarquer;
	JButton passer;


	/**Constructeur d'une action via des boutons
	 * 
	 * @param plateau
	 */
	public Action(Plateau plateau) {
		
		deplacement = new JButton(new ImageIcon("images/boutons/deplacement.png"));
		deplacement.setSize(Tailles.BOUTONLARGEx, Tailles.BOUTONLARGEy);

		action = new JButton(new ImageIcon("images/boutons/action.png"));
		action.setSize(Tailles.BOUTONLARGEx, Tailles.BOUTONLARGEy);

		debarquer = new JButton(new ImageIcon("images/boutons/debarquer.png"));
		debarquer.setSize(Tailles.BOUTONLARGEx, Tailles.BOUTONLARGEy);
		
		passer = new JButton(new ImageIcon("images/boutons/passer.png"));
		passer.setSize(Tailles.BOUTONLARGEx, Tailles.BOUTONLARGEy);

	}

	/**
	 * Affiche le bouton de l'action
	 * @param plateau
	 * @param parcelle
	 * @return Plateau avec bouton action
	 */
	private Plateau afficher(Plateau plateau, Parcelle parcelle) {

		Plateau p = plateau;

		if (parcelle instanceof Personnage) {

			deplacement.setLocation(Tailles.PLATEAU + Tailles.CONSOLEx + Tailles.BORDURE*2 + Tailles.TAILLE*14, 100);
			p.getWindow().getContentPane().add(deplacement);

			action.setLocation(Tailles.PLATEAU + Tailles.CONSOLEx + Tailles.BORDURE*2 + Tailles.TAILLE*14, 200);
			p.getWindow().getContentPane().add(action);
			
			passer.setLocation(Tailles.PLATEAU + Tailles.CONSOLEx + Tailles.BORDURE*2 + Tailles.TAILLE*14, 300);
			p.getWindow().getContentPane().add(passer);

		} else if (parcelle instanceof Navire) {

			debarquer.setLocation(Tailles.PLATEAU + Tailles.CONSOLEx + Tailles.BORDURE*2 + Tailles.TAILLE*14, 100);
			p.getWindow().getContentPane().add(debarquer);
		}
		return p;

	}

	/**
	 * Retire le bouton du plateau
	 * @param plateau
	 * @param parcelle
	 * @return Plateau sans bouton
	 */
	private Plateau effacer(Plateau plateau, Parcelle parcelle) {

		Plateau p = plateau;

		if (parcelle instanceof Personnage) {

			p.getWindow().remove(deplacement);
			p.getWindow().remove(action);
			p.getWindow().remove(passer);

		} else if (parcelle instanceof Navire) {

			p.getWindow().remove(debarquer);
		}
		return p;

	}

	/**
	 * Propose a l'utilisateur de cliquer sur les actions possibles en fonction
	 * de la Parcelle selectionnee prealablement.
	 * 
	 * @param plateau
	 * @param parcelle
	 * @param equipe
	 * @return le choix d'action
	 */
	public String choix(Plateau plateau, Parcelle parcelle, int equipe) {

		plateau = afficher(plateau, parcelle);
		plateau.affichage();

		plateau.println("Cliquez sur l'action de votre choix:", equipe);

		this.choix = "";

		while (choix.equals("") ) {

			if (parcelle instanceof Personnage) {

				deplacement.addActionListener(this);
				action.addActionListener(this);
				passer.addActionListener(this);

			} else if (parcelle instanceof Navire) {

				debarquer.addActionListener(this);
			}
			

			if (choix.equals("erreur")) {

				plateau = effacer(plateau, parcelle);
				plateau.println("Erreur", equipe);

				choix(plateau, parcelle, equipe);
			}
			
			if (passerTour) {
				choix = "passer";
			}

		}

		plateau = effacer(plateau, parcelle);

		return choix;

	}

	@Override
	/** Au clic de la souris, modifie la variable "choix" pour donner le choix au joueur de cliquer sur l'action qu'il veut 
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {

		Object bouton = e.getSource();

		if (bouton == deplacement) {
			choix = "Deplacement";
		} else if (bouton == action) {
			choix = "Action";
		} else if (bouton == debarquer) {
			choix = "Debarquer un personnage";
		} else if (bouton == passer) {
			passerTour = true;		
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
