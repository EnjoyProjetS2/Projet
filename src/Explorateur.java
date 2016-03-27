
public class Explorateur extends Personnage{
	
	private int numero;
	Explorateur(String nom, int equipe, int x, int y,int numero){
		super( nom, equipe,x , y);
		this.numero = numero;
	}
	
	void souleverRocher(){}
	
	public String toString(){
		if(this.equipe == 1){
			return "E";
		} else {
			return "e";
		}
	}

	public int getNumero() {
		return numero;
	}
	
}
