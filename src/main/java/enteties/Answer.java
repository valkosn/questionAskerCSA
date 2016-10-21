package enteties;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by Valko Serhii on 10/21/2016.
 */
public final class Answer {

    private int id;

    private String answer;

    @JsonCreator
    public Answer(String answer){
        this.answer = answer;
    }

    public Answer(int id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }
}
