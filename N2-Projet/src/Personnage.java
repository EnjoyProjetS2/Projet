public class Personnage extends Parcelle {

	protected String nom;
	protected Equipe e;
	private int x;
	private int y;

	/*
	 * Constructeur defaut
	 * 
	 * @param Personnage
	 */
	Personnage() {
	}

	/*
	 * Constructeur avec un nom, une equipe et des coordonnees
	 * 
	 * @param Personnage
	 */
	Personnage(String nom, Equipe e, int x, int y) {
		this.nom = nom;

		if (e.getID() == 1 || e.getID() == 2) {
			this.e = e;
		}

		this.x = x;
		this.y = y;
	}

	/*
	 * Retourne le nom
	 * 
	 * @param getNom
	 * 
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/*
	 * Modifie le nom
	 * 
	 * @param setNom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/*
	 * Retourne le nom de l'equipe
	 * 
	 * @param getEquipe
	 * 
	 * @return equipe
	 */
	public Equipe getEquipe() {
		return this.e;
	}

	/*
	 * Modifie le nom de l'equipe
	 * 
	 * @param setEquipe
	 */
	public void setEquipe(Equipe equipe) { // verif 1 ou 2
		this.e = equipe;
	}

	/*
	 * Retourne la coordonnee x
	 * 
	 * @param getX
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/*
	 * Modifie la valeur de x
	 * 
	 * @param setX
	 */
	public void setX(int x) {
		this.x = x;
	}

	/*
	 * Retourne la coordonnee y
	 * 
	 * @param getY
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/*
	 * Modifie la valeur de y
	 * 
	 * @param setY
	 */
	public void setY(int y) {
		this.y = y;
	}

}
