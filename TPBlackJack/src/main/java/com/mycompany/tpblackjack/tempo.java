/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpblackjack;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author conta
 */
public class tempo {
    
    public static List<Card> CreationDeck(){                //
        List<Card> cards = new ArrayList<>();               //creation de la liste 
        for (int i=0; i < 4; i++){
            for (int j=1; j < 14; j++){
                Card card = new Card();
                card.setValue(j); 
                card.setCardType(EnumCardType.values()[i]);                                            //ajout la valeur de j a card
                cards.add(card);                          //renvoie une card a l'objet cards
            }
            
        }
    return cards;
    }
            
    
}
