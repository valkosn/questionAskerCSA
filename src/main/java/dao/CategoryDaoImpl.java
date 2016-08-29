package dao;

import java.util.Set;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public class CategoryDaoImpl implements CategoryDao {

    Set<String> categories;

    @Override
    public Set<String> getCategories() {
        return categories;
    }
}
