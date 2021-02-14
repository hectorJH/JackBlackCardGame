package Player;

import Deck52.Card;
import Enums.Suits;
import Exceptions.*;

import java.util.ArrayList;

public class Player
{
    private String name;
    public ArrayList<Card> hand;
    public ArrayList<Card> specialCards;
    public Card bomb;
    public Card effectCard;
    public double wallet;
    private int cardTotal = 0;
    private boolean hasBeenBombed = false;

    public Player(String playerName)
    {
        this.name = playerName;
        hand = new ArrayList<>();
        specialCards = new ArrayList<>();
        wallet = 500.00;
    }
    public void addCard(Card card) {hand.add(card);}
    public void addSpecial(Card card) {specialCards.add(card);}
    public void moveSpecialToHand()
    {
        hand.addAll(specialCards);
    }

    public void addBombCard(Card bombCard){bomb = bombCard;}

    public double getWallet(){return wallet;}
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
                if(cardValue >= 10)
                    accumulator += 10;
                else
                    accumulator += cardValue;
        }

        cardTotal += accumulator;

        return accumulator;
    }
    public void applyProtection(boolean aceHigh)
    {
        effectCard = bomb;
        int rankValue = this.bomb.getRank();

        if(rankValue >= 10)
            rankValue = 10;
        else if(rankValue == 1 && aceHigh)
            rankValue = 11;
        else if (rankValue == 1 & !aceHigh)
            rankValue = 1;
        else
            rankValue += 0;

        cardTotal = cardTotal - rankValue;
    }
    public void bombed(Card bomb, boolean aceHigh)
    {
        effectCard = bomb;
        int rankValue = bomb.getRank();
        if(rankValue >= 10)
            rankValue = 10;
        else if(rankValue == 1 && aceHigh)
            rankValue = 11;
        else if (rankValue == 1 & !aceHigh)
            rankValue = 1;
        else
            rankValue += 0;

        cardTotal = cardTotal + rankValue;
        hasBeenBombed = true;
    }

    public int getEffectCardRank()
    {
        if(effectCard != null)
            return effectCard.getRank();
        else
            return -1;
    }

    public int getCardTotal(){return cardTotal;}

    public boolean getHasBeenBombed(){return hasBeenBombed;}

    public Card getBombCard(){return bomb;}

    public String getHand()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(name + "'s Hand: ");
        int cardRank;
        Suits suit;

        for(int i =0; i < hand.size(); i++)
        {
            cardRank = hand.get(i).getRank();
            suit = hand.get(i).getSuit();
            sb.append("|" + suit);

            if(cardRank == 11)
                sb.append(" JACK|");
            else if(cardRank == 12)
                sb.append(" QUEEN|");
            else if(cardRank == 13)
                sb.append(" KING|");
            else if(cardRank == 1)
                sb.append(" ACE|");
            else
                sb.append(" " + cardRank + "|");
        }



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