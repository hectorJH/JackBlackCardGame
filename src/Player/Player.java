package Player;

import Deck52.Card;
import Exceptions.*;

import java.util.ArrayList;

public class Player
{
    private String name;
    public ArrayList<Card> hand;
    public Card bomb;
    public Card effectCard;
    public double wallet;
    private int cardTotal = 0;
    private boolean hasBeenBombed = false;

    public Player(String playerName)
    {
        this.name = playerName;
        hand = new ArrayList<>();
        wallet = 500.00;
    }
    public void addCard(Card card) {hand.add(card);}
    public void addBombCard(Card bombCard){bomb = bombCard;}

    public double handleWinnings(double winnings)
    {
        wallet += winnings;
        return wallet;
    }
    public void placeBet(double amount) throws InsufficientFunds
    {
        wallet = wallet - amount;

        if(wallet < 0)
        {
            throw new InsufficientFunds("Not enough money to place bet");
        }

    }
    public int getPlayerCount(boolean aceHigh)
    {
        int cardValue;
        int accumulator = 0;
        for(int i = 0; i < hand.size(); i++)
        {
            cardValue = hand.get(i).getRank();
            if(cardValue == 1 && aceHigh)
                accumulator += 11;
            else if(cardValue == 1 && !aceHigh)
                accumulator += 1;
            else
                accumulator += cardValue;
        }

        cardTotal = accumulator;

        return accumulator;
    }
    public void applyProtection()
    {
        effectCard = bomb;
        cardTotal = cardTotal - this.bomb.getRank();
    }
    public void bombed(Card bomb)
    {
        effectCard = bomb;
        cardTotal = cardTotal + bomb.getRank();
        hasBeenBombed = true;
    }

    public int getEffectCardRank(){
        return effectCard.getRank();
    }

    public int getCardTotal(){return cardTotal;}

    public boolean getHasBeenBombed(){return hasBeenBombed;}

    public Card getBombCard(){return bomb;}

    public String getHand()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(name + "'s Hand: ");
        sb.append(hand.toString());

        return sb.toString();
    }

    public String getName(){return this.name;}

    public void roundReset()
    {
        hasBeenBombed = false;
        cardTotal = 0;
        hand.clear();
    }

}