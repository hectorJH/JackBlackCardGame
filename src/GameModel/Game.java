package GameModel;

import Player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
    //private fields~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    static int moneyPot;
    private double buyInAmount;
    private boolean aceHigh;
    //HashMap<String, Player> players;

    private Player p1;
    private Player p2;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public Game(String p1Name, String p2Name) {
        moneyPot = 0;
        buyInAmount = 0;
        p1 = new Player(p1Name);
        p2 = new Player(p2Name);
    }

    public void setAceValue(boolean aceValue){
        aceHigh = aceValue;
    }

    public String showPlayerCards(String playerName){
        if(p1.getName().equals(playerName))
            return p1.getHand();
        else
            return p2.getHand();
    }

    public String takeBets(String playerName){
        try{
            p1.placeBet(buyInAmount);
        } catch (Exception e){
            e.getMessage();
            return playerLost(playerName);
        }
        return "\nBets have been placed";
    }

    /**
     *
     * @param playerName player that LOST
     * @return
     */
    String playerLost(String playerName){
        if(p1.getName().equals(playerName))
            return "\n" + p2.getName() + " has destroyed "
                    + p1.getName() + ". Well done " + p2.getName() + "!";
        else
            return "\n" + p1.getName() + " has absolutely wrecked "
                    + p2.getName() + ". Congrats " + p1.getName() + "!";
    }


}
