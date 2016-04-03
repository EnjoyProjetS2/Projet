
public class Eau extends Parcelle {

	/**Constructeur: Cree une parcelle d'eau non traversable
	 * 
	 */
	public Eau() {
		super.traversable = false;
	}
	
	/**Retour une vague pour les parcelles d'eau
	 * 
	 */
	public String toString() {
		return "~";
	}
	
	
}
