package models;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.Level;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;

import models.CSVParser;
import models.DatabaseHelper;
import java.util.List;


public class Main {

    public static void main(String[] args) {
//        CSVParser parser = new CSVParser();
//        List<Student> students = parser.parseCsv("src/main/java/models/basicprogramming_2.csv");
//
//        // Сортировка групп по сумме среднего балла ДЗ и среднего балла Упражнений
//        Map<String, List<Student>> studentsByGroup = new HashMap<>();
//        for (Student student : students) {
//            studentsByGroup.computeIfAbsent(student.getGroup(), k -> new ArrayList<>()).add(student);
//        }
//
//        List<Map.Entry<String, List<Student>>> sortedGroups = new ArrayList<>(studentsByGroup.entrySet());
//        // Явное приведение типов для entry в Comparator
//        Collections.sort(sortedGroups, Comparator.comparingDouble((Map.Entry<String, List<Student>> entry) -> {
//            List<Student> groupStudents = entry.getValue();
//            double avgDz = groupStudents.stream().mapToInt(Student::getTotalGradeDz).average().orElse(0);
//            double avgUp = groupStudents.stream().mapToInt(Student::getTotalGradeUp).average().orElse(0);
//            return avgDz + avgUp; // Сумма средних баллов
//        }).reversed());
////
//        System.out.println("Успеваемость в зависимотси от группы по убыванию");
//        for (Map.Entry<String, List<Student>> entry : sortedGroups) {
//            List<Student> groupStudents = entry.getValue();
//            System.out.println("Группа: " + entry.getKey());
//            System.out.println("Средний балл по ДЗ за весь курс: " + Math.round(groupStudents.stream().mapToInt(Student::getTotalGradeDz).average().orElse(0))); // Округление до целого
//            System.out.println("Средний балл по Упражнениям за весь курс: " + Math.round(groupStudents.stream().mapToInt(Student::getTotalGradeUp).average().orElse(0))); // Округление до целого
//            System.out.println("--------------------");
//
//        }
        try (DatabaseHelper dbHelper = new DatabaseHelper()) {
            dbHelper.setupDatabase(); // Создаем/инициализируем таблицы
            DatabaseService dbService = new DatabaseService(dbHelper);

            // Чтение данных из CSV
            CSVParser parser = new CSVParser();
            List<Student> students = parser.parseCsv("src/main/java/models/basicprogramming_2.csv");

            // Перенос данных в базу
            for (Student student : students) {
                // 1. Добавляем/получаем группу
                GroupEntity group = dbService.addOrGetGroup(student.getGroup());

                // 2. Добавляем студента
                StudentEntity studentEntity = dbService.addStudent(new StudentEntity(student.getName(), group));

                // 3. Добавляем или получаем тему "ДЗ"
                TopicEntity dzTopic = dbService.addOrGetTopic("ДЗ");
                dbService.addGrade(studentEntity, dzTopic, student.getTotalGradeDz());

                // 4. Добавляем или получаем тему "Упражнение"
                TopicEntity upTopic = dbService.addOrGetTopic("Упражнение");
                dbService.addGrade(studentEntity, upTopic, student.getTotalGradeUp());
            }

            System.out.println("Данные успешно перенесены в базу данных.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//        Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
//        rootLogger.setLevel(Level.INFO); // Устанавливаем уровень по умолчанию
//
//        Logger ormliteLogger = (Logger) LoggerFactory.getLogger("com.j256.ormlite");
//        ormliteLogger.setLevel(Level.WARN); // Отключаем DEBUG для ORMLite
//
//        System.out.println("Logging configured. Starting application...");
//        try (DatabaseHelper dbHelper = new DatabaseHelper()) {
//            dbHelper.setupDatabase();
//            DatabaseService dbService = new DatabaseService(dbHelper);
//
//            // Получение данных из таблиц
//            List<GroupEntity> groups = dbService.getAllGroups();
//            List<StudentEntity> students = dbService.getAllStudents();
//            List<GradeEntity> grades = dbService.getAllGrades();
//
//            // Вывод данных о группах
//            System.out.println("Группы:");
//            for (GroupEntity group : groups) {
//                System.out.println("ID: " + group.getId() + ", Название: " + group.getName());
//            }
//
//            // Вывод данных о студентах
//            System.out.println("\nСтуденты:");
//            for (StudentEntity student : students) {
//                System.out.println("ID: " + student.getId() + ", Имя: " + student.getName() +
//                        ", Группа: " + (student.getGroup() != null ? student.getGroup().getName() : "Нет"));
//            }
//
//            System.out.println("\nТемы:");
//            for (TopicEntity topic : dbService.getAllTopics()) {
//                System.out.println("ID: " + topic.getId() + ", Название: " + topic.getName());
//            }
//
//            System.out.println("\nОценки:");
//            for (GradeEntity grade : dbService.getAllGrades()) {
//                System.out.println("ID: " + grade.getId() + ", Студент: " + grade.getStudent().getName() +
//                        ", Тема: " + grade.getTopic().getName() +
//                        ", Оценка: " + grade.getGradeValue());
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    }

