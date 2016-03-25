
public class Navire extends Parcelle {

	private int equipe;
	private int posX;
	private int posY;
	
	/** Constructeur: cr�� un navire d'une �quipe 1 ou 2 
	 * au bord de l'ile � la position x rentr�e en param�tre
	 * 
	 * @param team
	 * @param x
	 */
	public Navire(int team, int x) {
		if (team == 1 || team == 2) {
			this.equipe = team;
			this.posX = x;
			
			if (team == 1) {
				this.posY = 1;
			} else {
				this.posY = Constantes.TAILLEY-2;
			}			
		}		
	}

	/** Retourne N ou n selon l'�quipe d�tenant le navire
	 * 
	 */
	public String toString() {

		if (equipe == 1) {
			return "N";
		} else {
			return "n";
		}
	}

	public boolean traverser(Personnage p) {
		if (p.getEquipe() == equipe) {
			return true;
		} else {
			return false;
		}
	}

	public boolean actionner(Personnage p) {
		return false;
	}

}
