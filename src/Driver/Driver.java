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
      int bombCount = 0;

      final int numPlayers = 2;

      Player currPlayer;         //placeholder current player
      String playerHand;         //placeholder for player's hand

      boolean endGame;           //bool check game has ended
      String rematch;            //string check if they want to play again

      Scanner input = new Scanner(System.in);

      do {

         helloPlayers();            //greet players explain rules

         //create new instance of deck
         deck = new Deck();
         endGame = false;

         //setup game with a player 1 and 2, buy in, and ace high/low
         game = addPlayers(input);

         //print out initial hands
         System.out.println(game.showPlayerCards(true));
         System.out.println(game.showPlayerCards(false));

         //main game loop
         do {
            //take bets from both players
            game.takeBets(true);
            game.takeBets(false);


            //handles players hitting
            boolean hit = true;
            for(int i = 0; i < 2; i++)
            {
               do{
                  System.out.println("Player " + i + " do you want to hit? (y/n)");
                  String result = input.nextLine();

                  if(result.equalsIgnoreCase("y"))
                  {
                     if(i == 0)
                        game.playerHit(true);
                     else
                        game.playerHit(false);
                  }
                  else if(result.equalsIgnoreCase("n"))
                     hit = false;
                  else
                     System.out.println("That was an invalid command.");

               }while(hit);
            }

            //print out current hands
            System.out.println(game.showPlayerCards(true));
            System.out.println(game.showPlayerCards(false));

            for(int i = 0; i < 2; i++)
            {
               System.out.println("");
            }
            //how to use the bomb.


            endGame = true;

         }while(!endGame);


         System.out.println("Would you like to play again?");
         rematch = input.nextLine();

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

      System.out.println("Enter buy in amount");
      double buyin = input.nextDouble();
      input.nextLine();

      String response;
      System.out.println("Are aces high or low?: H for High, L for low");
      response = input.nextLine();

      if(response.equalsIgnoreCase("H"))
         return new Game(player1, player2, buyin, true);
         //else if (response.equalsIgnoreCase("L"))
      else
         return new Game(player1, player2, buyin,false );
   }

   public static void helloPlayers()
   {
      System.out.println("\nWelcome to Jack Black\n");
      System.out.println("Explain Rules here");
   }
   public static void wildCardDecision(int player, Scanner kb, Game game) {
      String input;
      System.out.println("Player 1, Do you want to keep your wild card? " +
              "Y if yes," + "\nor N if you wish to give it to your enememy.");
      input = kb.nextLine();

//   if (player == 1) {
//      if (input.toLowerCase().charAt(0) == 'y')
//         game.wildCardDecision(true, true);
//      else
//         game.wildCardDecision(true, false);
//   } else {
//      if (input.toLowerCase().charAt(0) == 'y')
//         game.wildCardDecision(false, true);
//      else
//         game.wildCardDecision(false, false);
   }
}
