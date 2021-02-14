package Driver;
import Deck52.*;
import GameModel.*;
import Player.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
   public static void main (String args[]) {
      Deck deck;

      //game model handles game logic
      Game game;

      //player counter to determine whose turn it is
      int playerCt = 0;
      int bombCount = 0;
      boolean jbChanceToAnswer = true;
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

         //main game loop
         do {

            game.startRound();

            //print out initial hands
            System.out.println(game.showPlayerCards(true));
            System.out.println(game.showPlayerCards(false));

            boolean p1_lost;
            boolean p2_lost;

            //take bets from both players
            p1_lost = game.takeBets(true);
            p2_lost = game.takeBets(false);

            if(p1_lost | p2_lost)
            {
               endGame = true;
            }
            else {
               //handle player hitting
               playerHits(input, game);

               //print out current hands
               System.out.println(game.showPlayerCards(true));
               System.out.println(game.showPlayerCards(false));

               //how do users want to use their bomb cards
               handleBombs(input,game);

               boolean playerOneJBAnswer =
                       JackBlackCheck(input, game, true);
               boolean playerTwoJBAnswer =
                       JackBlackCheck(input, game, true);

               if((playerOneJBAnswer && playerTwoJBAnswer) ||
                       (!playerOneJBAnswer && !playerTwoJBAnswer) )
               {
                  //compares the hands move winnings to winner
                  String roundWinner = game.compareHand();
                  System.out.println(roundWinner);
               }
               else if(playerOneJBAnswer)
               {
                  game.playerLostRound(false);
               }
               else
                     game.playerLostRound(true);

               game.getP1().moveSpecialToHand();
               game.getP2().moveSpecialToHand();
               System.out.println(game.showPlayerCards(true));
               System.out.println(game.showPlayerCards(false));

               System.out.println("\nPlayer One's Wallet: " + game.getP1().getWallet());
               System.out.println("Player Two's Wallet: " + game.getP2().getWallet());

               game.getP1().roundReset();
               game.getP2().roundReset();
               //check if a player has lost all their money.
               //restart the round
            }

         }while(!endGame);


         System.out.print("Would you like to play again? ");
         rematch = input.nextLine();

      }while (rematch.equalsIgnoreCase("Y"));

   }

   public static Game addPlayers(Scanner input)
   {
      String player1;
      String player2;
      System.out.print("Enter player 1's name: ");
      player1 = input.nextLine();

      System.out.print("Enter player 2's name: ");
      player2 = input.nextLine();

      System.out.print("Enter buy in amount: ");
      double buyin = input.nextDouble();
      input.nextLine();

      String response;
      System.out.print("Are aces high or low? (H for High, L for low): ");
      response = input.nextLine();

      if(response.equalsIgnoreCase("H"))
         return new Game(player1, player2, buyin, true);
         //else if (response.equalsIgnoreCase("L"))
      else
         return new Game(player1, player2, buyin,false );
   }

   public static void playerHits(Scanner input, Game game)
   {
      //handles players hitting
      boolean hit = true;
      for(int i = 0; i < 2; i++)
      {
         do{
            System.out.print("Player " + (i + 1) + " do you want to hit? " +
                    "(y/n) ");
            String result = input.nextLine();

            if(result.equalsIgnoreCase("y"))
            {
               hit = true;
               if(i == 0)
               {
                  game.playerHit(true);
                  System.out.println(game.showPlayerCards(true));
               }
               else
               {
                  game.playerHit(false);
                  System.out.println(game.showPlayerCards(false));
               }

            }
            else if(result.equalsIgnoreCase("n"))
               hit = false;
            else
               System.out.println("That was an invalid command.");

         }while(hit);
      }
   }
   public static void handleBombs(Scanner input, Game game)
   {
      String bombOrProtect;
      for(int i = 0; i < 2; i++)
      {
         System.out.print("Player " + i + " bomb or protect (B/P): ");
         bombOrProtect = input.nextLine();
         if(bombOrProtect.equalsIgnoreCase("B"))
         {
            if(i == 0)
            {
               Card special = game.wildcardDecision(true, true);
               System.out.println("Player One Bombed Player Two");
               game.getP2().addSpecial(special);
            }
            else
            {
               Card special = game.wildcardDecision(false, true);
               System.out.println("Player Two Bombed Player One");
               game.getP1().addSpecial(special);
            }
         }
         else if(bombOrProtect.equalsIgnoreCase("P"))
         {
            if(i == 0)
            {
               Card special = game.wildcardDecision(true, false);
               System.out.println("Player One protected themself");
               game.getP1().addSpecial(special);
            }
            else
            {
               Card special = game.wildcardDecision(false, false);
               System.out.println("Player Two protected themself");
               game.getP2().addSpecial(special);
            }
         }
         else
            System.out.println("invalid command");

         //TODO check if both players choose violence
      }
   }

   public static boolean JackBlackCheck(Scanner kb, Game game,
                                     boolean isPlayerOne){
      String result;
      boolean answerCorrect = false;

      if(11 == game.getEffectCardFromPlayer(isPlayerOne)) {
         game.askJBQuestion();
         result = kb.nextLine().toLowerCase();
         answerCorrect = game.jackBlackCheckAnswer(result.charAt(0));
      }else if (11 == game.getEffectCardFromPlayer(!isPlayerOne)){
         game.askJBQuestion();
         result = kb.nextLine().toLowerCase();
         answerCorrect = game.jackBlackCheckAnswer(result.charAt(0));
      }
         return answerCorrect;
   }

   public static void helloPlayers()
   {
      System.out.println("\nWelcome to Jack Black\n");
      System.out.println("Explain Rules here");
   }
}
