package com.example.eventmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ProjectDataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyProject.db";
    private static final int DATABASE_VERSION = 1;
    public ProjectDataBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//after successful creation, will display “created” in logcat
        Log.d(DATABASE_NAME, "created");
    }

    //public static final String signup_TABLE = "signup";
    //public static final String registration_TABLE = "registration";
    //public static final String name_COLUMN = "name";

    //public static final String password_COLUMN = "password";
    //public static final String eventname_COLUMN = "eventname";
    /*public static final String CREATE_signup_TABLE= "CREATE TABLE "
            + signup_TABLE + "("+name_COLUMN+"TEXT,"
            +password_COLUMN+"TEXT"+")";
    public static final String CREATE_registeration_TABLE= "CREATE TABLE "
            + signup_TABLE + "("+name_COLUMN+"TEXT,"
            +eventname_COLUMN+"TEXT"+")";*/
    private static ProjectDataBaseHelper instance;
    public ProjectDataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE signup(name TEXT,password TEXT)");
        //db.execSQL(CREATE_registeration_TABLE);
        db.execSQL("CREATE TABLE register(name TEXT,email TEXT,eventname TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists signup" );
        //db.execSQL("DROP TABLE IF EXISTS " + registration_TABLE);
        db.execSQL("drop Table if exists register" );
        onCreate(db);
    }
    public Boolean insertData(String name, String email,String eventname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name", name);
        contentValues.put("eventname", eventname);
        long result = db.insert("register", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkname(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from signup where name = ?",new String[]{name});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


    public Boolean insertData(String name, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name", name);
        contentValues.put("password", password);
        long result = db.insert("signup", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }
    public Boolean checkregistername(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from signup where name = ?",new String[]{name});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checknamepassword(String name, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from signup where name = ? and password = ?", new String[] {name,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

}


