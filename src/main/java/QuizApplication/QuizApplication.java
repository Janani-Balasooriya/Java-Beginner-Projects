package QuizApplication;

import java.util.Scanner;

public class QuizApplication {
    public static void main(String [] args){
        int score = 0;
        Scanner sc = new Scanner(System.in);
        
        // Add the Questions in this String Array
        String [] questions = {
            "What is the Capital of France?",
            "Which planet is known as the Red Planet?",
            "Where is the Great Wall located?",
            "Which Country has the Highest Population in 2023?",
            "Who invented the Light Bulb?",
            "Which animal was the first animal to go into Space?",
            "Which river is the largest in the World?",
            "What is the name of the Highest Mountain Peak?",
            "Who was the First Female to go in Space?",
            "Who is the Owner of Tesla Motors?"

        };

        String [] options = {
            "A) London  B) Paris  C) Delhi",
            "A) Earth  B) Mars  C) Jupiter",
            "A) Australia  B) Korea  C) China",
            "A) Canada  B) India  C) Russia",
            "A) Isaac Newton  B) Thomas A. Edison  C) Albert Einstein",
            "A) Monkey  B) Dog  C) Cat",
            "A) Nile  B) Amazon  C) Dnieper",
            "A) Mt. Kilimanjaro  B) Mt. Everest  C) Mt. Girnar",
            "A) Valentina Tereshkova  B) Sunita Williams  C) Kalpana Chawla",
            "A) Donald Trump  B) Steve Jobs  C) Elon Musk",

        };

        String [] correctAnswers = {"B", "B", "C", "B", "B", "B", "A", "B", "A", "C"};

        // Display and process the questions
        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            System.out.println(options[i]);

            System.out.print("Your answer (Enter A, B, or C): ");
            String userAnswer = sc.nextLine().toUpperCase();

            if (userAnswer.equals(correctAnswers[i])) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is " + correctAnswers[i] + "\n");
            }
        }

        // Display the final score
        System.out.println("Quiz complete! Your score: " + score + " out of " + questions.length);

        // Close the scanner
        sc.close();
    }
}
