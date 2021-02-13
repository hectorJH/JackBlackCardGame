package Player;

import Deck52.Card;

import java.util.ArrayList;

public class Player
{
    private String name;
    public ArrayList<Card> hand;
    public Card wild;
    public double wallet;

    public Player(String playerName)
    {
        this.name = playerName;
        wallet = 500.00;
    }
    public void addCard(Card card) {hand.add(card);}
    public void getWildCard(Card wildCard){wild = wildCard;}

    public double handleWinnings(double winnings)
    {
        wallet += winnings;
        return wallet;
    }
    public void placeBet(double amount) throws Exception
    {
        wallet = wallet - amount;
    }
    public String getHand()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(name + "'s Hand: ");
        sb.append(hand.toString());

        return sb.toString();
    }
    public String getName(){return this.name;}

}
