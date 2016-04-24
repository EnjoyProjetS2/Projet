import javax.swing.JOptionPane;

public class Explorateur extends Personnage {

	/**
	 * Constructeur: cree un Explorateur a partir de Personnage
	 * 
	 * @param nom
	 * @param e
	 * @param x
	 * @param y
	 */
	public Explorateur(String nom, Equipe e, int x, int y) {
		super(nom, e, x, y);
		super.energie = 100;
		super.id = 5;
	}

	/**
	 * Capacite de l'explorateur, il peut soulever les rocher et verifier
	 * l'interieur
	 * 
	 * @param terrain
	 * @return
	 */
	public boolean souleverRocher(Rocher terrain) {
		super.energie -= 5;
		if (terrain.clef == true) { // si ce rocher a la cle
			terrain.clef = false;
			this.possessionClef = true;
			JOptionPane.showMessageDialog(null, "L'equipe "+super.equipe.getNom()+" a trouve la clef !");
			return true;
		} else if (terrain.coffre == true) {
			JOptionPane.showMessageDialog(null, "Oh...un coffre a l'air ici.");
			if(this.possessionClef){
				this.possessionCoffre = true;
			}
			JOptionPane.showMessageDialog(null, "L'equipe "+super.equipe.getNom() +" vient de prendre le tresor !");
			return true;
		}
		return false;
	}

	/**
	 * Retourne les informations du personnage
	 * 
	 */
	public String toString() {		
		return "Explorateur "+this.nom+" (Equipe: "+super.getEquipe().getNom()+") - Vie: "+super.energie+"/"+Jeu.maxVie;
	}
}
