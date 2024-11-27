package models;

public class Grade {
    private Student student;
    private Topic topic;
    private int grade;

    // Конструктор
    public Grade(Student student, Topic topic, int grade) {
        this.student = student;
        this.topic = topic;
        this.grade = grade;
    }

    // Геттеры
    public Student getStudent() {
        return student;
    }

    public Topic getTopic() {
        return topic;
    }


    public int getGrade() {
        return grade;
    }
}