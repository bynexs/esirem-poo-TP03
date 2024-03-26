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

    public static List<Card> suffle(List<Card> cards){
        Collections.shuffle(cards);
        return cards;
    }
    
    public void InitialisationGame(List<Card> cards){
        Player player = new Player();
        Player dealer = new Player();
        Hand handPlayer = new Hand();
        Hand handDealer = new Hand();
    
        for(int i = 0; i < 4; i++){
            Card card = cards.get(i);
            if(i % 2 == 0){
                handPlayer.setCards(card);
            }else{
                handDealer.setCards(card);
            }
        }
        player.setHand(handPlayer);
        dealer.setHand(handDealer);
    }
}
