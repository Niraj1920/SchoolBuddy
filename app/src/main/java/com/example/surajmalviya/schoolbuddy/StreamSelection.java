package com.example.surajmalviya.schoolbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.surajmalviya.schoolbuddy.model.Student;

public class StreamSelection extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Student receivedStudent;
    private Spinner spin_stream, spin_elective, spin_optional;
    private ArrayAdapter<CharSequence> electiveAdapter, optionalAdapter;
    private TextView view_elective2, view_elective3, view_compulsary;
    private String stream, elective1, elective2, elective3, optional, compulsory;
    boolean classSelected;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream_selection);

        getSupportActionBar().setTitle("Add Student");
        getSupportActionBar().setSubtitle("Enter Academic Details");

        receivedStudent =getIntent().getExtras().getParcelable(AddStudentPersonal.STUDENT);
        spin_stream=(Spinner)findViewById(R.id.stream);
        spin_stream.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> spin_streamAdapter=
                ArrayAdapter.createFromResource(this, R.array.stream, R.layout.spinner_item);
        spin_streamAdapter.setDropDownViewResource(R.layout.spinner_item);
        spin_stream.setAdapter(spin_streamAdapter);

        spin_elective = (Spinner) findViewById(R.id.elective_1);
        spin_optional = (Spinner) findViewById(R.id.optional);
        view_elective2 = (TextView)findViewById(R.id.elective_2);
        view_elective3 = (TextView)findViewById(R.id.elective_3);
        view_compulsary = (TextView)findViewById(R.id.compulsary);

        spin_elective.setOnItemSelectedListener(this);
        spin_optional.setOnItemSelectedListener(this);

        spin_elective.setVisibility(View.INVISIBLE);
        spin_optional.setVisibility(View.INVISIBLE);


        view_elective2.setVisibility(View.INVISIBLE);
        view_elective3.setVisibility(View.INVISIBLE);
        view_compulsary.setVisibility(View.INVISIBLE);


        stream = null;
        elective1 = null;
        elective2 = null;
        elective3 = null;
        optional = null;
        compulsory = "ENGLISH";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.ok_option:
                forwardDetails();
                return true;
            case R.id.reset_student:
                resetDetails();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


    public void chooseStandard(View view){
       // boolean choosen = ((RadioButton)view).isChecked();

        switch(view.getId()){

            case R.id.eleventh:
                receivedStudent.setStandard(11);
                classSelected=true;
                break;
            case R.id.twelfth:
                receivedStudent.setStandard(12);
                classSelected=true;
                break;
            default:
                receivedStudent.setStandard(-1);
                classSelected=false;
                break;
        }

    }

    private void forwardDetails(){
        if(checkNull()){
            receivedStudent.setStream(stream);
            receivedStudent.setSubjects(new String[]{elective1,elective2,elective3,optional,compulsory});
            Intent nextStep = new Intent(this,SaveResult.class);
            nextStep.putExtra(AddStudentPersonal.STUDENT, receivedStudent);
            startActivity(nextStep);
        }
        else{
            Toast.makeText(this,"select all fields !", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetDetails(){

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        try {
            switch (parent.getId()){
                case R.id.stream:
                    if(position == 1){
                        electiveAdapter =  ArrayAdapter.createFromResource(this,R.array.elective_science,R.layout.spinner_item);
                        optionalAdapter = ArrayAdapter.createFromResource(this,R.array.optional_science,R.layout.spinner_item);
                        view_elective2.setText("Elective II: Physics");
                        view_elective3.setText("Elective III: Chemistry");
                        view_compulsary.setText("COMPULSARY: English");
                        electiveAdapter.setDropDownViewResource(R.layout.spinner_item);
                        spin_elective.setAdapter(electiveAdapter);
                        spin_elective.setVisibility(View.VISIBLE);
                        optionalAdapter.setDropDownViewResource(R.layout.spinner_item);
                        spin_optional.setAdapter(optionalAdapter);
                        spin_optional.setVisibility(View.VISIBLE);
                        view_elective2.setVisibility(View.VISIBLE);
                        view_elective3.setVisibility(View.VISIBLE);
                        view_compulsary.setVisibility(View.VISIBLE);
                        stream = parent.getItemAtPosition(position).toString();
                        elective2 = view_elective2.getText().toString();
                        elective3 = view_elective3.getText().toString();
                    }
                    else if(position == 2){
                        electiveAdapter =  ArrayAdapter.createFromResource(this,R.array.elective_commerce,R.layout.spinner_item);
                        optionalAdapter = ArrayAdapter.createFromResource(this,R.array.optional_commerce,R.layout.spinner_item);
                        view_elective2.setText("Elective II: Statistics");
                        view_elective3.setText("Elective III: Business Studies");
                        view_compulsary.setText("COMPULSARY: English");
                        electiveAdapter.setDropDownViewResource(R.layout.spinner_item);
                        spin_elective.setAdapter(electiveAdapter);
                        spin_elective.setVisibility(View.VISIBLE);

                        optionalAdapter.setDropDownViewResource(R.layout.spinner_item);
                        spin_optional.setAdapter(optionalAdapter);
                        spin_optional.setVisibility(View.VISIBLE);
                        view_elective2.setVisibility(View.VISIBLE);
                        view_elective3.setVisibility(View.VISIBLE);
                        view_compulsary.setVisibility(View.VISIBLE);
                        stream = parent.getItemAtPosition(position).toString();
                        elective2 = view_elective2.getText().toString();
                        elective3 = view_elective3.getText().toString();
                    }
                    else {
                        spin_elective.setVisibility(View.INVISIBLE);
                        view_elective2.setVisibility(View.INVISIBLE);
                        view_elective3.setVisibility(View.INVISIBLE);
                        view_compulsary.setVisibility(View.INVISIBLE);
                        spin_optional.setVisibility(View.INVISIBLE);
                        stream = null;
                        elective2 = null;
                        elective3 = null;
                    }

                    break;

                case R.id.elective_1:

                    if(position==1){
                        elective1 = parent.getItemAtPosition(position).toString();
                    }
                    else if(position == 2){
                        elective1 = parent.getItemAtPosition(position).toString();
                    }
                    else {
                        elective1 = null;
                    }

                    break;

                case R.id.optional:

                    if(position==1){
                        optional = parent.getItemAtPosition(position).toString();
                    }
                    else if(position == 2){
                        optional = parent.getItemAtPosition(position).toString();
                    }
                    else {
                        optional = null;
                    }

                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    private boolean checkNull(){
         return receivedStudent !=null &&
                elective1!=null &&
                elective2!=null &&
                elective3 !=null &&
                optional != null &&
                compulsory !=null &&
                classSelected &&
                stream !=null;
    }

}
