package GameModel;
import java.util.ArrayList;
import java.util.Random;

public class JackBlack {
   final private static int MAC_INDEX = 2;
   private ArrayList<Question> questions;
   final private static Question Q1 = new Question("What color is Jack " +
           "Black's hair?", "Black","Blue", "Orange", 'a');
   final private static Question Q2 = new Question("Which movie does Jack" +
           "Black play an instrument? ", "School of Rock","Orange County",
           "Jumanji", 'a');
   final private static Question Q3 = new Question("Which movie is Jack" +
           "Black a geographer? ", "School of Rock","Orange County",
           "Jumanji", 'a');

   private int currentQuestion;

   public JackBlack() {
      currentQuestion = 0;
      questions = new ArrayList<Question>();
      questions.add(Q1);
      questions.add(Q2);
      questions.add(Q3);
   }

   public String getQuestion() {
      if (currentQuestion == 2) {
         currentQuestion = 0;
      }

      return questions.get(currentQuestion++).toString();
   }

   public boolean checkAnswerChoice(char choice) {
      return questions.get(currentQuestion).checkAnswer(choice);
   }
}
