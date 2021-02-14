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
      //Game game = new Game();

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
         playerQ = new ArrayList<Player>();

         //setup game to be aceHigh
         addPlayers(input, playerQ);

         //deal two face up cards and one face down
         initialDeal(playerQ, deck);

         //print out both players curent hands
         printPlayerCards(playerQ);




         rematch = "Y";

      }while (rematch.equalsIgnoreCase("Y"));

   }

   public static void addPlayers(Scanner input, ArrayList<Player> playerQ)
   {
      String name;
      System.out.println("Enter player 1's name");
      name = input.nextLine();

      Player person = new Player(name);

      playerQ.add(person);

      System.out.println("Enter player 2's name");
      name = input.nextLine();

      person = new Player(name);
      playerQ.add(person);
   }
   public static void initialDeal(ArrayList<Player> playerQ, Deck deck)
   {

      //for loop hands out initial two cards
      for(int i =0; i < playerQ.size(); i++)
      {
         playerQ.get(i).addCard(deck.drawCard());
         if(i == playerQ.size()-1 & playerQ.get(i).numberCards() != 2)
            i = 0;
      }

      for(int i = 0; i < playerQ.size(); i++)
         playerQ.get(i).addBombCard(deck.drawCard());
   }

   public static void printPlayerCards(ArrayList<Player> playerQ)
   {
      for(int i =0; i < playerQ.size(); i++)
      {
         System.out.println(playerQ.get(i).getHand());
      }
   }
   public static void helloPlayers()
   {
      System.out.println("\nWelcome to Jack Black\n");
      System.out.println("Explain Rules here");
   }
}