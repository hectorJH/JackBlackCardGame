package Deck52;
import Enums.Suits;
import java.util.ArrayList;

import java.util.Random;

public class Deck {
   private ArrayList<Card> cards;

   public Deck() {
      cards = new ArrayList<Card>();
      populateDeck();
      // ******** For testing Jacks **********
//      Card c1 = new Card(Suits.DIAMOND, 11);
//      Card c2 = new Card(Suits.HEART, 11);
//      Card c3 = new Card(Suits.CLUB, 11);
//      Card c4 = new Card(Suits.SPADE, 11);
//      Card c5 = new Card(Suits.DIAMOND, 11);
//      Card c6 = new Card(Suits.HEART, 11);
//      Card c7 = new Card(Suits.CLUB, 11);
//      Card c8 = new Card(Suits.SPADE, 11);
//      Card c9 = new Card(Suits.DIAMOND, 11);
//      Card c10 = new Card(Suits.HEART, 11);
//      Card c11 = new Card(Suits.CLUB, 11);
//      Card c12 = new Card(Suits.SPADE, 11);
//      Card c13 = new Card(Suits.DIAMOND, 11);
//      Card c14 = new Card(Suits.HEART, 11);
//      Card c15 = new Card(Suits.CLUB, 11);
//      Card c16 = new Card(Suits.SPADE, 11);
//      Card c17 = new Card(Suits.DIAMOND, 11);
//      Card c18 = new Card(Suits.HEART, 11);
//      Card c19 = new Card(Suits.CLUB, 11);
//      Card c20= new Card(Suits.SPADE, 11);
//      Card c21 = new Card(Suits.DIAMOND, 11);
//      Card c22 = new Card(Suits.HEART, 11);
//      Card c23 = new Card(Suits.CLUB, 11);
//      Card c24 = new Card(Suits.SPADE, 11);
//      cards.add(c1);
//      cards.add(c2);
//      cards.add(c3);
//      cards.add(c4);
//      cards.add(c5);
//      cards.add(c6);
//      cards.add(c7);
//      cards.add(c8);
//      cards.add(c9);
//      cards.add(c10);
//      cards.add(c11);
//      cards.add(c12);
//      cards.add(c13);
//      cards.add(c14);
//      cards.add(c15);
//      cards.add(c16);
//      cards.add(c17);
//      cards.add(c18);
//      cards.add(c19);
//      cards.add(c20);
//      cards.add(c21);
//      cards.add(c22);
//      cards.add(c23);
//      cards.add(c24);
   }

   public void populateDeck() {
      Suits s;

      if (cards.size() != 0)
         cards.clear();

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
      if (cards.size() <= 10) {
         populateDeck();
      }

      int randomIndex = generateRandomIndex();
      Card c = cards.get(randomIndex);
      cards.remove(randomIndex);
      return c;
   }

   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < cards.size(); i++) {
         sb.append(cards.get(i).toString() + "\n");
      }

      return sb.toString();
   }

   private int generateRandomIndex() {
      Random rand = new Random();
      return rand.nextInt(cards.size());
   }

}
