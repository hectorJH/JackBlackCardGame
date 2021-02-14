package Deck52;
import Deck52.Card;
import Enums.Suits;
import java.util.ArrayList;

import java.util.Random;

public class Deck {
   private ArrayList<Card> cards;

   public Deck() {
      cards = new ArrayList<Card>();
      Suits s;

      for (int suit = 1; suit <= 4; suit++) {
         for (int rank = 1; rank <= 13; rank++) {
            if (suit == 1)
               s = Suits.DIAMOND;
            if (suit == 2)
               s = Suits.HEART;
            if (suit == 3)
               s = Suits.CLUB;
            else s = Suits.SPADE;
            cards.add(new Card(s, rank));
         }
      }
   }

   public Card drawCard() {
      int randomIndex = generateRandomIndex();
      Card c = cards.get(randomIndex);
      cards.remove(randomIndex);
      return c;
   }

   private int generateRandomIndex() {
      Random rand = new Random();
      return rand.nextInt(51);
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < cards.size(); i++) {
         sb.append(cards.get(i).toString() + "\n");
      }

      return sb.toString();
   }

}
