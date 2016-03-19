public class Parcelle {

	public String element;
	public static boolean poseCoffre = false;
	public static boolean poseClef = false;
	public boolean coffre = false;
	public boolean clef = false;

	/**Constructeur: cree une parcelle vide
	 * @param element
	 */
	public Parcelle() {
		this.element = Constantes.VIDE; 
	}	
	
	/**Constructeur: Cree une parcelle vide, avec pour parametre une chaine de caracteres
	 * @param Parcelle
	 * */
	public Parcelle(String elem) {		
		if (elem.equals(Constantes.VIDE) || elem.equals(null)) {
			this.element = Constantes.VIDE;
		} else {
			this.element = elem;
		}
	}
			
	/**Retourne l'element de la parcelle
	 * @param getElement
	 * @return l'element  
	 */
	public String getElement() {
		return this.element;
	}
	/**Redefinit l'element de la parcelle
	 * @param setElement
	 */
	public void setElement(String elem) {
		if (elem.equals(Constantes.VIDE) || elem.equals(null)) {
			this.element = Constantes.VIDE;
		} else {
			this.element = elem;
		}
	}
	
	/**Renvoie vrai si l'element est Constantes.VIDE
	 * @param estVide
	 * @return vrai si est Constantes.VIDE
	 */
	public boolean estVide(){
		return this.element.equals(Constantes.VIDE) || this.element.equals(null);
	}
	
	/**Renvoie vrai si l'element est un rocher
	 * @param estRocher
	 * @return
	 */
	public boolean estRocher(){
		return this.element.equals(Constantes.ROCHER);
	}
	
	/**Renvoie vrai si l'element est un navire
	 * @param estNavire
	 * @return
	 */
	public boolean estNavire(){
		return this.element.equals(Constantes.NAV1) || this.element.equals(Constantes.NAV2);
	}
	
	/**Renvoie vrai si l'element est de l'eau
	 * @param estNavire
	 * @return
	 */
	public boolean estEau(){
		return this.element.equals(Constantes.EAU);
	}
	
	/**Renvoie vrai si l'element est du sable
	 * @param estNavire
	 * @return
	 */
	public boolean estSable(){
		return this.element.equals(Constantes.SABLE);
	}
	
	
	/** Affiche la premiere lettre de l'element en majuscule
	 * @param toString
	 * @see java.lang.Object#toString()
	 */
	public String toString() {		
		if (estVide() || estSable()) {
			return " ";
		} else {
			return element.substring(0,1).toUpperCase();	
		}
	}
	
}
