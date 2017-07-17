package com.example.surajmalviya.schoolbuddy;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.surajmalviya.schoolbuddy.database.DatabaseSource;
import com.example.surajmalviya.schoolbuddy.model.Student;
import com.example.surajmalviya.schoolbuddy.utility.EmailValidator;
import com.example.surajmalviya.schoolbuddy.utility.GeneralValidation;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class AddStudentPersonal extends AppCompatActivity {

    private EditText nameField,contactField,emailField,dobField;
    private TextView idField;
    private ImageView studentImage;
    private ToggleButton changeGenderButton;
    int scholarID=-1;
    private static final int PICK_STUDENT_PHOTO=1;
    public static String STUDENT="student";
    private String image_path;
    EmailValidator validator=new EmailValidator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_personal);

        getSupportActionBar().setTitle("Add Student");
        getSupportActionBar().setSubtitle("Enter Personal Details");

        idField=(TextView)findViewById(R.id.sch_id);
        nameField= (EditText) findViewById(R.id.name);
        contactField= (EditText) findViewById(R.id.contact);
        emailField= (EditText) findViewById(R.id.email);
        studentImage= (ImageView) findViewById(R.id.student_image);
        dobField= (EditText)findViewById(R.id.dob);
        changeGenderButton=((ToggleButton)findViewById(R.id.gender_button));
        changeGenderButton.setChecked(true);
        image_path="boy-0.png";
        this.scholarID=getIntent().getExtras().getInt(MainActivity.NEXT_ID);

        idField.setText("SCHOLAR ID: "+scholarID);




       nameField.setOnKeyListener(new View.OnKeyListener() {
           @Override
           public boolean onKey(View v, int keyCode, KeyEvent event) {
               if(keyCode== KeyEvent.KEYCODE_ENTER){
                   String name=nameField.getText().toString();
                   if(TextUtils.isEmpty(name)){
                       nameField.setError("Name is Required");
                   }
                   else if(!GeneralValidation.ensureFullName(name)){
                       nameField.setError("Enter Full Name");
                   }
               }
               return false;
           }
       });

       contactField.setOnKeyListener(new View.OnKeyListener() {
           @Override
           public boolean onKey(View v, int keyCode, KeyEvent event) {
               if(keyCode== KeyEvent.KEYCODE_ENTER){
                   String contact=contactField.getText().toString();
                   if(TextUtils.isEmpty(contact)){
                       contactField.setError("Contact is Required");
                   }
                   else if(!GeneralValidation.isValidContactNumber(contact)){
                       contactField.setError("Enter Correct Contact Detail");
                   }
               }
               return false;
           }
       });

        emailField.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode== KeyEvent.KEYCODE_ENTER){
                    String email=emailField.getText().toString();
                    if(TextUtils.isEmpty(email)){
                        emailField.setError("Email is Required");
                    }
                    else if(!EmailValidator.validate(email)){
                        emailField.setError("Enter Correct Email ID");
                    }
                    else{
                        if(validateToForward()){
                            forwardDetails();
                        }
                    }
                }
                return false;
            }
        });

        dobField.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode== KeyEvent.KEYCODE_ENTER){
                    String dob=dobField.getText().toString();
                    if(TextUtils.isEmpty(dob)){
                        dobField.setError("DOB is Required");
                    }
                    else if(!GeneralValidation.isValidDateOfBirth(dob)){
                        dobField.setError("Invalid DOB !");
                    }
                }
                return false;
            }
        });
    }



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
            default:    return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //check which request we are responding to !

        if(requestCode==PICK_STUDENT_PHOTO){
            //Make Sure the request was successfull
            if(resultCode ==RESULT_OK){
                //Do the stuff ! /* Fetch the Data from the intent came in ! */
                Uri imageUri= data.getData();
                Log.d("uri", String.valueOf(imageUri));
            }

        }
    }

    public void changeGender(View view) {
        Log.d("Inside", String.valueOf(view));
        boolean status=((ToggleButton)view).isChecked();
        Drawable myDrawable=getStudentImage(status);
        studentImage.setImageDrawable(myDrawable);
    }

    private Drawable getStudentImage(boolean gender) //gender- BOY- TRUE, GIRL- FALSE
    {
        int min=0;
        int max= gender? 22 : 26;

        Random rand=new Random();

        int randomNumber= rand.nextInt((max - min) + 1) + min;

        String fileName= gender? "boy-"+randomNumber+".png" : "girl-"+randomNumber+".png";

        InputStream inputStream = null;
        Drawable drawable=null;
        try {
            image_path=fileName;
            inputStream = getBaseContext().getAssets().open(fileName);
            drawable=Drawable.createFromStream(inputStream,null);
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return drawable;
    }

    public void showCalendar(View view) {
//        Toast.makeText(this,"Calendar to be Opened !",Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this, StreamSelection.class);
//        startActivity(intent);

//        DatabaseSource source = new DatabaseSource(this);
//        source.addStudent()


    }


    public void forwardDetails(){

        if(validateToForward()){
            String name=nameField.getText().toString();
            String dob=dobField.getText().toString();
            String contact=contactField.getText().toString();
            String email=emailField.getText().toString();
            int gender=changeGenderButton.isChecked()?1:0;
            Log.d("image",image_path);
            Student new_student=new Student(scholarID,name,-1,dob,gender,email,contact,"",image_path);
            Intent forward=new Intent(this,StreamSelection.class);
            forward.putExtra(STUDENT,new_student);
            startActivity(forward);
        }
    }

    public void resetDetails(){
        nameField.setText("");
        emailField.setText("");
        contactField.setText("");
        dobField.setText("");
        changeGenderButton.setChecked(true);
    }


    public void getImageFromPhone(View view) {
        Intent getCustomImageIntent = new Intent();
        getCustomImageIntent.setType("image/*");
        getCustomImageIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(getCustomImageIntent,"Select Image"), PICK_STUDENT_PHOTO);
    }

    private boolean validateToForward(){
        if(nameField.getText().toString().isEmpty()){
            nameField.setError("Student Name is Required !");
            return false;
        }
        else if(dobField.getText().toString().isEmpty()){
            dobField.setError("Date of Birth is Required !");
            return false;
        }
        else if(contactField.getText().toString().isEmpty()){
            contactField.setError("Contact Detail id Required !");
            return false;
        }
        else if(emailField.getText().toString().isEmpty()){
            emailField.setError("Email is Required !");
            return false;
        }
        else{
            String name=nameField.getText().toString();
            String dob=dobField.getText().toString();
            String contact=contactField.getText().toString();
            String email=emailField.getText().toString();
            boolean send=false;
            send=GeneralValidation.ensureFullName(name);
            if(send){
                send=GeneralValidation.isValidDateOfBirth(dob);
                if(send){
                    send=GeneralValidation.isValidContactNumber(contact);
                    if(send){
                        send= EmailValidator.validate(email);
                    }
                    else{
                        emailField.setError("Enter Correct Email ID !");
                    }
                }
                else{
                    dobField.setError("Enter Correct DOB !");
                }
            }
            else{
                nameField.setError("Enter Full Name !");
            }
            return send;
         }
    }


    private void saveData(){

        Student s= new Student(1,"",4,"",4,"","","","");

        DatabaseSource databaseSource = new DatabaseSource(this);
        databaseSource.addStudent(s);


    }
}
