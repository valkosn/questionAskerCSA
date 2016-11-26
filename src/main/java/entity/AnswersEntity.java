package entity;

import javax.persistence.*;

/**
 * Created by Valko Serhii on 11/25/2016.
 */
@Entity
@Table(name = "answers", schema = "public", catalog = "asker")
public class AnswersEntity {
    private int uid;
    private int questionId;
    private String answerValue;
    private Boolean rightAnswer;

    @Id
    @Column(name = "uid")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "question_id")
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "answer_value")
    public String getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(String answerValue) {
        this.answerValue = answerValue;
    }

    @Basic
    @Column(name = "right_answer")
    public Boolean getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswersEntity that = (AnswersEntity) o;

        if (uid != that.uid) return false;
        if (questionId != that.questionId) return false;
        if (answerValue != null ? !answerValue.equals(that.answerValue) : that.answerValue != null) return false;
        if (rightAnswer != null ? !rightAnswer.equals(that.rightAnswer) : that.rightAnswer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + questionId;
        result = 31 * result + (answerValue != null ? answerValue.hashCode() : 0);
        result = 31 * result + (rightAnswer != null ? rightAnswer.hashCode() : 0);
        return result;
    }
}
