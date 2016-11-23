package enteties;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;

/**
 * Created by Valko Serhii on 11/4/2016.
 */
@Entity()
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryId;

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
}
