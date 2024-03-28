/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TotoZone
 */
public class Hand {
    private List<Card> cards = new ArrayList<>();
    private int value;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(Card card) {
        this.cards.add(card);
    }

    public int getValue() {
        value = 0;
        for(Card card : cards){
            value += card.getValue();
        }
        
        return value;
    }   
}
