package GameModel;

import Player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    //private fields~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    static int moneyPot;
    private double buyInAmount;
    HashMap<String, Player> players;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public Game() {
        moneyPot = 0;
        buyInAmount = 0;
        players = new HashMap<String, Player>();
    }

    public void addPlayers(String name){
        Player addedPlayer = new Player(name);

        players.put(name, addedPlayer);
    }

    public String showPlayerCards(String playerName){
        return players.get(playerName).getHand();
    }

    public void takeBets(String playerName){
        boolean exceptionThrown = false;
        try{
            players.get(playerName).placeBet(buyInAmount);
        } catch (Exception e){
            exceptionThrown = true;
            e.getMessage();
        }

    }


}
