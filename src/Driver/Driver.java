package Driver;
import Deck52.*;
import GameModel.*;
import Player.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
   public static void main (String args[]) {
      System.out.println("Hello World!");

      Deck deck;

      //game model handles game logic
      Game game;

      //player counter to determine whose turn it is
      int playerCt = 0;

      final int numPlayers = 2;

      ArrayList<Player> playerQ; //holds player objects
      Player currPlayer;         //placeholder current player
      String playerHand;         //placeholder for player's hand

      boolean endGame;           //bool check game has ended
      String rematch;            //string check if they want to play again

      helloPlayers();            //greet players explain rules

      Scanner input = new Scanner(System.in);

      do {


         //create new instance of deck
         deck = new Deck();
         endGame = false;

         //setup game with a player 1 and 2
         game = addPlayers(input);

         //set aces high or low
         setAce(input, game);

         //print out initial hands
         System.out.println(game.showPlayerCards(true));
         System.out.println(game.showPlayerCards(false));





         rematch = "Y";

      }while (rematch.equalsIgnoreCase("Y"));

   }

   public static Game addPlayers(Scanner input)
   {
      String player1;
      String player2;
      System.out.println("Enter player 1's name");
      player1 = input.nextLine();


      System.out.println("Enter player 2's name");
      player2 = input.nextLine();

      return new Game(player1, player2);
   }
   public static void setAce(Scanner input, Game game)
   {
      String response;
      System.out.println("Are aces high or low?: H for High, L for low");
      response = input.nextLine();

      if(response.equalsIgnoreCase("H"))
         game.setAceValue(true);
      else if (response.equalsIgnoreCase("L"))
         game.setAceValue(false);
      else
         System.out.println("something else happneded ");

   }

   public static void helloPlayers()
   {
      System.out.println("\nWelcome to Jack Black\n");
      System.out.println("Explain Rules here");
   }
}