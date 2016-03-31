import java.util.ArrayList;
import java.util.List;

public class Equipe{	
	
	private String nom;
	private Navire navire;
	

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
			this.navire = new Navire();
			this.navire.setEquipe(this);
			
		} else {
			this.nom = null;
			this.ID = 0;
		}
	}
	
	public Navire getNavire() {
		return navire;
	}

	public void setNavire(Navire navire) {
		this.navire = navire;
		this.navire.setEquipe(this);

	}
	
	/**Retourne la liste des personnages de l'equipe
	 * 
	 * @return
	 */
	public List<Personnage> getListePersos() {
		return this.listePersos;
	}
	
	public void afficherEquipe(){
		System.out.println(this.nom +":");
		for (int i = 0; i < listePersos.size(); i++) {
			System.out.println(listePersos.get(i));
		}
	}
	
	public boolean ajoutPersonnage(Personnage p){
		if(!listePersos.contains(p)){
			listePersos.add(p);
			
			return true;
		}
		return false;
	}
		

	/**Retourne l'identifiant de l'equipe
	 * 
	 * @return
	 */
	public int getID() {
		return ID;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
}
