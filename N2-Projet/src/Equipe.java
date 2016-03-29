import java.util.ArrayList;
import java.util.List;

public class Equipe{
	
	
	private String nom;
	private final int ID; //1 ou 2

	private List<Personnage> listePersos = new ArrayList<>();
	private boolean[][] vision = new boolean[Constantes.TAILLEX][Constantes.TAILLEY];
	
	
	public Equipe(String nom, int ID) {		
		if (ID == 1 || ID == 2) {
			this.nom = nom;
			this.ID = ID;
			
			for (int i=0; i<vision.length; i++) {
				for (int j = 0; j < vision.length; j++) {
					vision[i][j] = false;
					// a terminer
				}
			}
			
			
		} else {
			this.nom = null;
			this.ID = 0;
		}
	}
	
	public boolean ajouterPersonnage(Personnage p) {
		if (p.getEquipe().getID() == this.ID) {
			listePersos.add(p);
			return true;
		} else {
			System.out.println("Erreur: " +p.nom+"n'est pas dans cette equipe");
			return false;
		}
	}
	
	public boolean retirerPersonnage(Personnage p) {
		if (listePersos.contains(p)) {
			listePersos.remove(p);
			return true;
		} else {
			System.out.println("Erreur: " +p.nom+"n'est pas dans cette equipe");
			return false;
		}
	}
	
	public List<Personnage> getListePersos() {
		return this.listePersos;
	}
	
	public void setVision() {
		
		boolean[][] newVision = vision;
		//a terminer
		
	}

	public int getID() {
		return ID;
	}
	
	
}
