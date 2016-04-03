
public class Explorateur extends Personnage{

	
	/**Constructeur: cree un Explorateur a partir de Personnage
	 * 
	 * @param nom
	 * @param e
	 * @param x
	 * @param y
	 */
	public Explorateur(String nom, Equipe e, int x, int y){
		super( nom, e, x, y);
	}
	
	/**Capacite de l'explorateur, il peut soulever les rocher et verifier l'interieur
	 * 
	 * @param terrain
	 * @return
	 */
	public boolean souleverRocher(Rocher terrain){
		if(terrain instanceof Rocher){ //si l'element est un rocher on agit
			if(terrain.clef == true){ //si ce rocher a la cle
				terrain.clef = false;
				this.possessionClef = true;
			} else if(terrain.coffre == true){
				System.out.println("Le coffre est là !");
			}
			return true;
		}
		return false;
	}
	
	/**Retourne un E ou un e selon l'equipe
	 * 
	 */
	public String toString(){
		if(super.getEquipe().getID() == 1){
			return "E";
		} else {
			return "e";
		}
	}	
}
