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
    private List<Answer> answers;

    // TODO: 10/21/2016 add default (empty) category
    public QA(int id, String question, List<Answer> answers) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }
    @JsonCreator
    public QA(@JsonProperty("id") int id, @JsonProperty("question") String question, @JsonProperty("answers") List<Answer> answers, @JsonProperty("category") String category) {
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

    public List<Answer> getAnswers() {
        return answers;
    }

}
