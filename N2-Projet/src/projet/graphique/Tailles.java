package projet.graphique;

import projet.plateau.Jeu;

public final class Tailles {
	
	public final static int PARCELLE = 37; //taille d'une image carree de parcelle
	
	public final static int BOUTONLARGEx = 150; //largeur d'un bouton de choix
	public final static int BOUTONLARGEy = 50; //hauteur d'un bouton de choix
	
	public final static int BOUTONDIRx = 37;
	public final static int BOUTONDIRy = 37;

	
	public final static int PLATEAU = PARCELLE*Jeu.tailleX; //taille du plateau en fonction d'une parcelle
	
	public final static int FENETREx = 850+PLATEAU; //largeur de la fenetre
	public final static int FENETREy = 155+PLATEAU; //hauteur de la fenetre
	
	public final static int CONSOLEx = 500;

	


}
