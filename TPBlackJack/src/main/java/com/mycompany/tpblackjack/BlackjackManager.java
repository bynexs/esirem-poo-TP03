/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpblackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author conta
 */
public class BlackjackManager {

    public static List<Card> CreationDeck() {                //
        List<Card> cards = new ArrayList<>();               //creation de la liste 
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < 14; j++) {
                
                Card card = new Card();
                card.setValue(j);                       //ajout la valeur de j a card
                card.setCardType(EnumCardType.values()[i]);
                cards.add(card);                          //renvoie une card a l'objet cards
            }

        }
        return cards;
    }

    public static List<Card> suffle(List<Card> cards) {
        Collections.shuffle(cards);
        return cards;
    }

    public static void DisplayPlayerHandAndDealearCard(Player player, Player dealer) {
        System.out.println(player.getName() + " à une main \n");
        System.out.println("Balance: " + player.getBalance());
        for (Card card : player.getHand().getCards()) {
            System.out.println(card.getCardType().toString() + " avec valeur " + card.getValue());
        }
        System.out.println(dealer.getName() + " à  une carte visible:");

        if (!dealer.getHand().getCards().isEmpty()) {
            System.out.println(dealer.getHand().getCards().get(0).getCardType().toString() + " avec valeur " + dealer.getHand().getCards().get(0).getValue());
        } else {
            System.out.println("Pas de carte");
        }
    }

    public static void InitialisationGame(List<Card> cards, Player player, Player dealer) {
        Hand handPlayer = new Hand();
        Hand handDealer = new Hand();

        for (int i = 0; i < 4; i++) {
            Card card = cards.get(i);
            if (i % 2 == 0) {
                handPlayer.setCards(card);
            } else {
                handDealer.setCards(card);
            }
        }
        player.setHand(handPlayer);
        dealer.setHand(handDealer);
        DisplayPlayerHandAndDealearCard(player, dealer);
    }

    public static Player PickCard(List<Card> cards, Player player) {
        player.getHand().setCards(cards.get(0));
        return player;
    }

    public static void ShowAllCard(Player player, Player dealer) {
        System.out.println("La main de:" + player.getName());
        for (Card card : player.getHand().getCards()) {
            System.out.println(card.getCardType().toString() + " avec valeur " + card.getValue());
        }
        System.out.println("Total points: " + player.getHand().getValue());

        System.out.println("La main de la Banque");
        for (Card card : dealer.getHand().getCards()) {
            System.out.println(card.getCardType().toString() + " avec valeur " + card.getValue());
        }
        System.out.println("Total points: " + dealer.getHand().getValue());

        if(player.getHand().getValue() > dealer.getHand().getValue() && player.getHand().getValue() <= 21){
            System.out.println("Joueur: " + player.getName() + " a gagné");
        }else{
            System.out.println("La banque a gagné");
        }
    }
}
