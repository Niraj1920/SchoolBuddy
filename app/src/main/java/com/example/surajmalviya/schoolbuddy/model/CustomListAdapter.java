package com.example.surajmalviya.schoolbuddy.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.surajmalviya.schoolbuddy.R;
import com.example.surajmalviya.schoolbuddy.StudentInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 *
 * Created by Suraj Malviya on 15/07/2017.
 */

public class CustomListAdapter extends ArrayAdapter<Student> {

    public static final String SELECTED_STUDENT_ID = "SELECTED_STUDENT";
    private List<Student> myStudents;
    private LayoutInflater mInflator;

    public CustomListAdapter(@NonNull Context context, @NonNull List<Student> objects) {
        super(context, R.layout.listview_item, objects);
        this.myStudents = objects;
        mInflator = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
           convertView = mInflator.inflate(R.layout.listview_item, parent, false);
        }

        final Student _thisStudent = myStudents.get(position);

        String studentName = _thisStudent.getName();
        int studentScholarID = _thisStudent.getScholarID();
        String studentStream = _thisStudent.getStream();
        int studentStandard = _thisStudent.getStandard();
        String studentPicture = _thisStudent.getImage();


        String class_stream = studentStandard+ ""+Html.fromHtml("<sup>th</sup>")+" & "+studentStream;

        TextView tileScholarID = (TextView)convertView.findViewById(R.id.tileschid);
        tileScholarID.setText(studentScholarID+"");

        TextView tileStudentName = (TextView)convertView.findViewById(R.id.tile_studentName);
        tileStudentName.setText(studentName);

        TextView tileClassStream =(TextView)convertView.findViewById(R.id.tile_classStream);
        tileClassStream.setText(class_stream);

        InputStream inputStream = null;
        Drawable drawable =null;
        try{
            inputStream = getContext().getAssets().open(studentPicture);
            drawable = Drawable.createFromStream(inputStream,null);
        }
        catch(IOException ei){
            ei.printStackTrace();
        }
        finally{
            try {
                if(inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ImageView studentImageView = (ImageView) convertView.findViewById(R.id.student_img);
        studentImageView.setImageDrawable(drawable);

        Button moreInfo = (Button)convertView.findViewById(R.id.view_detail);
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(),_thisStudent.getName() ,Toast.LENGTH_SHORT).show();
                Intent info = new Intent(getContext(), StudentInfo.class);
                info.putExtra(SELECTED_STUDENT_ID, _thisStudent.getScholarID());
                getContext().startActivity(info);
            }
        });

        Button resultInfo = (Button) convertView.findViewById(R.id.view_result);
        resultInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_thisStudent.getStudentResult() != null) {
                    Toast.makeText(getContext(), "GRADE : " + _thisStudent.getStudentResult().getGrade(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "RESULT NOT AVAILABLE !" + "", Toast.LENGTH_SHORT).show();
                }
            }
        });
       return convertView;
    }
}
