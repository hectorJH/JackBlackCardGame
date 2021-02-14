package Deck52;
import Enums.*;

public class Card {
   Suits suit;
   int rank;

   Card(Suits suit, int rank) {
      this.suit = suit;
      this.rank = rank;
   }

   public Suits getSuit(){return suit;}
   public int getRank(){return rank;}
   @Override
   public String toString() {
      return this.rank + " " + this.suit;
   }
}
