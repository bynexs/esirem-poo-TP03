/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tpblackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author conta
 */
public class BlackjackManager {

    public static List<Card> CreationDeck() {                //
        List<Card> cards = new ArrayList<>();               //creation de la liste 
        int id = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                Card card = new Card();                       
                card.setCardType(EnumCardType.values()[i]);
                if(j >= 10){
                    card.setValue(10);
                    card.setSpecialCard(EnumSpecialCard.values()[id]);
                    id ++;
                }else{
                    card.setValue(j);
                    card.setSpecialCard(EnumSpecialCard.CHIFFRE);
                }
                cards.add(card);                          //renvoie une card a l'objet cards
            }
            id = 1 ;
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
                cards.remove(card);
            } else {
                handDealer.setCards(card);
                cards.remove(card);
            }
        }
        player.setHand(handPlayer);
        dealer.setHand(handDealer);
        DisplayPlayerHandAndDealearCard(player, dealer);
    }

    public static Player PickCard(List<Card> cards, Player player) {
        Card temp = cards.get(0);
        Scanner myobj = new Scanner(System.in);
        if(cards.get(0).getValue() == 1){
            System.out.println("Un As tu préféré 1 ou 11");
            int valueAs = myobj.nextInt();
            if(valueAs == 11 || valueAs == 1){
                temp.setValue(valueAs);
            }
        }
        player.getHand().setCards(cards.get(0));
        cards.remove(temp);
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
