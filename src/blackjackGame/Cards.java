package blackjackGame;

import java.util.ArrayList;
import java.util.Random;
/**
 * deze class maken we de kaarten aan
 * @author Huub
 *
 */
public class Cards {
	/**
	 * deze class geeft een constant collectie van waarde van de kaart
	 * @author Huub
	 *
	 */
	public enum Value {
		TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, KING, QUEEN, ACE
	}
	/**
	 * deze class geeft een constant van de voorkant van de kaarten
	 * @author Huub
	 *
	 */
	public enum Suit {
		CLUB, DIAMOND, SPADE, HEART
	}
	
	/**
	 * lijst van kaarten
	 */
	private ArrayList<Card> cards;   
	
	/**
	 * deze methode geeft de nummer van de kaarten
	 */
	private int startDeck;
	
	/**
	 * constructor, om de volle deck te maken
	 */
	public Cards() {
		this.createFullCards();
		this.startDeck = this.cards.size();
	}
	
	/**
	 * deze methode maakt 6 decks van kaarten aan
	 */
	public void createFullCards() {
		if(this.cards == null || this.cards.size() <= this.startDeck)
			this.cards = new ArrayList<Card>();
		
		int decks = 6;
		for(int i = 0; i < decks; i++) {
			for(Suit suit: Suit.values()) {
				for(Value value: Value.values()) {
					this.cards.add(new Card(value, suit));
				}
			}
		}
	}
	
	/**
	 * deze methode maakt een shuffle aan, zodat de decks van kaarten geshuffeld worden.
	 */
	public void shuffle() {
		ArrayList<Card> tmpDeck = new ArrayList<Card>(); {
			//use Random
			Random random = new Random();
			int randomCardIndex = 0;
			int originalSize = this.cards.size();
			for(int i = 0; i < originalSize; i++) {
				// gen Random Index, dit doen we zodat we een stapel van de ene eraf halen en bij de andere erbij doen.
				randomCardIndex = random.nextInt((this.cards.size()-1 - 0) + 1) + 0;
				tmpDeck.add(this.cards.get(randomCardIndex));
				this.cards.remove(randomCardIndex);
			}
			//hier worden de kaarten opgeslagen dat geshuffeld zijn
			this.cards = tmpDeck;
		}
	}
	
	/**
	 * verwijderd een kaart van de deck
	 * @param i index van de arraylijst
	 */
	public void removeCard(int i) {
		this.cards.remove(i);
	}
	
	/**
	 * haalt een kaart van de deck
	 * @param i index van de kaart van de arraylijst 
	 * @return
	 */
	public Card getCard(int i) {
		return this.cards.get(i);
	}
	
	/**
	 * Add one kaart van de deck
	 * @param addCard
	 */
	public void addcard(Card addCard) {
		this.cards.add(addCard);
	}
	
	/**
	 * Draws 1 kaart van de deck
	 * @return
	 */
	public Card draw() {
		Card result = this.getCard(0);
		this.removeCard(0);
		this.reset();
		return result;
	}
	
	/**
	 * Reset de deck als de aantal waarde minder dan de helft is.
	 */
	public void reset() {
		if(this.cards.size() <= startDeck/2) {
			this.createFullCards();
		}
	}
	
	/**
	 * deze methode haalt de nummer van de kaarten, dus alle kaarten van de deck
	 * @return
	 */
	public int getSize() {
		return this.cards.size();
	}
}
	
