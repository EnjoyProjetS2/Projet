package projet.plateau;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import projet.parcelle.Personnage;

public class Direction implements ActionListener {

	int nombre = 4;
	String choix = "";

	JButton gauche;
	JButton droite;
	JButton haut;
	JButton bas;

	public Direction(Plateau plateau) {

		gauche = new JButton(new ImageIcon("images/boutons/gauche.png"));
		gauche.setSize(37, 37);

		droite = new JButton(new ImageIcon("images/boutons/droite.png"));
		droite.setSize(37, 37);

		haut = new JButton(new ImageIcon("images/boutons/haut.png"));
		haut.setSize(37, 37);

		bas = new JButton(new ImageIcon("images/boutons/bas.png"));
		bas.setSize(37, 37);

	}

	public Plateau afficher(Plateau plateau) {

		Plateau p = plateau;
		gauche.setLocation(plateau.getTaille() * 37 + 620, 100);
		p.getWindow().getContentPane().add(gauche);

		droite.setLocation(plateau.getTaille() * 37 + 620 + 2 * 37, 100);
		p.getWindow().getContentPane().add(droite);

		haut.setLocation(plateau.getTaille() * 37 + 620 + 37, 100 - 36);
		p.getWindow().getContentPane().add(haut);

		bas.setLocation(plateau.getTaille() * 37 + 620 + 37, 100 + 36);
		p.getWindow().getContentPane().add(bas);

		return p;

	}

	public Plateau effacer(Plateau plateau) {

		Plateau p = plateau;
		p.getWindow().remove(gauche);
		p.getWindow().remove(droite);
		p.getWindow().remove(haut);
		p.getWindow().remove(bas);

		return p;

	}

	/**
	 * Choix de la direction demandee a l'utilisateur
	 * 
	 * @return String : direction
	 */
	public String choix(Personnage p, Plateau plateau) {		
		
		plateau = afficher(plateau);
		plateau.affichage();

		plateau.println("Cliquez sur le bouton de votre choix:", p.getEquipe().getID());

		this.choix = "";
		
		while (choix.equals("")) {
			
			gauche.addActionListener(this);
			droite.addActionListener(this);
			bas.addActionListener(this);
			haut.addActionListener(this);
			
			if (choix.equals("erreur")) {		
				
				
				plateau = effacer(plateau);
				plateau.println("Erreur: la parcelle n'est pas traversable", p.getEquipe().getID());
				
				choix(p, plateau);
			}
			
		}
		
		plateau = effacer(plateau);

		return choix;

		/*
		 * 
		 * if (p instanceof Guerrier || p instanceof Piegeur) { String[]
		 * direction = { "Ouest", "Est", "Nord", "Sud", "Nord-Ouest",
		 * "Nord-Est", "Sud-Ouest", "Sud-Est" }; avis = (String)
		 * JOptionPane.showInputDialog(null, "Que faire:",
		 * "Déplacement du personnage", JOptionPane.DEFAULT_OPTION, null,
		 * direction, direction[0]); } else { String[] direction = { "Ouest",
		 * "Est", "Nord", "Sud" }; avis = (String)
		 * JOptionPane.showInputDialog(null, "Que faire:",
		 * "Déplacement du personnage", JOptionPane.DEFAULT_OPTION, null,
		 * direction, direction[0]); }
		 * 
		 * plateau = effacer(plateau); plateau.affichage();
		 * 
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
