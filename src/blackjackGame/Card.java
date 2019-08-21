package blackjackGame;

import blackjackGame.Cards.Suit;
import blackjackGame.Cards.Value;

/**
 * The class van een card
 * @author Huub
 *
 */
public class Card {
	/**
	 * waarde van de card
	 */
	private Cards.Value value;
	/**
	 * Suit of the card
	 */
	private Cards.Suit suit;
	
	/**
	 * Constructor om de set van de card met zijn value en suit
	 * @param value card's value
	 * @param suit card's suit
	 */
	public Card(Value value, Suit suit) {
		this.value = value;
		this.suit = suit;
	}

	/**
	 * Display the card suit and value
	 */
	public void Display() {
		switch(this.suit) {
			case CLUB:
				System.out.print("\u2663");
				break;
			case SPADE:
				System.out.print("\u2660");
				break;
			case HEART:
				System.out.print("\u2665");
				break;
			case DIAMOND:
				System.out.print("\u2666");
				break;
			default:
				break;
		}
		
		if(this.value == Value.ACE)
			System.out.print("A");
		else if(this.value == Value.KING)
			System.out.print("K");
		else if(this.value == Value.QUEEN)
			System.out.print("V");
		else if(this.value == Value.JACK)
			System.out.print("J");
		else
			System.out.print(getIntValue());
	}
	
	/**
	 * haalt de waarde van de card
	 * @return geeft waarde van de card terug
	 */
	public int getIntValue() {
		int result;
		switch(this.value) {
			case TWO: result = 2; break;
			case THREE: result = 3; break;
			case FOUR: result = 4; break;
			case FIVE: result = 5; break;
			case SIX: result = 6; break;
			case SEVEN: result = 7; break;
			case EIGHT: result = 8; break;
			case NINE: result = 9; break;
			case TEN:
			case JACK:
			case KING:
			case QUEEN: result = 10; break;
			case ACE: result = 0; break;
			default: result = 0; break;
		}
		return result;
	}
	
	/**
	 * zet de waarde van de card
	 * @param value
	 */
	public void setValue(Cards.Value value) {
		this.value = value;
	}

	/**
	 * zet de suit van de card
	 * @param suit
	 */
	public void setSuit(Cards.Suit suit) {
		this.suit = suit;
	}

	/**
	 * haalt de waarde van de card
	 * @return geeft de waarde van de card terug
	 */
	public Cards.Value getValue() {
		return value;
	}

	/**
	 * haalt de suit van de card
	 * @return geeft de waar de suit van de card terug
	 */
	public Cards.Suit getSuit() {
		return suit;
	}
}



