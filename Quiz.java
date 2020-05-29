import java.util.Random; 

public class Quiz
{
    // INSTANCE VARIABLES
    static Random rand;
    static Window w;
    static boolean continuePlaying;
    static int cnt;
    static int correct;
    private static final String[] Replay;
    
	/*
	 * This is just a way to have all the variables inside be static.
	 */
    static {
        Quiz.rand = new Random();
        Quiz.w = new Window();
        Quiz.continuePlaying = true;
        Quiz.cnt = 1;
        Quiz.correct = 0;
        Replay = new String[] { "Start learning!" };
    }
	
    /*
	 * This method, main, runs the game using the other methods found in this class.
	 * It begins by introducing the user to the program, as well as introducting
	 * that exit and quit can be used to end the program. It asks the user for the
	 * amount of questions, and when completed tells them how many they got right.
	 */
    public static void main(final String[] args) {
        final boolean test2 = false;
        int check = 0;
        boolean test3 = false;
        Quiz.cnt = 1;
        final int a = Quiz.w.option(Quiz.Replay, "Welcome to a math game designed to test your addition and subtraction skills. \nGood luck on the test!");
        final String b = Msg.in("How many questions do you want to be asked? \nIf you want to stop the test at any time type quit or exit.");
        if (b.equalsIgnoreCase("exit")) {
            System.exit(0);
        }
        if (b.equalsIgnoreCase("quit")) {
            System.exit(0);
        }
        else {
            final int loop = Integer.parseInt(CheckStr(b));
            while (Quiz.cnt <= loop) {
                check = 0;
                final int n1 = Quiz.rand.nextInt(10);
                final int n2 = Quiz.rand.nextInt(10);
                final char operator = genOperator(Quiz.rand.nextInt(3));
                final String x = Msg.in(evaluateQuestion(n1, n2, operator, Quiz.cnt, loop));
                final int answer = Integer.parseInt(CheckStr(x));
                if (answer == calcAnswer(n1, n2, operator)) {
                    final String[] hi = { "Great job, you got it right!", "Nice work!", "You're doing great!", "Amazing work!", "You got it right! Keep up the good work!", "You're a smart cookie!" };
                    final String _hi = hi[(int)(Math.random() * 6.0)];
                    Quiz.w.msg(_hi);
                    ++Quiz.cnt;
                    ++Quiz.correct;
                    if (Quiz.cnt > loop) {
                        break;
                    }
                    continue;
                }
                else {
                    if (answer == calcAnswer(n1, n2, operator)) {
                        continue;
                    }
                    final String[] hi2 = { "You got it wrong... try and work hard to get the next one right!", "You got it wrong... I know you can do better, keep trying your hardest!", "You got it wrong... but don't lose heart, just try and get other questions right!", "You got it wrong... with some more work you could've gotten it right!" };
                    final String _hi2 = hi2[(int)(Math.random() * 4.0)];
                    Quiz.w.msg(String.valueOf(String.valueOf(_hi2)) + " The correct answer was " + calcAnswer(n1, n2, operator));
                    ++Quiz.cnt;
                    if (Quiz.cnt > loop) {
                        break;
                    }
                    continue;
                }
            }
            endScore(Quiz.correct, loop);
            test3 = true;
            Quiz.w.msg("Good job on your math training, regardless of what score you got, it's always good to practice!");
            System.exit(0);
        }
    }
	
    /*
	 * This method returns an operator, either a "-" sign or a "+" sign.
	 * This method uses these operators to calculate in calcAnswer as well
	 * as to display the question in evaluateQuestion.
	 */
    public static char genOperator(final int a) {
        switch (a) {
            case 0: {
                return '+';
            }
            default: {
                return '-';
            }
        }
    }
	
    /*
	 * This method uses all the information about the question in order to present it to the user.
	 * This method does not calculate anything, and is instead used to display the question.
	 */
    public static String evaluateQuestion(int n1, int n2, final char operator, final int cnt, final int lo) {
        if (n1 - n2 < 0) {
            final int n3 = n1;
            n1 = n2;
            n2 = n3;
        }
        return "Question #" + cnt + " out of " + lo + "\nThe problem is: " + n1 + " " + operator + " " + n2 + ".";
    }
	
    /*
	 * This method uses the two numbers and the given operator to calculate what the right answer
	 * is for that problem. In the main method, it is used to compare the user's answer and check
	 * if it is right.
	 */
    public static int calcAnswer(int n1, int n2, final char operator) {
        if (n1 - n2 < 0) {
            final int n3 = n1;
            n1 = n2;
            n2 = n3;
        }
        switch (operator) {
            case '+': {
                return n1 + n2;
            }
            case '-': {
                return n1 - n2;
            }
            default: {
                return -1;
            }
        }
    }
	
    /*
	 * This method checks the string to see if the user has put in an integer.
	 * If they have put a quit or exit that means they want to end the test, so the window closes
	 * and due to not completing the test they are not given results.
	 * If they didn't input either of these commands, they are asked to input a number or exit/quit.
	 */
    public static String CheckStr(final String input) {
        try {
            Integer.parseInt(input);
        }
        catch (Exception e) {
            if (input.equalsIgnoreCase("exit")) {
                Quiz.w.msg("Since you haven't finished it wouldn't be fair to calculate your results, come back soon. \n I hope this quiz was helpful!");
                System.exit(0);
            }
            else if (input.equalsIgnoreCase("quit")) {
                Quiz.w.msg("Since you haven't finished it wouldn't be fair to calculate your results, come back soon. \n I hope this quiz was helpful!");
                System.exit(0);
            }
            else {
                Quiz.w.msg("Please input a number, exit, or quit. ");
                Msg.in("Again, please input a number, exit, or quit. \n If you don't, I'll assume you wanted to exit or get your current question wrong.");
            }
            return "-1";
        }
        return input;
    }
	
    /*
	 * This method is called at the end of the quiz, and gives the user their final score in the test.
	 * If it's a bad score, it gives the percentage and asks the user to improve on the next test.
	 * If it's a good score, it displays the percentage and congratulates them.
	 */
    public static void endScore(final int a, final int b) {
        if (a / (double)b * 100.0 < 1.0) {
            Quiz.w.msg("You got 0.0% correct... Try and take another test and improve!");
        }
        else {
            Quiz.w.msg("You got " + a / (double)b * 100.0 + "% correct! Good job!");
        }
    }
}
