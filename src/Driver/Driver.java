package Driver;
import Deck52.*;
import GameModel.*;
import Player.*;
import java.util.ArrayList;

public class Driver {
   public static void main (String args[]) {
      System.out.println("Hello World!");

      Deck deck;

      //game model handles game logic
      Game game = new Game();

      //player counter to determine whose turn it is
      int playerCt = 0;

      final int numPlayers = 2;

      ArrayList<Player> playerQ; //holds player objects
      Player currPlayer;         //placeholder current player
      String playerHand;         //placeholder for player's hand

      boolean endGame;           //bool check game has ended
      String rematch;            //string check if they want to play again

      helloPlayers();            //greet players explain rules

      do {

         //create new instance of deck
         deck = new Deck();
         endGame = false;

         //getPlayers();

         rematch = "Y";

      }while (rematch.equalsIgnoreCase("Y"));

   }

   public static void helloPlayers()
   {
      System.out.println("\nWelcome to Jack Black\n");
      System.out.println("Explain Rules here");
   }
}