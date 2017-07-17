package com.example.surajmalviya.schoolbuddy.utility;

import com.example.surajmalviya.schoolbuddy.model.Student;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Suraj Malviya on 17/07/2017.
 */

public class GeneralUtilities {

    public static List<Student> sortByID(List<Student> students){
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if(o1.getScholarID() == o2.getScholarID()){
                    return 0;
                }
                else if(o1.getScholarID() > o2.getScholarID()){
                    return 1;
                }
                else{
                    return -1;
                }
            }
        });
        return students;
    }

    public static List<Student> sortByName(List<Student> students){
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return students;
    }

}
