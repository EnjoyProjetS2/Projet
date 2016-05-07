package projet.graphique;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelFondPlateau extends JPanel {
	private Image image;

	public PanelFondPlateau() {
		image = (new ImageIcon("images/fond.jpg")).getImage();
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null); 
	}
}
