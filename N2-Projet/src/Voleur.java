
public class Voleur extends Personnage{

	Voleur(String nom, Equipe e, int x, int y){
		super( nom, e, x, y);
	}
	
	void voler(Personnage perso){
		if(perso instanceof Explorateur){ //si l'element est un personnage on agit
			if(perso.possessionClef == true){ //si ce perso a la cle
				perso.possessionClef = false;
				this.possessionClef = true;
			} 
		}
	}
	
	public String toString(){
		if(super.getEquipe().getID() == 1){
			return "V";
		} else {
			return "v";
		}
	}	
}
