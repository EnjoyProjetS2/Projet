
import java.awt.Font;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class ConsolePane extends JScrollPane {
	private static final long serialVersionUID = 3L;
	private JTextArea textArea;

	/**Constructeur
	 * 
	 */
	public ConsolePane() {
		super();
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFocusable(false);
		textArea.setFont(new Font("Serif", Font.PLAIN, 18));
		textArea.setText("Bienvenue dans la chasse au tresor !\n");
		this.setViewportView(textArea);
	}
	
	/**Affiche les coordonn�es
	 * 
	 * @param message
	 */
	public void println(String message) {
		textArea.append(message + '\n');
		// Positionne la scrollPane à son extrémité inférieure.
		JScrollBar vertical = this.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
	}
}
