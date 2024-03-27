/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpblackjack;

/**
 *
 * @author TotoZone
 */
public class Card {
    private int value;
    private EnumCardType cardType;
    private EnumSpecialCard specialCard;

    public EnumSpecialCard getSpecialCard() {
        return specialCard;
    }

    public void setSpecialCard(EnumSpecialCard specialCard) {
        this.specialCard = specialCard;
    }
    
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public EnumCardType getCardType() {
        return cardType;
    }

    public void setCardType(EnumCardType cardType) {
        this.cardType = cardType;
    }
    
}
