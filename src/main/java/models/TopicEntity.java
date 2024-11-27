package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "topics")
public class TopicEntity {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(unique = true, canBeNull = false)
    private String name;

    public TopicEntity() {
        // ORM требует пустой конструктор
    }

    public TopicEntity(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
