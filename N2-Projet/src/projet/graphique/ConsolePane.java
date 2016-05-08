package projet.graphique;

import java.awt.Font;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsolePane extends JScrollPane {
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
		textArea.setFont(new Font("Serif", Font.PLAIN, 12));
		this.setViewportView(textArea);
	}
	
	/**Affiche les coordonnees
	 * 
	 * @param message
	 */
	public void println(String message) {
		//Pour effacer la JtextArea
		if (message == "clear") {
			textArea.setText("");;
		}else{
		textArea.append(message + '\n');
		// Positionne la scrollPane à son extrémité inférieure.
		JScrollBar vertical = this.getVerticalScrollBar();
		vertical.setValue(vertical.getMaximum());
		}
	}
}
