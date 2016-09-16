package utils;

import java.sql.Connection;

/**
 * Created by Valko Serhii on 16-Sep-16.
 */
public interface ConnectionProvider {

    Connection getConnection();
}
