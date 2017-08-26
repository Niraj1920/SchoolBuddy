package com.example.surajmalviya.schoolbuddy;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.surajmalviya.schoolbuddy.model.CustomListAdapter;
import com.example.surajmalviya.schoolbuddy.model.DataSource;
import com.example.surajmalviya.schoolbuddy.model.Student;

import java.io.IOException;

public class StudentInfo extends AppCompatActivity {

    private Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        int scholarID = getIntent().getExtras().getInt(CustomListAdapter.SELECTED_STUDENT_ID);
        this.student = DataSource.getDataSource().getStudentMap().get(scholarID);

        ImageView imageView = (ImageView)findViewById(R.id.student_image);
        try {
            imageView.setImageDrawable(getDrawableFromName(student.getImage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Drawable getDrawableFromName(String image) throws IOException {
        Drawable drawable = Drawable.createFromStream(getAssets().open(image),null);
        return drawable;
    }
}
