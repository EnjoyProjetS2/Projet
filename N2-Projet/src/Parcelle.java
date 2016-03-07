
public class Parcelle {

	private String element;
	
	public Parcelle() { //Cr�e une parcelle vide
		this.element = "vide"; 
	}
	
	public Parcelle(String elem) {		
		if (elem.equals("vide") || elem.equals(null)) {
			this.element = "vide";
		} else {
			this.element = elem;
		}
	}
			
	public String getElement() {
		return element.substring(0,1).toUpperCase()+element.substring(1, element.length());
	}

	public void setElement(String elem) {
		if (elem.equals("vide") || elem.equals(null)) {
			this.element = "vide";
		} else {
			this.element = elem;
		}
	}

	public String toString() { //Retourne la premi�re lettre de l'�lement en majuscule		
		if (element.equals("vide")) {
			return " ";
		} else {
			return element.substring(0,1).toUpperCase();	
		}
	}
	
}
