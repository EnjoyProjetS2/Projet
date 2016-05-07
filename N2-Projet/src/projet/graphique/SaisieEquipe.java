package projet.graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import projet.parcelle.Equipe;
import projet.plateau.Jeu;

@SuppressWarnings("serial")
//PAS FINI
public class SaisieEquipe extends JFrame implements ActionListener{
	
	private int cptPerso;
	private JPanel panelDroite = new JPanel();
	private JPanel panelGauche = new JPanel();
	private JButton recommencer = new JButton();
	private Equipe e;
	private boolean flagretry = false;
	public static boolean retry = false;
	public SaisieEquipe(Equipe e) {
		this.e = e;
		retry = false;
		this.setPreferredSize(new Dimension(Tailles.FENETREx, Tailles.FENETREy));
		this.setLayout(null);
		Jpanel(panelDroite, 0, 0, 200, Tailles.FENETREy);
		Jpanel(panelGauche, 200, 0, Tailles.FENETREx-200, Tailles.FENETREy);
		panelDroite(this.e);
		panelGauche();
		ajoutJFrame();
		this.pack();
		this.setLocation(new Point(200, 50));
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		while(Jeu.nbPerso > cptPerso && !retry){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	private void panelGauche() {
		
		
	}
	private void panelDroite(Equipe e){
		panelDroite.setLayout(null);
		JTextField nomEquipe = new JTextField();
		nomEquipe.setText("Equipe "+e.getID());
		nomEquipe.setBounds(10, 10, 100, 50);
		panelDroite.add(nomEquipe);
		nomEquipe.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getExtendedKeyCode() == KeyEvent.VK_ENTER){
					e.setNom(nomEquipe.getText());
				}
			}
		});
		recommencer();
		panelDroite.add(recommencer);
	}
	private boolean recommencer(){
		recommencer.setText("recommencer");
		recommencer.setBounds(10, Tailles.FENETREy - 100, 150, 50);
		recommencer.addActionListener(this);
		return false;
	}
	private void ajoutJFrame(){
		this.getContentPane().add(panelDroite);
		this.getContentPane().add(panelGauche);
	}
	private boolean Jpanel(JPanel panel, int posX, int posY, int largeur, int hauteur){
		panel.setSize(largeur, hauteur);
		panel.setLocation(posX, posY);
		return false;
		
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		Object bouton = event.getSource();
		if (bouton == recommencer) {
			this.dispose();
			new SaisieEquipe(this.e);
		}
	}
}
