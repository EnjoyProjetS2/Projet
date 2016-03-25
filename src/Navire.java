
public class Navire extends Parcelle {

	private int equipe;
	private int posX;
	private int posY;
	
	/** Constructeur: créé un navire d'une équipe 1 ou 2 
	 * au bord de l'ile à la position x rentrée en paramètre
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

	/** Retourne N ou n selon l'équipe détenant le navire
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
