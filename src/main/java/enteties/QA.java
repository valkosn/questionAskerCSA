package enteties;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public final class QA {
    @JsonProperty("id")
    private int id;

    @JsonProperty("category")
    private String category;

    @JsonProperty("question")
    private String question;

    @JsonProperty("answers")
    private List<String> answers;

    public QA(int id, String question, List<String> answers) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }
    @JsonCreator
    public QA(@JsonProperty("id") int id, @JsonProperty("question") String question, @JsonProperty("answers") List<String> answers, @JsonProperty("category") String category) {
        this.id = id;
        this.question = question;
        this.answers = answers;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

}
