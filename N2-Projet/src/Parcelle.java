public abstract class Parcelle {
	
	protected boolean traversable = true;
	
	public static boolean poseCoffre = false;
	public static boolean poseClef = false;
		
	public boolean estTraversable() {
		return this.traversable;
	}
	
	public boolean estTraversablePar(Personnage p) {
		return this.traversable;
	}
	
	public void setTraversable(boolean b) {
		this.traversable = b;
	}
	
	
	/*public abstract boolean traverser(Personnage p); 
	//Traverser: si la parcelle est traversable: alors p peut aller dessus (return true)
	
	public abstract boolean actionner(Personnage p);
	//Actionner: si p a une action envers ce type de parcelle: alors action dessus
	*/

}
