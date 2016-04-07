
public class Rocher extends Parcelle{
		
		boolean clef = false;
		boolean coffre = false;
		
		/**Constructeur: cree un rocher et definit une clef et un coffre sur les deux premiers
		 * 
		 */
		public Rocher() {
			super.traversable = false;		
			
			if (Rocher.poseClef == false) {
				this.clef = true;
				Rocher.poseClef = true;
			}

			if (Rocher.poseCoffre == false && this.clef == false) {
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
