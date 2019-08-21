package blackjackGame;

import java.util.Scanner;

/**
 * hier maken we de classe aan om de game te spelen 
 * @author Huub
 *
 */
public class Casino {
	/**
	 * variable van de player
	 */
	private Player player;
	/**
	 * Input van de player
	 */
	private Scanner scanner;
	/**
	 * het spel
	 */
	private Blackjack blackJack;
	
	/**
	 * constructor, om de initialiseert de variable
	 */
	public Casino() {
		// initialiseert de speler
		player = new Player();
		// initialiseert scanner object
		scanner = new Scanner(System.in);
	}
	
	/**
	 * speler initialiseert het spel
	 */
	public void play() {
		// intro text + commentlijst
		System.out.println("Welkom in het iCasino! we gaan fijn Blackjack spelen!!");
		System.out.println("");
		System.out.println("Commando's");
		System.out.println("----------" );
		System.out.println("p = passen");
		System.out.println("d = draaien");
		System.out.println("2 = inzet verdubbelen");
		System.out.println("");
		
		///////////////////////////////////////////////////////////////////////////////
		// vraagt om de gebruikersnaam
		System.out.print("Wat is je naam? ");
		// speler input zijn naam
		String playerName = scanner.next();
		// zet de waarde van de speler
		player.setName(playerName);
		// welkom text & haalt de gebruikersnaam op plus zegt wat je start kapitaal is
		System.out.println("Welkom, " + player.getName() +". Je startkapitaal is € 1000");
		
		//
		blackJack = new Blackjack(player);
		
		while (true) {
	        ///////////////////////////////////////////////////////////////////////////////
			// vraagt met hoeveel handen de speler wilt spelen
			boolean isValid = false;
			int min, max;
			while(!isValid) {
				min = 1;
				max = 5;
				System.out.print("Met hoeveel handen wil je spelen (" + min + "-" + max + ")? " );
				isValid = handChecker(min, max);
			}
			
			//////////////////////////////////////////////////////////////////////////////
			
			isValid = false;
			while(!isValid) {
				min = 1;
				max = maxBet();
				System.out.print("Met welke inzet wil je spelen (" + min + "-" + max + ")? " );
				isValid = betChecker(min, max);
			}
			
			//////////////////////////////////////////////////////////////////////////////
			
			System.out.println("");
			
			// BlackJack Start
			blackJack.start();
			blackJack.draw();
			
			int finishedHand = 0;
			while(finishedHand != player.getTotalHands()) {
				finishedHand = blackJack.play(finishedHand);
				blackJack.display();
			}
			
			blackJack.result();
			// Game eindigd 
			
			String a = "";
			while(true) {
				System.out.print("Wil je nog een keer spelen (j/n)? ");
				a = scanner.next();
				if (a.equals("j") || a.equals("n")) {
					break;
				} else {
					System.out.println("Dat begrijp ik niet.");
				}
			}
			
			System.out.println();
			if (a.equals("j")) {
				blackJack.reset();
			}
			else if (a.equals("n")) {
				System.out.print("Tot de volgende keer!");
				break;
			}
		}
	}
	
	/**
	 * Check de input van het hand
	 * @param min de minimum waarde van de hand dat gespeelt kan worden
	 * @param max de maximum waarde van de hand dat gespeelt kan worden played
	 * @return geeft de condition of the result terug
	 */
	public boolean handChecker(int min, int max) {
		int hand = numberInputChecker(min, max);
		if(hand > -1) {
			player.setTotalHands(hand);
			return true;
		} else
			return false;
	}
	
	/**
	 * Check de input van de inzet
	 * @param min de minimum waarde van de inzet dat gespeelt kan worden
	 * @param max de maximum waarde van de bet dat gespeelt kan worden
	 * @return geeft de condition van de result terug
	 */
	public boolean betChecker(int min, int max) {
		int bet = numberInputChecker(min, max);
		if(bet > -1) {
			player.setTotalBet(bet);
			player.setCapital(player.getCapital() - player.getTotalBet());
			return true;
		} else
			return false;
	}
	
	/**
	 * Check de input gebaseerd op de minimum en maximum waarde
	 * @param min minimum waarde
	 * @param max maximum waarde
	 * @return geeft de result als een integer, -1 terug
	 */
	public int numberInputChecker(int min, int max) {
		int result;
		if(scanner.hasNextInt()) {
			result = scanner.nextInt();
			if(result < min || result > max) {
				System.out.println("Dat aantal ligt niet tussen " + min + " en " + max + ".");
				result = -1;
			}
		} else {
			System.out.println("Dat begrijp ik niet.");
			scanner.next();
			result = -1;
		}
		return result;
	}
	
	/**
	 * calculeerd de maximum inzet gebaseerd op de kapitaal en de nummer van de handen
	 * @return geeft de maximum inzet terug
	 */
	public int maxBet() {
		return player.getCapital() / player.getTotalHands();
	}
	
}
