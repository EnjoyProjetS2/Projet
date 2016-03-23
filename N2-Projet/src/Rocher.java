
public class Rocher extends Parcelle{
	
	public static boolean poseCoffre = false;
	public static boolean poseClef = false;
	public boolean coffre = false;
	public boolean clef = false;
	
	public Rocher() {
		super.traversable = false;
	}	
	
	public String toString() {
		return "R";
	}
	

}
