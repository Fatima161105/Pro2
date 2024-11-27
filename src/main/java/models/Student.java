package models;


public class Student {
    private final String name;
    private final String group;
    private final int totalGradeDz;
    private final int totalGradeUp;

    public Student(String name, String group, int totalGradeDz, int totalGradeUp) {
        this.name = name;
        this.group = group;
        this.totalGradeDz = totalGradeDz;
        this.totalGradeUp = totalGradeUp;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public int getTotalGradeDz() {
        return totalGradeDz;
    }

    public int getTotalGradeUp() {
        return totalGradeUp;
    }
}
