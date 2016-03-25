
public class Eau extends Parcelle {

	public Eau() {
		super.traversable = false;
	}
	
	public String toString() {
		return "~";
	}

	public boolean traverser(Personnage p) {
		return estTraversable();
	}

	public boolean actionner(Personnage p) {
		return false;
	}
	
	
	
	
}
