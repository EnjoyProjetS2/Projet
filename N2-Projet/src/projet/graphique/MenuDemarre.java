package projet.graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class MenuDemarre extends JFrame implements ActionListener {
	JButton jouer;
	JButton regle;
	JButton quitter;
	JButton retour;
	JTextArea lesRegles;
	JDialog popup;
	private boolean click = false;

	/**
	 * Constructeur du menu d'accueil
	 */
	public MenuDemarre() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(Tailles.FENETREx, Tailles.FENETREy));
		this.setLayout(new GridLayout(1,3));
		jouer();
		regles();
		quitter();
		popup();
		ajoutJFrame();
		this.pack();
		this.setLocation(new Point(200, 50));
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		while (!click) {
			this.repaint();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Affiche un pop up
	 * @return boolean
	 */
	private boolean popup(){
		popup = new JDialog(this, "Regles");
		popup.setSize(new Dimension(Tailles.FENETREx, Tailles.FENETREy));
		popup.setLocation(new Point(200, 50));
		popup.setModal(false);
		lesRegles();
		retour();
		popup.getContentPane().add(lesRegles, BorderLayout.CENTER);
		popup.getContentPane().add(retour, BorderLayout.SOUTH);
		return false;
	}
	
	/**
	 * Bouton pour jouer
	 * @return boolean
	 */
	//"images/boutons/jouer.png"
	private boolean jouer() {
		jouer = new JButton(new ImageIcon("images/boutons/jouer.png"));
		//jouer.setBounds(Tailles.FENETREx / 2, 30, 100, 50);
		jouer.addActionListener(this);
		return false;
	}

	/**
	 * Bouton pour les regles
	 * @return boolean
	 */
	private boolean regles() {
		regle = new JButton(new ImageIcon("images/boutons/regles.png"));
		//regle.setBounds(Tailles.FENETREx / 2, 30 + 70, 100, 50);
		regle.addActionListener(this);
		return false;
	}

	/**
	 * Bouton pour quitter
	 * @return boolean
	 */
	private boolean quitter() {
		quitter = new JButton(new ImageIcon("images/boutons/quitter.png"));
		//quitter.setBounds(Tailles.FENETREx / 2, 30 + 140, 100, 50);
		quitter.addActionListener(this);
		return false;
	}

	/**
	 * Bouton de retour
	 * @return boolean
	 */
	private boolean retour() {
		retour = new JButton("Retour");
		//retour.setBounds(Tailles.FENETREx / 2, 30+200, 100, 50);
		retour.addActionListener(this);
		return false;
	}
	
	/**
	 * Fenetre qui affiche les regles
	 * @return boolean
	 */
	private boolean lesRegles(){
		lesRegles = new JTextArea();
		//lesRegles.setBounds(Tailles.FENETREx / 6, 30, Tailles.FENETREx-Tailles.FENETREx / 3, 200);
		lesRegles.setText(
				"Le but du jeu est de trouver la clé cachée sous un rocher et de \n"
				+ "trouver le coffre puis ramener le trésor sur son navire. ");
		lesRegles.setEditable(false);
		lesRegles.setFocusable(false);
		lesRegles.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		return false;
	}

	/**
	 * Ajoute les boutons dans une fenetre
	 */
	private void ajoutJFrame() {
		this.getContentPane().add(jouer);
		this.getContentPane().add(regle);
		this.getContentPane().add(quitter);
	}

	@Override
	/**
	 * Attend le clic de la souris sur le bouton
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object bouton = e.getSource();
		if (bouton == jouer) {
			click = true;
			this.setVisible(false);
			// ajouter class
		} else if (bouton == regle) {
			popup.setVisible(true);
		} else if (bouton == quitter) {
			System.exit(0);
		} else if (bouton == retour) {
			popup.setVisible(false);
		}
	}
}
