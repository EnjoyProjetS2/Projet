
public class Navire extends Parcelle {
	
	private int equipe;
	private int posX;
	private int posY;
	
	
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

	public boolean traverser(Personnage p) {
		if (p.getEquipe().getID() == equipe) {
			return true;
		} else {
			return false;
		}
	}

	public boolean actionner(Personnage p) {
		return false;
	}
	
}
