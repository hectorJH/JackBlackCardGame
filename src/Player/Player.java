package Player;

import Deck52.Card;
import Exceptions.*;

import java.util.ArrayList;

public class Player
{
    private String name;
    public ArrayList<Card> hand;
    public Card bomb;
    public double wallet;

    public Player(String playerName)
    {
        this.name = playerName;
        wallet = 500.00;
    }
    public void addCard(Card card) {hand.add(card);}
    public void addBombCard(Card wildCard){bomb = wildCard;}

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
    public Card useBombCard(){return bomb;}
    public String getHand()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(name + "'s Hand: ");
        sb.append(hand.toString());

        return sb.toString();
    }
    public String getName(){return this.name;}

}