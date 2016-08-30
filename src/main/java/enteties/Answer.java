package enteties;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public final class Answer {
    private final String answer;

    public Answer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer='" + answer + '\'' +
                '}';
    }
}
