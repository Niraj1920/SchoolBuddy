package com.example.surajmalviya.schoolbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.surajmalviya.schoolbuddy.database.DatabaseSource;
import com.example.surajmalviya.schoolbuddy.model.DataSource;
import com.example.surajmalviya.schoolbuddy.model.Student;
import com.example.surajmalviya.schoolbuddy.model.StudentResult;

public class SaveResult extends AppCompatActivity {

    Student myStudent;
    DatabaseSource dbSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_result);
        getSupportActionBar().setSubtitle("Add Result");
        myStudent = getIntent().getExtras().getParcelable(AddStudentPersonal.STUDENT);
        Log.d("student in result", myStudent.toString());
        dbSource=new DatabaseSource(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.skip_student:
                goForward(null);
                return true;

            case R.id.reset_student_marks:
                resetStudentMarks();
                return true;

            case R.id.save_student:
                saveStudent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void goForward(StudentResult result){
        if(result!= null) myStudent.setStudentResult(result);

        dbSource.open();
        boolean done = dbSource.addStudent(myStudent);
        if(done){
            DataSource.getDataSource().saveStudent(myStudent);
            Toast.makeText(this,"Student Successfully Saved !", Toast.LENGTH_SHORT).show();
            Intent toMainActivity = new Intent(this, MainActivity.class);
            startActivity(toMainActivity);
        }
        else{
            Toast.makeText(this, "Couldnt save the student !", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetStudentMarks(){

    }

    private void saveStudent(){
        EditText ele1view = (EditText) findViewById(R.id.elect1_score);
        EditText ele2view = (EditText) findViewById(R.id.elect2_score);
        EditText ele3view = (EditText) findViewById(R.id.elect3_score);
        EditText ele4view = (EditText) findViewById(R.id.elect4_score);
        EditText ele5view = (EditText) findViewById(R.id.elect5_score);

        double ele1 = Double.parseDouble(ele1view.getText().toString());
        double ele2 = Double.parseDouble(ele2view.getText().toString());
        double ele3 = Double.parseDouble(ele3view.getText().toString());
        double ele4 = Double.parseDouble(ele4view.getText().toString());
        double ele5 = Double.parseDouble(ele5view.getText().toString());

        StudentResult result = new StudentResult(myStudent.getScholarID(), ele1, ele2, ele3, ele5, ele4);
        goForward(result);
    }


}
