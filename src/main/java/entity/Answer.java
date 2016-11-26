package entity;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by Valko Serhii on 10/21/2016.
 */
public final class Answer {

    private int answerId;

    private String answer;

    public Answer() {
    }

    @JsonCreator
    public Answer(String answer){
        this.answer = answer;
    }

    public Answer(int answerId, String answer) {
        this.answerId = answerId;
        this.answer = answer;
    }

    public int getAnswerId() {
        return answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


}
