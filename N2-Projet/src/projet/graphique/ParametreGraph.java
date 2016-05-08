package projet.graphique;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import projet.plateau.*;

public class ParametreGraph {
	static boolean validerParametre = false;

	/**
	 * Constructeur des parametres
	 * Affiche la fenetre de choix des parametres de jeu
	 */
	public ParametreGraph() {
		// TODO Auto-generated constructor stub
		JFrame fen = new JFrame("Parametre");
		fen.setPreferredSize(new Dimension(700, 500));
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
		JSlider slidergauche = new JSlider(JSlider.VERTICAL, 5, 15, 10);
		JLabel labgauche = new JLabel("Taille: " + slidergauche.getValue() + "X" + slidergauche.getValue());
		slidergauche.setMajorTickSpacing(10);
		slidergauche.setMinorTickSpacing(1);
		slidergauche.setPaintTicks(true);
		slidergauche.setPaintLabels(true);
		slidergauche.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if (slidergauche.getValue() >= 10) {
					labgauche.setText("Taille: " + slidergauche.getValue() + "X" + slidergauche.getValue());
				} else {
					labgauche.setText("Taille: 0" + slidergauche.getValue() + "X0" + slidergauche.getValue());
				}

			}
		});

		// ajout a pangauche
		pangauche.setLayout(new BorderLayout());
		pangauche.add(slidergauche, BorderLayout.CENTER);
		pangauche.add(labgauche, BorderLayout.NORTH);

		// panCentre
		JSlider sliderCentre = new JSlider(JSlider.VERTICAL, 1, 5, 1);
		JLabel labCentre = new JLabel("Nombre de personnages: " + sliderCentre.getValue());
		sliderCentre.setMajorTickSpacing(4);
		sliderCentre.setMinorTickSpacing(1);
		sliderCentre.setPaintTicks(true);
		sliderCentre.setPaintLabels(true);
		labCentre.setHorizontalAlignment(JLabel.CENTER);
		sliderCentre.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				labCentre.setText("Nombre de personnages: " + sliderCentre.getValue());
			}
		});
		// ajout a panCentre
		panCentre.setLayout(new BorderLayout());
		panCentre.add(labCentre, BorderLayout.NORTH);
		panCentre.add(sliderCentre, BorderLayout.CENTER);
		// panDroit
		JSlider sliderdroite = new JSlider(JSlider.VERTICAL, 2, 40, 10);
		JLabel labDroit = new JLabel("Pourcentage de rocher :" + sliderdroite.getValue() + " %");
		sliderdroite.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if (sliderdroite.getValue() >= 10) {
					labDroit.setText("Pourcentage de rocher :" + sliderdroite.getValue() + " %");
				} else {
					labDroit.setText("Pourcentage de rocher :0" + sliderdroite.getValue() + " %");
				}

			}
		});
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
		// la tempo pour attendre que le bouton soit appuyé sans bouffer tout le
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
		Jeu.nbPerso = sliderCentre.getValue();
		Jeu.pourcentageRocher = sliderdroite.getValue();
		Tailles.TAILLE = slidergauche.getValue();
		//Jeu.tailleY = Jeu.tailleX;
	}
}
