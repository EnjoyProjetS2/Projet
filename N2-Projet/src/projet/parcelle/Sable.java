package projet.parcelle;

public class Sable extends Parcelle {
	
	private boolean piege = false;

	/**
	 * Constructeur : cree une parcelle de sable
	 */
	public Sable() {
		super.traversable = true;
		super.id = 3;
	}
	
	@Override
	/**
	 * Definit si la parcelle est traversable
	 * @param p
	 * @return boolean
	 */
	public boolean estTraversablePar(Personnage p) {
		return super.traversable;
	}
	
	/**
	 * Retourne un espace sur la grille
	 */
	public String toString() {
		return " ";
	}
	
	/**
	 * Passe la parcelle � true si un piege est pose par le piegeur
	 */
	public void setPiege() {
		this.piege = true;
	}
	
	/**
	 * Retourne si la parcelle est piegee ou non
	 * @return boolean : piege
	 */
	public boolean estPiegee() {
		return this.piege;
	}
	
}
