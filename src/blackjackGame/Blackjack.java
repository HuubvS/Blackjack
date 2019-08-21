package blackjackGame;

import java.util.Scanner;

/**
 * de class voor de blackjack game
 * @author Huub
 *
 */
public class Blackjack {
	/**
	 * Variable of het deck
	 */
	private Cards playingDeck;
	/**
	 * de speler
	 */
	private Player player;
	/**
	 * de dealer
	 */
	private Hand dealer;
	/**
	 * User input scanner
	 */
	private Scanner scanner;
	
	/**
	 * initialiseert het variable en zet de player object
	 * @param player Player object
	 */
	public Blackjack(Player player) {
		// maakt het speelde deck aan
		playingDeck = new Cards();
		
		this.player = player;
		this.dealer = new Hand();
	}
	
	/**
	 * Start het spel bij shuffle van de deck
	 */
	public void start() {
		playingDeck.shuffle();
	}
	
	/**
	 * speler en dealer start het draw van de kaarten 
	 */
	public void draw() {
		// Start Dealing
		// Dealer krijgt 2 kaarten
		dealer.addCard(playingDeck.draw());
		dealer.addCard(playingDeck.draw());
		
		// Player gets two cards for each hands
		for(int i = 0; i < player.getTotalHands(); i++) {
			player.getHand(i).addCard(playingDeck.draw());
			player.getHand(i).addCard(playingDeck.draw());
		}
		
		display();
	}
	
	/**
	 * het spel start en de speler speelt het spel
	 * @param eindigd het hand dat het spel is begonnen
	 * @return geeft de waarde van de hand dat verliest terug
	 */
	public int play(int finishedHand) {
		scanner = new Scanner(System.in);
		for(int i = 0; i <player.getTotalHands(); i++) {
			if(!player.getHand(i).isDead() && !player.getHand(i).isBlackjack() && !player.getHand(i).isPass()) {
				String response;
				while(true) {
					System.out.print(player.getName() + ", wat wil je doen met hand "+ (i+1) +"? ");
					String inputResponse = scanner.next();
					if(!inputResponse.equals("p") && !inputResponse.equals("d") && !inputResponse.equals("2")) {
						System.out.println("Dat begrijp ik niet");
					} else {
						response = inputResponse;
						break;
					}
				}
				
				if(response.equals("d") || response.equals("2")) {
					player.getHand(i).addCard(playingDeck.draw());
					System.out.print("Nieuwe situatie: ");
					for(int j = 0; j < player.getHand(i).getTotalCard(); j++) {
						player.getHand(i).getCard(j).Display();
						System.out.print(" ");
					}
					
					if(response.equals("2")) {
						if(player.getCapital() >= player.getHand(i).getBet()) {
							player.setCapital(player.getCapital() - player.getHand(i).getBet());
							player.getHand(i).setBet(player.getHand(i).getBet()*2);
							player.getHand(i).setDubbel(true);
						}
					}
					
					System.out.print("		inzet = " + player.getHand(i).getBet());
					
					int totalValue = player.getHand(i).calculate();
					if(totalValue > 21) {
						System.out.print("  Dood");
						player.getHand(i).setDead(true);
						finishedHand++;
					} else if(totalValue == 21) {
						System.out.print("  BLACKJACK!");
						player.getHand(i).setBlackjack(true);
						finishedHand++;
					} else if(player.getHand(i).isDubbel()) {
						System.out.print("  Dubbel");
					}
					
					System.out.println("");
				} else if (response.equals("p")) {
					player.getHand(i).setPass(true);
					finishedHand++;
				}
			}
		}
		
		return finishedHand;
	}
	
	/**
	 * Display het huidige condition van de speler en dealer
	 */
	public void display() {
		System.out.println("");
		
		// Display dealer
		System.out.println("*********************************************************************");
		System.out.print("Deler: ");
		dealer.getCard(0).Display();
		System.out.println("");
		System.out.println("---------------------------------------------------------------------");
		
		// speler krijgt 2 kaarten in elke hand
		for(int i = 0; i < player.getTotalHands(); i++) {
			// Display player
			System.out.print(player.getName() + ", hand " + (i+1) + ": ");
			for(int j = 0; j < player.getHand(i).getTotalCard(); j++) {
				player.getHand(i).getCard(j).Display();
				System.out.print(" ");
			}
			System.out.print("		inzet = " + player.getHand(i).getBet());
			
			if(player.getHand(i).isBlackjack()) {
				System.out.print("  BLACKJACK!");
			} else if(player.getHand(i).isDead()) {
				System.out.print("  Dood");
			} else if(player.getHand(i).isPass()) {
				System.out.print("  Gepast");
			} else if(player.getHand(i).isDubbel()) {
				System.out.print("  Dubbel");
			}
			
			System.out.println("");
		}
		System.out.println("*********************************************************************");
		System.out.println("");
	}
	
	/**
	 * display het eind resultaat van het spel
	 */
	public void result() {
		//dealer
		System.out.print("Deler : ");
		dealer.getCard(0).Display();
		System.out.println("");
		
		 while(true) {
			 System.out.println("Druk op enter om de deler te laten spelen...");
			 scanner.nextLine();
			 
			 System.out.print("Deler : ");
			 for(int i = 0; i<dealer.getTotalCard(); i++) {
				 dealer.getCard(i).Display();
				 System.out.print(" ");
			 }
			 System.out.println();
			 
			 if(dealer.calculate() >= 17) {
				 break;
			 }
			 
			 if (dealer.calculate() < 17) {
				 dealer.addCard(playingDeck.draw());
				 
				 if(dealer.calculate() > 21) {
					 dealer.setDead(true);
				 }
			 }
		 }
		 
		int dealerValue = dealer.calculate();
		System.out.println();
		System.out.println("De deler heeft gepast en is geëindigd met " + dealerValue + " punten");
		
		// hierin wordt de resultaat van de speler gegeven win/verlies
		int endBet = 0;
		for(int i = 0; i < player.getTotalHands(); i++) {
			int totalValue = player.getHand(i).calculate();
			int bet = player.getHand(i).getBet();
			String result = "";
			if(!dealer.isDead()) {
				if(totalValue < dealerValue) {
					result = "verliest";
					player.getHand(i).setBet(0);
				}  else if (totalValue > dealerValue) {
					if(totalValue == 21) {
						player.getHand(i).setBet((int)(bet * 2.5));
						result = "wint";
					} else if(totalValue > 21) {
						result = "verliest";
						player.getHand(i).setBet(0);
					}
					else {
						player.getHand(i).setBet(bet * 2);
						result = "wint";
					}
				} else if(totalValue == dealerValue) {
					result = "gelijk spel";
				}
			} else {
				if(totalValue == 21) {
					result = "wint";
					player.getHand(i).setBet((int)(bet * 2.5));
				} else if(totalValue < 21) {
					result = "wint";
					player.getHand(i).setBet(bet * 2);
				} else if (totalValue > 21) {
					result = "verliest";
					player.getHand(i).setBet(0);
				}
			}
			System.out.println(player.getName()+", je "+result+" hand "+(i+1)+" met een inzet van "+bet+".");
			endBet = endBet + player.getHand(i).getBet();
		}
		player.setCapital(player.getCapital() + endBet);
		System.out.println("je kapitaal is nu € " + player.getCapital());
		
		
		//testing om te checken of deck wel gedeeld wordt.
//		while(true) {
//			System.out.println("Draw 10 Card (j) ");
//			String yes = scanner.nextLine();
//			if(yes.equals("q")) {
//				for(int i=0; i<10; i++) {
//					playingDeck.draw();
//				}
//				System.out.println("PLaying Deck Counter: "+playingDeck.getSize());
//			}
//		}
		
		
	}
	
	/**
	 * reset het spel hierbij wordt alleen de waarde van de dealer en spelers handen gereset.
	 */
	public void reset() {
		dealer = new Hand();
		player.resetHands();
	}
}
