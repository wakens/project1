package hardmath;

import java.util.Random;

public class Quiz
{
    static Random rand;
    static Window w;
    static boolean continuePlaying;
    static int cnt;
    static int correct;
    private static final String[] Replay;
    
    static {
        Quiz.rand = new Random();
        Quiz.w = new Window();
        Quiz.continuePlaying = true;
        Quiz.cnt = 1;
        Quiz.correct = 0;
        Replay = new String[] { "Codes", "Math" };
    }
    
    public static void runThis() {
        final boolean test2 = false;
        int check = 0;
        boolean test3 = false;
        Quiz.cnt = 1;
        Quiz.w.msg("Welcome to the hard version, this includes + - * along with bigger numbers and possible negative answers.");
        final String b = Msg.in("(Hard mode)\nHow many questions would you like to answer?\nOnly use numbers please");
        if (b.contentEquals("exit")) {
            System.exit(0);
        }
        if (b.contentEquals("Exit")) {
            System.exit(0);
        }
        final int loop = Integer.parseInt(CheckStr(b));
        while (Quiz.cnt <= loop) {
            check = 0;
            final int n1 = Quiz.rand.nextInt(26);
            final int n2 = Quiz.rand.nextInt(26);
            final char operator = genOperator(Quiz.rand.nextInt(3));
            final String x = Msg.in(evaluateQuestion(n1, n2, operator, Quiz.cnt, loop));
            if (x.contentEquals("exit")) {
                System.exit(0);
            }
            if (x.contentEquals("Exit")) {
                System.exit(0);
            }
            final int answer = Integer.parseInt(CheckStr(x));
            if (answer == calcAnswer(n1, n2, operator)) {
                final String[] hi = { "Correct!", "Awesome!", "Incredible!", "Knew you could do it!" };
                final String _hi = hi[(int)(Math.random() * 4.0)];
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
                final String[] hi2 = { "Horrible!", "How could you get that wrong?!", "Horrendous!", "Seriously?!" };
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
        Quiz.w.msg("Hope you enjoyed doing some math!");
        System.exit(0);
    }
    
    public static char genOperator(final int a) {
        switch (a) {
            case 0: {
                return '+';
            }
            case 1: {
                return '-';
            }
            case 2: {
                return '*';
            }
            case 3: {
                return '/';
            }
            default: {
                return '/';
            }
        }
    }
    
    public static String evaluateQuestion(final int n1, final int n2, final char operator, final int cnt, final int lo) {
        return "Question #" + cnt + " out of " + lo + "\nThe problem is: " + n1 + " " + operator + " " + n2 + ".";
    }
    
    public static int calcAnswer(final int n1, final int n2, final char operator) {
        switch (operator) {
            case '+': {
                return n1 + n2;
            }
            case '-': {
                return n1 - n2;
            }
            case '*': {
                return n1 * n2;
            }
            case '/': {
                return n1 / n2;
            }
            default: {
                return -1;
            }
        }
    }
    
    public static String CheckStr(final String input) {
        try {
            Integer.parseInt(input);
        }
        catch (Exception e) {
            if (input.equals("exit") || input.equals("Exit") || input.equals("EXIT")) {
                System.exit(0);
            }
            return "-1";
        }
        return input;
    }
    
    public static void endScore(final int a, final int b) {
        Quiz.w.msg("You got " + a / (double)b * 100.0 + "% correct!");
    }
}
