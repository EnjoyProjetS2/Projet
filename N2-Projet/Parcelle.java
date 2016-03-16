public class Parcelle {

	public String element;
	public static boolean poseCoffre = false;
	public static boolean poseClef = false;
	public boolean coffre = false;
	public boolean clef = false;

	/* Constructeur: cree une parcelle vide
	 * @param element
	 */
	public Parcelle() {
		this.element = "vide"; 
	}	
	
	/* Constructeur: Cree une parcelle vide, avec pour parametre une chaine de caracteres
	 * @param Parcelle
	 * */
	public Parcelle(String elem) {		
		if (elem.equals("vide") || elem.equals(null)) {
			this.element = "vide";
		} else {
			this.element = elem;
		}
	}
			
	/*Retourne l'element de la parcelle
	 * @param getElement
	 * @return l'element  
	 */
	public String getElement() {
		return this.element;
	}

	/*Redefinit l'element de la parcelle
	 * @param setElement
	 */
	public void setElement(String elem) {
		if (elem.equals("vide") || elem.equals(null)) {
			this.element = "vide";
		} else {
			this.element = elem;
		}
	}
	
	/*Renvoie vrai si l'element est "vide"
	 * @param estVide
	 * @return vrai si est "vide"
	 */
	public boolean estVide(){
		return this.element.equals("vide");
	}
	
	/* Affiche la premiere lettre de l'element en majuscule
	 * @param toString
	 * @see java.lang.Object#toString()
	 */
	public String toString() {		
		if (element.equals("vide")) {
			return " ";
		} else {
			return element.substring(0,1).toUpperCase();	
		}
	}
	
}
