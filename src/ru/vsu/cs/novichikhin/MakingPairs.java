package ru.vsu.cs.novichikhin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MakingPairs {

    public List<List<String>> createPairs(List<List<String>> fullList) {
        List<List<String>> pairs = new ArrayList<>();
        List<Integer> courses = findCoursesNumbers(fullList);
        for (Integer course : courses) {
            pairs.add(makePairInCourse(course, fullList));
        }
        return pairs;
    }

    private List<Integer> findCoursesNumbers(List<List<String>> fullList) {
        List<Integer> allCourses = new ArrayList<>();
        for (List<String> data : fullList) {
            Student student = new Student(data);
            allCourses.add(student.getCourseNumber());
        }
        List<Integer> courses = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            int numberRepeated = Collections.frequency(allCourses, i);
            if (numberRepeated > 0) {
                courses.add(i);
            }
        }
        return courses;
    }

    private List<String> makePairInCourse(int course, List<List<String>> fullList) {
        List<List<String>> listCourse = new ArrayList<>();
        for (List<String> list : fullList) {
            Student student = new Student(list);
            if (course == student.getCourseNumber()) {
                listCourse.add(list);
            }
        }

        List<List<String>> boysOnCourse = new ArrayList<>();
        List<List<String>> girlsOnCourse = new ArrayList<>();

        for (List<String> data : listCourse) {
            Student student = new Student(data);
            if (student.getGender().equals("мужской")) {
                boysOnCourse.add(data);
            }
            if (student.getGender().equals("женский")) {
                girlsOnCourse.add(data);
            }
        }

        List<String> boysWithMaximum = findStudentWithMaximumAverageScore(boysOnCourse);
        List<String> girlsWithMaximum = findStudentWithMaximumAverageScore(girlsOnCourse);

        List<String> pair = new ArrayList<>();
        pair.add(Integer.toString(course));

        if ((boysWithMaximum.size() == 0) || (girlsWithMaximum.size() == 0)) {
            pair.add("пары от этого курса на баллу не будет, т.к. курс полностью состоит из представителей одного пола");
        } else {
            String boy = selectStudent(boysWithMaximum);
            String girl = selectStudent(girlsWithMaximum);

            pair.add(boy + " и " + girl);
        }
        return pair;
    }

    private List<String> findStudentWithMaximumAverageScore(List<List<String>> list) {
        List<String> listMaximum = new ArrayList<>();
        int maximum = -1;
        for (List<String> data : list) {
            Student student = new Student(data);
            if (maximum < student.getAverageScore()) {
                maximum = student.getAverageScore();
            }
        }

        for (List<String> data : list) {
            Student student = new Student(data);
            if (maximum == student.getAverageScore()) {
                listMaximum.add(student.getFio());
            }
        }
        return listMaximum;
    }

    private String selectStudent(List<String> list) {
        String student;
        if (list.size() == 1) {
            student = list.get(0);
        } else {
            Random random = new Random();
            student = list.get(random.nextInt(list.size() - 1));
        }
        return student;
    }
}
