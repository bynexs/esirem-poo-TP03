/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.tpblackjack;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author TotoZone
 */
public class TPBlackJack {

    public static void main(String[] args) {
        List<Card> cards = BlackjackManager.suffle(BlackjackManager.CreationDeck());
        Player player = new Player();
        Player dealer = new Player();

        BlackjackManager.InitialisationGame(cards, player, dealer);

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Entre ton nom");
        player.setName(myObj.nextLine());

        dealer.setName("Robot");
        player.setBalance(1000);
    }
}
