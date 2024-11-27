package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "students")
public class StudentEntity {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private GroupEntity group;

    public StudentEntity() {
        // Конструктор без параметров обязателен для ORMLite
    }

    public StudentEntity(String name, GroupEntity group) {
        this.name = name;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GroupEntity getGroup() {
        return group;
    }
}
