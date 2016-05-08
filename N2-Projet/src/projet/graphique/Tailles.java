package projet.graphique;

public final class Tailles {
	
	public static int TAILLE = 10;
	
	public final static int PARCELLE = 37; //taille d'une image carree de parcelle
	
	public final static int BOUTONLARGEx = 150; //largeur d'un bouton de choix
	public final static int BOUTONLARGEy = 50; //hauteur d'un bouton de choix
	
	public final static int BOUTONDIRx = 37;
	public final static int BOUTONDIRy = 37;
	
	public final static int BORDURE = 50;
	
	public final static int PLATEAU = PARCELLE*TAILLE; //taille du plateau en fonction d'une parcelle
	
	public final static int CONSOLEx = 500;
	public final static int CONSOLEy = PLATEAU / 4;
	
	public final static int FENETREx = CONSOLEx + PLATEAU + BORDURE*2 + 400; //largeur de la fenetre
	public final static int FENETREy = 300+PLATEAU; //hauteur de la fenetre
	
	

	


}
