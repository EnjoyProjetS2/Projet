
public class Sable extends Parcelle {
	
	private boolean piege = false;

	/**Constructeur
	 * 
	 */
	public Sable() {
		super.traversable = true;
		super.id = 3;
	}
	
	/**Retourne un espace sur la grille
	 * 
	 */
	public String toString() {
		return " ";
	}
	
	public void setPiege() {
		this.piege = true;
	}
	
	public boolean estPiegee() {
		return this.piege;
	}
	
}
