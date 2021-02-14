package GameModel;

public class Question {
   private String description;
   private String a;
   private String b;
   private String c;
   private char correct;

   Question(String d, String a, String b, String c, char correct) {
      description = d;
      this.a = a;
      this.b = b;
      this.c = c;
      this.correct = correct;
   }

   public boolean checkAnswer(char c) {
      return correct == c;
   }

   @Override
   public String toString() {
      return description + " Your options are: \n" +
              "a, " + a + "\n" + "b, " + b + "\n" +
              "c, " + c + "\n";
   }
}
