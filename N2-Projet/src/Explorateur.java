
public class Explorateur extends Personnage{

	Explorateur(String nom, Equipe e, int x, int y){
		super( nom, e, x, y);
	}
	
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
	
	public String toString(){
		if(super.getEquipe().getID() == 1){
			return "E";
		} else {
			return "e";
		}
	}	
}
