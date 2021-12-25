package ru.vsu.cs.novichikhin;

import java.util.List;

public class Student {
    String fio;
    String gender;
    int courseNumber;
    int averageScore;

    public Student(List<String> list) {
        this.fio = list.get(0);
        this.gender = list.get(1);
        this.courseNumber = Integer.parseInt(list.get(2));
        this.averageScore = Integer.parseInt(list.get(3));
    }

    public String getFio() {
        return fio;
    }

    public String getGender() {
        return gender;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public int getAverageScore() {
        return averageScore;
    }
}
