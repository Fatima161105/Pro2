package models;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import java.sql.SQLException;
import java.util.List;


public class DatabaseService {
    private final Dao<GroupEntity, Integer> groupDao;
    private final Dao<StudentEntity, Integer> studentDao;
    private final Dao<GradeEntity, Integer> gradeDao;
    private final Dao<TopicEntity, Integer> topicDao;

    public DatabaseService(DatabaseHelper dbHelper) throws SQLException {
        groupDao = DaoManager.createDao(dbHelper.getConnectionSource(), GroupEntity.class);
        studentDao = DaoManager.createDao(dbHelper.getConnectionSource(), StudentEntity.class);
        gradeDao = DaoManager.createDao(dbHelper.getConnectionSource(), GradeEntity.class);
        topicDao = DaoManager.createDao(dbHelper.getConnectionSource(), TopicEntity.class);
    }

    public GroupEntity addOrGetGroup(String groupName) throws SQLException {
        GroupEntity group = groupDao.queryBuilder()
                .where().eq("name", groupName)
                .queryForFirst();

        if (group == null) {
            group = new GroupEntity(groupName);
            groupDao.create(group);
        }
        return group;
    }

    public StudentEntity addStudent(StudentEntity student) throws SQLException {
        studentDao.create(student);
        return student;
    }

    public GradeEntity addGrade(StudentEntity student, TopicEntity topic, int gradeValue) throws SQLException {
        GradeEntity grade = new GradeEntity(student, topic, gradeValue);
        gradeDao.create(grade);
        return grade;
    }

    public TopicEntity addOrGetTopic(String name) throws SQLException {
        TopicEntity topic = topicDao.queryBuilder()
                .where().eq("name", name)
                .queryForFirst();

        if (topic == null) {
            topic = new TopicEntity(name);
            topicDao.create(topic);
        }
        return topic;
    }


    public List<TopicEntity> getAllTopics() throws SQLException {
        return topicDao.queryForAll();
    }
    public List<GroupEntity> getAllGroups() throws SQLException {
        return groupDao.queryForAll();
    }
    public List<StudentEntity> getAllStudents() throws SQLException {
        return studentDao.queryForAll();
    }
    public List<GradeEntity> getAllGrades() throws SQLException {
        return gradeDao.queryForAll();
    }


}