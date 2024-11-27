package models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    public List<Student> parseCsv(String filePath) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/models/basicprogramming_2.csv"))) {
            String line;

            // Пропустим заголовки
            br.readLine(); // Первая строка (названия тем)
            br.readLine(); // Вторая строка (названия колонок)
            br.readLine(); // Третья строка(максимум)

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(";"); // Разделяем по разделителю ";"

                if (columns.length < 3) {
                    continue; // Если строка неполная, пропускаем её
                }

                // Извлекаем имя и группу
                String name = columns[0]; // Например, "Фамилия Имя"
                String group = columns[1]; // Например, "РТФ.2021 Пересдачи Часть 1."

                // Извлекаем баллы по ДЗ и Упражнениям
                int totalGradeDz = 0;
                int totalGradeUp = 0;
                try {
                    totalGradeDz = Integer.parseInt(columns[4]);
                    totalGradeUp = Integer.parseInt(columns[3]);
                } catch (NumberFormatException e) {
                    // Если значение не числовое, присваиваем 0
                }

                // Создаем объект Student и добавляем в список

                students.add(new Student(name, group, totalGradeDz, totalGradeUp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}