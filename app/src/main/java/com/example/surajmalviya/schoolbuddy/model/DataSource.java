package com.example.surajmalviya.schoolbuddy.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Suraj Malviya on 02/06/2017.
 */


/*
* Singleton for storing "Students" state in memory !
*/

public class DataSource {

    private static DataSource dataSource;
    private List<Student> mStudentList;
    private Map<Integer, Student> mStudentMap;
    private String name;
    private DataSource() {
        Log.d("DataSource Created !","");
        this.mStudentList=new ArrayList<>();
        this.mStudentMap=new HashMap<>();
    }

    public static DataSource getDataSource(){
        if(dataSource == null){
            dataSource = new DataSource();
        }
        return dataSource;
    }

    public List<Student> getList(){
        return dataSource.mStudentList;
    }

    public void setName(String name){
        this.name = name;
    }

    public Map<Integer, Student> getStudentMap(){
        return dataSource.mStudentMap;
    }

    public void saveStudent(Student student){
        dataSource.mStudentList.add(student);
        dataSource.mStudentMap.put(student.getScholarID(),student);
    }

    public void setStudents(List<Student> list){
        for(Student item : list ){
            saveStudent(item);
        }

    }

    public void deleteStudent(Student student){
        dataSource.mStudentList.remove(student);
        dataSource.mStudentMap.remove(student.getScholarID());
    }


    @Override
    public String toString() {
        return this.name +" --- "+dataSource.name;
    }
}
