package GameModel;

import Deck52.Card;
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
    private JackBlack jb;
    private boolean winnerFound;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public Game(String p1Name, String p2Name, double buyInMoney,
                boolean aceIsValue) {
        target = 21;
        moneyPot = 0;
        jb = new JackBlack();
        buyInAmount = buyInMoney;
        aceHigh = aceIsValue;
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

    public void playerHit(boolean isPlayerOne) {
        if(isPlayerOne) {
            p1.addCard(cardDeck.drawCard());
            showPlayerCards(isPlayerOne);
        } else {
            p2.addCard(cardDeck.drawCard());
            showPlayerCards(isPlayerOne);
        }
    }

    public Card wildcardDecision(boolean isPlayerOne, boolean isBomb){
        //need to be able to access player's wildcard and give it to another
        //player

        //need to be able to access a player's count, a player's wildcard,
        // and a player's bomb
        if(isPlayerOne)
        {
            if(isBomb) {
                p2.bombed(p1.getBombCard());
                return p2.effectCard;
            }
            else
            {
                p1.applyProtection();
                return p1.effectCard;
            }
        }
        else
            {
            if(isBomb)
            {
                p1.bombed(p2.getBombCard());
                return p1.effectCard;
            }
            else
            {
                p2.applyProtection();
                return p2.effectCard;
            }
        }
    }

    public double getBuyInAmount(){
        return buyInAmount;
    }

    public String askJBQuestion(){
       return jb.getQuestion();
    }

    public int getEffectCardFromPlayer(boolean isPlayerOne){
        if(isPlayerOne)
            return p1.getEffectCardRank();
        else
            return p2.getEffectCardRank();
    }

    public String jackBlackQuestion(){
        return jb.getQuestion();
    }

    public boolean jackBlackCheckAnswer(char ch){
        return jb.checkAnswerChoice(ch);
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
            if(p1.getCardTotal() > p2.getCardTotal())
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
     * False = winner not found, true = winner foound
     * @param isPlayerOne
     * @return
     */
    public boolean takeBets(boolean isPlayerOne) {
        if (isPlayerOne) {
            try {
                p1.placeBet(buyInAmount);
                moneyPot += buyInAmount;
            } catch (Exception e) {
                e.getMessage();
                winnerFound = true;
                System.out.println(playerLostGame(isPlayerOne));
            }
        } else {
            try {
                p2.placeBet(buyInAmount);
                moneyPot += buyInAmount;
            } catch (Exception e) {
                e.getMessage();
                winnerFound = true;
                System.out.println(playerLostGame(isPlayerOne));
            }
        }
        return winnerFound;
    }

    public String playerLostRound(boolean isPlayerOne) {
        if (isPlayerOne) {
            p2.handleWinnings(moneyPot);
            return "\n" + p2.getName() + " has won the round!";
        } else {
            p1.handleWinnings(moneyPot);
            return "\n" + p1.getName() + " has won the round!";
        }
    }

    public String playerLostGame(boolean isPlayerOne){
        if (!isPlayerOne)
            return "\n" + p2.getName() + " has destroyed "
                    + p1.getName() + ". Well done " + p2.getName() + "!";
        else
            return "\n" + p1.getName() + " has absolutely wrecked "
                    + p2.getName() + ". Congrats " + p1.getName() + "!";
    }

    public Player getP1() {return p1;}
    public Player getP2(){return p2;}
}



