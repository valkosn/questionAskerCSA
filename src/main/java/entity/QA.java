package entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
//TODO: refactor it
public final class QA {
    @JsonProperty("questionId")
    private int questionId;

    @JsonProperty("category")
    private Category category;

    @JsonProperty("question")
    private String question;

    @JsonProperty("answers")
    private List<Answer> answers;

    public QA() {
    }

    // TODO: 10/21/2016 add default (empty) category
    public QA(int questionId, String question, List<Answer> answers) {
        this.questionId = questionId;
        this.question = question;
        this.answers = answers;
    }

    @JsonCreator
    public QA(@JsonProperty("questionId") int questionId,
              @JsonProperty("question") String question,
              @JsonProperty("answers") List<Answer> answers,
              @JsonProperty("category") Category category) {
        this.questionId = questionId;
        this.question = question;
        this.answers = answers;
        this.category = category;
    }


    public int getQuestionId() {
        return questionId;
    }

    public Category getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
