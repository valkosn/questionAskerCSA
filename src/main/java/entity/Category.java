package entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Valko Serhii on 11/4/2016.
 */
@Entity()
@Table(name = "categories")
public class Category implements Serializable{

    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Basic
    @Column(name = "category_value")
    private String categoryName;

    public Category() {
    }

    @JsonCreator
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category that = (Category) o;

        if (categoryId != that.categoryId) return false;
        if (categoryName != null ? !categoryName.equals(that.categoryName) : that.categoryName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryId;
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        return result;
    }
}
