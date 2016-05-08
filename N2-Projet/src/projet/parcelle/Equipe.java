package projet.parcelle;
import java.util.ArrayList;
import java.util.List;
import projet.*;
import projet.graphique.Tailles;
import projet.plateau.Ile;
import projet.plateau.Jeu;
public class Equipe {

	private String nom;
	private Navire navire;
	private final int ID; // 1 ou 2
	private List<Personnage> listePersos = new ArrayList<>();
	private int[][] vision;

	/**
	 * Constructeur: Cree une equipe avec un nom et un ID (1 ou 2)
	 * 
	 * @param nom
	 * @param ID
	 */
	public Equipe(String nom, int ID) {
		if (ID == 1 || ID == 2) {
			this.nom = nom;
			this.ID = ID;
			this.navire = new Navire();
			this.navire.setEquipe(this);			

		} else {
			this.nom = null;
			this.ID = 0;
		}

	}

	/**
	 * Modifie la vision 
	 * @param ile
	 * @return tableau d'int
	 */
	public int[][] setVision(Ile ile) {

		int[][] grille = new int[Tailles.TAILLE][Tailles.TAILLE];
		// initialisation du brouillard
		for (int i = 0; i < ile.getGrille().length; i++) {
			for (int j = 0; j < ile.getGrille()[i].length; j++) {
				grille[i][j] = 7;

			}
		}
		for (int i = 0; i < ile.getGrille().length; i++) {
			for (int j = 0; j < ile.getGrille()[i].length; j++) {

				if (ile.getGrille()[i][j] instanceof Personnage || ile.getGrille()[i][j] instanceof Navire) {
					grille[i][j] = ile.getGrille()[i][j].getId();
					for (int x = i - 1; x <= i + 1; x++) {
						for (int y = j - 1; y <= j + 1; y++) {
							grille[x][y] = ile.getGrille()[x][y].getId();
							ile.getGrille()[x][y].setVisitee(true);

						}
					}
					
				} else if (ile.getGrille()[i][j] instanceof Eau) {
					grille[i][j] = 10;
				} else {
					grille[i][j] = 7;
				}

				if (ile.getGrille()[i][j].isVisitee() == true) {
					grille[i][j] = ile.getGrille()[i][j].getId();

				}
			}

		}

		return grille;

	}

	/**
	 * Affiche la vision associee
	 * @return tableau d'int représentant la vision
	 */
	public int[][] getVision() {
		return this.vision;
	}	

	/**
	 * Retourne le nom de l'equipe
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Retourne le navire de l'equipe
	 * @return navire
	 */
	public Navire getNavire() {
		return navire;
	}

	/**
	 * Definit le navire de l'equipe
	 * @param navire
	 */
	public void setNavire(Navire navire) {
		this.navire = navire;
		this.navire.setEquipe(this);
	}

	/**
	 * Retourne la liste des personnages de l'equipe
	 * @return listePersos
	 */
	public List<Personnage> getListePersos() {
		return this.listePersos;
	}

	/**
	 * Affiche les membres de l'equipe
	 */
	public void afficherEquipe() {
		System.out.println(this.nom + ":");
		for (int i = 0; i < listePersos.size(); i++) {
			System.out.println(listePersos.get(i));
		}
	}

	/**
	 * Ajoute un personnage dans l'equipe
	 * @param p
	 * @return boolean
	 */
	public boolean ajoutPersonnage(Personnage p) {
		if (!listePersos.contains(p)) {
			listePersos.add(p);

			return true;
		}
		return false;
	}

	/**
	 * Retourne l'identifiant de l'equipe
	 * @return ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Definit le nom de l'equipe
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Teste si un personnage de l'equipe ne serait pas (malencontreusement) mort
	 * Retourne vrai si au moins un personnage est en vie
	 * 
	 * @return boolean
	 */
	public boolean survie() {
		for (int i = 0; i < this.getListePersos().size(); i++) {
			if (this.getListePersos().get(i).getEnergie() > 0) {
				return true;
			}
		}
		return false;
	}
}
