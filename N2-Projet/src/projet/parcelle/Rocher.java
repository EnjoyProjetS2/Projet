package projet.parcelle;

public class Rocher extends Parcelle{
		
		boolean clef = false;
		boolean coffre = false;
		
		/**Constructeur: cree un rocher et definit une clef et un coffre sur les deux premiers
		 * 
		 */
		public Rocher() {
			super.traversable = false;
			super.id = 1;
			
			if (Rocher.poseClef == false) {
				System.out.println("poseclef");
				this.clef = true;
				Rocher.poseClef = true;
			}

			if (Rocher.poseCoffre == false && this.clef == false) {
				System.out.println("posecoffre");
				this.coffre = true;
				Rocher.poseCoffre = true;
			}		
		}	
		
		/**Retourne R
		 * 
		 */
		public String toString() {
			
			//test de la position de la clef et du coffre sur le mode texte
			if (this.clef) { return "K"; }
			if (this.coffre) { return "C"; }		
			
			return "R";
		}
}
