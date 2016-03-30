import java.util.ArrayList;
import java.util.List;

public class Personnage extends Parcelle{
	
	protected String nom;
	protected int equipe;
	private int x;
	private int y;
	private static List<Personnage> listePersos = new ArrayList<>();	

	/* Constructeur par défaut
	 * @param Personnage
	 */
	Personnage(){}
	
	/* Constructeur avec un nom, une equipe et des coordonnées
	 * @param Personnage
	 */
	Personnage(String nom, int equipe, int x, int y){
		this.nom = nom;
		this.equipe = equipe; //verif 1 ou 2 lol
		this.x = x;
		this.y = y;
	}
	
	/** Afficher la liste des personnages suivi de leur �quipe
	 * 
	 */
	public static void afficherPersonnages() {
		for (int i=0; i<listePersos.size(); i++) {
			System.out.println(listePersos.get(i).getNom()+" | Equipe: "+listePersos.get(i).getEquipe());
		}
	}

	/** Retourne la liste des personnagges
	 * 
	 * @return
	 */
	public static List<Personnage> getListePersos() {
		return listePersos;
	}	
		
	
	/* Retourne le nom
	 * @param getNom
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}
	
	/* Modifie le nom
	 * @param setNom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/* Retourne le nom de l'equipe
	 * @param getEquipe
	 * @return equipe
	 */
	public int getEquipe() {
		return equipe;
	}
	
	/* Modifie le nom de l'equipe
	 * @param setEquipe
	 */
	public void setEquipe(int equipe) { //verif 1 ou 2
		this.equipe = equipe;
	}
	
	/* Retourne la coordonnee x
	 * @param getX
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/* Modifie la valeur de x
	 * @param setX
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/* Retourne la coordonnee y
	 * @param getY
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/* Modifie la valeur de y
	 * @param setY
	 */
	public void setY(int y) {
		this.y = y;
	}


	
}