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
    private int target;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public Game(String p1Name, String p2Name, double buyInMoney,
                boolean aceIsValue) {
        target = 21;
        moneyPot = 0;
        buyInAmount = buyInMoney;
//        aceHigh = aceIsValue;
        p1 = new Player(p1Name);
        p2 = new Player(p2Name);
        cardDeck = new Deck();
        cardDeck.populateDeck();

        p1.addBombCard(cardDeck.drawCard());
        p2.addBombCard(cardDeck.drawCard());

        for (int i = 0; i < 2; i++) {
            p1.addCard(cardDeck.drawCard());
            p2.addCard(cardDeck.drawCard());
        }
    }

    public void setAce(boolean aceIsHigh) {
        aceHigh = aceIsHigh;
    }


    public void playerHit(boolean isPlayerOne) {
        if(isPlayerOne) {
            p1.addCard(cardDeck.drawCard());
            showPlayerCards(isPlayerOne);
        } else {
            p2.addCard(cardDeck.drawCard());
            showPlayerCards(isPlayerOne);
        }
    }

    public void wildcardDecision(boolean isPlayerOne, boolean isBomb){
        //need to be able to access player's wildcard and give it to another
        //player

        //need to be able to access a player's count, a player's wildcard,
        // and a player's bomb
        if(isPlayerOne){
            if(isBomb)
                p2.addBombCard(p1.getBombCard());
            else
                p1.applyProtection();
        } else {
            if(isBomb)
                p1.addBombCard(p2.getBombCard());
            else
                p2.applyProtection();
        }
    }

    public String compareHand(){
        double p1RoundResult = Math.abs(target - p1.getPlayerCount(aceHigh));
        double p2RoundResult = Math.abs(target - p2.getPlayerCount(aceHigh));

        if(!p1.getHasBeenBombed() && !p2.getHasBeenBombed()) {
            if (p1RoundResult < p2RoundResult)
                return playerLostRound(false);
            else
                return playerLostRound(true);
        } else {
            if(p1.getPlayerCount(aceHigh) > p2.getPlayerCount(aceHigh))
                return playerLostRound(false);
            else
                return playerLostRound(true);
        }
    }

    public String showPlayerCards(boolean isPlayerOne) {
        if (isPlayerOne)
            return p1.getHand();
        else
            return p2.getHand();
    }

    /**
     * Used at the beginning of the round
     *
     * @param isPlayerOne
     * @return
     */
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

    String playerLostRound(boolean isPlayerOne) {
        if (isPlayerOne) {
            p2.handleWinnings(moneyPot);
            return "\n" + p2.getName() + " has won the round!";
        } else {
            p1.handleWinnings(moneyPot);
            return "\n" + p1.getName() + " has won the round!";
        }
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



