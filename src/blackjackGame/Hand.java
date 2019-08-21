package blackjackGame;

import java.util.ArrayList;

import blackjackGame.Cards.Value;

/**
 * deze class houdt de hand van de kaarten en de inzet
 * 
 * @author Huub
 *
 */
public class Hand {
	/**
	 * Nummer van de bet
	 */
	private int bet;
	/**
	 * lijst van kaarten
	 */
	private ArrayList<Card> cards;
	/**
	 * deze checkt de conditie of de hand dood is of niet.
	 */
	private boolean dead;
	/**
	 * deze checkt de conditie als er een blackjack is of niet
	 */
	private boolean blackjack;
	/**
	 * deze checkt de conditie of de speler de ronde wil passen
	 */
	private boolean pass;
	/**
	 * deze checkt de conditie of de speler kan dubbelen of niet
	 */
	private boolean dubbel;
	
	/**
	 * een constructor om de waarde te initialiseren
	 */
	public Hand() {
		this.cards = new ArrayList<>();
		this.dead = false;
		this.blackjack = false;
		this.pass = false;
		this.dubbel = false;
	}
	
	/**
	 * deze method is een kaart toe te voegen.
	 * @param card
	 */
	public void addCard(Card card) {
		cards.add(card);
	}
	
	/**
	 * deze method zorgt ervoor dat de speler een kaart krijgt gebaseerd op de index
	 * @param index van de kaarten van de arraylijst
	 * @return
	 */
	public Card getCard(int index) {
		return this.cards.get(index);
	}
	
	/**
	 * deze method zorgt ervoor dat de totale waarde van de kaarten berekent worden.
	 * @return
	 */
	public int calculate() {
		int result = 0;
		int aces = 0;
		for(Card card: this.cards) {
			result = result + card.getIntValue();
			if(card.getValue() == Value.ACE)
				aces++;
		} 
		if(aces > 0) {
			// deze methode berekent de waarde van de A die kan een 11 of een 1 zijn.
			for (int i = 0; i < aces; i++) {
				if(result > 10)
					result += 1;
				else
					result += 11;
			}
		}
		
		return result;
	}
	
	/**
	 * deze method haalt de nummer van de totale kaart
	 * @return
	 */
	public int getTotalCard() {
		return this.cards.size();
	}
	

	/**
	 * deze method haalt de totale inzet
	 * @return
	 */
	public int getBet() {
		return bet;
	}

	/**
	 * deze method bevestig de bet
	 * @param bet
	 */
	public void setBet(int bet) {
		this.bet = bet;
	}

	/**
	 * deze method gaat checken of de hand dood is of niet
	 * @return
	 */
	public boolean isDead() {
		return dead;
	}

	/**
	 * deze method bevest dat de speler dood is
	 * @param dead
	 */
	public void setDead(boolean dead) {
		this.dead = dead;
	}

	/**
	 * deze method will check of de speler blackjack heeft of niet
	 * @return
	 */
	public boolean isBlackjack() {
		return blackjack;
	}

	/**
	 * deze method bevestig dat de speler blackjack heeft
	 * @param blackjack
	 */
	public void setBlackjack(boolean blackjack) {
		this.blackjack = blackjack;
	}

	/**
	 * deze method gaat checken of de speler op pass heeft gedrukt
	 * @return
	 */
	public boolean isPass() {
		return pass;
	}

	/**
	 * deze method bevestig dat er op pass is gedrukt
	 * @param pass
	 */
	public void setPass(boolean pass) {
		this.pass = pass;
	}

	/**
	 * deze method gaat checken of er op dubbel is gedrukt 
	 * @return
	 */
	public boolean isDubbel() {
		return dubbel;
	}

	/**
	 * deze method bevestig dat er op dubbel is gedrukt
	 * @param dubbel
	 */
	public void setDubbel(boolean dubbel) {
		this.dubbel = dubbel;
	}
	
}
