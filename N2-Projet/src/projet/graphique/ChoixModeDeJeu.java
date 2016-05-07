package projet.graphique;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import projet.plateau.Jeu;

@SuppressWarnings("serial")
public class ChoixModeDeJeu extends JFrame implements ActionListener{
	JButton modeVersus;
	JButton modeCreatif;
	JButton modeSolo;
	boolean bahOuiOnMetUnBooleanPourGererTroisPossibiliter;
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
		while (!Jeu.solo && !Jeu.modeCreatif && !bahOuiOnMetUnBooleanPourGererTroisPossibiliter) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	private boolean modeVersus() {
		modeVersus = new JButton("mode Versus");
		//modeVersus.setBounds(Tailles.FENETREx / 2, 30, 100, 50);
		modeVersus.addActionListener(this);
		return false;
	}

	private boolean modeCreatif() {
		modeCreatif = new JButton("mode Creatif");
		//modeCreatif.setBounds(Tailles.FENETREx / 2, 30 + 70, 100, 50);
		modeCreatif.addActionListener(this);
		return false;
	}

	private boolean modeSolo() {
		modeSolo = new JButton("mode Solo");
		//modeSolo.setBounds(Tailles.FENETREx / 2, 30 + 140, 100, 50);
		modeSolo.addActionListener(this);
		return false;
	}
	private void ajoutJFrame() {
		this.getContentPane().add(modeVersus);
		this.getContentPane().add(modeCreatif);
		this.getContentPane().add(modeSolo);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object bouton = e.getSource();
		if (bouton == modeVersus) {
			bahOuiOnMetUnBooleanPourGererTroisPossibiliter = true;
			this.setVisible(false);
		}else if(bouton == modeSolo){
			Jeu.solo = true;
			this.setVisible(false);
		}else if(bouton == modeCreatif){
			Jeu.modeCreatif = true;
			this.setVisible(false);
		}
	}
	
}
