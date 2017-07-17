package com.example.surajmalviya.schoolbuddy.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.surajmalviya.schoolbuddy.model.Student;
import com.example.surajmalviya.schoolbuddy.model.StudentResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suraj Malviya on 02/06/2017.
 */

public class DatabaseSource
{
    private SQLiteDatabase mDatabase;
    private SQLiteOpenHelper mDbHelper;
    private Context mContext;

    public DatabaseSource(Context context)
    {
        Log.d("In DatabaseSource","");
        mContext=context;
        mDbHelper=new DBHelper(mContext);
        mDatabase=mDbHelper.getWritableDatabase();
    }

    public void open()
    {
        if(!mDatabase.isOpen())
        {
            mDatabase=mDbHelper.getWritableDatabase();
            Log.d("opened db","");
        }
        else{
            Log.d("already opened !","");
        }
    }

    public void close()
    {
        mDatabase.close();
        Log.d("DB Closed !","");
    }

    public List<Student> fetchAllStudent()
    {

        List<Student> allStudents=new ArrayList<>();

        Cursor cursor
                =
                mDatabase.query(DataSchema.StudentTable.TABLE_NAME,
                        DataSchema.StudentTable.ALL_COLUMNS,
                        null,null,null,null,
                        null);

        while(cursor.moveToNext())
        {
            int _id =cursor.getInt(cursor.getColumnIndex(DataSchema.StudentTable._ID));
            String name = cursor.getString(cursor.getColumnIndex(DataSchema.StudentTable.NAME));
            int standard=cursor.getInt(cursor.getColumnIndex(DataSchema.StudentTable.CLASS));
            String DOB=cursor.getString(cursor.getColumnIndex(DataSchema.StudentTable.DOB));
            int gender=cursor.getInt(cursor.getColumnIndex(DataSchema.StudentTable.GENDER));
            String email=cursor.getString(cursor.getColumnIndex(DataSchema.StudentTable.EMAIL));
            String contact=cursor.getString(cursor.getColumnIndex(DataSchema.StudentTable.CONTACT));
            String stream=cursor.getString(cursor.getColumnIndex(DataSchema.StudentTable.STREAM));
            String image =cursor.getString(cursor.getColumnIndex(DataSchema.StudentTable.IMAGE));

            Student student=new Student(_id,name,standard,DOB,gender,email,contact,stream,image);

            String selection= DataSchema.SubjectPrefereceTable._ID+" = ?";
            String[] selectionArgs={_id+""};

            Cursor subjects
                    =
                    mDatabase.query(DataSchema.SubjectPrefereceTable.TABLE_NAME,
                            DataSchema.SubjectPrefereceTable.ALL_COLUMNS,
                            selection,      //columns in where clause
                            selectionArgs,  //values of selected where clause columns...
                            null,           //grouping of rows..
                            null,           //filter by row groups
                            null            //sort order
                            );

            if(subjects.moveToNext())
            {
                int _subjects_id=subjects.getInt(subjects.getColumnIndex(DataSchema.SubjectPrefereceTable._ID));
                if(_subjects_id==_id)
                {
                    String elective_i=subjects.getString(subjects.getColumnIndex(DataSchema.SubjectPrefereceTable.ELECTIVE_I));
                    String elective_ii=subjects.getString(subjects.getColumnIndex(DataSchema.SubjectPrefereceTable.ELECTIVE_II));
                    String elective_iii=subjects.getString(subjects.getColumnIndex(DataSchema.SubjectPrefereceTable.ELECTIVE_III));
                    String optional=subjects.getString(subjects.getColumnIndex(DataSchema.SubjectPrefereceTable.OPTIONAL));
                    String compulsory=subjects.getString(subjects.getColumnIndex(DataSchema.SubjectPrefereceTable.COMPULSARY));

                    String[] subject_preference={elective_i,elective_ii,elective_iii,optional,compulsory};

                    student.setSubjects(subject_preference);
                }
            }

            StudentResult student_result = fetchResult(student);
            student.setStudentResult(student_result);
            allStudents.add(student);
        }

        return allStudents;
    }


    public StudentResult fetchResult(Student student)
    {
        int scholarID=student.getScholarID();
        StudentResult score=new StudentResult(scholarID);

        String selection = DataSchema.ScoreTable._ID+"=?;";
        String[] selectionArgs={scholarID+""};

        Cursor result=mDatabase.query(DataSchema.ScoreTable.TABLE_NAME,
                DataSchema.ScoreTable.ALL_COLUMNS,
                selection,
                selectionArgs,
                null,null, null);


        if(result.moveToNext())
        {
            Log.e("in in","inin");
            double elect_1=result.getDouble(result.getColumnIndex(DataSchema.ScoreTable.ELECTIVE_I));
            double elect_2=result.getDouble(result.getColumnIndex(DataSchema.ScoreTable.ELECTIVE_II));
            double elect_3=result.getDouble(result.getColumnIndex(DataSchema.ScoreTable.ELECTIVE_III));
            double opt=result.getDouble(result.getColumnIndex(DataSchema.ScoreTable.OPTIONAL));
            double comp=result.getDouble(result.getColumnIndex(DataSchema.ScoreTable.COMPULSARY));

            score.setELECTIVE_I(elect_1);
            score.setELECTIVE_II(elect_2);
            score.setELECTIVE_III(elect_3);
            score.setOPTIONAL(opt);
            score.setCOMPULSARY(comp);

            score.setTotalScore();
            score.setPercentage();
            score.setGrade();
        }

        return score;
    }



    public boolean addStudent(Student student)
    {


        ContentValues normalDetail=student.getStudentValues();
        ContentValues subjectPreferenceValues= student.getStudentPreferenceValues();
        ContentValues resultValues=student.getStudentResultValues();

        //in StudentTable
        long student_table=mDatabase.insert(DataSchema.StudentTable.TABLE_NAME,null,normalDetail);

        //in StudentSubjectPreferenceTable
        long preference_table=mDatabase.insert(DataSchema.SubjectPrefereceTable.TABLE_NAME,null,subjectPreferenceValues);

        //in ScoreTable
        if(student.getStudentResult() != null){
            long score_table=mDatabase.insert(DataSchema.ScoreTable.TABLE_NAME,null,resultValues);
            return student_table != -1 && preference_table != -1 && score_table != -1;
        }
        else{
            return student_table != -1 && preference_table != -1;
        }



    }








}
