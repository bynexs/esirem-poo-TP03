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
        boolean sortie = false;
        
        BlackjackManager.InitialisationGame(cards, player, dealer);

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Entre ton nom");
        player.setName(myObj.nextLine());

        dealer.setName("Robot");
        player.setBalance(1000);
        
        System.out.println("Combien veux-tu parier ?");
        int balance = myObj.nextInt();
        if (balance > 0 && balance <= player.getBalance()) {
            player.setBalance(player.getBalance() - balance);
        }
        while (sortie != true || player.getHand().getValue() <= 21) {
            BlackjackManager.DisplayPlayerHandAndDealearCard(player, dealer);
            System.out.println("Veux-tu repiocer ?");
            String value = myObj.nextLine();
            if(value.equals("oui") || value.equals("o") || value.equals("yes") || value.equals("y")){
                player = BlackjackManager.PickCard(cards, player);
            }else{
                sortie = true;
                break;
            }
        }
        
        while(dealer.getHand().getValue() <= 16){
            System.out.println("La banque repioche !");
            dealer = BlackjackManager.PickCard(cards, dealer);
        }
        
        BlackjackManager.ShowAllCard(player, dealer);
    }   
}
