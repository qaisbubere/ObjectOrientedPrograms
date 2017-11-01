package com.bridgelabz.programs;
import com.bridgelabz.util.JSONPrograms;
public class DeckOfCardsByRank {

	public static void main(String[] args) {
		int noOfPlayers = 4;
		int noOfCards = 9;
		String[] deckOfCards = JSONPrograms.assignDeckOfCards();
		String[] deckOfShuffleCards = JSONPrograms.shuffle(deckOfCards);
		String[][] playerCards = JSONPrograms.distribute(noOfPlayers, noOfCards,deckOfShuffleCards);
		System.out.println("Cards before sorting...");
		System.out.println();
		JSONPrograms.printDistibutedCards(playerCards, noOfPlayers, noOfCards);
		System.out.println("Cards after sorting...");
		JSONPrograms.printSortedCards(playerCards,noOfPlayers,noOfCards);
	}

}
