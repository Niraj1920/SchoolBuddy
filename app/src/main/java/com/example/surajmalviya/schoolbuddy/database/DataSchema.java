package com.example.surajmalviya.schoolbuddy.database;

import android.provider.BaseColumns;

/**
 * Created by Suraj Malviya on 02/06/2017.
 */

@SuppressWarnings("ALL")

public final class DataSchema //database
{
    private DataSchema(){}

    /* Inner Classes that defines the Table Schemas...*/

    public static class StudentTable implements BaseColumns //table
    {
            public static final String TABLE_NAME="student";

            public static final String NAME="name";
            public static final String GENDER="gender";
            public static final String DOB="dob";
            public static final String EMAIL="email";
            public static final String CONTACT="contact";
            public static final String CLASS="class";
            public static final String IMAGE="image";
            public static final String STREAM="stream";

            public static final String[] ALL_COLUMNS= { _ID,NAME,CLASS, DOB, GENDER, EMAIL, CONTACT, STREAM, IMAGE };

            public static final String CREATE_TABLE
                    =
                    "CREATE TABLE "+TABLE_NAME+"("+
                            _ID + " INTEGER PRIMARY KEY," +
                            NAME+ " TEXT," +
                            CLASS+" INTEGER," +
                            DOB+" TEXT," +
                            GENDER+" INTEGER," +
                            EMAIL+" TEXT," +
                            CONTACT+" TEXT," +
                            STREAM +" TEXT," +
                            IMAGE+" TEXT"+
                            ");";

            public static final String DROP_TABLE
                    =
                    "DROP TABLE IF EXISTS " +TABLE_NAME;

    }

    public static final class ScoreTable implements BaseColumns
    {
        public static final String TABLE_NAME="score";

        public static final String ELECTIVE_I="elective_1";
        public static final String ELECTIVE_II="elective_2";
        public static final String ELECTIVE_III="elective_3";
        public static final String OPTIONAL="optional";
        public static final String COMPULSARY="compulasary";

        public static final String[] ALL_COLUMNS={_ID, ELECTIVE_I, ELECTIVE_II, ELECTIVE_III, OPTIONAL, COMPULSARY};

        public static final String CREATE_TABLE
                =
                "CREATE TABLE "+TABLE_NAME+"(" +
                        _ID+" INTEGER PRIMARY KEY," +
                        ELECTIVE_I+" REAL," +
                        ELECTIVE_II+" REAL," +
                        ELECTIVE_III+" REAL," +
                        OPTIONAL+" REAL," +
                        COMPULSARY+" REAL);";

        public static final String DROP_TABLE
                =
                "DROP TABLE IF EXISTS "+TABLE_NAME;
    }

    public static final class SubjectPrefereceTable implements  BaseColumns
    {

        public static final String TABLE_NAME="subject_preferences";

        public static final String ELECTIVE_I="elective_1";
        public static final String ELECTIVE_II="elective_2";
        public static final String ELECTIVE_III="elective_3";
        public static final String OPTIONAL="optional";
        public static final String COMPULSARY="compulasary";

        public static final String[] ALL_COLUMNS={_ID, ELECTIVE_I, ELECTIVE_II, ELECTIVE_III, OPTIONAL, COMPULSARY};

        public static final String CREATE_TABLE
                =
                "CREATE TABLE "+TABLE_NAME+"(" +
                        _ID+" INTEGER PRIMARY KEY," +
                        ELECTIVE_I+" TEXT," +
                        ELECTIVE_II+" TEXT," +
                        ELECTIVE_III+" TEXT," +
                        OPTIONAL+" TEXT," +
                        COMPULSARY+" TEXT);";
        public static final String DROP_TABLE
                =
                "DROP TABLE IF EXISTS "+TABLE_NAME;
    }


}
