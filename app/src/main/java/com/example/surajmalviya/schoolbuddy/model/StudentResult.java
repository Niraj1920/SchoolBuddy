package com.example.surajmalviya.schoolbuddy.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Suraj Malviya on 03/06/2017.
 */

public class StudentResult implements Parcelable {

    private int scholarID;
    private double ELECTIVE_I;
    private double ELECTIVE_II;
    private double ELECTIVE_III;
    private double OPTIONAL;
    private double COMPULSARY;
    private double TOTAL_SCORE;
    private double PERCENTAGE;
    private String GRADE;

    private final double G_TOTAL=500;

    public StudentResult(int scholarID)
    {
        this.scholarID = scholarID;
        this.TOTAL_SCORE=-1; //IN CASE RESULT NOT ENTERED !
    }

    public StudentResult(int scholarID,double ELECTIVE_I, double ELECTIVE_II, double ELECTIVE_III, double OPTIONAL, double COMPULSARY) {
        this.scholarID=scholarID;
        this.ELECTIVE_I = ELECTIVE_I;
        this.ELECTIVE_II = ELECTIVE_II;
        this.ELECTIVE_III = ELECTIVE_III;
        this.OPTIONAL = OPTIONAL;
        this.COMPULSARY = COMPULSARY;
        setTotalScore();
        setPercentage();
        setGrade();
    }


    public int getScholarID(){return scholarID;}

    public void setScholarID(int scholarID)
    {
        this.scholarID=scholarID;
    }

    public double getELECTIVE_I() {
        return ELECTIVE_I;
    }

    public void setELECTIVE_I(double ELECTIVE_I) {
        this.ELECTIVE_I = ELECTIVE_I;
    }

    public double getELECTIVE_II() {
        return ELECTIVE_II;
    }

    public void setELECTIVE_II(double ELECTIVE_II) {
        this.ELECTIVE_II = ELECTIVE_II;
    }

    public double getELECTIVE_III() {
        return ELECTIVE_III;
    }

    public void setELECTIVE_III(double ELECTIVE_III) {
        this.ELECTIVE_III = ELECTIVE_III;
    }

    public double getOPTIONAL() {
        return OPTIONAL;
    }

    public void setOPTIONAL(double OPTIONAL) {
        this.OPTIONAL = OPTIONAL;
    }

    public double getCOMPULSARY() {
        return COMPULSARY;
    }

    public void setCOMPULSARY(double COMPULSARY) {
        this.COMPULSARY = COMPULSARY;
    }

    public double getTotalScore() {
        return TOTAL_SCORE;
    }

    public void setTotalScore()
    {
        this.TOTAL_SCORE=ELECTIVE_I+ELECTIVE_II+ELECTIVE_III+OPTIONAL+COMPULSARY;
    }

    public double getPercentage() {
        return PERCENTAGE;
    }

    public void setPercentage()
    {
        this.PERCENTAGE= (getTotalScore()/G_TOTAL)*100;
    }

    public String getGrade() {
        return GRADE;
    }

    public void setGrade()
    {
        // <35-F, <=45-D, <=55-C, <=60-C+ <=70- B, <=80-B+, <9=0-A, <=100-A+,

        String grade;

        double per=getPercentage();

        if(per<35)
        {
            grade="F";
        }
        else if(per>=35 && per<=45)
        {
            grade="D";
        }
        else if(per>45 && per<=55)
        {
            grade="C";
        }
        else if(per>55 && per<=60)
        {
            grade="C+";
        }
        else if(per>60 && per<=70)
        {
            grade="B";
        }
        else if(per>70 && per<=80)
        {
            grade="B+";
        }
        else if(per>80 && per<=90)
        {
            grade="A";
        }
        else
        {
            grade="A+";
        }

        this.GRADE=grade;
    }


    @Override
    public String toString() {
        return "StudentResult{" +
                "scholarID=" + scholarID +
                ", ELECTIVE_I=" + ELECTIVE_I +
                ", ELECTIVE_II=" + ELECTIVE_II +
                ", ELECTIVE_III=" + ELECTIVE_III +
                ", OPTIONAL=" + OPTIONAL +
                ", COMPULSARY=" + COMPULSARY +
                ", TOTAL_SCORE=" + TOTAL_SCORE +
                ", PERCENTAGE=" + PERCENTAGE +
                ", GRADE='" + GRADE + '\'' +
                ", G_TOTAL=" + G_TOTAL +
                '}';
    }



    /*Parcelable*/
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.scholarID);
        dest.writeDouble(this.ELECTIVE_I);
        dest.writeDouble(this.ELECTIVE_II);
        dest.writeDouble(this.ELECTIVE_III);
        dest.writeDouble(this.OPTIONAL);
        dest.writeDouble(this.COMPULSARY);
        dest.writeDouble(this.TOTAL_SCORE);
        dest.writeDouble(this.PERCENTAGE);
        dest.writeString(this.GRADE);
        dest.writeDouble(this.G_TOTAL);
    }

    protected StudentResult(Parcel in) {
        this.scholarID = in.readInt();
        this.ELECTIVE_I = in.readDouble();
        this.ELECTIVE_II = in.readDouble();
        this.ELECTIVE_III = in.readDouble();
        this.OPTIONAL = in.readDouble();
        this.COMPULSARY = in.readDouble();
        this.TOTAL_SCORE = in.readDouble();
        this.PERCENTAGE = in.readDouble();
        this.GRADE = in.readString();

    }

    public static final Parcelable.Creator<StudentResult> CREATOR = new Parcelable.Creator<StudentResult>() {
        @Override
        public StudentResult createFromParcel(Parcel source) {
            return new StudentResult(source);
        }

        @Override
        public StudentResult[] newArray(int size) {
            return new StudentResult[size];
        }
    };

    /*End of Parcelable*/
}
