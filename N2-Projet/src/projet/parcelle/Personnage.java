package projet.parcelle;
public class Personnage extends Parcelle {

	protected String nom;
	protected Equipe equipe;
	private int x;
	private int y;
	protected boolean possessionClef;
	protected boolean possessionCoffre;
	protected int energie;
	/**
	 * Constructeur par defaut
	 */
	public Personnage() {
		super.traversable = false;
	}

	/**
	 * Constructeur avec un nom, une equipe et des coordonnees
	 * 
	 * @param nom
	 * @param equipe
	 * @param x
	 * @param y
	 */
	public Personnage(String nom, Equipe e, int x, int y) {
		this();
		this.nom = nom;

		if (e.getID() == 1 || e.getID() == 2) {
			this.equipe = e;
		}

		this.x = x;
		this.y = y;
	}	
	/**
	 * Retourne le nom
	 * 
	 * @return string
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Retourne la quantite d'energie
	 * @return int : energie
	 */
	public int getEnergie() {
		return energie;
	}

	/**
	 * Modifie la quantite d'energie
	 * @param energie
	 */
	public void setEnergie(int energie) {
		this.energie = energie;
	}

	/**
	 * Modifie le nom
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Retourne le nom de l'equipe
	 * @return equipe
	 */
	public Equipe getEquipe() {
		return this.equipe;
	}

	/**
	 * Modifie le nom de l'equipe
	 * @param equipe
	 */
	public void setEquipe(Equipe equipe) { // verif 1 ou 2
		this.equipe = equipe;
	}
	
	/**
	 * Retourne la coordonnee x
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Modifie la valeur de x
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Retourne la coordonnee y
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Modifie la valeur de y
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	@Override
	/**
	 * Definit si la parcelle est traversable
	 * @param p
	 * @return boolean
	 */
	public boolean estTraversablePar(Personnage p) {
		return super.traversable;
	}	
}
