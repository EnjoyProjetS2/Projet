
public class Explorateur extends Personnage{
	
	Explorateur(String nom, Equipe e, int x, int y){
		super( nom, e, x, y);
	}
	
	void souleverRocher(){}
	
	public String toString(){
		if(super.getEquipe().getID() == 1){
			return "E";
		} else {
			return "e";
		}
	}	
}
