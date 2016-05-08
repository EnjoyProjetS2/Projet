package projet.graphique;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import projet.plateau.Executable;

@SuppressWarnings("serial")
public class ChoixModeDeJeu extends JFrame implements ActionListener{
	JButton modeVersus;
	JButton modeCreatif;
	JButton modeSolo;
	boolean bahOuiOnMetUnBooleanPourGererTroisPossibiliter;
	
	/**
	 * Constructeur qui affiche la fenetre du choix du mode
	 */
	public ChoixModeDeJeu() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(Tailles.FENETREx, Tailles.FENETREy));
		this.setLayout(new GridLayout(1,3));
		modeVersus();
		modeCreatif();
		modeSolo();
		ajoutJFrame();
		this.pack();
		this.setLocation(new Point(200, 50));
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		while (!Executable.solo && !Executable.modeCreatif && !bahOuiOnMetUnBooleanPourGererTroisPossibiliter) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Active le mode 1v1
	 * @return boolean
	 */
	private boolean modeVersus() {
		modeVersus = new JButton(new ImageIcon("images/boutons/versus.png"));
		//modeVersus.setBounds(Tailles.FENETREx / 2, 30, 100, 50);
		modeVersus.addActionListener(this);
		return false;
	}

	/**
	 * Active le mode creatif
	 * @return boolean
	 */
	private boolean modeCreatif() {
		modeCreatif = new JButton(new ImageIcon("images/boutons/creatif.png"));
		//modeCreatif.setBounds(Tailles.FENETREx / 2, 30 + 70, 100, 50);
		modeCreatif.addActionListener(this);
		return false;
	}

	/**
	 * Active le mode solo contre l'IA
	 * @return boolean
	 */
	private boolean modeSolo() {
		modeSolo = new JButton(new ImageIcon("images/boutons/solo.png"));
		//modeSolo.setBounds(Tailles.FENETREx / 2, 30 + 140, 100, 50);
		modeSolo.addActionListener(this);
		return false;
	}
	
	/**
	 * Met le mode selectionne en place
	 */
	private void ajoutJFrame() {
		this.getContentPane().add(modeVersus);
		this.getContentPane().add(modeCreatif);
		this.getContentPane().add(modeSolo);
	}
	@Override
	/**
	 * Attend le clic de la souris
	 * @param e le clic
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object bouton = e.getSource();
		if (bouton == modeVersus) {
			bahOuiOnMetUnBooleanPourGererTroisPossibiliter = true;
			this.setVisible(false);
		}else if(bouton == modeSolo){
			Executable.solo = true;
			this.setVisible(false);
		}else if(bouton == modeCreatif){
			Executable.modeCreatif = true;
			this.setVisible(false);
		}
	}
	
}
