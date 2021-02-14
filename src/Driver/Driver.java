package Driver;
import Deck52.*;

public class Driver {
   public static void main (String args[]) {
      System.out.println("Hello World!");

      Deck d = new Deck();
      System.out.println(d.toString());
      Card c = d.drawCard();
      System.out.println("Drew card : " + c.toString());
      System.out.println();
      System.out.println(d.toString());
   }
}