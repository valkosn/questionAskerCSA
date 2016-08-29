package model;

import java.util.List;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public final class QA {
    private int id;
    private String category;
    private String question;
    private List<Answer> answers;

    public QA(int id, String question, List<Answer> answers) {
        this.id = id;
        this.question = question;
        this.answers = answers;
    }

    public QA(int id, String question, List<Answer> answers, String category) {
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

    @Override
    public String toString() {
        return "QA{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answers=" + answers +
                '}';
    }
}
