
public class Navire extends Parcelle {
	
	private int equipe;
	
	public Navire() {
	}
	
	public Navire(int team) {		
		if (team == 1 || team == 2) {
			this.equipe = team;
		}
	}

	public String toString() {
		
		if (equipe == 1) {
			return "N";
		} else {
			return "n"; 
		}
	}	
	
	
	public boolean estTraversable(Personnage p) {
		if (p.getEquipe() == equipe) {
			return true;
		} else {
			return false;
		}
	}
	
}
