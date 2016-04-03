import java.util.ArrayList;
import java.util.List;

public class Navire extends Parcelle {
	
	private Equipe equipe;
	private int x;
	private int y;
	private List<Personnage> persoDansNavire = new ArrayList<>();
	
	

	public Navire() {
	}
	

	public Navire(int x, int y) {
		this.x = x;
		this.y = y;	
	}	

	public boolean estTraversablePar(Personnage p) {
		if (p.getEquipe().getID() == equipe.getID()) {
			return true;
		} else {
			return false;
		}
	}	
	public boolean presenceDuCoffre(){
		for (Personnage personnage : persoDansNavire) {
			if(personnage.possessionCoffre){
				return true;
			}
		}
		return false;
	}
	public void embarquement() {
		for (int i=0; i<equipe.getListePersos().size(); i++) {
			getPersoDansNavire().add(equipe.getListePersos().get(i));
			equipe.getListePersos().get(i).setX(this.x);
			equipe.getListePersos().get(i).setX(this.y);
		}
	}
	
	public int dernierPassager() {
		
		if (persoDansNavire.isEmpty()) {
			return -1;
		}
		
		int idx;
		for (int i=0; i<persoDansNavire.size(); i++) {
			if (persoDansNavire.get(i) instanceof Personnage) {
				return i;
			}
		}
		return -1;
		
	}
	
	public List<Personnage> getPersoDansNavire() {
		return persoDansNavire;
	}
	
	public boolean estVide() {
		return persoDansNavire.isEmpty();
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public String toString() {

		if (equipe.getID() == 1) {
			return "N";
		} else {
			return "n";
		}
	}
	
	
	
	
}
