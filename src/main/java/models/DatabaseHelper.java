package models;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper implements AutoCloseable{
    private static final String DATABASE_URL = "jdbc:sqlite:aa.db";
    private ConnectionSource connectionSource;

    public DatabaseHelper() throws SQLException {
        connectionSource = new JdbcConnectionSource(DATABASE_URL);
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public void setupDatabase() throws SQLException {
        // Создаем таблицы, если их еще нет
        TableUtils.createTableIfNotExists(connectionSource, GroupEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, StudentEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, TopicEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, GradeEntity.class);

    }

    public void close() {
        if (connectionSource != null) {
            try {
                connectionSource.close();
            } catch (Exception ignored) {
            }
        }
    }
}