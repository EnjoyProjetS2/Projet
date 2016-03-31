import javax.swing.JOptionPane;

public class Menu {

	public Menu() {

		// menu accueil
		// nombre joueur

		Equipe un = null;
		Equipe deux = null;
		saisieEquipe(un);
		saisieEquipe(deux);

	}

	public void saisieEquipe(Equipe e) {

		String nomEquipe = JOptionPane.showInputDialog("Entrez un nom d'equipe:");

		e.ajoutPersonnage(
				new Explorateur(JOptionPane.showInputDialog("Quel est le nom de votre explorateur?"), e, 0, 0));

		String[] personnage = { "Explorateur", "Voleur" };
		//int choix = JOptionPane.showOptionDialog(null, "Choisissez un jour:", "Titre", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, personnage);

	}

	public static void main(String[] args) {

		new Menu();

	}

}
