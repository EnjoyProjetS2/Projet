
public class Explorateur extends Personnage {

	String nom = "Explorateur";

	Explorateur() {
	}

	void souleverRocher() {
	}

	public String toString() {
		if (this.getEquipe() == 1) {
			return "E";
		} else {
			return "e";
		}
	}

}
