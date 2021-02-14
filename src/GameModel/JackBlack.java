package GameModel;
import java.util.ArrayList;

public class JackBlack {
   final private static int MAX_INDEX = 9;
   private ArrayList<Question> questions;
   final private static Question Q1 = new Question("What color is Jack " +
           "Black's hair?", "Brown","Blue", "Orange", 'a');
   final private static Question Q2 = new Question("Which movie does Jack" +
           "Black play an instrument? ", "School of Rock","Orange County",
           "Jumanji", 'a');
   final private static Question Q3 = new Question("Which movie is Jack" +
           "Black a geographer? ", "School of Rock","Orange County",
           "Jumanji", 'a');
   final private static Question Q4 = new Question("What is Jack Black's " +
           "real name? ", "Jackson","Jacob",
           "Jack", 'b');
   final private static Question Q5 = new Question("What is the name of  " +
           "Jack Black's band?", "Candy Corn","Crazy Cat Daddies",
           "Tenacious D", 'c');
   final private static Question Q6 = new Question("What movie is Jack Black"
           + "most well known for?", "Orange County",
           "School of Rock", "Jumanji", 'c');
   final private static Question Q7 = new Question("What is Jack Black's " +
           "favorite candy?", "Unknown",
           "Milk Duds", "Smarties", 'c');
   final private static Question Q8 = new Question("What is Jack Black's " +
           "favorite candy?", "Unknown",
           "Milk Duds", "Smarties", 'c');
   final private static Question Q9 = new Question("What is Jack Black's " +
           "worst movie?", "Shallow Hal",
           "School of Rock", "Orange County", 'a');
   final private static Question Q10 = new Question("What city was Jack " +
           "Black born in?", "Santa Monica",
           "Los Angeles", "Boston", 'a');


   private int currentQuestion;

   public JackBlack() {
      currentQuestion = 0;
      questions = new ArrayList<Question>();
      questions.add(Q1);
      questions.add(Q2);
      questions.add(Q3);
      questions.add(Q4);
      questions.add(Q5);
      questions.add(Q6);
      questions.add(Q7);
      questions.add(Q8);
      questions.add(Q9);
      questions.add(Q10);
   }

   public String getQuestion() {
      if (currentQuestion == MAX_INDEX) {
         currentQuestion = 0;
      }

      return questions.get(currentQuestion++).toString();
   }

   public boolean checkAnswerChoice(char choice) {
      return questions.get(currentQuestion).checkAnswer(choice);
   }
}
