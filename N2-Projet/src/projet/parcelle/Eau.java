package projet.parcelle;

public class Eau extends Parcelle {

	/**Constructeur: Cree une parcelle d'eau non traversable
	 * 
	 */
	public Eau() {
		super.traversable = false;
		super.id = 4;
	}
	
	@Override
	/**
	 * Savoir si la parcelle est traversable par un personnage
	 * @return boolean
	 */
	public boolean estTraversablePar(Personnage p) {
		return super.traversable;
	}
	
	/**Retourne une vague pour les parcelles d'eau
	 * @return String 
	 */
	public String toString() {
		return "~";
	}
	
	
}
