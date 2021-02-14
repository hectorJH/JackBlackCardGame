package GameModel;

import Player.Player;

import java.util.HashMap;

public class Game {
    //private fields~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    static int moneyPot;
    private int buyInAmount;
    HashMap<String, Player> players;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public Game() {
        moneyPot = 0;
        buyInAmount = 0;
        players = new HashMap<String, Player>();

        System.out.println("Hector is trying to say something...AGAIN");
    }





}
