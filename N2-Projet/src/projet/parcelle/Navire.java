package projet.parcelle;
import java.util.ArrayList;
import java.util.List;

public class Navire extends Parcelle {
	private Equipe equipe;
	private int x;
	private int y;
	private List<Personnage> persoDansNavire = new ArrayList<>();

	/**
	 * Constructeur: cree un navire
	 */
	public Navire() {
		super.traversable = false;		
	}
	
	/**Cree un navire a des coordonnees precises
	 * @param x
	 * @param y
	 */
	public Navire(int x, int y) {
		this();
		this.x = x;
		this.y = y;	
	}	

	/**
	 * Retourne l'équipe
	 * @return equipe
	 */
	public Equipe getEquipe() {
		return equipe;
	}

	/**Retourne vrai si un personnage dans le navire a ramene le butin et fait gagner la partie
	 * 
	 * @return boolean
	 */
	public boolean presenceDuCoffre(){
		for (Personnage personnage : persoDansNavire) {
			if(personnage.possessionCoffre){
				return true;
			}
		}
		return false;
	}
	
	/**Deplace tous les personnages d'une equipe dans son navire pour le debut de la partie
	 * 
	 */
	public void embarquement() {
		for (int i=0; i<equipe.getListePersos().size(); i++) {
			getPersoDansNavire().add(equipe.getListePersos().get(i));
			equipe.getListePersos().get(i).setX(this.x);
			equipe.getListePersos().get(i).setY(this.y);
		}
	}
	
	/**Retourne un passager tant qu'il y en a dans le navire
	 * 
	 * @return int
	 */
	public int dernierPassager() {
		
		if (persoDansNavire.isEmpty()) {
			return -1;
		}
		
		for (int i=0; i<persoDansNavire.size(); i++) {
			if (persoDansNavire.get(i) instanceof Personnage) {
				return i;
			}
		}
		return -1;
		
	}
	
	/**Retourne la liste des personnges dans le navire
	 * 
	 * @return liste des personnages
	 */
	public List<Personnage> getPersoDansNavire() {
		return persoDansNavire;
	}
	
	/**Retourne vrai si le navire est vide
	 * 
	 * @return boolean
	 */
	public boolean estVide() {
		return persoDansNavire.isEmpty();
	}

	/**Definit la position X du navire
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**Definit la position Y du navire
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**Retourne la position X du navire
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**Retourne la position Y du navire
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**Definit l'equipe à laquelle appartient le navire
	 * 
	 * @param equipe
	 */
	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
		
		if (equipe.getID() == 1) {
			super.id = 2;
		} else if (equipe.getID() == 2) {
			super.id = 15;
		}
		
	}
	
	@Override
	public boolean estTraversablePar(Personnage p) {		
		return super.traversable;
	}

	/**Retourne l'appartenance du navire
	 * @return nom de l'equipe
	 */
	public String toString() {

		return "Navire de l'equipe "+equipe.getNom();
	}
	
	
	
	
}
