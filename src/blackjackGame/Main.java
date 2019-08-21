package blackjackGame;
/**
 * dit is de main class, hier roepen we de Casino class
 * @author Huub
 *
 */
public class Main {
	/**
	 * deze methode gaat het spel Casino initaliseren 
	 * @param args
	 */
	public static void main(String[] args) {
		Casino casino = new Casino();
		casino.play();
	}

}
