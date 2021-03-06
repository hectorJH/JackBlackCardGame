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

            System.out.println("\n--Start of Round--\n");
            //print out initial hands
            System.out.println(game.showPlayerCards(true));
            System.out.println(game.showPlayerCards(false));

            boolean p1_lost;
            boolean p2_lost;

            //take bets from both players
            p1_lost = game.takeBets(true);
            p2_lost = game.takeBets(false);

            if(p1_lost) {
               System.out.println("Player One does not have enough funds to play.");
               endGame = true;
            }
            else if(p2_lost) {
               System.out.println("Player Two does not have enough funds to play");
               endGame = true;
            }
            else
            {
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
               else if(playerOneJBAnswer) {
                  System.out.println("Player 1 knows all the facts and has \n" +
                          "won the round!");
                  game.playerLostRound(false);
               }
               else {
                  System.out.println("Player 2 knows all the facts and has \n" +
                          "won the round!");
                  game.playerLostRound(true);
               }

               //handles presenting end game hands to players

               game.getP1().moveSpecialToHand();
               game.getP2().moveSpecialToHand();
               System.out.println(game.showPlayerCards(true));
               System.out.println(game.showPlayerCards(false));

               System.out.println("\nPlayer One's Wallet: " + game.getP1().getWallet());
               System.out.println("Player Two's Wallet: " + game.getP2().getWallet() +"\n");

               game.getP1().roundReset();
               game.getP2().roundReset();

               //check if a player has lost all their money.
               //restart the round
            }

         }while(!endGame);


         System.out.print("\nWould you like to play again? (Y/N)");
         rematch = input.nextLine();

      }while (rematch.equalsIgnoreCase("Y"));

      happyFace();

   }

   public static Game addPlayers(Scanner input)
   {
      String player1;
      String player2;
      System.out.print("\nEnter player 1's name: ");
      player1 = input.nextLine();

      System.out.print("\nEnter player 2's name: ");
      player2 = input.nextLine();

      System.out.print("Enter buy in amount: ");
      double buyin = input.nextDouble();
      input.nextLine();

      String response;
      System.out.print("\nAre aces high or low? (H for High, L for low): ");
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
            System.out.print("\nPlayer " + (i + 1) + " do you want to hit? " +
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
         System.out.print("\nPlayer " + i + " bomb or protect (B/P): ");
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
         System.out.println(game.askJBQuestion());
         result = kb.nextLine().toLowerCase();
         answerCorrect = game.jackBlackCheckAnswer(result.charAt(0));
      }else if (11 == game.getEffectCardFromPlayer(!isPlayerOne)){
         System.out.println(game.askJBQuestion());
         result = kb.nextLine().toLowerCase();
         answerCorrect = game.jackBlackCheckAnswer(result.charAt(0));
      }
         return answerCorrect;
   }

   public static void helloPlayers()
   {
      System.out.println("\n***************** WELCOME TO JACK BLACK! *****************\n");
      System.out.println("Jack Black plays like the famous game 21 or Black\n" +
                        "Jack. Where the goal is to get closest to 21 without\n" +
                        "going over. However, there's a twist! Each player is\n" +
                        "given an additional" + "card, but neither player can \n" +
                        "see that card. From there the game plays out as normal\n" +
                        "each player can hit cards until trying to reach 21.\n" +
                        "After both players have added to their hands they can\n" +
                        "activate their hidden special card The players can do\n" +
                        "one of two things with their hidden card. They can\n" +
                        "bomb the other player which will add onto that player's\n" +
                        "score or they can protect themself decreasing their own\n" +
                        "score However, if both players choose to bomb each other\n" +
                        "then the game no longer becomes about reaching 21, but\n" +
                        "whoever has the highest card total!\n");

      System.out.println("And the final twist in the spirit of calling the game\n" +
                         "Jack Black When the special card is used and is a Jack\n" +
                         "the player's must answer trivia questions about Jack\n" +
                         "Black Movies to win the hand.\n");
   }

   public static void happyFace() {
      System.out.println("\nThank you for playing!\n\n");
      System.out.println("         ***          ***\n" +
              "        *   *        *   *\n" +
              "        * * *        * * *\n" +
              "        *   *        *   *\n" +
              "         ***          ***\n\n" +
              "              *     *\n" +
              "               *   *\n" +
              "                * *\n" +
              "                 *\n\n\n" +
              "       *****          *****\n" +
              "         *              *\n" +
              "          *            *\n" +
              "           * ******** *\n");

   }
}
