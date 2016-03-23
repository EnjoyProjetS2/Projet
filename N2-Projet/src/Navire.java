
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
	
	private boolean traverser(Personnage p) {
		if (p.equipe == equipe) {
			return true;
		} else {
			return false;
		}
	
	}
	
}
