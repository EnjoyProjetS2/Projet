import java.util.ArrayList;
import java.util.List;

public class Equipe{	
	
	private String nom;
	private final int ID; //1 ou 2

	private List<Personnage> listePersos = new ArrayList<>();
	
	//private boolean[][] vision = new boolean[Constantes.TAILLEX][Constantes.TAILLEY];
	
	
	/**Constructeur: Cree une equipe avec un nom et un ID (1 ou 2)
	 * 
	 * @param nom
	 * @param ID
	 */
	public Equipe(String nom, int ID) {		
		if (ID == 1 || ID == 2) {
			this.nom = nom;
			this.ID = ID;				
			
		} else {
			this.nom = null;
			this.ID = 0;
		}
	}
	
	/**Retourne la liste des personnages de l'equipe
	 * 
	 * @return
	 */
	public List<Personnage> getListePersos() {
		return this.listePersos;
	}
	
	/*public void setVision() {
		//a terminer		
	}*/

	/**Retourne l'identifiant de l'equipe
	 * 
	 * @return
	 */
	public int getID() {
		return ID;
	}
	
	
}
