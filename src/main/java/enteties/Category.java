package enteties;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by Valko Serhii on 11/4/2016.
 */
public class Category {

    private int id;

    private String category;

    @JsonCreator
    public Category(String category) {
        this.category = category;
    }

    public Category(int id, String category) {
        this.id = id;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }
}
