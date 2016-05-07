package projet.plateau;

import java.util.Random;

import javax.swing.JOptionPane;

import projet.parcelle.Navire;
import projet.parcelle.Parcelle;
import projet.parcelle.Personnage;
import projet.parcelle.Sable;

public class Deplacement {
	
	Ile ile;
	Personnage e;
	
	public Deplacement(Ile ile) {
		this.ile = ile;
	}
	
	public Deplacement(Ile ile, Personnage e) {
		this(ile);
		this.e = e;
	}
	
	public void setPersonnage(Personnage e) {
		this.e = e;
	}
	
	/**
	 * Retourne vrai si le deplacement est possible et deplace le personnage en parametre vers une direction precise
	 * 
	 * @param e
	 * @param deplacement
	 * @return boolean 
	 */
	public boolean deplacement(String deplacement) {

		Parcelle tmp = new Sable();
		int newPosX = 1, newPosY = 1;

		switch (deplacement) {
		case "gauche":
			newPosX = e.getX();
			newPosY = e.getY() - 1;
			break;

		case "droite":
			newPosX = e.getX();
			newPosY = e.getY() + 1;
			break;

		case "haut":
			newPosX = e.getX() - 1;
			newPosY = e.getY();
			break;

		case "bas":
			newPosX = e.getX() + 1;
			newPosY = e.getY();
			break;
			
		case "hautgauche":
			newPosX = e.getX() - 1;
			newPosY = e.getY() - 1;
			break;
			
		case "hautdroite":
			newPosX = e.getX() - 1;
			newPosY = e.getY() + 1;
			break;
			
		case "basgauche":
			newPosX = e.getX() + 1;
			newPosY = e.getY() - 1;
			break;
			
		case "basdroite":
			newPosX = e.getX() + 1;
			newPosY = e.getY() + 1;
			break;

		default:
			break;
		}

		if (ile.getGrille()[newPosX][newPosY].estTraversablePar(e)) {
			
			Sable sable = (Sable) ile.getGrille()[newPosX][newPosY];

			ile.getGrille()[newPosX][newPosY] = ile.getGrille()[e.getX()][e.getY()];
			ile.getGrille()[e.getX()][e.getY()] = tmp;	

			e.setX(newPosX);
			e.setY(newPosY);

			if (sable.estPiegee()) {
				e.setEnergie(e.getEnergie() - 50);
				JOptionPane.showMessageDialog(null, e.getNom()+" est tombe dans un piege. Il perd 50 d'energie.");
			}
			
			e.setEnergie(e.getEnergie() - 1);

			return true;

		} else if (ile.getGrille()[newPosX][newPosY] instanceof Navire) {

			Navire nav = (Navire) ile.getGrille()[newPosX][newPosY];

			if (nav.getEquipe().getID() == e.getEquipe().getID()) {

				e.getEquipe().getNavire().getPersoDansNavire().add(e);
				ile.getGrille()[e.getX()][e.getY()] = tmp;

				e.setX(newPosX);
				e.setY(newPosY);

				e.setEnergie(e.getEnergie() - 1);

				return true;

			}

		}

		return false;
	}
	
	public boolean deplacementAleatoire() {
		
		int choix = new Random().nextInt(4)+1;
		
		String retour = "";
		
		switch(choix) {
		case 1:
			retour = "gauche";
			break;
		case 2:
			retour = "droite";
			break;
		case 3:
			retour = "haut";
			break;
		case 4:
			retour = "bas";
		default:
			break;
		}
		
		return deplacement(retour);		
		
	}
	
public boolean debarquementAleatoire() {
		
		int choix = new Random().nextInt(4)+1;
		
		String retour = "";
		
		switch(choix) {
		case 1:
			retour = "gauche";
			break;
		case 2:
			retour = "droite";
			break;
		case 3:
			retour = "haut";
			break;
		case 4:
			retour = "bas";
		default:
			break;
		}
		
		return debarquement(retour);		
		
	}

	/**
	 * Retourne vrai si c'est possible et sort le personnage en parametre du navire en le placant autour
	 * 
	 * @param e
	 * @param deplacement
	 * @return boolean
	 */
	public boolean debarquement(String deplacement) {

		int newPosX = 1, newPosY = 1;

		switch (deplacement) {
		case "gauche":
			newPosX = e.getX();
			newPosY = e.getY() - 1;
			break;

		case "droite":
			newPosX = e.getX();
			newPosY = e.getY() + 1;
			break;

		case "haut":
			newPosX = e.getX() - 1;
			newPosY = e.getY();
			break;

		case "bas":
			newPosX = e.getX() + 1;
			newPosY = e.getY();
			break;
			
		case "hautgauche":
			newPosX = e.getX() - 1;
			newPosY = e.getY() - 1;
			break;
			
		case "hautdroite":
			newPosX = e.getX() - 1;
			newPosY = e.getY() + 1;
			break;
			
		case "basgauche":
			newPosX = e.getX() + 1;
			newPosY = e.getY() - 1;
			break;
			
		case "basdroite":
			newPosX = e.getX() + 1;
			newPosY = e.getY() + 1;
			break;

		default:
			break;
		}

		if (ile.getGrille()[newPosX][newPosY].estTraversablePar(e)) {
			
			Sable sable = (Sable) ile.getGrille()[newPosX][newPosY];

			ile.getGrille()[newPosX][newPosY] = e;
			e.setX(newPosX);
			e.setY(newPosY);
			e.getEquipe().getNavire().getPersoDansNavire().remove(e);
			
			if (sable.estPiegee()) {
				e.setEnergie(e.getEnergie() - 50);
				JOptionPane.showMessageDialog(null, e.getNom()+" est tombe dans un piege. Il perd 50 d'energie.");
			}
			
			
			return true;

		}

		return false;

	}	

}
