package entity;

import javax.persistence.*;

/**
 * Created by Valko Serhii on 11/25/2016.
 */
@Entity
@Table(name = "questions", schema = "public", catalog = "asker")
public class QuestionsEntity {
    private int uid;
    private Integer categoryId;
    private String questionValue;

    @Id
    @Column(name = "uid")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "category_id")
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "question_value")
    public String getQuestionValue() {
        return questionValue;
    }

    public void setQuestionValue(String questionValue) {
        this.questionValue = questionValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionsEntity that = (QuestionsEntity) o;

        if (uid != that.uid) return false;
        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        if (questionValue != null ? !questionValue.equals(that.questionValue) : that.questionValue != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (questionValue != null ? questionValue.hashCode() : 0);
        return result;
    }
}
