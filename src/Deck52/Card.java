package Deck52;
import Enums.*;

public class Card {
   Suits suit;
   int rank;

   Card(Suits suit, int rank) {
      this.suit = suit;
      this.rank = rank;
   }

   @Override
   public String toString() {
      return this.rank + " " + this.suit;
   }
}
