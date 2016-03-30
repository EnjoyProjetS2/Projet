
public class Eau extends Parcelle {

	/**Constructeur: Cree une parcelle d'eau non traversable
	 * 
	 */
	public Eau() {
		super.traversable = false;
	}
	
	public String toString() {
		return "~";
	}
	
	
}
