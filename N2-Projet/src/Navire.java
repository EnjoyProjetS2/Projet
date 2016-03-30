import java.util.ArrayList;
import java.util.List;

public class Navire extends Parcelle {
	
	protected Equipe equipe;
	private List<Personnage> dansNavire = new ArrayList<>();

	
	public Navire(Equipe e) {
		if (e.getID() == 1 || e.getID() == 2) {
			this.equipe = e;
		}
	}	

	public boolean estTraversablePar(Personnage p) {
		if (p.getEquipe().getID() == equipe.getID()) {
			return true;
		} else {
			return false;
		}
	}	

	public String toString() {

		if (equipe.getID() == 1) {
			return "N";
		} else {
			return "n";
		}
	}
	
	
	
}
