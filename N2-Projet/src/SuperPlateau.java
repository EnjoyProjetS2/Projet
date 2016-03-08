import java.util.Random;

public class SuperPlateau {
	private Plateau p;
	private Random ran = new Random();
	private int taille = 10;
	private int[][] jeu = new int[taille][taille];
	private String[] gifs = new String[]{"images/un.gif","images/deux.gif","images/trois.gif","images/quatre.gif"};
	public SuperPlateau() {
		// TODO Auto-generated constructor stub
		p = new Plateau(gifs, taille);
	}
	public SuperPlateau(Ile ile){
		p = new Plateau(gifs, ile.getLigne());
	}
	private void remplissage(){
		/*for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				jeu[i][j] = ran.nextInt(gifs.length+1);
			}
		}*/
		jeu[0][0] = 1;
		jeu[0][1] = 2;
	}
	public int getTaille() {
		return taille;
	}
	boolean deplacement(int x, int y, int a, int b){
		int[][] tmp = p.getJeu();
		if(tmp[a][b] == 0){
			tmp[a][b] = tmp[x][y];
			tmp[x][y] = 0;
			return true;
		}else{
			return false;
		}
	}
	public int[][] getJeu() {
		return jeu;
	}
	void setJeu(){
		remplissage();
		p.setJeu(jeu);
	}
	void affichage(){
		p.affichage();
	}
}
