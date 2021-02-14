package GameModel;

import Deck52.Deck;
import Player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
    //private fields~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    static double moneyPot;
    private double buyInAmount;
    private boolean aceHigh;
    private Deck cardDeck;
    private Player p1;
    private Player p2;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public Game(String p1Name, String p2Name, double buyInMoney,
                boolean aceIsValue) {
        moneyPot = 0;
        buyInAmount = buyInMoney;
        aceHigh = aceIsValue;
        p1 = new Player(p1Name);
        p2 = new Player(p2Name);

        cardDeck = new Deck();
        cardDeck.populateDeck();

        for (int i = 0; i < 2; i++) {
            p1.addCard(cardDeck.drawCard());
            p2.addCard(cardDeck.drawCard());
        }
    }

    public void playerHit(boolean isPlayerOne) {
        if (isPlayerOne) {
            p1.addCard(cardDeck.drawCard());

            showPlayerCards(isPlayerOne);
        } else {
            p2.addCard(cardDeck.drawCard());

            showPlayerCards(isPlayerOne);
        }
    }

    public String showPlayerCards(boolean isPlayerOne) {
        if (isPlayerOne)
            return p1.getHand();
        else
            return p2.getHand();
    }

    public String takeBets(boolean isPlayerOne) {
        if (isPlayerOne) {
            try {
                p1.placeBet(buyInAmount);
                moneyPot += buyInAmount;
            } catch (Exception e) {
                e.getMessage();
                return playerLostGame(isPlayerOne);
            }
        } else {
                try {
                    p2.placeBet(buyInAmount);
                    moneyPot += buyInAmount;
                } catch (Exception e) {
                    e.getMessage();
                    return playerLostGame(isPlayerOne);
                }
        }
        return "\nBets have been placed";
    }

    String playerLostRound (boolean isPlayerOne){
        if (isPlayerOne)
            return "\n" + p2.getName() + " has won the round!";
        else
            return "\n" + p1.getName() + " has won the round!";
    }

    String playerLostGame(boolean isPlayerOne){
        if (!isPlayerOne)
            return "\n" + p2.getName() + " has destroyed "
                        + p1.getName() + ". Well done " + p2.getName() + "!";
        else
            return "\n" + p1.getName() + " has absolutely wrecked "
                        + p2.getName() + ". Congrats " + p1.getName() + "!";
    }
}



