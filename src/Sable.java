
public class Sable extends Parcelle {

	public Sable() {
		super.traversable = true;
	}
	
	public String toString() {
		return " ";
	}

	@Override
	public boolean traverser(Personnage p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actionner(Personnage p) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
