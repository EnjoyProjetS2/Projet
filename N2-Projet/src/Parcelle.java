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
		this.element = "vide"; 
	}	
	
	/**Constructeur: Cree une parcelle vide, avec pour parametre une chaine de caracteres
	 * @param Parcelle
	 * */
	public Parcelle(String elem) {		
		if (elem.equals("vide") || elem.equals(null)) {
			this.element = "vide";
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
		if (elem.equals("vide") || elem.equals(null)) {
			this.element = "vide";
		} else {
			this.element = elem;
		}
	}
	
	/**Renvoie vrai si l'element est "vide"
	 * @param estVide
	 * @return vrai si est "vide"
	 */
	public boolean estVide(){
		return this.element.equals("vide") || this.element.equals(null);
	}
	
	/**Renvoie vrai si l'element est un rocher
	 * @param estRocher
	 * @return
	 */
	public boolean estRocher(){
		return this.element.equals("rocher");
	}
	
	/**Renvoie vrai si l'element est un navire
	 * @param estNavire
	 * @return
	 */
	public boolean estNavire(){
		return this.element.equals("navire1") || this.element.equals("navire2");
	}
	
	/**Renvoie vrai si l'element est de l'eau
	 * @param estNavire
	 * @return
	 */
	public boolean estEau(){
		return this.element.equals("eau");
	}
	
	/**Renvoie vrai si l'element est du sable
	 * @param estNavire
	 * @return
	 */
	public boolean estSable(){
		return this.element.equals("sable");
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
