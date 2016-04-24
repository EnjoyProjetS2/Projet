public abstract class Parcelle {
	
	
	protected int id;
	protected boolean traversable = true;
	private boolean visitee = false;
	
	public static boolean poseCoffre = false;
	public static boolean poseClef = false;
		
		
	/**Retourne vrai si la parcelle est traversable
	 * 
	 * @return
	 */
	public boolean estTraversable() {
		return this.traversable;
	}
	
	/**Retourne vrai si la parcelle est traversable par un personnage p
	 * 
	 * @param p
	 * @return
	 */
	public boolean estTraversablePar(Personnage p) {
		return this.traversable;
	}
	
	/**Definit la traversabilite de la parcelle
	 * 
	 * @param b
	 */
	public void setTraversable(boolean b) {
		this.traversable = b;
	}

	public boolean isVisitee() {
		return visitee;
	}

	public void setVisitee(boolean visitee) {
		this.visitee = visitee;
	}

	public int getId() {
		return id;
	}
	
	

}
