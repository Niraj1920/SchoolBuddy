package com.example.surajmalviya.schoolbuddy;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.surajmalviya.schoolbuddy.database.DBHelper;
import com.example.surajmalviya.schoolbuddy.database.DatabaseSource;
import com.example.surajmalviya.schoolbuddy.model.CustomListAdapter;
import com.example.surajmalviya.schoolbuddy.model.DataSource;
import com.example.surajmalviya.schoolbuddy.model.Student;
import com.example.surajmalviya.schoolbuddy.utility.GeneralUtilities;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String NEXT_ID="nextID";
    private int nextID;
    private DataSource mDataSource;
    private List<Student> mStudents;
    private DatabaseSource mDatabaseSource;
    private  CustomListAdapter adapter;
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataSource = DataSource.getDataSource();
        mStudents = mDataSource.getList();

        if(mStudents.isEmpty()){
            mDatabaseSource = new DatabaseSource(this);
            mStudents = mDatabaseSource.fetchAllStudent();
            mDataSource.setStudents(mStudents);
        }
        // checking if no student is registered !
        if(mStudents.isEmpty()){
            setContentView(R.layout.no_student);
        }
        else{
            setContentView(R.layout.activity_main);
            nextID = mStudents.size();
            mStudents = GeneralUtilities.sortByID(mStudents);
            adapter = new CustomListAdapter(this,mStudents);
            mListView = (ListView) findViewById(R.id.studentListView);
            mListView.setAdapter(adapter);
        }
    }

    public void fapOnClick(View view){
        Intent addStudentIntent=new Intent(this,AddStudentPersonal.class);
        addStudentIntent.putExtra(NEXT_ID,nextID);
        startActivity(addStudentIntent);
    }



}
