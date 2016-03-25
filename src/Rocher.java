
public class Rocher extends Parcelle{
			
	boolean clef = false;
	boolean coffre = false;
	
	public Rocher() {
		super.traversable = false;		
		
		if (Rocher.poseClef == false) {
			this.clef = true;
			Rocher.poseClef = true;
		}

		if (Rocher.poseCoffre == false && this.clef == false) {
			this.coffre = true;
			Rocher.poseCoffre = true;
		}		
	}	
	
	public String toString() {
		
		//test de la position de la clef et du coffre
		if (this.clef) { return "K"; }
		if (this.coffre) { return "C"; }		
		
		return "R";
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
