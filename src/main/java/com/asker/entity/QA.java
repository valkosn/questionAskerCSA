package com.asker.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
//TODO: refactor it

@Entity()
@Table(name = "questions")
public final class QA {
    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("questionId")
    private int questionId;

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = ""))
    @JsonProperty("category")
    private Category category;

    @Column(name = "question_value")
    @JsonProperty("question")
    private String question;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QA that = (QA) o;

        if (questionId != that.questionId) return false;
        if (question != null ? !question.equals(that.question) : that.question != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = questionId;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        return result;
    }
}
