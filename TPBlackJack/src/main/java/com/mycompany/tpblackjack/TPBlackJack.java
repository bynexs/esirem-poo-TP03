/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tpblackjack;

import java.util.List;

/**
 *
 * @author TotoZone
 */
public class TPBlackJack {

    public static void main(String[] args) {
        List<Card> cards = BlackjackManager.suffle(BlackjackManager.CreationDeck());
    }
}
