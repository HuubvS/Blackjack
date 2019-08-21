package blackjackGame;

import java.util.ArrayList;

/**
 * hier maken we een class aan voor de naam, hands en kapitaal
 * @author Huub
 *
 */
public class Player {
	private String name;
	private ArrayList<Hand> hands;
	private int capital;
	/**
	 * constructor initaliseren de naam, hands en de kapitaal
	 */
	public Player() {
		this.name = "";
		this.hands = new ArrayList<>();
		this.capital = 1000;
	}
	/**
	 * deze methode zet de waarde van de hand en voegt het toe aan de lijst
	 * @param hands
	 */
	public void setTotalHands(int hands) {
		for(int i = 0; i < hands; i++) {
			this.hands.add(new Hand());
		}
	}
	/**
	 * deze methode geeft nummer van de handen binnen het arraylijst 
	 * @return
	 */
	public int getTotalHands() {
		if(!hands.isEmpty())
			return hands.size();
		else
			return 0;
	}
	/**
	 * deze methode geeft hands object binnen het arraylijst gebaseerd op de index
	 * @param index
	 * @return
	 */
	public Hand getHand(int index) {
		return this.hands.get(index);
	}
	/**
	 * deze methode zet de totale inzet van de handen.
	 * @param bet
	 */
	public void setTotalBet(int bet) {
		for(Hand hand: this.hands) {
			hand.setBet(bet);
		}
	}
	/**
	 * deze methode sommeerd de totale inzet van alle handen.
	 * @return
	 */
	public int getTotalBet() {
		int total = 0;
		for(Hand hand: this.hands)
			total = total + hand.getBet();
		return total;
	}
	/**
	 * deze methode geeft de spelers naam 
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * deze methode zet een nieuwe waarde aan de naam
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * deze methode geeft de kapitaal
	 * @return
	 */
	public int getCapital() {
		return capital;
	}
	/**
	 * deze methode zet de kapitaal
	 * @param capital
	 */
	public void setCapital(int capital) {
		this.capital = capital;
	}
	/**
	 * deze methode reset de lijst van handen
	 */
	public void resetHands() {
		this.hands = new ArrayList<>();
	}
}
