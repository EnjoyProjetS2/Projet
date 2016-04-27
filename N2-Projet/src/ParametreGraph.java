import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ParametreGraph {
	static boolean validerParametre = false;

	public ParametreGraph() {
		// TODO Auto-generated constructor stub
		JFrame fen = new JFrame("Parametre");
		fen.setPreferredSize(new Dimension(500, 500));
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
		JLabel labgauche = new JLabel("Taille");
		JSlider slidergauche = new JSlider(JSlider.VERTICAL, 5, 15, 10);
		slidergauche.setMajorTickSpacing(10);
		slidergauche.setMinorTickSpacing(1);
		slidergauche.setPaintTicks(true);
		slidergauche.setPaintLabels(true);

		// ajout a pangauche
		pangauche.setLayout(new BorderLayout());
		pangauche.add(slidergauche, BorderLayout.CENTER);
		pangauche.add(labgauche, BorderLayout.NORTH);

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
		JSlider sliderdroite = new JSlider(JSlider.VERTICAL, 0, 40, 10);
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
		Jeu.tailleX = slidergauche.getValue();
		Jeu.tailleY = Jeu.tailleX;
		// System.out.println(nbPerso + " " + pourcentageRocher + " " +
		// tailleX);
	}
}
