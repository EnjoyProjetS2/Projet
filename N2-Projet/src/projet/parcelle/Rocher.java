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
			
			/*if (Rocher.poseClef == false) {
				this.clef = true;
				Rocher.poseClef = true;
			}

			if (Rocher.poseCoffre == false && this.clef == false) {
				this.coffre = true;
				Rocher.poseCoffre = true;
			}	*/	
		}
		
		/**
		 * Modifie si la cle est presente sous le rocher
		 * @param b
		 */
		public void setClef(boolean b) {
			this.clef = b;
		}
		
		/**
		 * Modifie si le rocher contient le coffre
		 * @param b
		 */
		public void setCoffre(boolean b) {
			this.coffre = b;

		}
		
		@Override
		/**
		 * Si la parcelle est traversable par un personnage
		 * @param p
		 * @return boolean
		 */
		public boolean estTraversablePar(Personnage p) {
			return super.traversable;
		}
		
		/**Retourne R
		 * @return string
		 */
		public String toString() {
			
			//test de la position de la clef et du coffre sur le mode texte
			if (this.clef) { return "K"; }
			if (this.coffre) { return "C"; }		
			
			return "R";
		}
}
