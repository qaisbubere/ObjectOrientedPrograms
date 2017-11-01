package com.bridgelabz.programs;
import com.bridgelabz.util.JSONPrograms;
public class DeckOfCards {
	
	public static void main(String[] args) {
		int noOfPlayers = 4;
		int noOfCards = 9;
	    String[] deckOfCards = JSONPrograms.assignDeckOfCards();
	    String[] deckOfShuffledCards = JSONPrograms.shuffle(deckOfCards);
	    String[][] playerCards = JSONPrograms.distribute(noOfPlayers,noOfCards,deckOfShuffledCards);
	    JSONPrograms.printDistibutedCards(playerCards,noOfPlayers,noOfCards);
}
}
