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
                card.setValue(j);
                card.setCardType(EnumCardType.values()[i]);                                            //ajout la valeur de j a card
                cards.add(card);                          //renvoie une card a l'objet cards
            }

        }
        return cards;
    }

    public static List<Card> suffle(List<Card> cards){
        Collections.shuffle(cards);
        return cards;
    }
    
    public static void DisplayPlayerHandAndDealearCard(Player player, Player dealer) {
        System.out.println(player.getName() + "à une main");
        for (Card card : player.getHand().getCards()) {
            System.out.println(card.getCardType().toString() + " avec valeur " + card.getValue());
        }
        System.out.println(dealer.getName() + "à  une carte visible:");
        
        if(!dealer.getHand().getCards().isEmpty()) {
            System.out.println(dealer.getHand().getCards().get(0).getCardType().toString() + " avec valeur " + dealer.getHand().getCards().get(0).getValue());
        }
        else {
            System.out.println("Pas de carte");
        }
    }
    
}
