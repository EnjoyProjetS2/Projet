
public class EssaiIle {

	public static void main(String[] args) {
			
			int tailleX = 10;
			int tailleY = 10;
		
			Parcelle[][] parcelle = new Parcelle[tailleX][tailleY];
			
			Ile ile = new Ile(parcelle);
			parcelle[1][0] = new Parcelle();
			parcelle[2][2] = new Parcelle("coffre");
			
			System.out.println(ile.toString());
			
			
			
		}

}