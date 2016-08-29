import model.Answer;
import model.QA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public class ConsoleRenderer implements Renderer{

    private long start = 0;
    private long endOfTest = 0;
    private int rightAnswers = 0;
    private int wrongAnswers = 0;

    @Override
    public void render(Set<QA> questions) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("To quit type [q], for next question type [n]");
            long timeForTestInSec = questions.size() * 30;
            System.out.println("To pass exam you have :" + (timeForTestInSec / 60.0) + " mins for " + questions.size() + " questions!");
            System.out.println("Good luck!!!\n\n");
            start = System.currentTimeMillis();
            endOfTest = start + timeForTestInSec * 1000;

            for (QA question : questions) {
                checkTime();
                List<Answer> answersToPublishing = printQuestionAndCheckAnswer(question);
                waiting:
                while (true) {
                    checkTime();
                    switch (checkTheAnswer(bufferedReader, question.getAnswers(), answersToPublishing)) {
                        case NEXT: {
                            System.out.println("\n\n");
                            break waiting;
                        }
                        case QUIT:
                            System.out.println("Bye-bye ;(");
                            exit();
                        case TYPO:
                            checkTime();
                            System.out.println("Please type number of the answer!!!");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        exit();
    }

    private List<Answer> printQuestionAndCheckAnswer(QA question) throws IOException {
        System.out.println(question.getQuestion());

        List<Answer> originalAnswers = question.getAnswers();
        List<Answer> answersToPublishing = new ArrayList<>(originalAnswers);

        Collections.shuffle(answersToPublishing);

        int i = 1;
        for (Answer answer : answersToPublishing) {
            System.out.println(i++ + ". " + answer.getAnswer());
        }

        return answersToPublishing;
    }

    private Options checkTheAnswer(BufferedReader bufferedReader, List<Answer> originalAnswers, List<Answer> answersToPublishing) throws IOException {
        String rawSelectedAnswer = bufferedReader.readLine();
        if (rawSelectedAnswer != null && !rawSelectedAnswer.isEmpty()) {
            if ("q".equalsIgnoreCase(rawSelectedAnswer)) {
                return Options.QUIT;
            }
            if ("n".equalsIgnoreCase(rawSelectedAnswer)) {
                return Options.NEXT;
            }
            int selectedAnswer;

            try {
                selectedAnswer = Integer.parseInt(rawSelectedAnswer) - 1;
            } catch (NumberFormatException e) {
                return Options.TYPO;
            }

            if (answersToPublishing.size() >= selectedAnswer) {
                if (answersToPublishing.get(selectedAnswer).getAnswer().equalsIgnoreCase(originalAnswers.get(0).getAnswer())) {
                    System.out.println("You are right!! :)");
                    rightAnswers++;
                } else {
                    System.out.println("NOOOO!!! You are wrong");
                    System.out.println("Right answer is " + originalAnswers.get(0).getAnswer());
                    System.out.println("Be careful next time");
                    wrongAnswers++;
                }
            }
            else
            {
                return Options.TYPO;
            }
        }
        return Options.NEXT;
    }

    private enum Options {
        NEXT, QUIT, TYPO
    }

    private void checkTime() {
        if (System.currentTimeMillis() > endOfTest) {
            System.out.println("TIME IS OUT :(");
            exit();
        }
    }

    private void exit() {
        System.out.println("You've made: " + rightAnswers + " right answers");
        System.out.println("You've made: " + wrongAnswers + " wrong answers");
        System.exit(1);
    }
}
