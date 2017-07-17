package com.example.surajmalviya.schoolbuddy.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.surajmalviya.schoolbuddy.database.DataSchema;

import java.util.Arrays;

/**
 * Created by Suraj Malviya on 02/06/2017.
 */

public class Student implements Parcelable {

    private int scholarID;
    private String name;
    private int standard; //class
    private String DOB;
    private int gender; //1-male, 0-female
    private String email;
    private String contact;
    private String stream;
    private StudentResult studentResult;
    private String image;

    private String[] subjects = new String[5]; //{ELECTIVE_I, ELECTIVE_II, ELECTIVE_III, OPTIONAL, COMPULSARY};

    public Student(int scholarID, String name, int standard, String DOB, int gender, String email, String contact, String stream, String image) {
        this.scholarID = scholarID;
        this.name = name;
        this.standard = standard;
        this.DOB = DOB;
        this.gender = gender;
        this.email = email;
        this.contact = contact;
        this.stream = stream;
        this.image=image;
    }

    public int getScholarID() {
        return scholarID;
    }

    public void setScholarID(int scholarID) {
        this.scholarID = scholarID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }

    public StudentResult getStudentResult() {
        return studentResult;
    }

    public void setStudentResult(StudentResult studentResult) {
        this.studentResult = studentResult;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ContentValues getStudentValues() {
        ContentValues values = new ContentValues();

        values.put(DataSchema.StudentTable._ID, scholarID);
        values.put(DataSchema.StudentTable.NAME, name);
        values.put(DataSchema.StudentTable.CLASS, standard);
        values.put(DataSchema.StudentTable.DOB, DOB);
        values.put(DataSchema.StudentTable.GENDER, gender);
        values.put(DataSchema.StudentTable.EMAIL, email);
        values.put(DataSchema.StudentTable.CONTACT, contact);
        values.put(DataSchema.StudentTable.STREAM, stream);
        values.put(DataSchema.StudentTable.IMAGE,image);
        return values;
    }

    public ContentValues getStudentPreferenceValues() {
        ContentValues values = new ContentValues();
        values.put(DataSchema.SubjectPrefereceTable._ID, scholarID);
        values.put(DataSchema.SubjectPrefereceTable.ELECTIVE_I, subjects[0]);
        values.put(DataSchema.SubjectPrefereceTable.ELECTIVE_II, subjects[1]);
        values.put(DataSchema.SubjectPrefereceTable.ELECTIVE_III, subjects[2]);
        values.put(DataSchema.SubjectPrefereceTable.OPTIONAL, subjects[3]);
        values.put(DataSchema.SubjectPrefereceTable.COMPULSARY, subjects[4]);
        return values;
    }

    public ContentValues getStudentResultValues() {
        if (studentResult != null) {
            ContentValues values = new ContentValues();
            values.put(DataSchema.ScoreTable._ID, scholarID);
            values.put(DataSchema.ScoreTable.ELECTIVE_I, studentResult.getELECTIVE_I());
            values.put(DataSchema.ScoreTable.ELECTIVE_II, studentResult.getELECTIVE_II());
            values.put(DataSchema.ScoreTable.ELECTIVE_III, studentResult.getELECTIVE_III());
            values.put(DataSchema.ScoreTable.OPTIONAL, studentResult.getOPTIONAL());
            values.put(DataSchema.ScoreTable.COMPULSARY, studentResult.getCOMPULSARY());
            return values;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "scholarID=" + scholarID +
                ", name='" + name + '\'' +
                ", standard=" + standard +
                ", DOB='" + DOB + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", stream='" + stream + '\'' +
                ", studentResult=" + studentResult +
                ", image='" + image + '\'' +
                ", subjects=" + Arrays.toString(subjects) +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.scholarID);
        dest.writeString(this.name);
        dest.writeInt(this.standard);
        dest.writeString(this.DOB);
        dest.writeInt(this.gender);
        dest.writeString(this.email);
        dest.writeString(this.contact);
        dest.writeString(this.stream);
        dest.writeParcelable(this.studentResult, flags);
        dest.writeString(this.image);
        dest.writeStringArray(this.subjects);
    }

    protected Student(Parcel in) {
        this.scholarID = in.readInt();
        this.name = in.readString();
        this.standard = in.readInt();
        this.DOB = in.readString();
        this.gender = in.readInt();
        this.email = in.readString();
        this.contact = in.readString();
        this.stream = in.readString();
        this.studentResult = in.readParcelable(StudentResult.class.getClassLoader());
        this.image = in.readString();
        this.subjects = in.createStringArray();
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}

    /*Parcelable*/
